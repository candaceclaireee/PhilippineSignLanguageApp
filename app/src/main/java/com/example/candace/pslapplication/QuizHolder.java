package com.example.candace.pslapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizHolder extends RecyclerView.ViewHolder {

    private Button img;
    private TextView leveltext;
    private String level;
    private QuizzesActivity activity;
    private ArrayList<LevelModel> list;

    public QuizHolder(@NonNull View itemView, final QuizzesActivity activity) {
        super(itemView);

        this.activity = activity;

        img = itemView.findViewById(R.id.imgBtn);
        leveltext = itemView.findViewById(R.id.leveltext);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUGGING", String.valueOf(level));

                Intent intent = new Intent(activity.getApplicationContext(), LevelActivity.class);
                intent.putExtra("LEVEL", level);
                intent.putExtra("LEVEL_LIST", list);
                activity.startActivity(intent);

            }
        });

    }
    public void setLevel(String lvl) {
        this.level = lvl;

        if (level.equals("1")) {
            leveltext.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Yellow));
            img.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Yellow));
        } else if (level.equals("2")) {
            leveltext.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Red));
            img.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Red));
        } else if (level.equals("3")) {
            leveltext.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Blue));
            img.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.Blue));
        }

        leveltext.setText("LEVEL" + lvl);
    }
    public void setImgBtn(int newImg){
//        img.setBackgroundResource(newImg);
    }

    public void setList(ArrayList<LevelModel> lst){ this.list = lst; }
}
