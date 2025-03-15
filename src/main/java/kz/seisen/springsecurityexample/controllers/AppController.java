package kz.seisen.springsecurityexample.controllers;


import kz.seisen.springsecurityexample.models.Application;
import kz.seisen.springsecurityexample.models.MyUser;
import kz.seisen.springsecurityexample.services.AppService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {



    private AppService appService;



    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to unprotected page";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> allApplications() {
        return appService.allApplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application applicationById(@PathVariable int id) {
        return appService.applicationById(id);
    }




    @PostMapping("/new-user")
    public String addNewUser(@RequestBody MyUser user) {
        appService.addUser(user);
        return "User added";
    }

}
