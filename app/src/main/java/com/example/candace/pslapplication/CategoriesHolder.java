package com.example.candace.pslapplication;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesHolder extends RecyclerView.ViewHolder{

    private TextView categories_text;
    private Button categories_button;

    private CategoriesActivity activity;

    private String name;
    private ArrayList<WordModel> list;

    public CategoriesHolder(View view, final CategoriesActivity activity) {
        super(view);

        this.activity = activity;
        categories_text = view.findViewById(R.id.categories_text);

        categories_button = view.findViewById(R.id.categories_button);
        categories_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity.getApplicationContext(), ListActivity.class);

                intent.putExtra("CATEGORY_NAME",name);
                intent.putExtra("CATEGORY_WORDS",list);

                activity.startActivity(intent);
            }
        });
    }

    public void setCategoryText(String word){
        name = word;
        if (word.equals("Alphabets")) {
            categories_text.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Yellow));
            categories_button.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Yellow));
        } else if (word.equals("Weekdays")) {
            categories_text.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Red));
            categories_button.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Red));
        } else if (word.equals("Basic Greetings")) {
            categories_text.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Blue));
            categories_button.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Blue));
        }
        categories_text.setText(word);
    }

    public void setWords(ArrayList<WordModel> words){
        list = words;
    }
}
