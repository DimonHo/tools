package com.wd.pub.mysqlservice.dao.primary;

import com.wd.pub.mysqlservice.entity.primary.TOrg;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by DimonHo on 2017/12/5.
 */
public interface TOrgDao extends JpaRepository<TOrg,Integer>{
}
