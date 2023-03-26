package com.GestionalePalestre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GestionalePalestre.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    public User findByEmailAndPass(String email,String pass);

    public User findByEmail(String email);
    
}
