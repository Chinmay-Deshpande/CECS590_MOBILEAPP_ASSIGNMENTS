package com.example.chinmaydeshpande.assignment_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;


/**
 * Created by Chinmay Deshpande on 2/25/2017.
 */

public class ViewPhoto_Activity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewphoto);


        // Intent in= new Intent();
        String path = getIntent().getStringExtra("ex");
        String caption_text = getIntent().getStringExtra("ex1");

        TextView tt = (TextView) findViewById(R.id.textView8);
        tt.setText(caption_text);

        //    tt.setText(path);
        File imgFile = new File(path);
        if (imgFile.exists()) {


            ExifInterface exif = null;

            try {
                exif = new ExifInterface(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int orientataion = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);


            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            Bitmap b1 = rotateBitmap(myBitmap, orientataion);


             ImageView myImage = (ImageView) findViewById(R.id.imageView);
            myImage.setImageBitmap(b1);
        }

        //  Toast.makeText(v.getContext(),"no IMAGE IS PRESENT'",Toast.LENGTH_SHORT).show();
    }


    public static Bitmap rotateBitmap(Bitmap bitmap,int ori) {





        Matrix matrix = new Matrix();
        switch (ori) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        }
        catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
    }


        //data.getExtras().getString(AddPhoto_Activity.ret_msg);




