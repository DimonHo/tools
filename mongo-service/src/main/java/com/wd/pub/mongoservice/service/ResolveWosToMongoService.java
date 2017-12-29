package com.wd.pub.mongoservice.service;


import com.wd.pub.mongoservice.entity.Journal;
import com.wd.pub.mongoservice.entity.WosSource;
import com.wd.pub.mongoservice.repository.WosRepository;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by DimonHo on 2017/12/22.
 * 解析wos文档到mongodb
 */
@Service
public class ResolveWosToMongoService extends ResolveService {

    private static final Logger logger = LoggerFactory.getLogger(ResolveWosToMongoService.class);

    @Autowired
    WosRepository wosRepository;
    private static Map<String,String> wosMap;
    private static List<WosSource> wosSourceList = new ArrayList<>();
    private static WosSource wosSource = new WosSource();
    private static Journal journalInfo = new Journal();
    private static String key = "";
    private static String value = "";
    //需要手动添加分隔符的字段
    private static List listField = CollectionUtil.newArrayList("references","authorsFull","authors","affiliationFull");

    public void main(String directryPath){
        wosMap = getWosMap("static/wosmap.properties");
        List<File> fileList = FileUtil.loopFiles(directryPath);
        fileList.forEach(f -> resolve(f));
    }

    public void resolve(File file){
        List<String> lines = FileUtils.readBomLines(file);
        Predicate<String> notnull = (n) -> !StringUtils.isEmpty(n);
        Predicate<String> notStartFN = (n) -> !n.startsWith("FN");
        Predicate<String> notStartVR = (n) -> !n.startsWith("VR");
        Predicate<String> notStartEF = (n) -> !n.startsWith("EF");
        lines.stream().filter(notnull.and(notStartFN).and(notStartVR).and(notStartEF)).forEach(l -> resolveLine(l,file));
    }

    public void resolveLine(String line,File file){
        if (!line.startsWith("   ")){
            key = wosMap.get(line.substring(0,2));
            if (!StringUtils.isEmpty(key)) {
                if ("docEnd".equals(key)){
                    wosSource.setFileName(CollectionUtil.newArrayList(file.getName()));
                    wosSource.setSoulu(CollectionUtil.newArrayList(file.getParent().replaceAll(".*\\\\", "")));
                    wosSource.setUrl("http://dx.doi.org/"+ wosSource.getDoi());
                    wosSource.setJournalInfo(journalInfo);
                    wosSourceList.add(wosSource);
                    if (wosSourceList.size()>1000){
                        wosRepository.save(wosSourceList);
                        wosSourceList.clear();
                    }

                    wosSource = new WosSource();
                    journalInfo = new Journal();
                }else{
                    value = line.substring(3);
                    if (key.startsWith("journalInfo.")){
                        ReflectUtil.setFieldValue(journalInfo,key.substring(12),value);
                    }else if (TypeUtil.getType(ReflectUtil.getField(wosSource.getClass(),key)) instanceof List){
                        ReflectUtil.setFieldValue(wosSource,key,CollectionUtil.newArrayList(value));
                    }else if ((Object)TypeUtil.getType(ReflectUtil.getField(wosSource.getClass(),key)) instanceof Integer){
                        ReflectUtil.setFieldValue(wosSource,key,Integer.parseInt(value));
                    }else{
                        ReflectUtil.setFieldValue(wosSource,key,value);
                    }
                }
            }
        }else{
            if (!StringUtils.isEmpty(key)) {
                if (listField.contains(key)){
                    value += (";"+line.substring(3));
                }else{
                    value += line.substring(2);
                }
                if (key.startsWith("journalInfo.")){
                    ReflectUtil.setFieldValue(journalInfo,key.substring(12),value);
                }else if("java.util.List<java.lang.String>".equals(TypeUtil.getType(ReflectUtil.getField(wosSource.getClass(),key)).getTypeName())){
                    ReflectUtil.setFieldValue(wosSource,key,CollectionUtil.newArrayList(value.split(";")));
                }else{
                    ReflectUtil.setFieldValue(wosSource,key,value);
                }
            }
        }
    }

    /**
     * 加载wos的map配置
     * @param wosmapPath
     * @return
     */
    public Map getWosMap(String wosmapPath){
        List<String> lines = FileUtil.readLines(wosmapPath,"UTF-8");
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
        return map;
    }
}
