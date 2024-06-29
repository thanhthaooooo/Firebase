package com.example.learnfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.adapter.ItemAdapter;
import com.example.model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class ListItemActivity extends AppCompatActivity {
    ListView lvItem;
    private List<Item> itemList;
    private ItemAdapter itemAdapter;
    DatabaseReference databaseReference;
    int val = 0;
    ConstraintLayout item;
    ImageView imageView;
    TextView txtProductName,txtAuthor,txtUnitPrice;


    String TAG = "FIREBASE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();

        databaseReference = FirebaseDatabase.getInstance().getReference("items");
        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(ListItemActivity.this,itemList);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) lvItem.getItemAtPosition(position);
                lvItem.setVisibility(View.GONE);
                val =1;
                String img = item.getImg().toString();
                String name = item.getName().toString();
                String author = item.getAuthor().toString();
                String price = item.getPrice().toString();

                txtProductName.setText(name);
                txtAuthor.setText(author);
                txtUnitPrice.setText(price);
                Picasso.get().load(img).into(imageView);

            }
        });

    }



    private void addViews() {
        lvItem = findViewById(R.id.lvItem);
        itemList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("items");
        itemAdapter = new ItemAdapter(ListItemActivity.this, itemList);


    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    Item item = dataSnapshot1.getValue(Item.class);
                    itemList.add(item);
                }
                lvItem.setAdapter(itemAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}