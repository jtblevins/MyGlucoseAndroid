package com.sugarcubes.myglucose.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper
{
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// DB Name:
	public static final String DB_NAME = "myglucose";

	// Table names:
	public static final String TABLE_LOGIN = "login";
	public static final String TABLE_GLUCOSE = "glucose_entries";
	public static final String TABLE_MEALS = "meal_entries";
	public static final String TABLE_MEAL_ITEMS = "meal_items";
	public static final String TABLE_EXERCISE = "exercise_entries";

	// Also add tables here:
	private String[] tables = {
			TABLE_LOGIN,
			TABLE_GLUCOSE,
			TABLE_MEALS,
			TABLE_MEAL_ITEMS,
			TABLE_EXERCISE
	};

	// ApplicationUser com.sugarcubes.myglucose.db table keys:
	public static final String KEY_ID = "_id";
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_USER_TYPE = "user_type";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_ADDRESS1 = "address1";
	public static final String KEY_ADDRESS2 = "address2";
	public static final String KEY_CITY = "city";
	public static final String KEY_STATE = "state";
	public static final String KEY_ZIP1 = "zip1";
	public static final String KEY_ZIP2 = "zip2";
	public static final String KEY_PHONE = "phone_number";
	public static final String KEY_DATE = "date";
	public static final String KEY_TIMESTAMP = "timestamp";
	// GlucoseEntry com.sugarcubes.myglucose.db table keys:
	public static final String KEY_MEASUREMENT = "measurement";
	public static final String KEY_BEFORE_AFTER = "before_after";
	public static final String KEY_WHICH_MEAL = "which_meal";
	// MealEntry com.sugarcubes.myglucose.db table keys:
	public static final String KEY_TOTAL_CARBS = "total_carbohydrates";
	// MealItem com.sugarcubes.myglucose.db table keys:
	public static final String KEY_NAME = "name";
	public static final String KEY_CARBS = "carbohydrates";
	public static final String KEY_SERVINGS = "servings";
	// ExerciseEntry com.sugarcubes.myglucose.db table keys:
	public static final String KEY_MINUTES_SPENT = "minutes_spent";


	public DB( Context context )
	{
		super( context, DB_NAME, null, DATABASE_VERSION );
	}

	public DB( Context context, SQLiteDatabase.CursorFactory factory )
	{
		super( context, DB_NAME, factory, DATABASE_VERSION );
	}

	public DB( Context context, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler )
	{
		super( context, DB_NAME, factory, DATABASE_VERSION, errorHandler );
	}

	@Override
	public void onCreate( SQLiteDatabase db )
	{
		// ADD NEW TABLES HERE

		// CREATE LOGIN TABLE
		String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_USER_ID + " TEXT,"
				+ KEY_USERNAME + " TEXT,"
				+ KEY_USER_TYPE + " INTEGER,"
				+ KEY_EMAIL + " TEXT UNIQUE,"
				+ KEY_PHONE + " TEXT,"
				+ KEY_ADDRESS1 + " TEXT,"
				+ KEY_ADDRESS2 + " TEXT,"
				+ KEY_CITY + " TEXT,"
				+ KEY_STATE + " TEXT,"
				+ KEY_ZIP1 + " INTEGER,"
				+ KEY_ZIP2 + " INTEGER,"
				+ KEY_TIMESTAMP + " INTEGER);";	// Retrieve as a *long* value

		// CREATE GLUCOSE TABLE
		String CREATE_GLUCOSE_ENTRIES_TABLE = "CREATE TABLE " + TABLE_GLUCOSE + "("
				+ KEY_ID + " TEXT PRIMARY KEY, "
				+ KEY_USER_ID + " TEXT, "
				+ KEY_MEASUREMENT + " INTEGER, "
				+ KEY_BEFORE_AFTER + " INTEGER, "
				+ KEY_WHICH_MEAL + " INTEGER, "
				+ KEY_DATE + " TEXT, "			// Parse and restrict readings to 3 per day
				+ KEY_TIMESTAMP + " INTEGER);";	// Retrieve as a *long* value
//		String CREATE_RECEIVED_PHOTOS_INDEX = "CREATE INDEX `received_photos_index` ON " +
//				DB.TABLE_GLUCOSE + "(" + DB.KEY_USER_ID + ");";

		// CREATE MEALS TABLE
		String CREATE_MEAL_ENTRIES_TABLE = "CREATE TABLE " + TABLE_MEALS + "("
				+ KEY_ID + " TEXT PRIMARY KEY, "
				+ KEY_USER_ID + " TEXT, "
				+ KEY_TOTAL_CARBS + " INTEGER, "
				+ KEY_DATE + " TEXT, "			// Parse and restrict readings to 3 per day
				+ KEY_TIMESTAMP + " INTEGER);";	// Retrieve as a *long* value
//		String CREATE_RECEIVED_PHOTOS_INDEX = "CREATE INDEX `received_photos_index` ON " +
//				DB.TABLE_GLUCOSE + "(" + DB.KEY_USER_ID + ");";

		// CREATE MEAL ITEMS TABLE
		String CREATE_MEAL_ITEMS_TABLE = "CREATE TABLE " + TABLE_MEAL_ITEMS + "("
				+ KEY_ID + " TEXT PRIMARY KEY, "
				+ KEY_USER_ID + " TEXT, "
				+ KEY_NAME + " TEXT, "
				+ KEY_CARBS + " INTEGER, "
				+ KEY_SERVINGS + " INTEGER, "
				+ KEY_DATE + " TEXT, "			// Parse and restrict readings to 3 per day
				+ KEY_TIMESTAMP + " INTEGER);";	// Retrieve as a *long* value
//		String CREATE_RECEIVED_PHOTOS_INDEX = "CREATE INDEX `received_photos_index` ON " +
//				DB.TABLE_GLUCOSE + "(" + DB.KEY_USER_ID + ");";

		// CREATE EXERCISE TABLE
		String CREATE_EXERCISE_ENTRIES_TABLE = "CREATE TABLE " + TABLE_EXERCISE + "("
				+ KEY_ID + " TEXT PRIMARY KEY, "
				+ KEY_USER_ID + " TEXT, "
				+ KEY_NAME + " TEXT, "
				+ KEY_MINUTES_SPENT + " INTEGER, "
				+ KEY_DATE + " TEXT, "			// Parse and restrict readings to 3 per day
				+ KEY_TIMESTAMP + " INTEGER);";	// Retrieve as a *long* value


		db.execSQL( CREATE_USERS_TABLE );
		db.execSQL( CREATE_GLUCOSE_ENTRIES_TABLE );
		db.execSQL( CREATE_MEAL_ENTRIES_TABLE );
		db.execSQL( CREATE_MEAL_ITEMS_TABLE );
		db.execSQL( CREATE_EXERCISE_ENTRIES_TABLE );
	}

	@Override
	public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
	{
		// Drop all tables if exist
		for( String table : tables )
		{
			db.execSQL( "DROP TABLE IF EXISTS " + table );
		}

		// Create tables again
		onCreate( db );

	} // onUpgrade

} // class