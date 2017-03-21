package com.example.chinmaydeshpande.assignment_3;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

import com.example.chinmaydeshpande.assignment_3.R;

/**
 * Created by Chinmay Deshpande on 3/15/2017.
 */

public class Eraser extends IntentService {

    public Eraser() {
        super("hi there, how r u?");
    }

    @Override

    protected void onHandleIntent(Intent intent) {


        MediaPlayer mp = MediaPlayer.create(Eraser.this, R.raw.eraser);
        mp.start();

        while(mp.isPlaying())
        {

            System.out.println();


        }
        mp.reset();
        mp.release();
    }
}
