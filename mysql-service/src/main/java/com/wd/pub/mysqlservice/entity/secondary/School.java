package com.wd.pub.mysqlservice.entity.secondary;

import javax.persistence.*;

/**
 * Created by DimonHo on 2017/12/6.
 */
@Entity
@Table(name="school")
public class School {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "school_flag")
    private String schoolFlag = "null";
    @Column(name = "edu_flag")
    private String eduFlag = "null";
    @Column(name = "school_name")
    private String schoolName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolFlag() {
        return schoolFlag;
    }

    public void setSchoolFlag(String schoolFlag) {
        this.schoolFlag = schoolFlag;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getEduFlag() {
        return eduFlag;
    }

    public void setEduFlag(String eduFlag) {
        this.eduFlag = eduFlag;
    }
}
