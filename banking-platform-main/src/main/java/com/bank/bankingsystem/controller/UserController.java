package com.bank.bankingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankingsystem.repository.UserRepository;
import com.bank.bankingsystem.service.UserService;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.bankingsystem.dto.UserRequestDto;
import com.bank.bankingsystem.dto.UserResponseDto;
import com.bank.bankingsystem.entity.User;


@RestController
@RequiredArgsConstructor
@Tag(
        name = "Authentication",
        description = "APIs for user registration and authentication."
)


public class UserController {

    private final UserService userService;

//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

    @Operation(
            summary = "Register New User",
            description = "Registers a new customer in the banking system."
    )
    @PostMapping("/users")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto requestDto)
    {
         return userService.createUser(requestDto);
        
        // return "User created in Database";

    }
    
    
    
}
