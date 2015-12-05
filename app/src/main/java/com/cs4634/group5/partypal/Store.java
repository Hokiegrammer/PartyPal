package com.cs4634.group5.partypal;

import android.net.Uri;

/**
 * Created by Felipe on 12/5/2015.
 */
public enum Store {
    WALMART(new String[] {
            "Radford Shopping Plaza Shopping Center, 7373 Peppers Ferry Blvd, Fairlawn, VA 24141",
            "2400 N Franklin St, Christiansburg, VA 24073"},
            R.mipmap.ic_walmart),
    TARGET (new String[] {
            "195 Conston Ave, Christiansburg, VA 24073"},
            R.mipmap.ic_target),
    DOLLAR_TREE(new String[] {
            "Radford Shopping Plaza Shopping Center, 7389 Peppers Ferry Blvd, Radford, VA 24141",
            "210 Marshall Dr, Christiansburg, VA 24073",
            "1341 S Main St, Blacksburg, VA 24060"},
            R.mipmap.ic_dollar_tree);

    private final String[] address;
    private final int storeImage;

    Store(String[] address, int storeImage) {

        this.address = address;
        this.storeImage = storeImage;
    }

    public String[] address() {
        return this.address;
    }
    public int image() { return this.storeImage; }
}
