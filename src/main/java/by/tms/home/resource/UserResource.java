package by.tms.home.resource;

import by.tms.home.model.User;
import by.tms.home.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        if (userService.addNewUser(user)) {
            return new ResponseEntity<>("User with username: " + user.getUsername() + " - SAVED!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User not saved!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/createWithArray")
    public ResponseEntity<Map<String, String>> createUsersWithArray(@Valid @RequestBody User[] users) {
        Map<String, String> resultOfSaving = userService.addUsersFromArray(users);
        return new ResponseEntity<>(resultOfSaving, HttpStatus.CREATED);
    }

    @PostMapping(path = "/createWithList")
    public ResponseEntity<Map<String, String>> createUsersWithList(@Valid @RequestBody List<User> users) {
        Map<String, String> resultOfSaving = userService.addUsersFromList(users);
        return new ResponseEntity<>(resultOfSaving, HttpStatus.CREATED);

    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable String username) {
        if (username.matches("^[a-zA-Z0-9]{3,25}$")) {
            User userByUsername = (User) userService.getUserByUsername(username);
            if (userByUsername != null) {
                return new ResponseEntity<>(userByUsername, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @Valid @RequestBody User newUser) {
        if (username.matches("^[a-zA-Z0-9]{3,25}$")) {
            if (userService.updateUser(username, newUser)) {
                return new ResponseEntity<>(username + " - UPDATED!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid username supplied!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        if (username.matches("^[a-zA-Z0-9]{3,25}$")) {
            if (userService.deleteUserByUsername(username)) {
                return new ResponseEntity<>(username + " - DELETED!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid username supplied!", HttpStatus.BAD_REQUEST);
        }
    }
}
