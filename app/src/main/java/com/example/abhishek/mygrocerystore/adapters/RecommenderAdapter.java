package com.example.abhishek.mygrocerystore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.abhishek.mygrocerystore.Models.RecommendedModel;
import com.example.abhishek.mygrocerystore.R;

import java.util.List;

public class RecommenderAdapter extends RecyclerView.Adapter<RecommenderAdapter.ViewHolders> {

    Context mContext;
    List<RecommendedModel> mrecommendedModelList;

    public RecommenderAdapter(Context mContext, List<RecommendedModel> mrecommendedModelList) {
        this.mContext = mContext;
        this.mrecommendedModelList = mrecommendedModelList;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

        Glide.with(mContext).load(mrecommendedModelList.get(position).getImg_url()).into(holder.rec_img);
        holder.name.setText(mrecommendedModelList.get(position).getName());
        holder.rating.setText(mrecommendedModelList.get(position).getRating());
        holder.description.setText(mrecommendedModelList.get(position).getDescription());
        holder.price.setText("Price: "+mrecommendedModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return mrecommendedModelList.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{
        ImageView rec_img;
        TextView name,description,rating,price;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            rec_img = itemView.findViewById(R.id.rec_image);
            name = itemView.findViewById(R.id.rec_name);
            description = itemView.findViewById(R.id.rec_description);
            rating = itemView.findViewById(R.id.rec_rating);
            price = itemView.findViewById(R.id.rec_price);
        }
    }
}
