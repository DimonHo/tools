package com.wd.pub.mysqlservice.entity.primary;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;

/**
 * Created by DimonHo on 2017/12/21.
 */
@Entity
@Table(name = "core_expansion")
public class CoreExpansion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String jguid;
    private String title;
    private String issn;
    private String cssn;
    private String categorySystem;
    private String category;
    private String rangeyear;
    private String core;
    @Column(columnDefinition = "tinyint default 1")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJguid() {
        return jguid;
    }

    public void setJguid(String jguid) {
        this.jguid = jguid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getCssn() {
        return cssn;
    }

    public void setCssn(String cssn) {
        this.cssn = cssn;
    }

    public String getCategorySystem() {
        return categorySystem;
    }

    public void setCategorySystem(String categorySystem) {
        this.categorySystem = categorySystem;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRangeyear() {
        return rangeyear;
    }

    public void setRangeyear(String rangeyear) {
        this.rangeyear = rangeyear;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
