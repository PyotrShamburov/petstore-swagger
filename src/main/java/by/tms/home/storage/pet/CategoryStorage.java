package by.tms.home.storage.pet;

import by.tms.home.model.Category;
import by.tms.home.model.exception.EntityAlreadyExistsException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryStorage {
    private static long id = 1;
    private List<Category> categoryList = new ArrayList<>();

    public boolean save(Category category) {
        if (!contains(category)) {
            category.setId(id++);
            return categoryList.add(category);
        }
        throw new EntityAlreadyExistsException("Category with this name already exists!");
    }

    public Category getById(long id) {
        for (Category category : categoryList) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public boolean contains(Category category) {
        return categoryList.contains(category);
    }
}
