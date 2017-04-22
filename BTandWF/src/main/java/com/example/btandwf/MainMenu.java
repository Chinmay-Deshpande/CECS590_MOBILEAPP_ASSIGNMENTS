package com.example.btandwf;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;



/**
 * Created by Chinmay Deshpande on 4/17/2017.
 */

public class MainMenu extends ActionBarActivity {
    //private BluetoothAdapter bluetoothAdapter = null;
    private static final int REQUEST_ENABLE_BT = 3;
    private ArrayAdapter<String> chatArrayAdapter;

    private ChatService chatService = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        Button b = (Button) findViewById(R.id.button);
        Button b1 = (Button) findViewById(R.id.button3);
        Button b5 = (Button) findViewById(R.id.button5);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Intent i = new Intent(MainMenu.this, StartBT.class);
                // startActivity(i);

                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter == null) {
                    // Device does not support Bluetooth
                }

                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }


                // bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, MainActivity.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, Imageselector.class);
                startActivity(i);
            }
        });



    }


  /*  public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                  //  setupChat();
                } else {
                    Toast.makeText(this, R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        }
*/

  /*  private void setupChat() {
        chatArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);
        lvMainChat.setAdapter(chatArrayAdapter);

        chatService = new ChatService(this, handler);

        outStringBuffer = new StringBuffer("");
    }*/
}
