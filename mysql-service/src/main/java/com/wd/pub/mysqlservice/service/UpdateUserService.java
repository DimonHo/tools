package com.wd.pub.mysqlservice.service;

import com.wd.pub.mysqlservice.dao.primary.TMemberDao;
import com.wd.pub.mysqlservice.dao.secondary.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by DimonHo on 2017/12/6.
 */
public class UpdateUserService {
    @Autowired
    TMemberDao tMemberDao;
    @Autowired
    UserDao userDao;

}
