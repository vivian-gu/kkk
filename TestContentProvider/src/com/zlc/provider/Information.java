package com.zlc.provider;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

public class Information implements Parcelable,BaseColumns{
	public final static String AUTHORITY = "com.zlc.provider.MyProvider";
	public final static Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/informations");
	//±í×Ö¶Î
	public final static String INFO_ID = "info_id";
	public final static String INFO_NAME = "info_name";
	public final static String INFO_AGE = "info_age";
	
	private String info_id,info_name,info_age;

	public Information(Parcel source){
		info_id = source.readString();
		info_name = source.readString();
		info_age = source.readString();
	}
	public String getInfo_id() {
		return info_id;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	public String getInfo_name() {
		return info_name;
	}

	public void setInfo_name(String info_name) {
		this.info_name = info_name;
	}

	public String getInfo_age() {
		return info_age;
	}

	public void setInfo_age(String info_age) {
		this.info_age = info_age;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(info_id);
		dest.writeString(info_name);
		dest.writeString(info_age);
	}
	public final static Parcelable.Creator<Information> CREATOR = new Parcelable.Creator<Information>() {

		@Override
		public Information createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Information(source);
		}

		@Override
		public Information[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Information[size];
		}
	};
}
