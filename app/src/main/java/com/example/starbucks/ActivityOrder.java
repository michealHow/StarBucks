package com.example.starbucks;

import android.accessibilityservice.AccessibilityService;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ActivityOrder extends AppCompatActivity {



    TextView tv;
    TextView tvSize;
    TextView tvDrink;
    ListView listView;
    String procesedOrder;
    static final String TAG ="Firebase data";
    //this is for pulling from fire base
    // ****************************************************************
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("New Order");

    List<Order> orderArrayList;
   // ArrayList<Order> arrayList ;
    ArrayAdapter<Order> arrayAdapter ;
    Order order;
    // ****************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        order = new Order();
        tv = findViewById(R.id.lblOrder);
        tv.setText("You Ordered: ");
        tvSize = findViewById(R.id.lblSize);
        tvSize.setText(getIntent().getStringExtra("Size"));
        tvDrink = findViewById(R.id.lblDrink);
        tvDrink.setText(getIntent().getStringExtra("Drink"));



        procesedOrder = "You, "+getIntent().getStringExtra("Username")+" Ordered :\n"
                +"Drink : "+getIntent().getStringExtra("Drink")+"\nSize : "+getIntent().getStringExtra("Size");


        //this is for pulling from fire base
        // ****************************************************************
        listView = findViewById(R.id.lView);
        orderArrayList = new ArrayList<>();
        //arrayAdapter = new ArrayAdapter<Order>(this,android.R.layout.simple_list_item_1,orderArrayList);
        //listView.setAdapter(arrayAdapter);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child : children)
                {
                    Toast.makeText(ActivityOrder.this, child.toString(), Toast.LENGTH_SHORT).show();

                    order = child.getValue(Order.class);

                    Toast.makeText(ActivityOrder.this, "in class" +order.toString(), Toast.LENGTH_SHORT).show();


                    orderArrayList.add(order);

                    Toast.makeText(ActivityOrder.this, order.toString(), Toast.LENGTH_SHORT).show();

                    Log.i(TAG, "Data in Firebase : "+ order);
                }

                arrayAdapter = new ArrayAdapter<>(ActivityOrder.this,android.R.layout.simple_list_item_1,orderArrayList);
                listView.setAdapter(arrayAdapter);


                //String value = dataSnapshot.getValue(Order.class).toString();
                //arrayList.add(value);
                //arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // ****************************************************************
    }

}
