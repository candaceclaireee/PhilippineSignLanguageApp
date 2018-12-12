package com.example.candace.pslapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class FavoritesHolder extends RecyclerView.ViewHolder{

    private TextView favorites_text;
    private ImageButton btn;
    private FavoritesActivity fave;
    private String word;
    private WordModel model;

    public FavoritesHolder(View view) {
        super(view);

        favorites_text = view.findViewById(R.id.favorite_text);
        favorites_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fave.getApplicationContext(), WordActivity.class);
                intent.putExtra("WordModelObject", model);
                fave.startActivity(intent);
            }
        });

        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fave.getApplicationContext(), WordActivity.class);
                intent.putExtra("WordModelObject", model);
                fave.startActivity(intent);
            }
        });
    }

    public void setWord(WordModel word){
        this.word = word.getWord();
        favorites_text.setText(word.getWord());
        model = word;
    }
    
    public void setActivity(FavoritesActivity activity){
        fave = activity;
    }
}
