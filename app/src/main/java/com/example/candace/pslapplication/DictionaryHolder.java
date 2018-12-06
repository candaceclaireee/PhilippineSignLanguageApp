package com.example.candace.pslapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DictionaryHolder extends RecyclerView.ViewHolder{

    private TextView dict_text;
    private ImageButton btn;
    private DictionaryActivity dict;
    private String word;
    private WordModel model;

    public DictionaryHolder(View view) {
        super(view);

        dict_text = view.findViewById(R.id.dict_text);
        btn = view.findViewById(R.id.gotonext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dict.getApplicationContext(), WordActivity.class);
                intent.putExtra("WORD",model.getWord());
                intent.putExtra("WORDFILIPINO",model.getWordFilipino());
                intent.putExtra("CATEGORY",model.getCategory());
                intent.putExtra("FAVORITE",model.getFavorite());
                intent.putExtra("LINK",model.getLink());
                model.setFavorite(true);
                dict.startActivity(intent);
            }
        });
    }

    public void setWord(WordModel word){
        this.word = word.getWord();
        dict_text.setText(word.getWord());
        model = word;
    }
    public void setActivity(DictionaryActivity activity){
        dict = activity;
    }

}
