package com.example.candace.pslapplication;

import java.util.ArrayList;

public class CategoriesModel {

    private String name;
    private ArrayList<WordModel> list;

    public CategoriesModel(String n, ArrayList<WordModel> lst){
        name = n;
        list = lst;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<WordModel> getList() {
        return list;
    }

    public void setList(ArrayList<WordModel> list) {
        this.list = list;
    }
}
