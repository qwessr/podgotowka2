package com.example.networkmodule.models;

public class Project {
    public Integer idType;
    public String name;
    public String description;
    public String startDate;
    public String endDate;
    public  Integer idCategory;
    public Project(){}

    public Project(Integer idType, String name, String description, String startDate, String endDate, Integer idCategory)
    {
        this.idType = idType;
        this.name = name;
        this.description =description;
        this.startDate =startDate;
        this.endDate = endDate;
        this.idCategory = idCategory;
    }
}
