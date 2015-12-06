package com.cs4634.group5.partypal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ShoppingList_Screen extends AppCompatActivity
{
    ArrayAdapter<String> adapter;

    ListView itemsList;

    private static boolean firstOpen = true;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_shopping_list__screen);

        // Will change from type String to Fragment.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, Home_Screen.shoppingList);

        itemsList = (ListView)findViewById(R.id.itemsList);

        itemsList.setAdapter(adapter);

        if (firstOpen = true) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Navigation");
            alertDialog.setMessage("Keep in mind that you can click the total at the bottom of the screen to begin navigating.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            firstOpen = false;
        }
    }
}
