package com.cs4634.group5.partypal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class Home_Screen extends AppCompatActivity
{
    Button inviteBtn;
    Button findSuppliesBtn;
    Button shoppingListBtn;
    Button venueBtn;

    // Will change from type String to Fragment.
    public static ArrayList<String> shoppingList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home__screen);

        inviteBtn = (Button)findViewById(R.id.inviteBtn);
        findSuppliesBtn = (Button)findViewById(R.id.findSuppliesBtn);
        shoppingListBtn = (Button)findViewById(R.id.shoppingListBtn);
        venueBtn = (Button)findViewById(R.id.venueBtn);

        inviteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), InvitationView.class);
                startActivityForResult(intent, 0);
            }
        });

        findSuppliesBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), SelectCategory_Screen.class);
                startActivityForResult(intent, 0);
            }
        });

        shoppingListBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), ShoppingList_Screen.class);
                startActivityForResult(intent, 0);
            }
        });

        venueBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Venue activity.
            }
        });
    }
}
