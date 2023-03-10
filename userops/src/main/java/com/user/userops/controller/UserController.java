package com.user.userops.controller;

import com.user.userops.model.User;
import com.user.userops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class UserController {

    // dependency injection
    @Autowired
    UserService userService;

   /* public UserController(UserService userService){
        this.userService=userService;
    }*/

    @Autowired
    MessageSource messageSource;

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users = userService.retrieveAllUsers();
        return ResponseEntity.ok().body(users);
    }

    // get a specific user
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getSpecificUser(@PathVariable int userId){
        Locale locale = LocaleContextHolder.getLocale();
        User user = userService.getUser(userId);
        if(user==null) throw new UserNotFoundException(messageSource.getMessage("error.userNotFound",null,"Default Error Message",locale));
        return ResponseEntity.ok().body(user);
    }

    // get a specific user using query parameter
    @GetMapping("/user")
    public ResponseEntity<User> getSpecificUserQuery(@RequestParam int userId){
        User user = userService.getUser(userId);
        if(user==null) throw new UserNotFoundException("user with id "+ userId+" not found");
        return ResponseEntity.ok().body(user);
    }

    // create a new user
    @PostMapping(path= "/users", consumes ="application/json", produces="application/json")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User newUser = userService.addUser(user);
        if(newUser==null)
            return ResponseEntity.internalServerError().body(newUser);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }


    // delete a user
    @DeleteMapping(path ="/users/{userId}")
    public ResponseEntity<String> deleteSpecificUser(@PathVariable int userId)
    {
        User deleteUser = userService.deleteUser(userId);
        if(deleteUser==null)
            throw new UserNotFoundException("user with id "+ userId+" not found");
        return ResponseEntity.noContent().build();
    }

    // update a user
    @PutMapping(path ="/users/{userId}", consumes ="application/json", produces="application/json")
    public ResponseEntity<User> updateSpecificUser(@PathVariable int userId, @RequestBody User user)
    {
        User updateUser = userService.updateUser(userId, user);
        if(updateUser==null)
            throw new UserNotFoundException("user with id "+ userId+" not found");
        return ResponseEntity.ok().body(updateUser);
    }

    // update a user partially
    @PatchMapping (path ="/usernameUpdate/{userId}", consumes ="application/json", produces="application/json")
    public ResponseEntity<User> updateSpecificUserPartially(@PathVariable int userId, @RequestBody User user)
    {
        User patchUser = userService.updateUserPartially(userId, user);
        if(patchUser==null)
            throw new UserNotFoundException("user with id "+ userId+" not found");
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("").buildAndExpand(patchUser.getId()).toUri();
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(patchUser);
    }
}
