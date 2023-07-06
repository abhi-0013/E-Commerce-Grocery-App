package com.example.abhishek.mygrocerystore.activities;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.abhishek.mygrocerystore.Models.ViewAllProduct_Model;
import com.example.abhishek.mygrocerystore.R;
import com.example.abhishek.mygrocerystore.adapters.ViewAllAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    private static final String TAG = "ViewAllActivity";

    FirebaseFirestore db;
    RecyclerView mrec;
    List<ViewAllProduct_Model> mProductList;
    ViewAllAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar toolbar = findViewById(R.id.toolbar_viewAll);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        mrec = findViewById(R.id.View_all_act_rec);
        mProductList = new ArrayList<>();
        mrec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        madapter = new ViewAllAdapter(this,mProductList);
        mrec.setAdapter(madapter);

        String type = getIntent().getStringExtra("type");
        type.toLowerCase();
//        Log.d(TAG, "onCreate: type: "+ type);
//        String search;
//        switch(type){
//            case "fruits":
//            case "vegetables":
//            case "beverages":
//            case "egg":
//                search = type;
//                break;
//            default: throw new InvalidParameterException("Error no valid type for view product");
//        }
//        Log.d(TAG, "onCreate: search : "+search);

        if(type != null){
            db.collection("ViewAllProducts").whereEqualTo("type",type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                            ViewAllProduct_Model viewAllProduct_model = documentSnapshot.toObject(ViewAllProduct_Model.class);
                            mProductList.add(viewAllProduct_model);
//                            Log.d(TAG, "onComplete: "+viewAllProduct_model.toString());
                            madapter.notifyDataSetChanged();
                        }
                    }else{
                        Log.d(TAG, "onComplete: "+task.getException());
                        Toast.makeText(ViewAllActivity.this,"ERROORR ======"+task.getException(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
}