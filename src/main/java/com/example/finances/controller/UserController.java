package com.example.finances.controller;

import com.example.finances.dto.UserDTOResponse;
import com.example.finances.dto.UserDto;
import com.example.finances.models.User;
import com.example.finances.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api")
@Validated
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public  UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/users")
    List<UserDTOResponse> listUsers(){
        return userService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}")
    public UserDTOResponse getUser(@PathVariable("userId") Long userId){
        User user = userService.getUser(userId);
        System.out.println(user.toString());
        return convertToDTO(user);
    }

    // O método PostMapping para criar usuário foi movido para o AuthController.

    @PutMapping("/users/{userId}")
    public UserDTOResponse updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDTO){
        User u = convertToEntity(userDTO);
        User userUpdated = userService.update(userId, u);
        return convertToDTO(userUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.delete(userId);
    }


    private UserDTOResponse convertToDTO(User u) {
        return modelMapper.map(u, UserDTOResponse.class);
    }

    private User convertToEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
