package by.tms.home.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;

    @Pattern(regexp = "^[A-Za-z]{3,15}[0-9]{0,5}$", message = "Wrong format! Use characters (3 - 15) and numbers(0 - 5)!")
    private String username;

    @Pattern(regexp = "^[\\p{Upper}]?[\\p{Lower}]{3,15}$", message = "Wrong format! Only characters(3 - 15)!")
    private String firstName;

    @Pattern(regexp = "^[\\p{Upper}]?[\\p{Lower}]{3,15}$", message = "Wrong format! Only characters(3 - 15)!")
    private String lastName;

    @Pattern(regexp = "[A-Za-z0-9._%+-]{2,10}@[A-Za-z0-9.-]{3,6}\\.[A-Za-z]{2,4}",
            message = "Invalid email address!")
    private String email;

    @Pattern(regexp = "^\\w{4,10}$", message = "Invalid password! (Char. and numbers 4 - 10)")
    private String password;

    @Pattern(regexp = "^(\\+\\d{12})|(\\d{11})$", message = "Wrong number!")
    private String phone;

    @Positive
    private int userStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
