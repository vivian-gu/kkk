package com.zlc.database;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class TestActivity extends Activity {
	private ContentResolver resolver;
	public final static String AUTHORITY = "com.zlc.provider.MyProvider";
	private  final static Uri CONTENT_URIS = Uri.parse("content://"+AUTHORITY+"/informations");
	private  final static Uri CONTENT_URI =  Uri.parse("content://"+AUTHORITY+"/informations/1");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		resolver = getContentResolver();
		//step 1 insert data
		ContentValues values = new ContentValues();
		values.put("info_name", "zhoulc");
		values.put("info_age", "24");
		insert(CONTENT_URIS,values);
		//查询
		Cursor cursor = query(CONTENT_URI,null,null,null,null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				int index[] = new int[]{
					cursor.getColumnIndex("info_id"),
					cursor.getColumnIndex("info_name"),
					cursor.getColumnIndex("info_age")
				};
				do {
					System.out.println(cursor.getString(index[1]));
					System.out.println(cursor.getString(index[2]));
				} while (cursor.moveToNext());
			}
		}
	}
//	该方法用于往ContentProvider添加数据。
	public Uri insert(Uri uri,ContentValues values){
		Uri dst = resolver.insert(uri, values);
		return dst;
	}
//	该方法用于从ContentProvider删除数据。
	public int delete(Uri uri,String where, String[] selectionArgs){
		int colums = resolver.delete(CONTENT_URI, where, selectionArgs);
		return colums;
	}
//	该方法用于更新ContentProvider中的数据。
	public int update(Uri uri,ContentValues values, String where, String[] selectionArgs){
		int colums = resolver.update(CONTENT_URI, values, where, selectionArgs);
		return colums;
	}
//	该方法用于从ContentProvider中获取数据。
	public Cursor query(Uri uri,String[] projection, String where, String[] selectionArgs, String sortOrder){
		Cursor cursor = resolver.query(CONTENT_URI, projection, where, selectionArgs, sortOrder);
		return cursor;
	}
		
}
