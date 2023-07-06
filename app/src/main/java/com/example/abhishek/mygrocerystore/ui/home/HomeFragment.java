package com.example.abhishek.mygrocerystore.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abhishek.mygrocerystore.Models.HomeCategory;
import com.example.abhishek.mygrocerystore.Models.PopularModel;
import com.example.abhishek.mygrocerystore.Models.RecommendedModel;
import com.example.abhishek.mygrocerystore.R;
import com.example.abhishek.mygrocerystore.adapters.HomeAdapter;
import com.example.abhishek.mygrocerystore.adapters.PopularAdapter;
import com.example.abhishek.mygrocerystore.adapters.RecommenderAdapter;
import com.example.abhishek.mygrocerystore.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private ProgressBar mprogressbar;
    private ScrollView mscrollView;

    private RecyclerView mPopular_recyclerView , mCategory_recyclerView , mreccomended_recyclerView;
    private FirebaseFirestore db;


    // the below adapter is for popular list
    private List<PopularModel> mpopularModelList;
    private PopularAdapter mpopularAdapter;


    // the below adapter is for category list
    private List<HomeCategory> mhomeCategoryList;
    private HomeAdapter mhomeAdapter;

    // the below is the adapter for the recommended list

    private List<RecommendedModel> mrecommendedModelList;
    private RecommenderAdapter mrecommenderAdapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mprogressbar = root.findViewById(R.id.home_progress_bar);
        mscrollView = root.findViewById(R.id.home_srcoll_view);

        FirebaseApp.initializeApp(requireActivity());
        db = FirebaseFirestore.getInstance();

        // intializeing the popular recycler view
        mPopular_recyclerView = root.findViewById(R.id.popular_rec);

        // intializing the category recycler view
        mCategory_recyclerView = root.findViewById(R.id.explore_rec);

        // intializing the recommender recycler View
        mreccomended_recyclerView = root.findViewById(R.id.recommend_rec);

        mprogressbar.setVisibility(View.VISIBLE);
        mscrollView.setVisibility(View.GONE);

        // for popular items recycler view
        mPopular_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        mpopularModelList = new ArrayList<>();
        mpopularAdapter = new PopularAdapter(getContext(),mpopularModelList);

        mPopular_recyclerView.setAdapter(mpopularAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                mpopularModelList.add(document.toObject(PopularModel.class));
                                mpopularAdapter.notifyDataSetChanged();

                                mprogressbar.setVisibility(View.GONE);
                                mscrollView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(getContext(),"error: "+ task.getException(),Toast.LENGTH_LONG).show();
                        }
                    }
                });




        // for category item recycler view
        mhomeCategoryList = new ArrayList<>();
        mCategory_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        mhomeAdapter = new HomeAdapter(getContext() , mhomeCategoryList);
        Log.d(TAG, "onCreateView: till herer===========");
        mCategory_recyclerView.setAdapter(mhomeAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                mhomeCategoryList.add(document.toObject(HomeCategory.class));
                                mhomeAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(getContext(),"error: "+ task.getException(),Toast.LENGTH_LONG).show();
                        }
                    }
                });



        // for recommended recycler view
        // for popular items recycler view
        mreccomended_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        mrecommendedModelList = new ArrayList<>();
        mrecommenderAdapter = new RecommenderAdapter(getContext(),mrecommendedModelList);

        mreccomended_recyclerView.setAdapter(mrecommenderAdapter);

        db.collection("recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                mrecommendedModelList.add(document.toObject(RecommendedModel.class));
                                mrecommenderAdapter.notifyDataSetChanged();

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