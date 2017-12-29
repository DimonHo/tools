package com.wd.pub.mysqlservice.dao.primary;

import com.wd.pub.mysqlservice.entity.primary.TMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by DimonHo on 2017/12/6.
 */
public interface TMemberDao extends JpaRepository<TMember,Integer> {
    List<TMember> findByUsernameNot(String name);
}
