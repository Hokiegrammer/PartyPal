package com.cs4634.group5.partypal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class SelectCategory_Screen extends AppCompatActivity
{
    ListView categoryList;

    ArrayAdapter<String> adapter;
    ArrayList<String> categories = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_select_category__screen);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, categories);

        categoryList = (ListView)findViewById(R.id.categoryList);

        categoryList.setAdapter(adapter);

        categories.add("Tableware");
        categories.add("Decorations");
        categories.add("Party Favors");
        categories.add("Entertainment");

        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                String selectedCategory = parent.getItemAtPosition(pos).toString();


                Intent intent = new Intent(view.getContext(), SupplyList_Screen.class);
                intent.putExtra("CATEGORY_NAME", selectedCategory);
                startActivityForResult(intent, 0);
            }
        });
    }
}
