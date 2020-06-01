package com.lambda.spark;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    private DatabaseReference mrootDatabase, mdemoDatabase, mleafDatabase;
    ArrayList<String> myProducts;
    ListView myList;
    ArrayAdapter<String> productAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_shopping);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent= new Intent(ShoppingList.this, ShopNav.class);
                startActivity(mapIntent);
            }
        });

        myList = findViewById(R.id.myShoppingList);
        mrootDatabase = FirebaseDatabase.getInstance().getReference();
        mdemoDatabase = mrootDatabase.child("demo3");
        myProducts = new ArrayList<String>();
        Log.e("check1","1");
        //myProducts.add("Rahul");
        //ArrayAdapter<String>()
        productAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myProducts);
        myList.setAdapter(productAdapter);
        mdemoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (int i = 0; i < 8; i++){
                        if(snapshot.child(String.valueOf(i)).exists()){
                            myProducts.add((String) snapshot.child(String.valueOf(i)).child("title").getValue());
                            Log.e("check val",myProducts.get(0));
                            Log.e("list test", (String) snapshot.child(String.valueOf(i)).child("title").getValue());
                            productAdapter.notifyDataSetChanged();
                        }
                    }
//                    Log.e("list test", (String) snapshot.child(String.valueOf()).child("title").getValue());
                }
                //Log.e("check val",myProducts.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("cancelled","kyun");
            }
        });
        //Log.e("check val",myProducts.get(0));



    }
}
