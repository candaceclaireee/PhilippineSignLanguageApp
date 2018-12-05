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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesHolder> {

    private ArrayList<CategoriesModel> categoriesList;
    private CategoriesActivity activity;

    private ArrayList<WordModel> alphabetList;
    private ArrayList<WordModel> weekdayList;
    private ArrayList<WordModel> basicgreetingsList;

    /* For the Firebase Database */
    private DatabaseReference mDatabase;
    private ArrayList<WordModel> words;

    public CategoriesAdapter(CategoriesActivity activity){
        this.activity = activity;

        initializeFirebaseData();

        words = new ArrayList<WordModel>();
        categoriesList = new ArrayList<CategoriesModel>();
        alphabetList = new ArrayList<WordModel>();
        weekdayList = new ArrayList<WordModel>();
        basicgreetingsList = new ArrayList<WordModel>();
    }

    @Override
    public CategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_categories, parent, false);

        CategoriesHolder holder = new CategoriesHolder(view, activity);
        return holder;
    }

    @Override
    public void onBindViewHolder(CategoriesHolder holder, final int position) {
        holder.setCategoryText(categoriesList.get(position).getName());
        holder.setWords(categoriesList.get(position).getList());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
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
                    WordModel word = dataSnapshotChild.getValue(WordModel.class);
                    words.add(word);
                }
                storageContainer(words);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    /* For the Firebase Database */
    public void storageContainer(ArrayList<WordModel> words){
        this.words = words;

        for (int i=0; i<words.size(); i++) {
            if (words.get(i).getCategory().equals("alphabet")) {
                alphabetList.add(words.get(i));
            } else if (words.get(i).getCategory().equals("weekday")) {
                weekdayList.add(words.get(i));
            } else if (words.get(i).getCategory().equals("basicgreetings")) {
                basicgreetingsList.add(words.get(i));
            }
        }
        categoriesList.add(new CategoriesModel("Alphabets", alphabetList));
        categoriesList.add(new CategoriesModel("Weekdays", weekdayList));
        categoriesList.add(new CategoriesModel("Basic Greetings", basicgreetingsList));
    }
}
