package com.example.demo.services.user;

import com.example.demo.entities.user.AppUser;
import com.example.demo.repositories.user.AppUserRepository;
import com.example.demo.repositories.user.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcountService implements IAcountService{
    @Autowired
    private AppUserRepository userRepository ;
    @Autowired
    private AppUserRoleRepository userRoleRepository ;


    @Override
    public List<AppUser> readUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser createUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public AppUser updateUser(AppUser appUser, Long id) {
        AppUser appUser1 = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        appUser1.setUsername(appUser.getUsername());
        appUser1.setPassword(appUser.getPassword());
        appUser1.setEmail(appUser.getEmail());
        appUser1.setRoles(appUser.getRoles());
        return userRepository.save(appUser1);
    }
}
