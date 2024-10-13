package com.sahil.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.sahil.api.entity.Users;
import com.sahil.api.respository.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repository;

    public Users saveUser(Users user) {
        System.out.println(user);
        return repository.save(user);
    }

    public List<Users> saveUsers(List<Users> users) {
        return repository.saveAll(users);
    }

    public List<Users> getUsers() {
        return repository.findAll();
    }

    public Users getUserById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Users getUserByName(String name) {
        return repository.findByName(name);
    }

    public String deleteUser(String id) {
        repository.deleteById(id);
        return "user removed !! " + id;
    }

    public Users updateUser(Users user) {
        
        Users existingUser = repository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setPassword(user.getPassword());
        return repository.save(existingUser);
    }

    public int countUsers() {
        return (int) repository.count();
    }
}
