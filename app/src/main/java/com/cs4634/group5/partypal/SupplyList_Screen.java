package com.cs4634.group5.partypal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class SupplyList_Screen extends AppCompatActivity
{
    TextView supplyListTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_supply_list_screen);

        supplyListTitle = (TextView)findViewById(R.id.supplyListTitle);

        Intent intent = getIntent();
        String screenTitle = (String) intent.getStringExtra("CATEGORY_NAME");

        supplyListTitle.setText(screenTitle);

        populateList(screenTitle);
    }

    /**
     * Populate the list of items based on the category selected by the user.
     * @param listType
     */
    public static void populateList(String listType)
    {
        if (listType.equals(listType))
        {
            // Pull from XML database of scraped items.
        }
    }
}
