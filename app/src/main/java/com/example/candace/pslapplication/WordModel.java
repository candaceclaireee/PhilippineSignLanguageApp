package com.example.candace.pslapplication;

public class WordModel {

    private String word;
    private String wordFilipino;

    public WordModel(String w,String wF){
        word = w;
        wordFilipino = wF;
    }

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
}
