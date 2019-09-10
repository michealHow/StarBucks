package com.example.starbucks;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ActivityMenu extends AppCompatActivity
{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("New Order");

    Order orders;

    private Button getBtnOrder;
    private ImageView imageViewSmall;
    private ImageView imageViewTall;
    private ImageView imageViewGrande;
    private ImageView imageViewFreeze;
    private String coffee;
    private String size;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        orders = new Order();
        imageViewFreeze = findViewById(R.id.imgFreeze);
        imageViewSmall = findViewById(R.id.imageSmall);
        imageViewTall = findViewById(R.id.imageTall);
        imageViewGrande = findViewById(R.id.imageGrande);

         imageViewFreeze.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 coffee = "Freeze";
                 Toast.makeText(ActivityMenu.this, coffee, Toast.LENGTH_SHORT).show();
                 return true;
             }
         });
        imageViewSmall.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 size = "Short";
                 Toast.makeText(ActivityMenu.this, size, Toast.LENGTH_SHORT).show();
                 return true;
             }
         });
        imageViewTall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                size = "Tall";
                Toast.makeText(ActivityMenu.this, size, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        imageViewGrande.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                size = "Grande";
                Toast.makeText(ActivityMenu.this, size, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        username = getIntent().getStringExtra("Username");
        getBtnOrder = findViewById(R.id.btnOrder);
        getBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DisplayOrder(username ,size,coffee);
            }
        });
    }


    public void DisplayOrder(String u ,String s, String o)
    {
        String order = "You Ordered: "+s+" "+o;
        Toast.makeText(ActivityMenu.this, order, Toast.LENGTH_SHORT).show();
        orders =  new Order(u,o,s);
        orders.setProductName(o);
        orders.setProductSize(s);
        orders.setUserName(u);
        myRef.push().setValue(orders);
        //myRef.push().setValue(orders.getUserName()+","+orders.getProductName()+","+orders.getProductSize());
        Intent intent = new Intent(this, ActivityOrder.class);
        intent.putExtra("Size",s);
        intent.putExtra("Drink",o);
        startActivity(intent);





    }
}
