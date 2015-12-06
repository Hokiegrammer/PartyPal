package com.cs4634.group5.partypal;

import android.net.Uri;

/**
 * Created by Philip on 12/5/2015.
 */
public enum Store {
    WALMART(
            "Walmart",
            R.mipmap.ic_walmart),
    TARGET (
            "Target",
            R.mipmap.ic_target),
    DOLLAR_TREE(
            "Dollar+Tree",
            R.mipmap.ic_dollar_tree);

    private final String address;
    private final int storeImage;

    Store(String address, int storeImage) {

        this.address = address;
        this.storeImage = storeImage;
    }

    public String address() {
        return this.address;
    }
    public int image() { return this.storeImage; }
}
