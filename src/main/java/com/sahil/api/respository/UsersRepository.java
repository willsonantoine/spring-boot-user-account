package com.sahil.api.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahil.api.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {

    Users findByName(String name);
        
}