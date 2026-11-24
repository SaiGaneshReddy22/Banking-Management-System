package com.bank.bankingsystem.controller;

import com.bank.bankingsystem.dto.AccountResponseDto;
import com.bank.bankingsystem.dto.UserResponseDto;
import com.bank.bankingsystem.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(
        name = "Administration",
        description = "Administrative APIs for managing users and bank accounts."
)
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/accounts")
    public List<AccountResponseDto> getAllAccounts() {
        return adminService.getAllAccounts();
    }

    @PutMapping("/accounts/{accountNumber}/freeze")
    public String freezeAccount(
            @PathVariable String accountNumber) {

        adminService.freezeAccount(accountNumber);

        return "Account has been frozen successfully.";
    }

    @PutMapping("/accounts/{accountNumber}/unfreeze")
    public String unfreezeAccount(
            @PathVariable String accountNumber) {

        adminService.unfreezeAccount(accountNumber);

        return "Account has been unfrozen successfully.";
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(
            @PathVariable Long userId) {

        adminService.deleteUser(userId);

        return "User deleted successfully.";
    }
}