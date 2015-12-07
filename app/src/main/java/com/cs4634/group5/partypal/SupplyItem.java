package com.cs4634.group5.partypal;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Philip on 12/5/2015.
 */
public class SupplyItem implements Serializable, Comparable {

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

    public void setUrl(String u) {
        if (!u.isEmpty()) {
//            Log.d("MOOP", "SIZE OF THE HASH " + SupplyList_Screen.images.size());
            if (imageBmp == null) {
                new DownloadImageTask().execute(u);
            }
        }
        imageURL = u;
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
    public Bitmap getImageBmp() {
        return imageBmp; }
    public String getCategory() { return category; }

    /**
     * Compares this object to the specified object to determine their relative
     * order.
     *
     * @param another the object to compare to this instance.
     * @return a negative integer if this instance is less than {@code another};
     * a positive integer if this instance is greater than
     * {@code another}; 0 if this instance has the same order as
     * {@code another}.
     * @throws ClassCastException if {@code another} cannot be converted into something
     *                            comparable to {@code this} instance.
     */
    @Override
    public int compareTo(Object another) {
        if (another.getClass() == SupplyItem.class) {
            SupplyItem otherItem = (SupplyItem) another;

            String otherString = otherItem.getPrice();
            String thisString = this.getPrice();

            if (otherString.indexOf(".") == 2) {
                otherString = "$0" + otherString.substring(1);
//                Log.d("TEST", otherString);
            }
            if (thisString.indexOf(".") == 2) {
                thisString = "$0" + thisString.substring(1);
//                Log.d("TEST", otherString);
            }

            if (thisString.compareToIgnoreCase(otherString) == 0) {
                return this.getName().compareToIgnoreCase(otherItem.getName());
            } else {
                return thisString.compareToIgnoreCase(otherString);
            }
        } else {
            return 0;
        }
    }

    public boolean compareNames(SupplyItem otherItem) {
        return otherItem.getName().equals(this.getName());
    }

    private class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url = null;
            Bitmap bmp = null;
            if (SupplyList_Screen.images.containsKey(urls[0])) {
                Log.d("MOOP", "SCORE");
                FileInputStream inputStream = null;
                try {
                    inputStream = Home_Screen.context.openFileInput(String.valueOf(urls[0].hashCode()));
                    bmp = BitmapFactory.decodeFileDescriptor(inputStream.getFD());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
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
            }
            return bmp;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

        }

        protected void onPostExecute(Bitmap result) {

            imageBmp = result;
            if (!SupplyList_Screen.images.containsKey(imageURL)) {
                try {
                    FileOutputStream fos = Home_Screen.context.openFileOutput(String.valueOf(imageURL.hashCode()), Context.MODE_PRIVATE);
                    SupplyList_Screen.images.put(imageURL, 1);
                    result.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
