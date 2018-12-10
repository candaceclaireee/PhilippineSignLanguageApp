package com.example.candace.pslapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class LevelModel implements Serializable {
    private String word;
    private String wordFilipino;
    private String category;
    private String link;

    private DatabaseReference mDatabase;
    private DatabaseReference mRef;

    public LevelModel(String w, String wF, String c, String l){
        word = w;
        wordFilipino = wF;
        category = c;
        link = l;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("words");

    }

    public LevelModel(){ }  // this is a parameter-less constructor called upon firebase initialization

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordFilipino() {
        return wordFilipino;
    }

    public void setWordFilipino(String wordFilipino) {
        this.wordFilipino = wordFilipino;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
