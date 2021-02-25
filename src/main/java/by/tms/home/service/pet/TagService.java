package by.tms.home.service.pet;

import by.tms.home.model.Tag;
import by.tms.home.storage.pet.TagStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagStorage tagStorage;

    public boolean saveTag(Tag tag) {
        return tagStorage.save(tag);
    }

    public Tag getById(long id) {
        return tagStorage.getById(id);
    }
}
