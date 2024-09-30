package com.example.firstspring.Repo;

import com.example.firstspring.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User , Integer> {

    @Query(value = "SELECT * FROM user WHERE age > 18", nativeQuery = true)
    List<User> findAdultUsers();


}
