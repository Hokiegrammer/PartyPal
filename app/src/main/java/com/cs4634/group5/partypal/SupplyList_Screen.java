package com.cs4634.group5.partypal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class SupplyList_Screen extends AppCompatActivity {
    String TAG = "supplyList_Screen";
    TextView supplyListTitle;
    ListView supplyList;

    Button toMyListBtn;

    ArrayAdapter<SupplyItem> adapter;

    public static ArrayList<SupplyItem> tablewareItems = new ArrayList<SupplyItem>();
    public static ArrayList<SupplyItem> decorationItems = new ArrayList<SupplyItem>();
    public static ArrayList<SupplyItem> partyFavorItems = new ArrayList<SupplyItem>();
    public static ArrayList<SupplyItem> entertainmentItems = new ArrayList<SupplyItem>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_supply_list_screen);

        supplyListTitle = (TextView) findViewById(R.id.supplyListTitle);
        supplyList = (ListView) findViewById(R.id.supplyList);

        Intent intent = getIntent();
        String screenTitle = (String) intent.getStringExtra("CATEGORY_NAME");

        supplyListTitle.setText(screenTitle);

        toMyListBtn = (Button)findViewById(R.id.toMyListBtn);

        toMyListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShoppingList_Screen.class);
                startActivityForResult(intent, 0);
            }
        });

        try {

            // Retrieve the list from internal storage
            tablewareItems = (ArrayList<SupplyItem>) InternalStorage.readObject(this, "tablewareitems");

            // Retrieve the list from internal storage
            decorationItems = (ArrayList<SupplyItem>) InternalStorage.readObject(this, "decorationItems");

            // Retrieve the list from internal storage
            partyFavorItems = (ArrayList<SupplyItem>) InternalStorage.readObject(this, "partyFavorItems");

            // Retrieve the list from internal storage
            entertainmentItems = (ArrayList<SupplyItem>) InternalStorage.readObject(this, "entertainmentItems");

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            pullFromXML();
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
            pullFromXML();
        }

        populateList(screenTitle);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /**
     * Populate the list of items based on the category selected by the user.
     *
     * @param listType
     */
    public void populateList(String listType) {
        if (listType.equals("Tableware")) {
            // Pull from XML database of scraped items.
            adapter = new FindSupplies_Adapter(getApplicationContext(), -1, tablewareItems);
            assert (!adapter.isEmpty());
        } else if (listType.equals("Decorations")) {
            // Pull from XML database of scraped items.
            adapter = new FindSupplies_Adapter(getApplicationContext(), -1, decorationItems);
            assert (!adapter.isEmpty());
        } else if (listType.equals("Party Favors")) {
            // Pull from XML database of scraped items.
            adapter = new FindSupplies_Adapter(getApplicationContext(), -1, partyFavorItems);
            assert (!adapter.isEmpty());
        } else if (listType.equals("Entertainment")) {
            // Pull from XML database of scraped items.
            adapter = new FindSupplies_Adapter(getApplicationContext(), -1, entertainmentItems);
            assert (!adapter.isEmpty());
        }

        supplyList.setAdapter(adapter);
    }


    /**
     * Will populate array lists of supply items from the XML file
     */
    public void pullFromXML() {
        SupplyItem newItem = new SupplyItem();
        String itemName = "";
        String itemURI = "";
        String itemPrice = "";
        Store itemStore = null;
        String itemCategory = "";

        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            InputStream is = getResources().openRawResource(getResources().
                    getIdentifier("raw/inventory", "raw", getPackageName()));

            parser.setInput(is, null);

            int eventType = parser.getEventType();

            // Loop through the file.
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();

                if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("pageUrl")) {
                    eventType = parser.next();

                    if (eventType == XmlPullParser.TEXT) {
                        if (parser.getText().contains("target")) {
                            itemStore = Store.TARGET;
                        } else if (parser.getText().contains("dollartree")) {
                            itemStore = Store.DOLLAR_TREE;
                        } else {
                            itemStore = Store.WALMART;
                        }
                    }
                }

                if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("results")) {
                    newItem = new SupplyItem();
                    itemName = "";
                    itemURI = "";
                    itemPrice = "";
                    itemCategory = "";
                } else if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("price")) {
                    eventType = parser.next();

                    if (eventType == XmlPullParser.TEXT) {
                        itemPrice = parser.getText();
                    }
                } else if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("name")) {
                    eventType = parser.next();

                    if (eventType == XmlPullParser.TEXT) {
                        itemName = parser.getText();
                    }
                } else if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("pic")) {
                    eventType = parser.next();

                    if (eventType == XmlPullParser.TEXT) {
                        itemURI = parser.getText();
                    }
                } else if (eventType == XmlPullParser.START_TAG && name.equalsIgnoreCase("category")) {
                    eventType = parser.next();

                    if (eventType == XmlPullParser.TEXT) {
                        itemCategory = parser.getText();
                    }
                } else if (eventType == XmlPullParser.END_TAG && name.equalsIgnoreCase("results")) {
                    newItem.setPrice(itemPrice);
                    newItem.setName(itemName);
                    newItem.setUrl(itemURI);
                    newItem.setCategory(itemCategory);
                    newItem.setStore(itemStore);

                    // ------------------------------------------------------
                    // Parse URI for Store
                    // ------------------------------------------------------

                    // Add supply item to correct category list.
                    if (newItem.getCategory().equals("tableware")) {
                        tablewareItems.add(newItem);
                    } else if (newItem.getCategory().equals("decoration")) {
                        decorationItems.add(newItem);
                    } else if (newItem.getCategory().equals("party favor")) {
                        partyFavorItems.add(newItem);
                    } else if (newItem.getCategory().equals("entertainment")) {
                        entertainmentItems.add(newItem);
                    }
                }

                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(this, "tablewareItems", tablewareItems);

            // Save the list of entries to internal storage
            InternalStorage.writeObject(this, "decorationItems", decorationItems);

            // Save the list of entries to internal storage
            InternalStorage.writeObject(this, "partyFavorItems", partyFavorItems);

            // Save the list of entries to internal storage
            InternalStorage.writeObject(this, "entertainmentItems", entertainmentItems);

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SupplyList_Screen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs4634.group5.partypal/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SupplyList_Screen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs4634.group5.partypal/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
