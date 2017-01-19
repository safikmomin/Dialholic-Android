package com.example.safik.dialholic.apidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by safik on 1/24/2016.
 */

public class ProfileData {

    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProfileData() {
    }

    /**
     *
     * @param data
     */
    public ProfileData(Data data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }

}