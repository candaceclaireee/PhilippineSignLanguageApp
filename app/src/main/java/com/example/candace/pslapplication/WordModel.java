package com.example.candace.pslapplication;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class WordModel implements Serializable {

    private String word;
    private String wordFilipino;
    private String category;
    private boolean favorite;
    private String link;

    private DatabaseReference mDatabase;
    private DatabaseReference mRef;

    public WordModel(String w, String wF, String c, boolean f, String l){
        word = w;
        wordFilipino = wF;
        category = c;
        favorite = f;
        link = l;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("words");

    }

    public WordModel(){ }  // this is a parameter-less constructor called upon firebase initialization

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

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
