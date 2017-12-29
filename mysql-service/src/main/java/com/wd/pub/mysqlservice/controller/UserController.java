package com.wd.pub.mysqlservice.controller;

import com.wd.pub.mysqlservice.service.ImportUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DimonHo on 2017/12/6.
 */
@RestController
public class UserController {
    @Autowired
    ImportUserService importUserService;
    @GetMapping("/user")
    public String saveUser(){
        importUserService.importUser();
        return "ok";
    }
}
