package com.cs4634.group5.partypal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class FindSupplies_Screen extends AppCompatActivity
{
    ListView SupplyListView;

    ArrayAdapter<SupplyItem> adapter;
    ArrayList<SupplyItem> supplies = new ArrayList<SupplyItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_find_supplies__screen);

        //TODO fill supply list

        adapter = new FindSupplies_Adapter(this.getApplicationContext(), -1, supplies);

        SupplyListView = (ListView)findViewById(R.id.c);

        categoryList.setAdapter(adapter);


        // TODO Hard coded, will update later.
        categories.add("Category 1");
        categories.add("Category 2");
        categories.add("Category 3");


        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                String selectedCategory = parent.getItemAtPosition(pos).toString();


                Intent intent = new Intent(view.getContext(), Category_Screen.class);
                intent.putExtra("CATEGORY_NAME", selectedCategory);
                startActivityForResult(intent, 0);
            }
        });
    }
}
