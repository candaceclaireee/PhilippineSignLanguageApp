package com.example.candace.pslapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryHolder> {

    private DictionaryActivity activity;
    private String search;

    /* For the Firebase Database */
    private DatabaseReference mDatabase;
    private ArrayList<WordModel> allWords;

    public DictionaryAdapter(DictionaryActivity activity, String search){
        allWords = new ArrayList<WordModel>();
        this.activity = activity;
        initializeFirebaseData();
    }

    @Override
    public DictionaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_dictionary, parent, false);

        DictionaryHolder holder = new DictionaryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DictionaryHolder holder, final int position) {
        holder.setWord(allWords.get(position));
        holder.setActivity(activity);
    }

    @Override
    public int getItemCount() {
        return allWords.size();
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
                    allWords.add(word);
                }
                storageContainer(allWords);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    /* For the Firebase Database */
    public void storageContainer(ArrayList<WordModel> words) {
        this.allWords = words;
    }

}
