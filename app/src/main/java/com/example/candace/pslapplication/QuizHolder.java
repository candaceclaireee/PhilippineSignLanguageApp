package com.example.candace.pslapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class QuizHolder extends RecyclerView.ViewHolder {

    private Button img;
    private String level;
    private QuizzesActivity activity;
    private ArrayList<LevelModel> list;

    public QuizHolder(@NonNull View itemView, final QuizzesActivity activity) {
        super(itemView);

        this.activity = activity;

        img = itemView.findViewById(R.id.imgBtn);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUGGING", String.valueOf(level));
//                if(level=="1")
//                    img.setText("1");
//                else if(level=="2")
//                    img.setText("2");
//                else if(level=="3")
//                    img.setText("3");

                Intent intent = new Intent(activity.getApplicationContext(), LevelActivity.class);
                intent.putExtra("LEVEL", level);
                intent.putExtra("LEVEL_LIST", list);
                activity.startActivity(intent);

            }
        });

    }
    public void setLevel(String lvl) {
        this.level = lvl;
//        img.setText(lvl);
    }
    public void setImgBtn(int newImg){
        img.setBackgroundResource(newImg);
    }

    public void setList(ArrayList<LevelModel> lst){ this.list = lst; }
}
