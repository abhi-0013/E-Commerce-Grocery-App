package com.example.abhishek.mygrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.abhishek.mygrocerystore.Models.ViewAllProduct_Model;
import com.example.abhishek.mygrocerystore.R;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    private ImageView img_item ,plus , minus;
    Button addTo_cart;
    private TextView price , description , ratings , item_frequency , name;

    ViewAllProduct_Model viewAllProduct_model =null;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img_item = findViewById(R.id.img_detail);
        plus = findViewById(R.id.imageplus);
        minus = findViewById(R.id.imageMinus);
        price = findViewById(R.id.detail_price);
        description = findViewById(R.id.detail_description);
        ratings = findViewById(R.id.detail_ratings);
        item_frequency = findViewById(R.id.detail_item_frequency);
        name = findViewById(R.id.detail_name);

        addTo_cart = findViewById(R.id.btn_add_tocart);



        toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("details");

        if( object instanceof ViewAllProduct_Model){
            viewAllProduct_model = (ViewAllProduct_Model) object;
        }

        if(viewAllProduct_model != null){
            Glide.with(getApplicationContext()).load(viewAllProduct_model.getImg_url()).into(img_item);
            price.setText("Price: $"+viewAllProduct_model.getPrice());
            ratings.setText(viewAllProduct_model.getRatings());
            description.setText(viewAllProduct_model.getDescription());
        }

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(item_frequency.getText().toString());
                count++;
                item_frequency.setText(Integer.toString(count));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(item_frequency.getText().toString());
                if(count !=0){
                count--;
                }
                item_frequency.setText(Integer.toString(count));
            }
        });

        addTo_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Succesfully added in the cart",Toast.LENGTH_LONG).show();
            }
        });
    }
}