package com.pes.mlab.newhealthcareapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class HealthActivity extends ActionBarActivity {

    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8;
    SQLiteDatabase db;
    String INSERT;
    public String TABLE =
            "student_id VARCHAR[20],height INTEGER[3],weight INTEGER[3]," +
                    "abd_cir INTEGER[3],wst_cir INTEGER[3]," +
                    "p_rate INTEGER[3],r_rate INTEGER[2]," +
                    "bp_sys INTEGER[3],bp_dia INTEGER[3]," +
                    "nails INTEGER[1],bath INTEGER[1]," +
                    "groom INTEGER[1],oral INTEGER[1]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        editText1 = (EditText) findViewById(R.id.height);
        editText2 = (EditText) findViewById(R.id.weight);
        editText3 = (EditText) findViewById(R.id.abdominal);
        editText4 = (EditText) findViewById(R.id.waist);
        editText5 = (EditText) findViewById(R.id.prate);
        editText6 = (EditText) findViewById(R.id.rrate);
        editText7 = (EditText) findViewById(R.id.systole);
        editText8 = (EditText) findViewById(R.id.diastole);


        db = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS health (" + TABLE + ")");
        studentidDialog();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_health:
                NEXT();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    String sid;

    public void studentidDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Student ID");
        // Get the layout inflater
        builder.setCancelable(false);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.mydialog, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView);
        final EditText studentID = (EditText) dialogView.findViewById(R.id.studid);
        // Add action buttons

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                sid = studentID.getText().toString();
                //System.out.println(sid);
                if (sid.length() <= 1) {
                    showError();
                    studentidDialog();
                } else {
                    //setStudentID();
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Student ID: " + sid, Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void Intent() {

        Intent i = new Intent(this, UpdateActivity.class);
        startActivity(i);
        //Toast.makeText(getApplicationContext(),sid,Toast.LENGTH_LONG).show();
    }

    public void showError() {
        Toast.makeText(this, "Enter Student ID", Toast.LENGTH_LONG).show();
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    int nails = 10, groom = 10, bath = 10, oralcare = 10;

    public void onRadioselect(View v) {
        switch (v.getId()) {
            case R.id.x1:
                nails = 1;
                break;
            case R.id.x2:
                nails = 0;
                break;
            case R.id.x3:
                bath = 1;
                break;
            case R.id.x4:
                bath = 0;
                break;
            case R.id.x5:
                groom = 1;
                break;
            case R.id.x6:
                groom = 0;
                break;
            case R.id.x7:
                oralcare = 1;
                break;
            case R.id.x8:
                oralcare = 0;
                break;
        }
    }


    public void NEXT() {

        if (editText1.getText().toString().length() == 0 ||
                editText2.getText().toString().length() == 0 ||
                editText3.getText().toString().length() == 0 ||
                editText4.getText().toString().length() == 0 ||
                editText5.getText().toString().length() == 0 ||
                editText6.getText().toString().length() == 0 ||
                editText7.getText().toString().length() == 0 ||
                editText8.getText().toString().length() == 0) {

            showMessage("Error", "Please enter all values");
            return;
        }

        INSERT =
                "'" + sid + "','" + editText1.getText().toString() + "','" + editText2.getText().toString() + "'" +
                        ",'" + editText3.getText().toString() + "','" + editText4.getText().toString() + "'" +
                        ",'" + editText5.getText().toString() + "','" + editText6.getText().toString() + "'" +
                        ",'" + editText7.getText().toString() + "','" + editText8.getText().toString() + "'" +
                        ",'" + nails + "'" +
                        ",'" + bath + "'" +
                        ",'" + groom + "'" +
                        ",'" + oralcare + "'";

        db.execSQL("INSERT INTO health VALUES (" + INSERT + ")");
        showMessage("Success", "Record added");
        Intent S = new Intent(this, HealthActivity.class);
        startActivity(S);
    }

    public void backDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Current Data Will be Lost");
        // Get the layout inflater
        builder.setCancelable(false);
        LayoutInflater inflater = this.getLayoutInflater();
        // Add action buttons
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                backIntent();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                return;
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void backIntent() {
        Intent back = new Intent(this, UpdateActivity.class);
        startActivity(back);
    }

    @Override
    public void onBackPressed() {
        backDialog();
    }
}
