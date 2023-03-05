package com.user.userops.controller;

import com.user.userops.model.User;
import com.user.userops.model.UserV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class VersionController
{
    // url based
    @GetMapping("/users/v1")
    public User getv1User(){
        return User.builder().id(1).name("GTJ").
                dob(LocalDate.now()).build();
    }

    @GetMapping("/users/v2")
    public UserV2 getv2User(){
        UserV2.Name name = UserV2.Name.builder().
                firstName("George").lastName("T Jose").build();

        return UserV2.builder().id(1).name(name).
                dob(LocalDate.now().minusYears(18)).build();
    }

    // param based
    @GetMapping(path = "/users", params = "version=v1")
    public User getv1UserParam(){
        return User.builder().id(1).name("Issac").
                dob(LocalDate.now()).build();
    }

    @GetMapping(path = "/users",params = "version=v2")
    public UserV2 getv2UserParam(){
        UserV2.Name name = UserV2.Name.builder().
                firstName("Issac").lastName("Newton").build();

        return UserV2.builder().id(1).name(name).
                dob(LocalDate.now().minusYears(18)).build();
    }

    // header based
    @GetMapping(path = "/users", headers = "version=v1")
    public User getv1UserHeader(){
        return User.builder().id(1).name("Issac").
                dob(LocalDate.now()).build();
    }

    @GetMapping(path = "/users",headers = "version=v2")
    public UserV2 getv2UserHeader(){
        UserV2.Name name = UserV2.Name.builder().
                firstName("Issac").lastName("Newton").build();

        return UserV2.builder().id(1).name(name).
                dob(LocalDate.now().minusYears(18)).build();
    }

    // MIME/Content based
    @GetMapping(path = "/users", produces = "application/com.users.userops.v1+json")
    public User getv1UserContent(){
        return User.builder().id(1).name("Issac").
                dob(LocalDate.now()).build();
    }

    @GetMapping(path = "/users",produces = "application/com.users.userops.v2+json")
    public UserV2 getv2UserContent(){
        UserV2.Name name = UserV2.Name.builder().
                firstName("Issac").lastName("Newton").build();

        return UserV2.builder().id(1).name(name).
                dob(LocalDate.now().minusYears(18)).build();
    }
}
