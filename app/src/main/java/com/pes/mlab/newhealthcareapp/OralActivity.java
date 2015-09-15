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

public class OralActivity extends ActionBarActivity {

    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7;
    int[] b = new int[50];
    boolean check1, check2, check3, check4, check5, check6 = false;
    SQLiteDatabase d_base;
    public String INSERT;
    public String TABLE =
            "student_id VARCHAR[20],anaemia_var INTEGER[1],anaemia_com VARCHAR[140]," +
                    "dc_var INTEGER[1],dc_com VARCHAR[140]," +
                    "flou_var INTEGER[1],flou_com VARCHAR[140]," +
                    "ging_var INTEGER[1],ging_com VARCHAR[140]," +
                    "ulc_var INTEGER[1],ulc_com VARCHAR[140]," +
                    "dev_var INTEGER[1],dev_com VARCHAR[140]," +
                    "other VARCHAR[140]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oral);

        editText1 = (EditText) findViewById(R.id.OREB1);
        editText2 = (EditText) findViewById(R.id.OREB2);
        editText3 = (EditText) findViewById(R.id.OREB3);
        editText4 = (EditText) findViewById(R.id.OREB4);
        editText5 = (EditText) findViewById(R.id.OREB5);
        editText6 = (EditText) findViewById(R.id.OREB6);
        editText7 = (EditText) findViewById(R.id.OREB7);

        d_base = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        d_base.execSQL("CREATE TABLE IF NOT EXISTS oral (" + TABLE + ")");
        studentidDialog();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_oral, menu);
        return true;
    }

    public void onRadioselect(View v) {
        switch (v.getId()) {
            case R.id.ORRB1:
                editText1.setVisibility(v.VISIBLE);
                b[0] = 1;
                check1 = true;
                break;
            case R.id.ORRB2:
                editText1.setVisibility(v.GONE);
                b[0] = 0;
                check1 = true;
                break;

            case R.id.ORRB3:
                editText2.setVisibility(v.VISIBLE);
                b[1] = 1;
                check2 = true;
                break;
            case R.id.ORRB4:
                editText2.setVisibility(v.GONE);
                b[1] = 0;
                check2 = true;
                break;

            case R.id.ORRB5:
                editText3.setVisibility(v.VISIBLE);
                b[2] = 1;
                check3 = true;
                break;
            case R.id.ORRB6:
                editText3.setVisibility(v.GONE);
                b[2] = 0;
                check3 = true;
                break;

            case R.id.ORRB7:
                editText4.setVisibility(v.VISIBLE);
                b[3] = 1;
                check4 = true;
                break;
            case R.id.ORRB8:
                editText4.setVisibility(v.GONE);
                b[3] = 0;
                check4 = true;
                break;

            case R.id.ORRB9:
                editText5.setVisibility(v.VISIBLE);
                b[4] = 1;
                check5 = true;
                break;
            case R.id.ORRB10:
                editText5.setVisibility(v.GONE);
                b[4] = 0;
                check5 = true;
                break;

            case R.id.ORRB11:
                editText6.setVisibility(v.VISIBLE);
                b[5] = 1;
                check6 = true;
                break;
            case R.id.ORRB12:
                editText6.setVisibility(v.GONE);
                b[5] = 0;
                check6 = true;
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_oral:
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
                    setStudentID();
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

    public void showError() {
        Toast.makeText(this, "Enter Student ID", Toast.LENGTH_LONG).show();
    }

    public void setStudentID() {
        //studentID.setText(AddActivity.sid);
    }

    public void Intent() {

        Intent i = new Intent(this, UpdateActivity.class);
        startActivity(i);
    }

    public void NEXT() {
        if (!check1 || !check2 || !check3 || !check4 || !check5 || !check6) {
            showMessage("Error", "Please enter all values");
            return;
        }

        INSERT =
                "'" + sid + "','" + b[0] + "','" + editText1.getText().toString() + "'," +
                        "'" + b[1] + "','" + editText2.getText().toString() + "'," +
                        "'" + b[2] + "','" + editText3.getText().toString() + "'," +
                        "'" + b[3] + "','" + editText4.getText().toString() + "'," +
                        "'" + b[4] + "','" + editText5.getText().toString() + "'," +
                        "'" + b[5] + "','" + editText6.getText().toString() + "'," +
                        "'" + editText7.getText().toString() + "'";

        d_base.execSQL("INSERT INTO oral VALUES(" + INSERT + ")");
        showMessage("Success", "Record added");
        Intent D = new Intent(this, OralActivity.class);
        startActivity(D);
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

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
