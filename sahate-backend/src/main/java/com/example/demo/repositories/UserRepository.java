package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;
import java.util.List;
import java.util.Optional;




public interface UserRepository extends JpaRepository<User,Long>{
    
    Optional<User> findByEmail(String email);
    public List<User> findByRole(String role);


}