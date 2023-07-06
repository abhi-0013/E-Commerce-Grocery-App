package com.example.abhishek.mygrocerystore.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.abhishek.mygrocerystore.Models.HomeCategory;
import com.example.abhishek.mygrocerystore.R;
import com.example.abhishek.mygrocerystore.activities.ViewAllActivity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolders> {

    private static final String TAG = "HomeAdapter";
    Context mcontext;
    List<HomeCategory> mhomeCategoryList;

    public HomeAdapter(Context mcontext, List<HomeCategory> mhomeCategoryList) {
        Log.d(TAG, "HomeAdapter: called");
        this.mcontext = mcontext;
        this.mhomeCategoryList = mhomeCategoryList;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        Glide.with(mcontext).load(mhomeCategoryList.get(position).getImg_url()).into(holder.cateogory_img);
        holder.name.setText(mhomeCategoryList.get(position).getName());
        int pos = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, ViewAllActivity.class);
                intent.putExtra("type",mhomeCategoryList.get(pos).getType());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: called");
        return mhomeCategoryList.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{

        ImageView cateogory_img;
        TextView name;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolders: of home adapter called");
            cateogory_img = itemView.findViewById(R.id.category_img);
            name = itemView.findViewById(R.id.category_name);
        }
    }
}
