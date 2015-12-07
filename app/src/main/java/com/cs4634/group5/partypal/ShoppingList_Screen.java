package com.cs4634.group5.partypal;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ShoppingList_Screen extends AppCompatActivity implements LocationListener {
    FindSupplies_Adapter adapter;

    ListView itemsList;

    private Activity thisActivity = this;

    private static boolean firstOpen = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_shopping_list__screen);

        // Will change from type String to Fragment.
        adapter = new FindSupplies_Adapter(this, -1, Home_Screen.shoppingList);

        itemsList = (ListView) findViewById(R.id.itemsList);

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

        TextView totalTextView = (TextView) findViewById(R.id.total_price);

        float priceTotal = getPriceTotal();
        String displayedTotal = String.valueOf(priceTotal);
        totalTextView.setText(displayedTotal);

        totalTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), Map_Screen.class);
                startActivityForResult(intent, 0);
            }
        });
    }


    /**
     * Will get and return the total price of the shopping list.
     */
    public float getPriceTotal()
    {
        float total = 0;

        String priceStr = "";
        String parsedPrice = "";
        float priceOfItem = 0;

        for (int i = 0; i < Home_Screen.shoppingList.size(); i++)
        {
            priceStr = Home_Screen.shoppingList.get(i).getPrice();
            parsedPrice = priceStr.substring(1, priceStr.length());

            priceOfItem = Float.parseFloat(parsedPrice);
            total = total + priceOfItem;
        }

        return total;
    }


    /**
     * Called when the location has changed.
     * <p>
     * <p> There are no restrictions on the use of the supplied Location object.
     *
     * @param location The new location, as a Location object.
     */
    @Override
    public void onLocationChanged(Location location) {

    }

    /**
     * Called when the provider status changes. This method is called when
     * a provider is unable to fetch a location or if the provider has recently
     * become available after a period of unavailability.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     * @param status   {@link LocationProvider#OUT_OF_SERVICE} if the
     *                 provider is out of service, and this is not expected to change in the
     *                 near future; {@link LocationProvider#TEMPORARILY_UNAVAILABLE} if
     *                 the provider is temporarily unavailable but is expected to be available
     *                 shortly; and {@link LocationProvider#AVAILABLE} if the
     *                 provider is currently available.
     * @param extras   an optional Bundle which will contain provider specific
     *                 status variables.
     *                 <p>
     *                 <p> A number of common key/value pairs for the extras Bundle are listed
     *                 below. Providers that use any of the keys on this list must
     *                 provide the corresponding value as described below.
     *                 <p>
     *                 <ul>
     *                 <li> satellites - the number of satellites used to derive the fix
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    /**
     * Called when the provider is enabled by the user.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     */
    @Override
    public void onProviderEnabled(String provider) {

    }

    /**
     * Called when the provider is disabled by the user. If requestLocationUpdates
     * is called on an already disabled provider, this method is called
     * immediately.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     */
    @Override
    public void onProviderDisabled(String provider) {

    }
}
