package com.pes.mlab.newhealthcareapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class EyeContActivity extends ActionBarActivity {

    //Declaring elements in the activity
    Button btn1;
    NumberPicker vdn1, vdn2, vdn3, vdn4, vnn1, vnn2, vnn3, vnn4, rdn1, rdn2, rdn3, rdn4, rdn5, rdn6, rnn1, rnn2, rnn3, rnn4, rnn5, rnn6;
    RelativeLayout R1;
    //Declaring form output strings
    static String vd1, vd2, vd3, vd4, rd1, rd2, rd3, rd4, rd5, rd6, vn1, vn2, vn3, vn4, rn1, rn2, rn3, rn4, rn5, rn6;
    //Declaring Radio Selection variable
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_cont);
        //Invoking StudentID Dialog box
        studentidDialog();
        //Initializing variables
        EyeActivity.check = false;
        //Initializing elements in the activity
        btn1 = (Button) findViewById(R.id.btn1);

        vdn1 = (NumberPicker) findViewById(R.id.numberPicker1);
        vdn2 = (NumberPicker) findViewById(R.id.numberPicker2);
        vdn3 = (NumberPicker) findViewById(R.id.numberPicker3);
        vdn4 = (NumberPicker) findViewById(R.id.numberPicker4);
        vnn1 = (NumberPicker) findViewById(R.id.numberPicker5);
        vnn2 = (NumberPicker) findViewById(R.id.numberPicker6);
        vnn3 = (NumberPicker) findViewById(R.id.numberPicker7);
        vnn4 = (NumberPicker) findViewById(R.id.numberPicker8);
        rdn1 = (NumberPicker) findViewById(R.id.numberPicker9);
        rdn2 = (NumberPicker) findViewById(R.id.numberPicker10);
        rdn3 = (NumberPicker) findViewById(R.id.numberPicker11);
        rdn4 = (NumberPicker) findViewById(R.id.numberPicker12);
        rdn5 = (NumberPicker) findViewById(R.id.numberPicker14);
        rdn6 = (NumberPicker) findViewById(R.id.numberPicker15);
        rnn1 = (NumberPicker) findViewById(R.id.numberPicker16);
        rnn2 = (NumberPicker) findViewById(R.id.numberPicker17);
        rnn3 = (NumberPicker) findViewById(R.id.numberPicker18);
        rnn4 = (NumberPicker) findViewById(R.id.numberPicker19);
        rnn5 = (NumberPicker) findViewById(R.id.numberPicker20);
        rnn6 = (NumberPicker) findViewById(R.id.numberPicker21);

        R1 = (RelativeLayout) findViewById(R.id.R1);

        vdn1.setMaxValue(6);
        vdn1.setMinValue(1);
        vdn2.setMaxValue(6);
        vdn2.setMinValue(1);
        vdn3.setMaxValue(6);
        vdn3.setMinValue(1);
        vdn4.setMaxValue(6);
        vdn4.setMinValue(1);
        vnn1.setMaxValue(6);
        vnn1.setMinValue(1);
        vnn2.setMaxValue(6);
        vnn2.setMinValue(1);
        vnn3.setMaxValue(6);
        vnn3.setMinValue(1);
        vnn4.setMaxValue(6);
        vnn4.setMinValue(1);
        rdn1.setMaxValue(6);
        rdn1.setMinValue(1);
        rdn2.setMaxValue(6);
        rdn2.setMinValue(1);
        rdn3.setMaxValue(6);
        rdn3.setMinValue(1);
        rdn4.setMaxValue(6);
        rdn4.setMinValue(1);
        rdn5.setMaxValue(6);
        rdn5.setMinValue(1);
        rdn6.setMaxValue(6);
        rdn6.setMinValue(1);
        rnn1.setMaxValue(6);
        rnn1.setMinValue(1);
        rnn2.setMaxValue(6);
        rnn2.setMinValue(1);
        rnn3.setMaxValue(6);
        rnn3.setMinValue(1);
        rnn4.setMaxValue(6);
        rnn4.setMinValue(1);
        rnn5.setMaxValue(6);
        rnn5.setMinValue(1);
        rnn6.setMaxValue(6);
        rnn6.setMinValue(1);
        vdn1.setValue(6);
        vdn2.setValue(6);
        vdn3.setValue(6);
        vdn4.setValue(6);
        vnn1.setValue(6);
        vnn2.setValue(6);
        vnn3.setValue(6);
        vnn4.setValue(6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eye_cont, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        backDialog();
    }

    //Method to INSERT content of the activity
    //*****Implementation of errors are missing********************************************
    public void NEXTEYE(View view) {
        if (a == 1 || a == 2) {
            vd1 = Integer.toString(vdn1.getValue());
            vd2 = Integer.toString(vdn2.getValue());
            vd3 = Integer.toString(vdn3.getValue());
            vd4 = Integer.toString(vdn4.getValue());
            vn1 = Integer.toString(vnn1.getValue());
            vn2 = Integer.toString(vnn2.getValue());
            vn3 = Integer.toString(vnn1.getValue());
            vn4 = Integer.toString(vnn2.getValue());

            if (a == 1) {
                rd1 = Integer.toString(rdn1.getValue());
                rd2 = Integer.toString(rdn2.getValue());
                rd3 = Integer.toString(rdn3.getValue());
                rd4 = Integer.toString(rdn4.getValue());
                rd5 = Integer.toString(rdn5.getValue());
                rd6 = Integer.toString(rdn6.getValue());
                rn1 = Integer.toString(rnn1.getValue());
                rn2 = Integer.toString(rnn2.getValue());
                rn3 = Integer.toString(rnn3.getValue());
                rn4 = Integer.toString(rnn4.getValue());
                rn5 = Integer.toString(rnn5.getValue());
                rn6 = Integer.toString(rnn6.getValue());
            }

            Intent S = new Intent(this, EyeActivity.class);
            startActivity(S);
        } else {
            Toast.makeText(getApplicationContext(), "Enter all Value", Toast.LENGTH_LONG).show();
            return;
        }

    }

    //Method to handle radio buttons
    public void OnRadioSelected(View v) {
        switch (v.getId()) {
            case R.id.RB1:
                R1.setVisibility(v.VISIBLE);
                a = 1;
                break;
            case R.id.RB2:
                R1.setVisibility(v.GONE);
                a = 2;
                rd1 = "null";
                rd3 = "null";
                rd4 = "null";
                rd5 = "null";
                rd6 = "null";
                rn1 = "null";
                rn2 = "null";
                rn3 = "null";
                rn4 = "null";
                rn5 = "null";
                rn6 = "null";
                break;
        }
    }

    static String sideye;
    //Method to create studentId dialog box
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

                sideye = studentID.getText().toString();
                //System.out.println(sid);
                if (sideye.length() <= 1) {
                    showError();
                    studentidDialog();
                } else {
                    //setStudentID();
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Student ID: " + sideye, Toast.LENGTH_SHORT).show();
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

    //Method to alert user about deletion of data
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
                Intent();
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

    public void showError() {
        Toast.makeText(this, "Enter Student ID", Toast.LENGTH_LONG).show();
    }

    //Method to change intent to root activity
    public void Intent() {

        Intent i = new Intent(this, UpdateActivity.class);
        startActivity(i);
    }

}
