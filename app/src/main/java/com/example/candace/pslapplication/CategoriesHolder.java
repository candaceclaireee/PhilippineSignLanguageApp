package com.example.candace.pslapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CategoriesHolder extends RecyclerView.ViewHolder{

    private TextView categories_text;
    private Button categories_button;

    private CategoriesActivity activity;

    public CategoriesHolder(View view, CategoriesActivity activity) {
        super(view);

        this.activity = activity;
        categories_text = view.findViewById(R.id.categories_text);
        categories_button = view.findViewById(R.id.categories_button);
    }

    public void setCategoryText(String word){
        if (word.equals("Alphabet")) {
            categories_text.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Yellow));
            categories_button.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Yellow));
        } else if (word.equals("Days of the Week")) {
            categories_text.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Red));
            categories_button.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Red));
        } else if (word.equals("Basic Greetings")) {
            categories_text.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Blue));
            categories_button.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Blue));
        }
        categories_text.setText(word);
    }
}
