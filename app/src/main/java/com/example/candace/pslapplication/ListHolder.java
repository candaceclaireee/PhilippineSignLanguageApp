package com.example.candace.pslapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListHolder extends RecyclerView.ViewHolder{

    private TextView listcategories_text;
    private Button listcategories_button;

    private String word;
    private String wordFilipino;
    private String category;
    private boolean favorite;
    private String link;
    private WordModel model;


    public ListHolder(View view,  final ListActivity activity) {
        super(view);

        listcategories_text = view.findViewById(R.id.listcategories_text);
        listcategories_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity.getApplicationContext(), WordActivity.class);
                intent.putExtra("WordModelObject", model);
                activity.startActivity(intent);
            }
        });
        listcategories_button = view.findViewById(R.id.listcategories_button);
        listcategories_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity.getApplicationContext(), WordActivity.class);
                intent.putExtra("WordModelObject", model);
                activity.startActivity(intent);
            }
        });
    }

    public void setModel(WordModel model){
        this.model = model;
        listcategories_text.setText(model.getWord());
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
        listcategories_text.setText(word);
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
