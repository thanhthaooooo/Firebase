package com.example.adapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learnfirebase.R;
import com.example.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Activity context;
    private List<Item> itemList;

    public ItemAdapter(Activity context, List<Item> itemList){
        super(context, R.layout.item, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater= context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item,null,true);
        Item item = itemList.get(position);
        ImageView img;
        TextView txtProductName, txtAuthour, txtUnitPrice;
        img= view.findViewById(R.id.imgProduct);
        txtProductName= view.findViewById(R.id.txtProductName);
        txtAuthour= view.findViewById(R.id.txtAuthor);
        txtUnitPrice= view.findViewById(R.id.txtUnitPrice);


        Picasso.get().load(itemList.get(position).getImg()).into(img);
        txtProductName.setText(itemList.get(position).getName());
        txtAuthour.setText(itemList.get(position).getAuthor());
        txtUnitPrice.setText(String.valueOf(itemList.get(position).getPrice()));



        return view;
    }
}
