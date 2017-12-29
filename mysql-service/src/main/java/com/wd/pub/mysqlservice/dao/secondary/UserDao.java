package com.wd.pub.mysqlservice.dao.secondary;

import com.wd.pub.mysqlservice.entity.secondary.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by DimonHo on 2017/12/6.
 */
public interface UserDao extends JpaRepository<User,Integer> {
}
