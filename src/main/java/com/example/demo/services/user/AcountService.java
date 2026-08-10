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
import java.util.Objects;
import java.util.Set;

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

    @Override
    public AppUser updateUserRole(Long userId, AppUserRole role) {
        try{
            AppUser user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with id :" + userId));
            Set<AppUserRole> roles = (Set<AppUserRole>) user.getRoles();
            for( AppUserRole role1 : roles){
                if(Objects.equals(role1.getId(), role.getId())){
                    role1.setUserRole(role.getUserRole());
                    role1.setAppUserList(role.getAppUserList());
                }
            }
            user.setRoles(roles);
            return userRepository.save(user);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AppUser updateUser(Long id, AppUser appUser) {
        try{
            AppUser user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id :" + id));
            user.setUsername(appUser.getUsername());
            user.setEmail(appUser.getEmail());
            user.setPassword(appUser.getPassword());
            user.setReviews(appUser.getReviews());
            user.setRoles(appUser.getRoles());
            userRepository.save(user);
            System.out.println("User update Seccess Full");
            return user;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}