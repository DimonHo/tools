package com.wd.pub.mysqlservice.controller;

import com.wd.pub.mysqlservice.service.ExclImportMysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DimonHo on 2017/12/22.
 */
@RestController
public class ExclImportMysqlController {

    @Autowired
    ExclImportMysqlService exclImportMysqlService;

    @GetMapping("/excl/start")
    public String start(){
        String exclPath = "C:/Users/Administrator/Desktop/CSCDCSSCI核心扩展全部数据导入整理版.xlsx";
        exclImportMysqlService.exclToMysql(exclPath);
        return "ok";
    }
}
