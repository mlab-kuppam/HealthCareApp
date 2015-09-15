package com.pes.mlab.newhealthcareapp;

/**
 * Created by Aamir on 27-08-2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread =new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent i =new Intent("com.pes.mlab.newhealthcareapp");
                    startActivity(i);
                }
            }
        };
        timerThread.start();


    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
