package com.wd.pub.mysqlservice.controller;

import com.wd.pub.mysqlservice.service.ImportEduFlagService;
import com.wd.pub.mysqlservice.service.ImportSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by DimonHo on 2017/12/6.
 */
@RestController
public class ImportSchoolController {

    @Autowired
    ImportSchoolService importSchoolService;

    @Autowired
    ImportEduFlagService importEduFlagService;
    @GetMapping("/importSchool")
    public String importSchool(){
        importSchoolService.importSchoolData();
        return "ok";
    }

    @GetMapping("/importEdu")
    public String importEdu(){
        try {
            String path = "static/tempSources/eduFlag.json";
            importEduFlagService.importEduFlag(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
