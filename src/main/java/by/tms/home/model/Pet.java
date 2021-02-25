package by.tms.home.model;

import by.tms.home.model.enums.PetStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<Tag> tags;
    private PetStatusEnum petStatus;
}
