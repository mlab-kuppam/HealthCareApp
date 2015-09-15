package com.pes.mlab.newhealthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class UpdateActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    public void CHANGE(View view) {
        switch (view.getId()) {
            case R.id.oral:
                Intent i = new Intent(this, OralActivity.class);
                startActivity(i);
                break;
            case R.id.eye:
                Intent j = new Intent(this, EyeContActivity.class);
                startActivity(j);
                break;
            case R.id.ent:
                Intent k = new Intent(this, ENTActivity.class);
                startActivity(k);
                break;
            case R.id.skin:
                Intent l = new Intent(this, SkinActivity.class);
                startActivity(l);
                break;
            case R.id.sys_one:
                Intent m = new Intent(this, SYS_ONEActivity.class);
                startActivity(m);
                break;
            case R.id.sys_two:
                Intent n = new Intent(this, SYS_twoActivity.class);
                startActivity(n);
                break;
            case R.id.health:
                Intent o = new Intent(this, HealthActivity.class);
                startActivity(o);
                break;


        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
