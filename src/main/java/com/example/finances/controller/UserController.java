package com.example.finances.controller;

import com.example.finances.dto.UserDTOResponse;
import com.example.finances.dto.UserDto;
import com.example.finances.models.User;
import com.example.finances.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;


@Controller
@RequestMapping(path = "/api")
@Validated
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public  UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/users")
    List<UserDTOResponse> listUsers(){
        return userService.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/users/{userId}")
    public UserDTOResponse getUser(@PathVariable @Min(4) Long userId){
        User user = userService.getUser(userId);
        System.out.println(user.toString());
        return convertToDTO(user);
    }


    @PostMapping(path = "/users")
    UserDTOResponse createUser(@Valid @RequestBody UserDto userDTO){
        User u = convertToEntity(userDTO);
        User saved = userService.save(u);
        return convertToDTO(saved);
    }

    @PutMapping("/users/{userId}")
    public UserDTOResponse updateTask(@PathVariable Long userId, @RequestBody UserDto userDTO){
        User u = convertToEntity(userDTO);
        User userUpdated = userService.update(userId, u);
        return convertToDTO(userUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.delete(userId);
    }


    private UserDTOResponse convertToDTO(User u) {
        return modelMapper.map(u, UserDTOResponse.class);
    }

    private User convertToEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
