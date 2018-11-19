package com.example.candace.pslapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesHolder> {

    private ArrayList<WordModel> favoritesList;

    public FavoritesAdapter(FavoritesActivity activity){
        favoritesList = new ArrayList<WordModel>();

        /* Test only */
        favoritesList.add(new WordModel("Test Human", "Tao"));
        favoritesList.add(new WordModel("Test Banana", "Saging"));
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
        holder.setWord(favoritesList.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }
}
