package com.wd.pub.mongoservice.service;

import com.xiaoleilu.hutool.io.FileUtil;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

/**
 * Created by DimonHo on 2017/12/28.
 */
public abstract class ResolveService {

    public Map properties;

    public List<File> fileList;

    /**
     * 加载wos的map配置
     * @param mapPath
     * @return
     */
    public void setWosMap(String mapPath){
        List<String> lines = FileUtil.readLines(mapPath,"UTF-8");
        Map<String,String> map = new TreeMap<String,String>();
        //不为空的行
        Predicate<String> notnull = (n) -> !StringUtils.isEmpty(n);
        //不是 # 开头的行
        Predicate<String> startsWithFilter = (n) -> !n.trim().startsWith("#");
        // 执行过滤不为空 且 不是#号开头的行
        lines.stream().filter(notnull.and(startsWithFilter))
                //执行拆分
                .map(l -> l.split("="))
                //执行put到map中
                .forEach(s -> map.put(s[0],s[1]));
        properties = map;
    }

    public void setFileList(String directryPath){
        fileList = FileUtil.loopFiles(directryPath);
    }
}
