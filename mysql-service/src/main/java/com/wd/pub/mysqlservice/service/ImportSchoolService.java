package com.wd.pub.mysqlservice.service;

import com.wd.pub.mysqlservice.dao.primary.TOrgDao;
import com.wd.pub.mysqlservice.dao.secondary.SchoolDao;
import com.wd.pub.mysqlservice.entity.primary.TOrg;
import com.wd.pub.mysqlservice.entity.secondary.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DimonHo on 2017/12/6.
 */
@Service
public class ImportSchoolService {

    @Autowired
    TOrgDao tOrgDao;
    @Autowired
    SchoolDao schoolDao;

    public void importSchoolData(){
        int i=0;
        List<TOrg> orgs = tOrgDao.findAll();
        for(TOrg org:orgs){
            School school = new School();
            school.setId(org.getId());
            school.setSchoolFlag(org.getFlag());
            school.setSchoolName(org.getName());
            schoolDao.save(school);
            System.out.println(++i);
        }
    }
}
