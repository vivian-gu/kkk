package com.zlc.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

public class MyProvider extends ContentProvider {
	private SQLiteDatabase sqDb;
	private DatabaseHelper helper;
	// 数据库名
	private final static String DATABASE_NAME = "zhoulc.db";
	// 版本
	private static final int DATABASE_VERSION = 1;
	// 表名
	private static final String TABLE_NAME = "Information";
	// 创建表的sql语句
	private final static String CREATE_TABLE = "Create table " + TABLE_NAME
			+ "( "+Information._ID+" integer primary key autoincrement," + Information.INFO_ID
			+ " TEXT," + Information.INFO_NAME + " TEXT," + Information.INFO_AGE
			+ " TEXT);";

	// Declaration Datababsehelper
	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
			onCreate(db);
		}

	}

	// UriMatcher add URI
	private static final UriMatcher MATCHER = new UriMatcher(
			UriMatcher.NO_MATCH);
	private final static int INFORMATIONS = 1;
	private final static int INFORMATION = 2;
	static {
		MATCHER.addURI(Information.AUTHORITY, "informations", INFORMATIONS);
		MATCHER.addURI(Information.AUTHORITY, "informations/#", INFORMATION);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		sqDb = helper.getWritableDatabase();
		int count = 0;
		switch (MATCHER.match(uri)) {
		case INFORMATIONS:
			count = sqDb.delete(TABLE_NAME, selection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
		return count;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (MATCHER.match(uri)) {
		case INFORMATIONS:
			return "vnd.android.cursor.dir/Information";
		case INFORMATION:
			return "vnd.android.cursor.item/Information";
		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		sqDb = helper.getWritableDatabase();
		Uri insertUri = null;
		long rowid = 0;
		switch (MATCHER.match(uri)) {
		case INFORMATIONS:
			rowid = sqDb.insert(TABLE_NAME, Information.INFO_ID, values);
			insertUri = ContentUris.withAppendedId(uri, rowid);
			Log.i("zhoulc", "insert record...values:" + values.toString());
			break;
		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
		return insertUri;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		helper = new DatabaseHelper(getContext());
		return helper == null ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		sqDb = helper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case INFORMATIONS:
			Cursor cursor = sqDb.query(TABLE_NAME, projection, selection,
					selectionArgs, null, null, sortOrder);
			return cursor;
		case INFORMATION://条件查询，
			long id = ContentUris.parseId(uri);
			String where = Information._ID + "=" + id; 
			 if (selection != null && !"".equals(selection))  
             {  
                 where = where + " and " + selection;  
             }  
             return sqDb.query(TABLE_NAME, projection, where, selectionArgs, null,  
                 null, sortOrder);  
		default:
			throw new IllegalArgumentException("unknow uri" + uri.toString());
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		sqDb = helper.getWritableDatabase();
		int count = 0;
		switch (MATCHER.match(uri)) {
		case INFORMATIONS:
			count = sqDb.update(TABLE_NAME, values, selection, selectionArgs);
			return count;
		default:
			throw new IllegalArgumentException("unknow uri" + uri.toString());
		}
		
	}

}
