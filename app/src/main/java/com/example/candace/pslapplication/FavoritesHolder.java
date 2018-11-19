package com.example.candace.pslapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class FavoritesHolder extends RecyclerView.ViewHolder{

    private TextView favorites_text;

    public FavoritesHolder(View view) {
        super(view);

        favorites_text = view.findViewById(R.id.favorites_text);
    }

    public void setWord(String word){
        favorites_text.setText(word);
    }
}
