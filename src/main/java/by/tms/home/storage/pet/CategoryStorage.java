package by.tms.home.storage.pet;

import by.tms.home.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryStorage {
    private static long id = 1;
    private List<Category> categoryList = new ArrayList<>();

    public boolean save(Category category) {
        category.setId(id++);
        return categoryList.add(category);
    }

    public Category getById(long id) {
        for (Category category : categoryList) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }
}
