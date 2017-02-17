package edu.csulb.android.tacopronto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

//import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    String order = "I want a ",phno="+15628507043";
    int total_price=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button);
       // b1.setBackgroundColor(Color.parseColor("FF81ECD6"));
        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                checkforsize();
                checktortilla();
                fillings();
                beverages();
                placeorder();

            }
        });

    }

    public void beverages()
    {
        order=order+"\nBEVERAGES are ";
        CheckBox soda=(CheckBox) findViewById(R.id.checkBox15);
        CheckBox cerveza=(CheckBox) findViewById(R.id.checkBox12);
        CheckBox margarita=(CheckBox) findViewById(R.id.checkBox13);
        CheckBox tequila=(CheckBox) findViewById(R.id.checkBox14);

        if(soda.isChecked())
        {

            order=order+"Soda ";
            total_price=total_price+2;
        }
        if(cerveza.isChecked())
        {
            order=order+"Cerveza ";
            total_price=total_price+3;
        }
        if(margarita.isChecked())
        {
            order=order+"Margarita ";
            total_price=total_price+4;
        }
        if(tequila.isChecked())
        {
            order=order+"Tequila ";
            total_price=total_price+5;
        }
        order=order+".";

    }
    public void fillings()
    {
        order=order+"\nFILLINGS are ";
        CheckBox beef=(CheckBox) findViewById(R.id.checkBox);
        CheckBox chicken=(CheckBox) findViewById(R.id.checkBox2);
        CheckBox whitefish=(CheckBox) findViewById(R.id.checkBox3);
        CheckBox cheese=(CheckBox) findViewById(R.id.checkBox4);
        CheckBox seafood=(CheckBox) findViewById(R.id.checkBox5);
        CheckBox rice=(CheckBox) findViewById(R.id.checkBox6);
        CheckBox beans=(CheckBox) findViewById(R.id.checkBox7);
        CheckBox pico_de_gallo=(CheckBox) findViewById(R.id.checkBox8);
        CheckBox gaucamolo=(CheckBox) findViewById(R.id.checkBox9);
        CheckBox LBT=(CheckBox) findViewById(R.id.checkBox10);

        if(beef.isChecked())
        {
            order=order+"Beef ";
            total_price=total_price+1;
        }
        if(chicken.isChecked())
        {
            order=order+"Chicken ";
            total_price=total_price+2;
        }
        if(whitefish.isChecked())
        {
            order=order+"Whitefish ";
            total_price=total_price+3;
        }
        if(cheese.isChecked())
        {
            order=order+"Cheese ";
            total_price=total_price+4;
        }
        if(seafood.isChecked())
        {
            order=order+"Seafood ";
            total_price=total_price+5;
        }
        if(rice.isChecked())
        {
            order=order+"Rice ";
            total_price=total_price+1;


        }
        if(beans.isChecked())
        {
            order=order+"Beans ";
            total_price=total_price+2;
        }
        if(pico_de_gallo.isChecked())
        {
            total_price=total_price+3;
            order=order+"Pico_de_gallo ";
        }
        if(gaucamolo.isChecked())
        {
            order=order+"Gaucamolo ";
            total_price=total_price+4;
        }
        if(LBT.isChecked())
        {
            order=order+"LBT ";
            total_price=total_price+5;
        }
        order=order+".";

    }



    public void checktortilla()
    {
        RadioButton r3=(RadioButton) findViewById(R.id.radioButton3);
        RadioButton r4=(RadioButton) findViewById(R.id.radioButton4);



        if(r3.isChecked())
        {
            order=order+"CORN ";
            total_price=total_price+3;

        }

        else if(r4.isChecked())
        {
            order=order+"FLOUR";
            total_price=total_price+6;
        }
        else if(r3.isChecked()==false && r4.isChecked()==false)
        {
            Toast.makeText(this, "Please select a tortilla",
                    Toast.LENGTH_LONG).show();
            return;
        }
    }


    public void checkforsize()
    {
        RadioButton r1=(RadioButton) findViewById(R.id.radioButton5);
        RadioButton r2=(RadioButton) findViewById(R.id.radioButton6);


        if(r1.isChecked())
        {
            order=order+"Large ";
            total_price=total_price+10;
        }

        else if(r2.isChecked())
        {
            order=order+"Medium ";
            total_price=total_price+5;
        }
        else if(r1.isChecked()==false && r2.isChecked()==false)
        {
            Toast.makeText(this, "Please select a size",
                    Toast.LENGTH_LONG).show();
            return;
        }
        order=order+"size taco with ";
    }

    public void placeorder()
    {


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phno, null, order+"\nTotal price is " + total_price+"$", null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }




}

