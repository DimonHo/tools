package com.wd.pub.mongoservice.repository;

import com.wd.pub.mongoservice.entity.WosSource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by DimonHo on 2017/12/29.
 */
public interface WosRepository extends MongoRepository<WosSource,String>{

}
