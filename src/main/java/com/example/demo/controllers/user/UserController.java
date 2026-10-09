package com.example.demo.controllers.user;

import com.example.demo.entities.user.AppUser;
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

}