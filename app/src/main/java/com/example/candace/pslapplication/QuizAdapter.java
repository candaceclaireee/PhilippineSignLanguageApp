package com.example.candace.pslapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

public class QuizAdapter extends RecyclerView.Adapter<QuizHolder> {

    private ArrayList<QuizModel> list;
    private QuizzesActivity activity;
    private int imageSet[];
    private ArrayList<LevelModel> level1;
    private ArrayList<LevelModel> level2;
    private ArrayList<LevelModel> level3;

    /* For the Firebase Database */
    private DatabaseReference mDatabase;
    private ArrayList<LevelModel> words;


    public QuizAdapter(QuizzesActivity activity){
        this.activity = activity;
        Log.d("DEBUGGING", "IN ADAPTER");

        imageSet = new int[]{
            R.drawable.level1,
            R.drawable.level2,
            R.drawable.level3,
        };

        list = new ArrayList<QuizModel>();


        level1 = new ArrayList<LevelModel>();
        level2 = new ArrayList<LevelModel>();
        level3 = new ArrayList<LevelModel>();
        words = new ArrayList<LevelModel>();
        list.add(new QuizModel("Level 1", level1));
        list.add(new QuizModel("Level 2", level2));
        list.add(new QuizModel("Level 3", level3));


        initializeFirebaseData();
        Log.d("DEBUGGING - size", String.valueOf(list.size()));
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("DEBUGGING", "IN ONCREATEHOLDER");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_quizzes, parent, false);
        QuizHolder holder = new QuizHolder(view, activity );
        Log.d("DEBUGGING", "HOLDER CREATED");

        return holder;
    }

    @Override
    public void onBindViewHolder(QuizHolder holder, final int position) {
        Log.d("DEBUGGING", "IN ONBINDVIEWHOLDER");
        holder.setLevel(String.valueOf(position+1));
        holder.setImgBtn(imageSet[position]);
        holder.setList(list.get(position).getList());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /* For the Firebase Database */
    public void initializeFirebaseData(){

        mDatabase = FirebaseDatabase.getInstance().getReference().child("words");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    LevelModel word = dataSnapshotChild.getValue(LevelModel.class);
                    words.add(word);
                }
                storageContainer(words);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    /* For the Firebase Database */
    public void storageContainer(ArrayList<LevelModel> word){
        this.words = word;

        for (int i=0; i<words.size(); i++) {
            if (words.get(i).getCategory().equals("alphabet")) {
                level1.add(words.get(i));
            } else if (words.get(i).getCategory().equals("weekday")) {
                level2.add(words.get(i));
            } else if (words.get(i).getCategory().equals("basicgreetings")) {
                level3.add(words.get(i));
            }
        }
       for(int i=0; i<list.size(); i++) {
           if (list.get(i).getName().contentEquals("Level 1"))
               list.get(i).setList(level1);
           else if(list.get(i).getName().contentEquals("Level 2"))
               list.get(i).setList(level2);
           else if(list.get(i).getName().contentEquals("Level 3"))
               list.get(i).setList(level3);
       }

        Log.d("DEBUGGING - size2", String.valueOf(list.size()));
    }
}
