package com.example.chinmaydeshpande.assignment_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chinmay Deshpande on 2/25/2017.
 */

public class AddPhoto_Activity extends AppCompatActivity {

    public static String ret_msg="rm";
    public static String ret_msg_1="rm1";
    public static String ret_msg_2="rm2";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    File image_path = null;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
   // private ImageView mImageView;
    String imageFileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addphoto);

        Button capture_image= (Button) findViewById(R.id.button);

        final EditText et= (EditText) findViewById(R.id.editText);
       // EditText et= (EditText) findViewById(R.id.editText);
        capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {


                    //EditText et= (EditText) findViewById(R.id.editText);


                    //String mCurrentPhotoPath;
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    imageFileName = "JPEG_" + timeStamp  + "_";
                    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                    try {
                        image_path = File.createTempFile(
                                imageFileName,  /* prefix */
                                ".jpg",         /* suffix */
                                storageDir      /* directory */
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Save a file: path for use with ACTION_VIEW intents
                    mCurrentPhotoPath = "file:"+image_path.getAbsolutePath();


                    if(image_path!=null) {
                        Uri photoURI = FileProvider.getUriForFile(AddPhoto_Activity.this,"com.example.android.fileprovider", image_path);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);



                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                    }
                }
            }
        });

        Button b2=(Button)findViewById(R.id.button2);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText edit_text=(EditText) findViewById(R.id.editText);
                String user_caption=edit_text.getText().toString();
                Context context= getApplicationContext();
                if(user_caption.equals("") || image_path.toString()==null)
                {
                    Toast.makeText(context, "Please Enter a Caption", Toast.LENGTH_LONG).show();
                    return;
                }
                //}

                Intent intent=new Intent();

               // String cap=et.getText().toString();
                intent.putExtra(ret_msg,imageFileName);
                intent.putExtra(ret_msg_1,image_path.toString());
                intent.putExtra(ret_msg_2,user_caption);

                setResult(Activity.RESULT_OK,intent);
                finish();
                                  }
                              });

    }



/*    public String EnterCaption()
    {
        EditText editText=(EditText) findViewById(R.id.editText);

        String check_user_caption=editText.getText().toString();

        if(check_user_caption!=null) {
            return  check_user_caption;
            //return;
        }
        else
        {

          return null;
        }

    }
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {


                ImageView im= (ImageView) findViewById(R.id.imageView2) ;
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
               im.setImageBitmap(mImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
