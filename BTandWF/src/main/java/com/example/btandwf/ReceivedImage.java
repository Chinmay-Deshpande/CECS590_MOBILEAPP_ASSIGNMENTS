package com.example.btandwf;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;



/**
 * Created by Chinmay Deshpande on 4/17/2017.
 */

public class ReceivedImage extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recivedimage);


        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");




        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.imageView2);
        image.setImageBitmap(bmp);

    }
}
