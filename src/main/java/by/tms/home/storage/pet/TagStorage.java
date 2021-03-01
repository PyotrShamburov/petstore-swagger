package by.tms.home.storage.pet;

import by.tms.home.model.Tag;
import by.tms.home.model.exception.EntityAlreadyExistsException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagStorage {
    private static long id = 1;
    private List<Tag> tagList = new ArrayList<>();

    public boolean save(Tag tag) {
        if (!contains(tag)) {
            tag.setId(id++);
            return tagList.add(tag);
        }
        throw new EntityAlreadyExistsException("Tag with name already exists!");
    }

    public Tag getById(long id) {
        for (Tag tag : tagList) {
            if (tag.getId() == id) {
                return tag;
            }
        }
        return null;
    }

    public boolean contains(Tag tag) {
        return tagList.contains(tag);
    }
}
