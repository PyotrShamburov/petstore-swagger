package by.tms.home.service.pet;

import by.tms.home.model.Category;
import by.tms.home.model.Pet;
import by.tms.home.model.Tag;
import by.tms.home.model.enums.PetStatusEnum;
import by.tms.home.storage.pet.PetStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetStorage petStorage;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;

    public boolean addPetToStorage(Pet pet) {
        if (addCategoryToPet(pet) && addTagsToPet(pet)) {
            return petStorage.save(pet);
        }
        return false;
    }


    public boolean addCategoryToPet(Pet pet) {
        Category category = (Category) categoryService.getById(pet.getCategory().getId());
        if (category != null){
            pet.setCategory(category);
            return true;
        }
        return false;
    }

    public boolean addTagsToPet(Pet pet) {
        List<Tag> petTags = new ArrayList<>();
        for (Tag tag : pet.getTags()) {
            Tag byId = tagService.getById(tag.getId());
            petTags.add(byId);
        }
        pet.setTags(petTags);
        return petTags.size() != 0;
    }

    public boolean updatePet(Pet newPet) {
        return petStorage.change(newPet);
    }

    public Pet getById(long id) {
       return petStorage.getById(id);
    }

    public List<Pet> getByStatus(PetStatusEnum status) {
        return petStorage.getByStatus(status);
    }

    public boolean deleteById(long id) {
        return petStorage.delete(id);
    }
}
