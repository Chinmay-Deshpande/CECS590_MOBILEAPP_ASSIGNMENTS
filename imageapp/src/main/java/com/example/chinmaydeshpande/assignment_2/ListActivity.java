package com.example.chinmaydeshpande.assignment_2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final int INTENT_MSG = 1;
    public String STR_CALL="ex",STR_CALL1="ex1";
    public String image_path = "",image_caption="";
    List<String> list_of_images= new ArrayList();

    ListView listView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

         listView=(ListView) findViewById(R.id.listview);

        list_of_images=new SelectQueryOnDB().oncall(getApplicationContext());

        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_of_images));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {

                TextView clickedview = (TextView) view;

                String selected_image=clickedview.getText().toString();

                SavedImage_Database sd= new SavedImage_Database(getApplicationContext());

                SQLiteDatabase database_handler = sd.getReadableDatabase();

                String query = "SELECT Path_field,Caption_label FROM mytable WHERE Caption_label ='"+selected_image+"'";


                Cursor result = database_handler.rawQuery( query, null );

               // Your default if none is found

                if(result.moveToFirst())
                {
                    image_path = result.getString(result.getColumnIndex("Path_field"));
                    image_caption = result.getString(result.getColumnIndex("Caption_label"));


                }


                Intent intent= new Intent(ListActivity.this, ViewPhoto_Activity.class);
                intent.putExtra(STR_CALL,image_path);
                intent.putExtra(STR_CALL1,image_caption);
                startActivity(intent);
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListActivity.this, AddPhoto_Activity.class);
                startActivityForResult(intent, INTENT_MSG);
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

      //  setContentView(R.layout.activity_list);


        list_of_images=new SelectQueryOnDB().oncall(getApplicationContext());


        listView.setAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_of_images));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {

                TextView clickedview = (TextView) view;

                String selected_image=clickedview.getText().toString();

                SavedImage_Database sd= new SavedImage_Database(getApplicationContext());

                SQLiteDatabase db2 = sd.getReadableDatabase();

                String query = "SELECT Path_field,Caption_label FROM mytable WHERE Caption_label ='" + selected_image+"'";

                Cursor result = db2.rawQuery( query, null );

                String returnString_path = "",returnstring_caption="";//,returnString1="";; // Your default if none is found

                if(result.moveToFirst())
                {
                    returnString_path = result.getString(result.getColumnIndex("Path_field"));
                    returnstring_caption=result.getString(result.getColumnIndex("Caption_label"));
                }


                Intent intent= new Intent(ListActivity.this, ViewPhoto_Activity.class);

                intent.putExtra(STR_CALL,returnString_path);
               intent.putExtra(STR_CALL1,returnstring_caption);

                startActivity(intent);
            }
        });
    }


  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

      if (requestCode == 1) {

          if(resultCode== Activity.RESULT_OK) {
              switch (requestCode) {


                  case INTENT_MSG:

                      String image_name = data.getExtras().getString(AddPhoto_Activity.ret_msg);
                      String image_path = data.getExtras().getString(AddPhoto_Activity.ret_msg_1);
                      String image_caption = data.getExtras().getString(AddPhoto_Activity.ret_msg_2);
                      //data.getExtras().getString(AddPhoto_Activity.ret_msg_1);


                      SavedImage_Database b = new SavedImage_Database(getApplicationContext());

                      SQLiteDatabase db = b.getWritableDatabase();


//                b.onCreate(db);


                      ContentValues new_image_Values = new ContentValues();


                      new_image_Values.put(SavedImage_Database.cp_COLUMN, image_name);

                      new_image_Values.put(SavedImage_Database.pf_COLUMN, image_path);

                      new_image_Values.put(SavedImage_Database.cl_COLUMN, image_caption);

                      db.insert(SavedImage_Database.db_table, null, new_image_Values);
                      break;
              }
          }
              else if (resultCode == Activity.RESULT_CANCELED) {
              }
          onStart();
          }

      }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater().inflate(R.menu.menu_list, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Uri packageURI = Uri.parse("package:com.example.chinmaydeshpande.assignment_2");
            Intent uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageURI);
            startActivity(uninstallIntent);



            //Intent intent = new Intent(Intent.ACTION_DELETE);
            // startActivity(intent);
            finish();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
