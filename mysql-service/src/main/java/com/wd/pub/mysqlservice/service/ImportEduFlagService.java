package com.wd.pub.mysqlservice.service;


import com.alibaba.fastjson.JSONObject;
import com.wd.pub.mysqlservice.dao.secondary.SchoolDao;
import com.wd.pub.mysqlservice.entity.secondary.School;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DimonHo on 2017/12/7.
 */
@Service
public class ImportEduFlagService {

    @Autowired
    SchoolDao schoolDao;

    public String importEduFlag(String filePath) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream in = classLoader.getResourceAsStream(filePath);
        String fileStr = IOUtils.toString(in); //or  IOUtils.toString(in,"UTF-8");
        JSONObject content = JSONObject.parseObject(fileStr);
        JSONObject data = content.getJSONObject("data");
        for (int i=0;i<50;i++){
            JSONObject d = data.getJSONObject(i+"")!=null?data.getJSONObject(i+""):null;
            if (d!=null){
                String schoolName = d.getString("name");
                School school = schoolDao.findBySchoolName(schoolName);
                if (school!=null){
                    school.setEduFlag(d.getString("code"));
                    schoolDao.save(school);
                }else{
                    school = new School();
                    school.setEduFlag(d.getString("code"));
                    school.setSchoolName(schoolName);
                    schoolDao.save(school);
                }
            }
        }
        return "ok";
    }
}
