package com.projects.webAPI.Controllers;

import com.projects.webAPI.Beans.UserDetails;
import com.projects.webAPI.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserDetails details) throws Exception {
        userService.addUser(details);
    }

}
