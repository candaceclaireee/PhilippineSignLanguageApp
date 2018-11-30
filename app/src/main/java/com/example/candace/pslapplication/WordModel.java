package com.example.candace.pslapplication;

public class WordModel {

    private String word;
    private String wordFilipino;
    private String category;
    private boolean favorite;


    public WordModel(String w, String wF, String c, boolean f){
        word = w;
        wordFilipino = wF;
        category = c;
        favorite = f;
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

}
