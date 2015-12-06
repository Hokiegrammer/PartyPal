package com.cs4634.group5.partypal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class SupplyList_Screen extends AppCompatActivity
{
    TextView supplyListTitle;
    ListView supplyList;

    ArrayAdapter<SupplyItem> adapter;

    public static ArrayList<SupplyItem> tablewareItems = new ArrayList<SupplyItem>();
    public static ArrayList<SupplyItem> decorationItems = new ArrayList<SupplyItem>();
    public static ArrayList<SupplyItem> partyFavorItems = new ArrayList<SupplyItem>();
    public static ArrayList<SupplyItem> entertainmentItems = new ArrayList<SupplyItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_supply_list_screen);

        supplyListTitle = (TextView)findViewById(R.id.supplyListTitle);
        supplyList = (ListView)findViewById(R.id.supplyList);

        Intent intent = getIntent();
        String screenTitle = (String) intent.getStringExtra("CATEGORY_NAME");

        supplyListTitle.setText(screenTitle);

        pullFromXML();

        populateList(screenTitle);
    }


    /**
     * Populate the list of items based on the category selected by the user.
     * @param listType
     */
    public void populateList(String listType)
    {
        if (listType.equals("Tableware"))
        {
            // Pull from XML database of scraped items.
            adapter = new ArrayAdapter<SupplyItem>(this, android.R.layout.simple_list_item_single_choice, tablewareItems);
        }
        else if (listType.equals("Decorations"))
        {
            // Pull from XML database of scraped items.
            adapter = new ArrayAdapter<SupplyItem>(this, android.R.layout.simple_list_item_single_choice, decorationItems);
        }
        else if (listType.equals("Party Favors"))
        {
            // Pull from XML database of scraped items.
            adapter = new ArrayAdapter<SupplyItem>(this, android.R.layout.simple_list_item_single_choice, partyFavorItems);
        }
        else if (listType.equals("Entertainment"))
        {
            // Pull from XML database of scraped items.
            adapter = new ArrayAdapter<SupplyItem>(this, android.R.layout.simple_list_item_single_choice, entertainmentItems);
        }
    }


    /**
     * Will populate array lists of supply items from the XML file
     */
    public void pullFromXML()
    {
        SupplyItem newItem = new SupplyItem();
        String itemName = "";
        String itemURI = "";
        String itemPrice = "";
        Store itemStore = null;
        String itemCategory = "";

        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        try
        {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            InputStream is = getResources().openRawResource(getResources().
                    getIdentifier("raw/inventory", "raw", getPackageName()));

            parser.setInput(is, null);

            int eventType = parser.getEventType();

            // Loop through the file.
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                String name = parser.getName();

                if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("results"))
                {
                    newItem = new SupplyItem();
                    itemName = "";
                    itemURI = "";
                    itemPrice = "";
                    itemCategory = "";
                }
                else if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("price"))
                {
                    eventType = parser.next();

                    if (eventType == XmlPullParser.TEXT)
                    {
                        itemPrice = parser.getText();
                    }
                }
                else if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("name"))
                {
                    eventType = parser.next();

                    if (eventType == XmlPullParser.TEXT)
                    {
                        itemName = parser.getText();
                    }
                }
                else if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("pic"))
                {
                    eventType = parser.next();

                    if (eventType == XmlPullParser.TEXT)
                    {
                        itemURI = parser.getText();
                    }
                }
                else if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("category"))
                {
                    eventType = parser.next();

                    if (eventType == XmlPullParser.TEXT)
                    {
                        itemCategory = parser.getText();
                    }
                }
                else if (eventType == XmlPullParser.END_TAG && name.equalsIgnoreCase("results"))
                {
                    newItem.setPrice(itemPrice);
                    newItem.setName(itemName);
                    newItem.setUri(itemURI);
                    newItem.setCategory(itemCategory);

                    // ------------------------------------------------------
                    // Parse URI for Store
                    // ------------------------------------------------------

                    // Add supply item to correct category list.
                    if (newItem.getCategory().equals("tableware"))
                    {
                        tablewareItems.add(newItem);
                    }
                    else if (newItem.getCategory().equals("decoration"))
                    {
                        decorationItems.add(newItem);
                    }
                    else if (newItem.getCategory().equals("party favor"))
                    {
                        partyFavorItems.add(newItem);
                    }
                    else if (newItem.getCategory().equals("entertainment"))
                    {
                        entertainmentItems.add(newItem);
                    }
                }

                eventType = parser.next();
            }
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
