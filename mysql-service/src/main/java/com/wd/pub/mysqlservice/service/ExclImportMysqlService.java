package com.wd.pub.mysqlservice.service;

import com.wd.pub.mysqlservice.dao.primary.CoreExpansionDao;
import com.wd.pub.mysqlservice.entity.primary.CoreExpansion;
import com.xiaoleilu.hutool.poi.excel.ExcelReader;
import com.xiaoleilu.hutool.poi.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DimonHo on 2017/12/21.
 * 将excl文档解析到mysql中
 */
@Service
public class ExclImportMysqlService {

    @Autowired
    CoreExpansionDao coreExpansionDao;

    public void exclToMysql(String exclPath){
        ExcelReader reader = ExcelUtil.getReader(exclPath);
        List<CoreExpansion> all = reader.readAll(CoreExpansion.class);
        coreExpansionDao.save(all);
    }
}
