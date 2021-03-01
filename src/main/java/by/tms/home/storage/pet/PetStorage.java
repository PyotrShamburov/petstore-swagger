package by.tms.home.storage.pet;

import by.tms.home.model.Pet;
import by.tms.home.model.exception.EntityAlreadyExistsException;
import by.tms.home.model.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetStorage {
    private static long id = 1;
    private List<Pet> petList = new ArrayList<>();

    public boolean save(Pet pet) {
        if (!contains(pet)) {
            pet.setId(id++);
            return petList.add(pet);
        }
        throw  new EntityAlreadyExistsException("Pet with this name already exists!");
    }

    public boolean change(Pet newPet) {
        for (int i = 0; i < petList.size(); i++) {
            if (petList.get(i).getId() == newPet.getId()) {
                newPet.setTags(petList.get(i).getTags());
                newPet.setCategory(petList.get(i).getCategory());
                petList.set(i, newPet);
                return true;
            }
        }
        throw new EntityNotFoundException("Pet with this ID does not found!");
    }

    public Pet getById(long id) {
        for (Pet pet : petList) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        throw new EntityNotFoundException("Pet with this ID does not found!");
    }

    public List<Pet> getByStatus(String status) {
        List<Pet> petsByStatus = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getPetStatus().toString().equals(status.trim().toUpperCase())) {
                petsByStatus.add(pet);
            }
        }
        return petsByStatus;
    }

    public boolean delete(long id) {
        for (Pet pet : petList) {
            if (pet.getId() == id) {
                petList.remove(pet);
                return true;
            }
        }
        throw new EntityNotFoundException("Pet with this ID does not exist!");
    }

    public boolean contains(Pet pet) {
        return petList.contains(pet);
    }
}
