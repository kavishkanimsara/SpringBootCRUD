package com.example.firstspring.Controller;

import com.example.firstspring.Dtos.UserDto;
import com.example.firstspring.Models.User;
import com.example.firstspring.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value ="api/v1/user" )
@CrossOrigin
public class UserController {
    @Autowired
    UserServices userServices;

    @GetMapping("/get-user")
    public List<UserDto> getUser(){
        return userServices.findAllUsers();
    }
    @GetMapping("/get-user/{id}")
    public UserDto getUserById(@PathVariable("id") int id) {
        return userServices.findUserById(id);
    }

    @PostMapping("/createUser")
    public UserDto postUser(@RequestBody UserDto userDto){
        return userServices.save(userDto);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody User request) {
        try {
            String response = userServices.updateUser(id, request);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the user");
        }
    }


    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable("id") int id){
        return userServices.deleteUser(id);
    }

    @GetMapping("/getAdult")
    public List<User> getAdultUsers() {
        return userServices.findAdultUsers();
    }



}
