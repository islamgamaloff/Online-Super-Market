package com.example.islamgsayed.onlinesupermarket;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button button;
    ViewFlipper viewFlipper;
    CardView  fruits,vegetables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        button=findViewById(R.id.logout);

        fruits = findViewById(R.id.Fruits_card);
        vegetables = findViewById(R.id.Vegetables_Card);
        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FruitsActivity.class));
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this , LoginActivity.class));

        }



        viewFlipper = findViewById(R.id.imgflipper);

        int image[]={R.drawable.splashscreen,R.drawable.flipper,R.drawable.backk};

//        for(int i=0;i<image.length;i++){imageeee(image[i]);}

        for(int images:image)
        {
            imageeee(images);
        }


    }
    public void imageeee(int image)
    {
        ImageView imageView =new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2500);
        viewFlipper.setAutoStart(true);
       viewFlipper.setInAnimation(this,R.anim.fade);
//        viewFlipper.setOutAnimation(this,R.anim.right);
    }
//        mAuth=FirebaseAuth.getInstance();
//        String userID=mAuth.getUid();
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
//
//        databaseReference.child("password").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue().toString();
//                Log.w("HomePage", value);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });
//    }

    }
