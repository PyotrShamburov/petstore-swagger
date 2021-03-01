package by.tms.home.storage.user;

import by.tms.home.model.User;
import by.tms.home.model.exception.EntityAlreadyExistsException;
import by.tms.home.model.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserStorage {
    private static long id = 1;
    private List<User> userList = new ArrayList<>();

    public boolean save(User user) {
        if (!contains(user)) {
            user.setId(id++);
            return userList.add(user);
        }
       throw new EntityAlreadyExistsException("User with this username already exists!");
    }

    public boolean delete(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                userList.remove(user);
                return true;
            }
        }
        throw new EntityNotFoundException("User with this username does not exist!");
    }

    public boolean update(String username, User newUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                newUser.setId(userList.get(i).getId());
                userList.set(i, newUser);
                return true;
            }
        }
        throw new EntityNotFoundException("User with this username does not exist!");
    }

    public User getByName(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new EntityNotFoundException("User with this username not found!");
    }

    public boolean contains(User user) {
        return userList.contains(user);
    }
}
