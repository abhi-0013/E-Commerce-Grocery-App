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
import com.example.abhishek.mygrocerystore.Models.ViewAllProduct_Model;
import com.example.abhishek.mygrocerystore.R;
import com.example.abhishek.mygrocerystore.activities.DetailActivity;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolders> {

    Context context;
    List<ViewAllProduct_Model> viewAllProduct_modelList;

    public ViewAllAdapter(Context context, List<ViewAllProduct_Model> viewAllProduct_modelList) {
        this.context = context;
        this.viewAllProduct_modelList = viewAllProduct_modelList;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_view_all_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

        if(viewAllProduct_modelList.size() != 0){
        Glide.with(context).load(viewAllProduct_modelList.get(position).getImg_url()).into(holder.img);
        holder.name.setText(viewAllProduct_modelList.get(position).getName());
        holder.price.setText("Price: $"+viewAllProduct_modelList.get(position).getPrice());
        holder.description.setText(viewAllProduct_modelList.get(position).getDescription());
        holder.rating.setText(viewAllProduct_modelList.get(position).getRatings());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra("details",viewAllProduct_modelList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
        }else{
            holder.name.setText(R.string.NotAVAILABLE_TEXT);
            holder.price.setText("N/A");
            holder.description.setText("N/A");
            holder.rating.setText("N/A");
        }


    }

    @Override
    public int getItemCount() {
        return viewAllProduct_modelList.size()==0 ? 1:viewAllProduct_modelList.size()  ;
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{

        private final TextView name, description, rating, price;
        private ImageView img;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.view_all_nav_img);
            name = itemView.findViewById(R.id.view_all_nav_name);
            description = itemView.findViewById(R.id.view_all_nav_discription);
            rating = itemView.findViewById(R.id.view_All_nav_ratings);
            price = itemView.findViewById(R.id.view_all_nav_price);
        }
    }
}
