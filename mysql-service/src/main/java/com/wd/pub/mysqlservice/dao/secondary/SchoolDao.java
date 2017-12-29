package com.wd.pub.mysqlservice.dao.secondary;

import com.wd.pub.mysqlservice.entity.secondary.School;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by DimonHo on 2017/12/6.
 */
public interface SchoolDao extends JpaRepository<School,Integer> {

    School findBySchoolName(String schoolName);
}
