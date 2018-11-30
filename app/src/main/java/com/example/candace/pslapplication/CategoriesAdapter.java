package com.example.candace.pslapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesHolder> {
    private ArrayList<String> categoriesList;
    private CategoriesActivity activity;

    public CategoriesAdapter(CategoriesActivity activity){
        this.activity = activity;

        categoriesList = new ArrayList<String>();
        categoriesList.add("Alphabet");
        categoriesList.add("Days of the Week");
        categoriesList.add("Basic Greetings");
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
        holder.setCategoryText(categoriesList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}
