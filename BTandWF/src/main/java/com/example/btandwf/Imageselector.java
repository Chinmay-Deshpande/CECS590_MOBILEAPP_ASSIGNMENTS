package com.example.btandwf;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.ByteArrayOutputStream;

/**
 * Created by Chinmay Deshpande on 4/17/2017.
 */

public class Imageselector extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageselector);
    }
    public void loadImagefromGallery(View view) {
// Create intent to Open Image applications like Gallery, Google Photos
Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
}



protected void onActivityResult(int requestCode, int resultCode, Intent data) {
super.onActivityResult(requestCode, resultCode, data);
try {
// When an Image is picked
if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
&& null != data){
// Get the Image from data

Uri selectedImage = data.getData();
String[] filePathColumn = {MediaStore.Images.Media.DATA};

// Get the cursor
Cursor cursor = getContentResolver().query(selectedImage,
filePathColumn, null, null, null);
// Move to first row
cursor.moveToFirst();

int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
imgDecodableString = cursor.getString(columnIndex);
cursor.close();
ImageView imgView = (ImageView) findViewById(R.id.imgView);
// Set the Image in ImageView after decoding the String
imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));



    final byte[] byteArray;
    Bitmap bmp = BitmapFactory.decodeFile(imgDecodableString);
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
    byteArray = stream.toByteArray();

    Button b1 = (Button) findViewById(R.id.button4);

    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                byte[] b1=byteArray;
            Intent i = new Intent(Imageselector.this, ReceivedImage.class);
            i.putExtra("picture", b1);
            startActivity(i);
        }
    });


}
else {
Toast.makeText(this, "You haven't picked Image",
 Toast.LENGTH_LONG).show();
}
} catch (Exception e) {
Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
}

}


    }
