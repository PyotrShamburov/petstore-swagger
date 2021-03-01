package by.tms.home.service.user;

import by.tms.home.model.User;
import by.tms.home.storage.user.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserStorage userStorage;

    public boolean addNewUser(User user) {
        return userStorage.save(user);
    }

    public Map<String, String> addUsersFromArray(User[] users) {
        Map<String, String> resultOfSaving = new HashMap<>();
        for (User user : users) {
            if (userStorage.save(user)) {
               resultOfSaving.put(user.getUsername(), "SAVED");
            } else {
                resultOfSaving.put(user.getUsername(), "NOT SAVED!");
            }
        }
        return resultOfSaving;
    }

    public Map<String, String> addUsersFromList(List<User> users) {
        Map<String, String> resultOfSaving = new HashMap<>();
        for (User user : users) {
            if (userStorage.save(user)) {
                resultOfSaving.put(user.getUsername(), "SAVED");
            } else {
                resultOfSaving.put(user.getUsername(), "NOT SAVED!");
            }
        }
        return resultOfSaving;
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
