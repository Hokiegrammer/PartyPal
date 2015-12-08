package com.cs4634.group5.partypal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Philip on 12/5/2015.
 */
public class FindSupplies_Adapter extends ArrayAdapter<SupplyItem> {

    private final Context context;
    private List<SupplyItem> suppliesList;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public FindSupplies_Adapter(Context context, int resource, List<SupplyItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.suppliesList = objects;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_item, parent, false);

        // set name
        TextView nameView = (TextView) rowView.findViewById(R.id.itemName);
        nameView.setText(suppliesList.get(position).getName());

        // set price
        TextView priceView = (TextView) rowView.findViewById(R.id.itemPrice);
        priceView.setText(suppliesList.get(position).getPrice());

        // set item image
        final ImageView itemImageView = (ImageView) rowView.findViewById(R.id.itemImage);
        if (!suppliesList.get(position).getImageURL().isEmpty()) {
            Bitmap bmp = null;
            if (SupplyList_Screen.images.containsKey(suppliesList.get(position).getImageURL())) {

                FileInputStream inputStream = null;
                try {
                    inputStream = Home_Screen.context.openFileInput(String.valueOf(suppliesList.get(position).getImageURL().hashCode()));
                    bmp = BitmapFactory.decodeFileDescriptor(inputStream.getFD());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bmp == null) {
                itemImageView.setImageBitmap(suppliesList.get(position).getImageBmp());
            } else {
                itemImageView.setImageBitmap(bmp);
            }
            itemImageView.setBackgroundColor(Color.TRANSPARENT);
            itemImageView.postInvalidateDelayed(400);

        }

        itemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // set store image
        ImageView storeImageView = (ImageView) rowView.findViewById(R.id.storeImage);
        storeImageView.setImageResource(suppliesList.get(position).getStore().image());

        // set checkbox event
        CheckBox itemCheckBox = (CheckBox) rowView.findViewById(R.id.itemCheckBox);
        itemCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {

                if (isChecked)
                {
                    Home_Screen.shoppingList.add(suppliesList.get(position));
                }
                else
                {
                    Home_Screen.shoppingList.remove(suppliesList.get(position));
                }
            }
        });

        return rowView;
    }

    public void clear() {
        suppliesList = null;
    }

}
