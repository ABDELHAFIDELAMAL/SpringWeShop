package com.example.demo.services.user;


import com.example.demo.entities.user.AppUser;
import java.util.List;

public interface IAcountService {
    List<AppUser> readUsers();
    AppUser createUser(AppUser user);
    void deleteUser(Long id );
    AppUser updateUser(AppUser appUser , Long id);
}
