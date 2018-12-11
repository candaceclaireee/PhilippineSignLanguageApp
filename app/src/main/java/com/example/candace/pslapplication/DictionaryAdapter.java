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
import java.util.Iterator;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryHolder> implements Filterable {

    private DictionaryActivity activity;

    /* For the Firebase Database */
    private DatabaseReference mDatabase;
    private ArrayList<WordModel> allWords;
    private ArrayList<WordModel> fulllist;


    public DictionaryAdapter(DictionaryActivity activity){
        allWords = new ArrayList<WordModel>();
      //  allWords = sampleItems();

        fulllist = new ArrayList<WordModel>(allWords);
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
                    notifyDataSetChanged();
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

    private ArrayList<WordModel> sampleItems(){
        ArrayList<WordModel> sample = new ArrayList<WordModel>();

        sample.add(new WordModel("one", "isa", "number", true, "sample"));
        sample.add(new WordModel("two", "isa", "number", true, "sample"));
        sample.add(new WordModel("three", "isa", "number", true, "sample"));
        sample.add(new WordModel("four", "isa", "number", true, "sample"));
        sample.add(new WordModel("five", "isa", "number", true, "sample"));

        return sample;
    }
}
