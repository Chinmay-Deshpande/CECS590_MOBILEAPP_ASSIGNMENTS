package com.example.chinmaydeshpande.assignment_2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chinmay Deshpande on 2/28/2017.
 */

public class SelectQueryOnDB{


    SavedImage_Database db;
   List<String> list_of_images=new ArrayList<>();

    String column1=null,column2=null,column3=null;

    public List oncall(Context context)
    {
        db = new SavedImage_Database(context);
       // SQLiteDatabase db = b.getWritableDatabase();
        SQLiteDatabase db_handler = db.getReadableDatabase();


        Cursor cursor = db_handler.rawQuery("SELECT Caption_label,Path_field FROM mytable ", null);


        if (cursor.moveToFirst()) {
            do {
                //assing values


                column1 = cursor.getString(0);
                column2 = cursor.getString(1);

                list_of_images.add(column1);
                //column3 = c.getString(2);
                //Do something Here with values

            } while (cursor.moveToNext());
        }

      return  list_of_images;


    }
}
