package com.cs4634.group5.partypal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class Category_Screen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_category__screen);

        Intent intent = getIntent();
        String screenTitle = (String) intent.getStringExtra("CATEGORY_NAME");

        populateList(screenTitle);
    }

    /**
     * Populate the list of items based on the category selected by the user.
     * @param listType
     */
    public static void populateList(String listType)
    {
        if (listType.equals("Category 1"))
        {
            // Pull from XML database of scraped items.
        }
    }
}
