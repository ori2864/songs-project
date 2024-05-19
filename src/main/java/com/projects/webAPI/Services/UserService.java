package com.projects.webAPI.Services;

import com.projects.webAPI.Beans.UserDetails;
import com.projects.webAPI.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
        private final UserRepository userRepo;
    public void addUser(UserDetails details) throws Exception {
        if (userRepo.findById(details.getId()).isPresent()) {
            throw new Exception("this user already exists");
        } else userRepo.save(details);
    }
    public boolean login(UserDetails details) throws Exception{
        return userRepo.findById(details.getId()).isPresent();
    }
}
