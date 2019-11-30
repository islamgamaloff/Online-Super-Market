package com.example.islamgsayed.onlinesupermarket.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islamgsayed.onlinesupermarket.Adapters.fruitsAdapter;
import com.example.islamgsayed.onlinesupermarket.Models.product;
import com.example.islamgsayed.onlinesupermarket.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Islam.G sayed on 6/22/2018.
 */

public class fruitsFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    fruitsAdapter fruitsadapter;
    ArrayList<product> mproducts;
    DatabaseReference databaseReference;

    public fruitsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tablets, container, false);
        recyclerView = view.findViewById(R.id.myRecycle1);
        mproducts = new ArrayList<>();
        fruitsadapter = new fruitsAdapter(mproducts, getActivity());
        recyclerView.setAdapter(fruitsadapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("fruits");

        databaseReference.child("kinds").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot drags : dataSnapshot.getChildren()) {
                    product products = drags.getValue(product.class);
                    drags.child("").getValue().toString();
                    mproducts.add(products);


                }
                fruitsadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return view;
    }
    }

