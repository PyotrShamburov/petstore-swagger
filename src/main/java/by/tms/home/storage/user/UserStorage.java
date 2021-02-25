package by.tms.home.storage.user;

import by.tms.home.model.User;
import by.tms.home.model.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserStorage {
    private static long id = 1;
    private List<User> userList = new ArrayList<>();

    public boolean save(User user) {
        user.setId(id++);
        return userList.add(user);
    }

    public boolean delete(String username) {
        return userList.removeIf(user -> user.getUsername().equals(username));
    }

    public boolean update(String username, User newUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                newUser.setId(userList.get(i).getId());
                userList.set(i, newUser);
                return true;
            }
        }
        return false;
    }

    public User getByName(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
