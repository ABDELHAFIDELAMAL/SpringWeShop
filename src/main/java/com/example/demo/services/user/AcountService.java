package com.example.demo.services.user;

import com.example.demo.repositories.user.AppUserRepository;
import com.example.demo.repositories.user.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcountService {
    @Autowired
    private AppUserRepository userRepository ;
    @Autowired
    private AppUserRoleRepository userRoleRepository ;


}
