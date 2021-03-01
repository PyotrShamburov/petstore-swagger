package by.tms.home.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private long id;

    @Pattern(regexp = "^[\\p{Upper}]?[\\p{Lower}]{3,15}$", message = "Wrong format! Only characters(3 - 15)!")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
