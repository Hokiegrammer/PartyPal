package com.cs4634.group5.partypal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ShoppingList_Screen extends AppCompatActivity
{
    ArrayAdapter<String> adapter;

    ListView itemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_shopping_list__screen);

        // Will change from type String to Fragment.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, Home_Screen.shoppingList);

        itemsList = (ListView)findViewById(R.id.itemsList);

        itemsList.setAdapter(adapter);
    }
}
