package by.tms.home.service.user;

import by.tms.home.model.User;
import by.tms.home.model.exception.UserNotFoundException;
import by.tms.home.storage.user.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserStorage userStorage;

    public boolean addNewUser(User user) {
        return userStorage.save(user);
    }

    public boolean addUsersFromArray(User[] users) {
        int counterOfSave = 0;
        for (User user : users) {
            if (userStorage.save(user)) {
                ++counterOfSave;
            }
        }
        return counterOfSave == users.length;
    }

    public boolean addUsersFromList(List<User> users) {
        int counterOfSave = 0;
        for (User user : users) {
            if (userStorage.save(user)) {
                ++counterOfSave;
            }
        }
        return counterOfSave == users.size();
    }

    public User getUserByUsername(String username) {
        return userStorage.getByName(username);
    }

    public boolean updateUser(String username, User newUser) {
        return userStorage.update(username, newUser);
    }

    public boolean deleteUserByUsername(String username) {
        return userStorage.delete(username);
    }

}
