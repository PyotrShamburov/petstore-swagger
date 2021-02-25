package by.tms.home.storage.pet;

import by.tms.home.model.Pet;
import by.tms.home.model.enums.PetStatusEnum;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetStorage {
    private static long id = 1;
    private List<Pet> petList = new ArrayList<>();

    public boolean save(Pet pet) {
        pet.setId(id++);
        return petList.add(pet);
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
        return false;
    }

    public Pet getById(long id) {
        for (Pet pet : petList) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public List<Pet> getByStatus(PetStatusEnum status) {
        List<Pet> petsByStatus = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getPetStatus().equals(status)) {
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
        return false;
    }
}
