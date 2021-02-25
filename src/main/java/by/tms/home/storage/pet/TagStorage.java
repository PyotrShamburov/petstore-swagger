package by.tms.home.storage.pet;

import by.tms.home.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagStorage {
    private static long id = 1;
    private List<Tag> tagList = new ArrayList<>();

    public boolean save(Tag tag) {
        tag.setId(id++);
        return tagList.add(tag);
    }

    public Tag getById(long id) {
        for (Tag tag : tagList) {
            if (tag.getId() == id) {
                return tag;
            }
        }
        return null;
    }
}
