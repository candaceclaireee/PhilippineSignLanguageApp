package com.example.candace.pslapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    private ArrayList<WordModel> wordsList;
    private ListActivity activity;

    public ListAdapter(ListActivity activity, String name, ArrayList<WordModel> list){
        this.activity = activity;
        wordsList = new ArrayList<WordModel>(list);
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_listcategories, parent, false);

        ListHolder holder = new ListHolder(view, activity);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, final int position) {
        holder.setModel(wordsList.get(position));
    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }
}
