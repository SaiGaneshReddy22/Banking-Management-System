package com.bank.bankingsystem.service;



import com.bank.bankingsystem.dto.UserRequestDto;
import com.bank.bankingsystem.dto.UserResponseDto;
import com.bank.bankingsystem.entity.User;
import com.bank.bankingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto createUser(UserRequestDto requestDto) {


        userRepository.findByEmail(requestDto.getEmail())
            .ifPresent(existingUser -> {
                throw new RuntimeException("Email already exists");
            });


        User user = User.builder()
                .fullName(requestDto.getFullName())
                .email(requestDto.getEmail())
                .password(
                        passwordEncoder.encode(
                                requestDto.getPassword()
                        )
                )
                .role("Customer")
                .build();

         
        
        User savedUser = userRepository.save(user);

        return UserResponseDto.builder()
                .id(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }
}