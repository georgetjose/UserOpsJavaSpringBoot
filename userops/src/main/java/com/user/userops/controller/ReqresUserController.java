package com.user.userops.controller;

import com.user.userops.model.ReqresUser;
import com.user.userops.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/reqresapi")
public class ReqresUserController
{
    public static ArrayList<ReqresUser.ReqresUserData> reqresUserData = new ArrayList<>();

    @GetMapping(path = "/users")
    public ReqresUser getAllUsers()
    {
        reqresUserData.add(ReqresUser.ReqresUserData.builder().id(7).email("michael.lawson@reqres.in").first_name("Michael").last_name("Lawson").avatar("https://reqres.in/img/faces/7-image.jpg").build());
        reqresUserData.add(ReqresUser.ReqresUserData.builder().id(8).email("lindsay.ferguson@reqres.in").first_name("Lindsay").last_name("Ferguson").avatar("https://reqres.in/img/faces/8-image.jpg").build());
        ReqresUser.Support support = ReqresUser.Support.builder().url("https://reqres.in/#support-heading").text("To keep ReqRes free, contributions towards server costs are appreciated!").build();
        return ReqresUser.builder().page(2).perPage(6).total(12).totalPages(2).data(reqresUserData).support(support).build();
    }
}
