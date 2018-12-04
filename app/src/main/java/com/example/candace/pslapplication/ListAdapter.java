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
        wordsList = new ArrayList<WordModel>();

        for(int i=0;i<list.size();i++)
            wordsList.add(new WordModel(list.get(i).getWord(), list.get(i).getWordFilipino(), list.get(i).getCategory(),
                    list.get(i).getFavorite(), list.get(i).getLink()));
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
        holder.setWord(wordsList.get(position).getWord());
        holder.setWordFilipino(wordsList.get(position).getWordFilipino());
        holder.setLink(wordsList.get(position).getLink());
        holder.setFavorite(wordsList.get(position).getFavorite());
        holder.setCategory(wordsList.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }
}
