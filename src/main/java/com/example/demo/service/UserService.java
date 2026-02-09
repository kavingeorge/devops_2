package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User authenticate(String email, String password) {
        User user = userRepo.findByEmailAndPassword(email, password);
        return user;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void saveUser(String username, String email, String password, java.sql.Date dateofbirth, String companyname, String location, UserRole role) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setDateofbirth(dateofbirth);
        user.setCompanyname(companyname);
        user.setLocation(location);
        user.setRole(role);
        userRepo.save(user);
    }
}
