package com.cs4634.group5.partypal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public class Home_Screen extends AppCompatActivity
{
    Button inviteBtn;
    Button findSuppliesBtn;
    Button shoppingListBtn;

    // Will change from type supplyItem to Fragment.
    public static ArrayList<SupplyItem> shoppingList = new ArrayList<SupplyItem>();

    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        setContentView(R.layout.content_home__screen);

        inviteBtn = (Button)findViewById(R.id.inviteBtn);
        findSuppliesBtn = (Button)findViewById(R.id.findSuppliesBtn);
        shoppingListBtn = (Button)findViewById(R.id.shoppingListBtn);

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
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

}
