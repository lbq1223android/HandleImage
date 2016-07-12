package com.oasis.handleimage.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liling on 2016/6/22.
 */
public class FuctionBean implements Parcelable {
    public String funcionName ;
    public Class className ;





    public  FuctionBean(String funcionName,Class className){
        this.funcionName = funcionName ;
        this.className = className ;
    }

    public String getFuncionName() {
        return funcionName;
    }

    public void setFuncionName(String funcionName) {
        this.funcionName = funcionName;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.funcionName);
        dest.writeSerializable(this.className);
    }

    public FuctionBean() {
    }

    protected FuctionBean(Parcel in) {
        this.funcionName = in.readString();
        this.className = (Class) in.readSerializable();
    }

    public static final Parcelable.Creator<FuctionBean> CREATOR = new Parcelable.Creator<FuctionBean>() {
        @Override
        public FuctionBean createFromParcel(Parcel source) {
            return new FuctionBean(source);
        }

        @Override
        public FuctionBean[] newArray(int size) {
            return new FuctionBean[size];
        }
    };
}
