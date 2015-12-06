package com.cs4634.group5.partypal;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Philip on 12/5/2015.
 */
public class SupplyItem implements Serializable {

    private String name;
    private String imageURL;
    private Bitmap imageBmp;
    private String price;
    private Store store;
    private String category;

    public void setName(String n)
    {
        name = n;
    }

    public void setUrl(String u)
    {
        imageURL = u;
        if (!u.isEmpty()) {
            new DownloadImageTask().execute(u);
        }
    }

    public void setPrice(String p)
    {
        if (p.contains("-")) {
           price =  p.substring(0,p.indexOf("-"));
        } else {
            price = p;
        }
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
    public String getImageURL() { return imageURL; }
    public Bitmap getImageBmp() { return imageBmp; }
    public String getCategory() { return category; }

    private class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url = null;
            Bitmap bmp = null;
            try {
                url = new URL(urls[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            imageBmp = result;
        }
    }
}
