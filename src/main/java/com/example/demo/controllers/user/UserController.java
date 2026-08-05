package com.example.demo.controllers.user;

import com.example.demo.entities.user.AppUser;
import com.example.demo.entities.user.AppUserRole;
import com.example.demo.entities.user.UserRole;
import com.example.demo.services.user.IAcountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IAcountService acountService;


    @PostMapping("/roles/add")
    public ResponseEntity<AppUserRole> addNewRole(@RequestBody AppUserRole role) {
        return new ResponseEntity<>(acountService.addNewRole(role), HttpStatus.CREATED);
    }


    @PostMapping("/add")
    public ResponseEntity<AppUser> addNewUser(@RequestBody AppUser user) {
        return new ResponseEntity<>(acountService.addNewUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/roles/add-to-user")
    public ResponseEntity<Void> addRoleToUser(@RequestParam UserRole roleName, @RequestParam String username) {
        acountService.addRoleToUser(roleName, username);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/roles/delete")
    public ResponseEntity<Void> deleteUserRole(@RequestParam String username, @RequestParam UserRole roleName) {
        acountService.deleteUserRole(username, roleName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-username")
    public ResponseEntity<AppUser> findUserByUserName(@RequestParam String username) {
        return ResponseEntity.ok(acountService.findUserByUserName(username));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> findAllUsers() {
        return ResponseEntity.ok(acountService.findAllUsers());
    }

    @GetMapping("/roles/all")
    public ResponseEntity<List<AppUserRole>> getAllRoles() {
        return ResponseEntity.ok(acountService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(acountService.findUserById(id));
    }
}