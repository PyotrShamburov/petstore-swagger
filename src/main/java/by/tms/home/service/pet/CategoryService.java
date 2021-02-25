package by.tms.home.service.pet;

import by.tms.home.model.Category;
import by.tms.home.storage.pet.CategoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryStorage categoryStorage;

    public boolean addToStorage(Category category) {
        return categoryStorage.save(category);
    }

    public Category getById(long id) {
        return categoryStorage.getById(id);
    }
}
