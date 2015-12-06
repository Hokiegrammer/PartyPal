package com.cs4634.group5.partypal;


import android.net.Uri;

/**
 * Created by Philip on 12/5/2015.
 */
public class SupplyItem {

    private String name;
    private String imageURI;
    private String price;
    private Store store;
    private String category;

    public void setName(String n)
    {
        name = n;
    }

    public void setUri(String u)
    {
        imageURI = u;
    }

    public void setPrice(String p)
    {
        price = p;
    }

    public void setStore(Store s)
    {
        store = s;
    }

    public void setCategory(String c)
    {
        category = c;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public Store getStore() { return store; }
    public String getImageURI() { return imageURI; }
    public String getCategory() { return category; }
}
