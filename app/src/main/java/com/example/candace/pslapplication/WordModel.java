package com.example.candace.pslapplication;

import java.io.Serializable;

public class WordModel implements Serializable {

    private String word;
    private String wordFilipino;
    private String category;
    private boolean favorite;
    private String link;

    public WordModel(String w, String wF, String c, boolean f, String l){
        word = w;
        wordFilipino = wF;
        category = c;
        favorite = f;
        link = l;
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
