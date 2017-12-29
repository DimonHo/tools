package com.wd.pub.mysqlservice.service;

import com.wd.pub.mysqlservice.dao.primary.TMemberDao;
import com.wd.pub.mysqlservice.dao.secondary.UserDao;
import com.wd.pub.mysqlservice.entity.primary.TMember;
import com.wd.pub.mysqlservice.entity.secondary.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DimonHo on 2017/12/6.
 */
@Service
public class ImportUserService {
    @Autowired
    TMemberDao tMemberDao;

    @Autowired
    UserDao userDao;

    public void importUser() {
        List<TMember> tMemberList = tMemberDao.findByUsernameNot("Tourist");
        int index = 0;
        for (TMember tMember : tMemberList) {
            User user = new User();
            user.setId(tMember.getId());
            user.setEmail(tMember.getEmail());
            user.setUsername(tMember.getUsername());
            user.setNickname(tMember.getNickname());
            user.setLastLoginIp(tMember.getLastLoginIp());
            user.setLoginTime(tMember.getLoginTime());
            user.setPhone(tMember.getPhone());
            user.setPwd(tMember.getPwd());
            user.setQq(tMember.getQq());
            user.setRegisterIp(tMember.getRegisterIp());
            user.setRegisterTime(tMember.getRegisterTime());
            user.setSchoolFlag(tMember.getSchoolFlag());
            user.setSchoolName(tMember.getSchool());
            user.setSex(tMember.getSex());
            user.setForbidden(tMember.getForbidden()-1);
            userDao.save(user);
            System.out.println("导入成功："+ ++index);
        }

    }
}
