package com.sahil.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.api.entity.Users;
import com.sahil.api.services.UsersService;
import com.sahil.api.utils.AllFonctions;
import com.sahil.api.utils.PasswordManagement;

@RestController
class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * This method is used to add a user to the database.
     * 
     * If there are no users in the database, a default admin user is created.
     * 
     * @param user The user to be added.
     * 
     * @return The user that was added.
     */

    @PostMapping("/account/create")
    public Users addUsers(@RequestBody Users user) {

        int count = usersService.countUsers();
        if (count == 0) {
            Users admin = new Users();
            admin.setName("admin");
            admin.setUsername("admin");
            admin.setEmail("admin@admin.com");
            admin.setPhone("+243999999999");
            admin.setPassword(PasswordManagement.hashPassword("admin"));
            return usersService.saveUser(admin);
        } else {
            return usersService.saveUser(user);
        }
    }

    @GetMapping("/create")
    public List<Users> addUsers(List<Users> users) {
        return usersService.saveUsers(users);
    }

    public List<Users> getAllUsers() {
        return usersService.getUsers();
    }

    @GetMapping("/user/{id}")
    public Users getUserById(@PathVariable String id) {
        return usersService.getUserById(id);
    }
}