package com.example.abhishek.mygrocerystore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.abhishek.mygrocerystore.Models.nav_category_Models;
import com.example.abhishek.mygrocerystore.R;
import com.example.abhishek.mygrocerystore.activities.ViewAllActivity;

import java.util.List;

public class nav_category_Adapter extends RecyclerView.Adapter<nav_category_Adapter.ViewHolders> {

    Context mContext;
    List<nav_category_Models> mNav_Category_list;

    public nav_category_Adapter(Context mContext, List<nav_category_Models> mNav_Category_list) {
        this.mContext = mContext;
        this.mNav_Category_list = mNav_Category_list;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

        Glide.with(mContext).load(mNav_Category_list.get(position).getImg_url()).into(holder.rec_img);
        holder.name.setText(mNav_Category_list.get(position).getName());
        holder.Discount.setText(mNav_Category_list.get(position).getDiscount());
        holder.description.setText(mNav_Category_list.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewAllActivity.class);
                intent.putExtra("type",mNav_Category_list.get(holder.getAdapterPosition()).getName());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNav_Category_list.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{

        ImageView rec_img;
        TextView name,description,Discount;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            rec_img = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.cat_nav_name);
            description = itemView.findViewById(R.id.cat_nav_discription);
            Discount = itemView.findViewById(R.id.cat_nav_discount_value);
        }
    }
}
