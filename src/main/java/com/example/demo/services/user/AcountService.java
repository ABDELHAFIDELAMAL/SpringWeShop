package com.example.demo.services.user;

import com.example.demo.entities.user.AppUser;
import com.example.demo.entities.user.AppUserRole;
import com.example.demo.entities.user.UserRole;
import com.example.demo.repositories.user.AppUserRepository;
import com.example.demo.repositories.user.AppUserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor // Injection Repos
public class AcountService implements IAcountService {

    private final AppUserRepository userRepository;
    private final AppUserRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUserRole addNewRole(AppUserRole role) {
        return roleRepository.save(role);
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
        AppUserRole role = roleRepository.findByUserRole(roleName);
        if (user != null && role != null) {
            user.getRoles().add(role);
        }
    }

    @Override
    public void deleteUserRole(String username, UserRole roleName) {
        AppUser user = userRepository.findByUsername(username);
        AppUserRole role = roleRepository.findByUserRole(roleName);
        if (user != null && role != null) {
            user.getRoles().remove(role);
        }
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
    public List<AppUserRole> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public AppUser findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with id: " + id)
        );
    }
}