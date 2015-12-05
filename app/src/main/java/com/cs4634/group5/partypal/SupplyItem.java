package com.cs4634.group5.partypal;


import android.net.Uri;

/**
 * Created by Philip on 12/5/2015.
 */
public class SupplyItem {

    private String name;
    private Uri imageURI;
    private float price;
    private Store store;

    public String getName() { return name; }
    public float getPrice() { return price; }
    public Store getStore() { return store; }
    public Uri getImageURI() { return imageURI; }
}
