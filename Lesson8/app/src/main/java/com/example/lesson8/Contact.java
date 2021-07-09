package com.example.lesson8;

import java.io.Serializable;

public class Contact implements Serializable {

    private int mId;
    private String mName;
    private String mAddress;
    private String mPhone;

    public Contact() {
    }

    public Contact(String mName, String mAddress, String mPhone) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhone = mPhone;
    }

    public Contact(int mId, String mName, String mAddress, String mPhone) {
        this.mId = mId;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhone = mPhone;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
