package com.example.abhishek.mygrocerystore.ui.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abhishek.mygrocerystore.Models.PopularModel;
import com.example.abhishek.mygrocerystore.Models.nav_category_Models;
import com.example.abhishek.mygrocerystore.R;
import com.example.abhishek.mygrocerystore.adapters.nav_category_Adapter;
import com.example.abhishek.mygrocerystore.databinding.FragmentCategoryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class categoryFragment extends Fragment {
    private static final String TAG = "categoryFragment";
    private RecyclerView mRecyclerView;
    private nav_category_Adapter mAdapter;
    private List<nav_category_Models> mModelList;

    private FirebaseFirestore db;

    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FirebaseApp.initializeApp(getContext());
        db = FirebaseFirestore.getInstance();

        mRecyclerView = root.findViewById(R.id.category_recycler);

        mModelList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mAdapter = new nav_category_Adapter(getContext(),mModelList);

        mRecyclerView.setAdapter(mAdapter);

        db.collection("nav_category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                mModelList.add(document.toObject(nav_category_Models.class));
                                mAdapter.notifyDataSetChanged();

//                                mprogressbar.setVisibility(View.GONE);
//                                mscrollView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(getContext(),"error: "+ task.getException(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}