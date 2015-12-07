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
import android.net.Uri;
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
//                LatLng target = new LatLng(37.156946, -80.422643);
//                LatLng dollarTree = new LatLng(37.216086, -80.400445);
//                LatLng walmart = new LatLng(37.151522, -80.584975);
//                Polyline line1 = mMap.addPolyline(myLocation, target);
//                Polyline line2 = mMap.addPolyline(myLocation, dollarTree);
//                Polyline line3 = mMap.addPolyline(myLocation, walmart);


                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=37.156946,-80.422643&daddr=37.156946,-80.422643"));
                startActivity(intent);
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
     * On Status Change.
     * @param provider
     * @param status
     * @param extras
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
