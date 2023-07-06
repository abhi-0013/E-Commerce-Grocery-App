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
import com.example.abhishek.mygrocerystore.Models.PopularModel;
import com.example.abhishek.mygrocerystore.R;
import com.example.abhishek.mygrocerystore.activities.ViewAllActivity;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolders> {

    private Context mcontext;
    private List<PopularModel> mpopularModelList;

    public PopularAdapter(Context mcontext, List<PopularModel> mpopularModelList) {
        this.mcontext = mcontext;
        this.mpopularModelList = mpopularModelList;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        Glide.with(mcontext).load(mpopularModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(mpopularModelList.get(position).getName());
        holder.rating.setText(mpopularModelList.get(position).getRating());
        holder.description.setText(mpopularModelList.get(position).getDescription());
        holder.discount.setText(mpopularModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, ViewAllActivity.class);
                intent.putExtra("type",mpopularModelList.get(holder.getAdapterPosition()).getType());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mpopularModelList.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{

        ImageView popImg;
        TextView name,description,rating,discount;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_image);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_description);
            rating = itemView.findViewById(R.id.pop_rating);
            discount = itemView.findViewById(R.id.pop_discount);
        }
    }
}
