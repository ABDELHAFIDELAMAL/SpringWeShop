package com.example.demo.services.user;


import com.example.demo.entities.user.AppUser;
import java.util.List;

import com.example.demo.entities.user.UserRole;

public interface IAcountService {
    void addNewRole(String role);
    AppUser addNewUser(AppUser user);
    void addRoleToUser(UserRole roleName, String username);
    void deleteUserRole(String username, UserRole roleName);
    AppUser findUserByUserName(String username);
    List<AppUser> findAllUsers();
    List<String> getAllRoles();
    AppUser findUserById(Long id);
    AppUser updateUserRole(Long userId , String role);
    AppUser updateUser(Long id , AppUser appUser);
}