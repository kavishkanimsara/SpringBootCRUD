package com.example.firstspring.Service;

import com.example.firstspring.Dtos.UserDto;
import com.example.firstspring.Models.User;
import com.example.firstspring.Repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServices {
    @Autowired  // //inject dependencies (services)
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public UserDto save(UserDto userDto) {
        userRepo.save(modelMapper.map(userDto, User.class));
        return userDto;
    }
    public List<UserDto> findAllUsers() {
        return userRepo.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto findUserById(int id) {
        return userRepo.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public List<User> findAdultUsers() {
        return userRepo.findAdultUsers();
    }

    public boolean deleteUser(int id) {
        User user = userRepo.findById(id).orElseThrow(NoSuchElementException::new);
        if(user==null) return false;
        userRepo.delete(user);
        return true;
    }

    public String updateUser(int id, User request) {
        // Find the existing user by ID, or throw an exception if not found
        User user = userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        // Update properties of the existing user with values from the request
        user.setName(request.getName());
        user.setAge(request.getAge());

        // Save the updated user to the repository
        User updatedUser = userRepo.save(user);

        // Return a meaningful message along with the updated user's information
        return "User Updated: " + updatedUser.toString();
    }



}
