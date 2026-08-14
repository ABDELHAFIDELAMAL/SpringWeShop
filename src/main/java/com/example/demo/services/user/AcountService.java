package com.example.demo.services.user;

import com.example.demo.entities.user.AppUser;
import com.example.demo.entities.user.UserRole;
import com.example.demo.repositories.user.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor // Injection Repos
public class AcountService implements IAcountService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addNewRole(String role) {
    }

    @Override
    public AppUser addNewUser(AppUser user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public void addRoleToUser(UserRole roleName, String username) {
        AppUser user = userRepository.findByUsername(username);

    }

    @Override
    public void deleteUserRole(String username, UserRole roleName) {
        AppUser user = userRepository.findByUsername(username);
    }

    @Override
    public AppUser findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<String> getAllRoles() {
        return null;
    }

    @Override
    public AppUser findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with id: " + id)
        );
    }

    @Override
    public AppUser updateUserRole(Long userId, String role) {
        return null;
    }

    @Override
    public AppUser updateUser(Long id, AppUser appUser) {
        try{
            AppUser user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id :" + id));
            user.setUsername(appUser.getUsername());
            user.setEmail(appUser.getEmail());
            user.setPassword(appUser.getPassword());
            user.setReviews(appUser.getReviews());
            userRepository.save(user);
            System.out.println("User update Seccess Full");
            return user;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}