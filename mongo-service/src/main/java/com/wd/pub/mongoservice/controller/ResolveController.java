package com.wd.pub.mongoservice.controller;

import com.wd.pub.mongoservice.service.ResolveWosToMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DimonHo on 2017/12/22.
 */
@RestController
public class ResolveController {
    @Autowired
    ResolveWosToMongoService resolveWosToMongoService;

    @GetMapping("/resolve/wos")
    public String resolveWos(){
        resolveWosToMongoService.main("D:\\wosall\\Wos2017\\Wos2017");
        return "ok";
    }
}
