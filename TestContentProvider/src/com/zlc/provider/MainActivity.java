package com.zlc.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Context mContext = MainActivity.this;
		Uri myUri = Information.CONTENT_URI;
		ContentValues values = new ContentValues();
		values.put(Information.INFO_NAME, "zhoulc");
		values.put(Information.INFO_AGE, "99");
		getContentResolver().insert(myUri, values);
		
	}
}


//获取分辨率

//DisplayMetrics dm = new DisplayMetrics();
//getWindowManager().getDefaultDisplay().getMetrics(dm);
//int mWidth = dm.widthPixels;
//int mHeight = dm.heightPixels;
//Log.i("TEST", "Display Metrics width:" + mWidth + " mHeight:" + mHeight);
