package com.example.candace.pslapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizModel implements Serializable {

    private String name;
    private ArrayList<LevelModel> list;

    public QuizModel(String n, ArrayList<LevelModel> list){
        this.name = n;
       this.list = list;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<LevelModel> getList() {
        return list;
    }

    public void setList(ArrayList<LevelModel> list) {
        this.list = list;
    }
}
