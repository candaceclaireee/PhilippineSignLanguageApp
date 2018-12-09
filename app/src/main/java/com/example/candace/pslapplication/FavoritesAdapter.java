package com.example.candace.pslapplication;

import android.support.v7.widget.RecyclerView;
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

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesHolder> {

    private FavoritesActivity activity;

    /* For the Firebase Database */
    private DatabaseReference mDatabase;
    private ArrayList<WordModel> favoritesList;

    public FavoritesAdapter(FavoritesActivity activity){
        favoritesList = new ArrayList<WordModel>();
        this.activity = activity;
        initializeFirebaseData();
    }

    @Override
    public FavoritesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_favorites, parent, false);

        FavoritesHolder holder = new FavoritesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FavoritesHolder holder, final int position) {
        holder.setWord(favoritesList.get(position));
        holder.setActivity(activity);
    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
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
                    favoritesList.add(word);
                }
                storageContainer(favoritesList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    /* For the Firebase Database */
    public void storageContainer(ArrayList<WordModel> words) {
        //this.favoritesList = words;

        ArrayList<WordModel> wm = new ArrayList<WordModel>();

        for(WordModel w: words){
            if(w.getFavorite() == true)
                wm.add(w);

        }

        favoritesList = wm;
    }


}
