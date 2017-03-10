package com.example.chinmaydeshpande.assignment_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chinmay Deshpande on 2/25/2017.
 */

public class SavedImage_Database extends SQLiteOpenHelper {

    public static final String KEY_ID="id";
    public static final String cp_COLUMN = "Caption_field";
    public static final String pf_COLUMN = "Path_field";
    public  static  final String db_name="MyImageDatabase";
    public static final String db_table="mytable";
    public static final String cl_COLUMN="Caption_label";
    public static final int DATABASE_VERSION = 3;


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + db_table;


    private static final String IMAGE_DATABASE_TABLE_CREATE="CREATE TABLE " +db_table
        + " (" + KEY_ID + " INTEGER PRIMARY KEY," + cp_COLUMN + " TEXT,"+pf_COLUMN+" TEXT,"+cl_COLUMN+" TEXT);";


    public SavedImage_Database( Context context) {
       super(context ,db_name , null, DATABASE_VERSION);
       // onCreate();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(IMAGE_DATABASE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
