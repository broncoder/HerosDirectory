package com.example.dota2heros;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable{
    private String name;
    private String filename;

    public Hero(Parcel source){
    	readFromParcel(source);
    }
    public Hero(String name, String filename) {
        this.name = name;
        this.filename = filename;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

		@Override
		public Object createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Hero(source);
		}

		@Override
		public Object[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Hero[size];
		}
    };
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
	}
	public void readFromParcel(Parcel source){
		name = source.readString();
	}
}