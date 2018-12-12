package com.example.candace.pslapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryHolder> implements Filterable {

    private DictionaryActivity activity;

    /* For the Firebase Database */
    private DatabaseReference mDatabase;
    private ArrayList<WordModel> allWords;
    private ArrayList<WordModel> fulllist;


    public DictionaryAdapter(DictionaryActivity activity){
        allWords = new ArrayList<WordModel>();
        fulllist = new ArrayList<WordModel>();

        initializeFirebaseData();
        fulllist.clear();
        fulllist = new ArrayList<WordModel>(allWords);
        this.activity = activity;
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
                    notifyDataSetChanged();
                    fulllist.add(new WordModel(word.getWord(), word.getWordFilipino(),word.getCategory(),word.getFavorite(),word.getLink()));
                    notifyDataSetChanged();
                }

                Collections.sort(fulllist, new Comparator<WordModel>() {
                    @Override
                    public int compare(WordModel o1, WordModel o2) {
                        return o1.getWord().compareTo(o2.getWord());
                    }
                });
                storageContainer(fulllist);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    /* For the Firebase Database */
    public void storageContainer(ArrayList<WordModel> words) {

        this.allWords.addAll(words);
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<WordModel> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(fulllist);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(WordModel w: fulllist){
                    if(w.getWord().toLowerCase().contains(filterPattern))
                        filteredList.add(w);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allWords.clear();
            allWords.addAll((ArrayList) results.values);

            notifyDataSetChanged();
        }
    };
}
