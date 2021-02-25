package by.tms.home.resource;

import by.tms.home.model.User;
import by.tms.home.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.addNewUser(user)) {
            return new ResponseEntity<>("User with username: " + user.getUsername() + " - SAVED!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User not saved!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/createWithArray")
    public ResponseEntity<String> createUsersWithArray(@RequestBody User[] users) {
        if (userService.addUsersFromArray(users)) {
            return new ResponseEntity<>("Users from array - SAVED!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User not saved!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/createWithList")
    public ResponseEntity<String> createUsersWithList(@RequestBody List<User> users) {
        if (userService.addUsersFromList(users)) {
            return new ResponseEntity<>("Users from array - SAVED!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User not saved!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable String username) {
        if (username.matches("^[a-zA-Z0-9]{3,25}$")) {
            User userByUsername = (User) userService.getUserByUsername(username);
            if (userByUsername != null) {
                return new ResponseEntity<>(userByUsername, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User newUser) {
        if (username.matches("^[a-zA-Z0-9]{3,25}$")) {
            if (userService.updateUser(username, newUser)) {
                return new ResponseEntity<>(username + " - UPDATED!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found!", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid user supplied!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        if (username.matches("^[a-zA-Z0-9]{3,25}$")) {
            if (userService.deleteUserByUsername(username)) {
                return new ResponseEntity<>(username + " - DELETED!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found!", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid user supplied!", HttpStatus.BAD_REQUEST);
        }
    }
}
