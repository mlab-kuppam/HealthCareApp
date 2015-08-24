package com.pes.mlab.newhealthcareapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ENTActivity extends ActionBarActivity {


    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16;
    public int[] ear = new int[50];
    boolean check1, check2, check3, check4, check5, check6, check7, check8, check9, check10, check11, check12, check13, check14, check15 = false;

    SQLiteDatabase d_base;

    public String TABLE =
            "student_id VARCHAR[20],oe_r_var INTEGER," +
                    "oe_r_com VARCHAR[140]," +
                    "oe_l_var INTEGER[1]," +
                    "oe_l_com VARCHAR[140]," +
                    "asom_r_var INTEGER[1]," +
                    "asom_r_com VARCHAR[140]," +
                    "asom_l_var INTEGER[1]," +
                    "asom_l_com VARCHAR[140]," +
                    "csom_r_var INTEGER[1]," +
                    "csom_r_com VARCHAR[140]," +
                    "csom_l_var INTEGER[1]," +
                    "csom_l_com VARCHAR[140]," +
                    "wax_r_var INTEGER[1]," +
                    "wax_r_com VARCHAR[140]," +
                    "wax_l_var INTEGER[1]," +
                    "wax_l_com VARCHAR[140]," +
                    "hear_r_var INTEGER[1]," +
                    "hear_r_com VARCHAR[140]," +
                    "hear_l_var INTEGER[1]," +
                    "hear_l_com VARCHAR[140]," +
                    "ade_var INTEGER[1]," +
                    "ade_com VARCHAR[140]," +
                    "pha_var INTEGER[1]," +
                    "pha_com VARCHAR[140]," +
                    "rhi_var INTEGER[1]," +
                    "rhi_com VARCHAR[140]," +
                    "speech_var INTEGER[1]," +
                    "speech_com VARCHAR[140]," +
                    "epi_var INTEGER[1]," +
                    "epi_com VARCHAR[140]," +
                    "others VARCHAR[140]";

    public String INSERT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent);
        studentidDialog();

        e1 = (EditText) findViewById(R.id.eT1);
        e2 = (EditText) findViewById(R.id.eT2);
        e3 = (EditText) findViewById(R.id.eT3);
        e4 = (EditText) findViewById(R.id.eT4);
        e5 = (EditText) findViewById(R.id.eT5);
        e6 = (EditText) findViewById(R.id.eT6);
        e7 = (EditText) findViewById(R.id.eT7);
        e8 = (EditText) findViewById(R.id.eT8);
        e9 = (EditText) findViewById(R.id.eT9);
        e10 = (EditText) findViewById(R.id.eT10);
        e11 = (EditText) findViewById(R.id.epiCom);
        e12 = (EditText) findViewById(R.id.adeCom);
        e13 = (EditText) findViewById(R.id.phCom);
        e14 = (EditText) findViewById(R.id.rhiCom);
        e15 = (EditText) findViewById(R.id.speechCom);
        e16 = (EditText) findViewById(R.id.addInfo);

        d_base = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        d_base.execSQL("CREATE TABLE IF NOT EXISTS ear (" + TABLE + ")");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_ent:
                NEXT1();
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
                    Toast.makeText(getApplicationContext(),"Student ID: "+sid,Toast.LENGTH_SHORT).show();
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
        //Toast.makeText(getApplicationContext(),sid,Toast.LENGTH_LONG).show();
    }

    public void earClick(View view) {
        switch (view.getId()) {
            case R.id.e2:
                ear[0] = 0;
                check1 = true;
                e1.setVisibility(view.GONE);
                break;

            case R.id.e4:
                ear[1] = 0;
                check2 = true;
                e2.setVisibility(view.GONE);
                break;

            case R.id.e6:
                ear[2] = 0;
                check3 = true;
                e3.setVisibility(view.GONE);
                break;

            case R.id.e8:
                ear[3] = 0;
                check4 = true;
                e4.setVisibility(view.GONE);
                break;

            case R.id.e10:
                ear[4] = 0;
                check5 = true;
                e5.setVisibility(view.GONE);
                break;

            case R.id.e12:
                ear[5] = 0;
                check6 = true;
                e6.setVisibility(view.GONE);
                break;

            case R.id.e14:
                ear[6] = 0;
                check7 = true;
                e7.setVisibility(view.GONE);
                break;

            case R.id.e16:
                ear[7] = 0;
                check8 = true;
                e8.setVisibility(view.GONE);
                break;

            case R.id.e18:
                ear[8] = 0;
                check9 = true;
                e9.setVisibility(view.GONE);
                break;

            case R.id.e20:
                ear[9] = 0;
                check10 = true;
                e10.setVisibility(view.GONE);
                break;

            case R.id.e22:
                ear[10] = 0;
                check11 = true;
                e11.setVisibility(view.GONE);
                break;

            case R.id.e24:
                ear[11] = 0;
                check12 = true;
                e12.setVisibility(view.GONE);
                break;

            case R.id.e26:
                ear[12] = 0;
                check13 = true;
                e13.setVisibility(view.GONE);
                break;

            case R.id.e28:
                ear[13] = 0;
                check14 = true;
                e14.setVisibility(view.GONE);
                break;

            case R.id.e30:
                ear[14] = 0;
                check15 = true;
                e15.setVisibility(view.GONE);
                break;
            case R.id.e1:
                ear[0] = 1;
                check1 = true;
                e1.setVisibility(view.VISIBLE);
                break;

            case R.id.e3:
                ear[1] = 1;
                check2 = true;
                e2.setVisibility(view.VISIBLE);
                break;

            case R.id.e5:
                ear[2] = 1;
                check3 = true;
                e3.setVisibility(view.VISIBLE);
                break;

            case R.id.e7:
                ear[3] = 1;
                check4 = true;
                e4.setVisibility(view.VISIBLE);
                break;

            case R.id.e9:
                ear[4] = 1;
                check5 = true;
                e5.setVisibility(view.VISIBLE);
                break;

            case R.id.e11:
                ear[5] = 1;
                check6 = true;
                e6.setVisibility(view.VISIBLE);
                break;

            case R.id.e13:
                ear[6] = 1;
                check7 = true;
                e7.setVisibility(view.VISIBLE);
                break;

            case R.id.e15:
                ear[7] = 1;
                check8 = true;
                e8.setVisibility(view.VISIBLE);
                break;

            case R.id.e17:
                ear[8] = 1;
                check9 = true;
                e9.setVisibility(view.VISIBLE);
                break;

            case R.id.e19:
                ear[9] = 1;
                check10 = true;
                e10.setVisibility(view.VISIBLE);
                break;

            case R.id.e21:
                ear[10] = 1;
                check11 = true;
                e11.setVisibility(view.VISIBLE);
                break;

            case R.id.e23:
                ear[11] = 1;
                check12 = true;
                e12.setVisibility(view.VISIBLE);
                break;

            case R.id.e25:
                ear[12] = 1;
                check13 = true;
                e13.setVisibility(view.VISIBLE);
                break;
            case R.id.e27:
                ear[13] = 1;
                check14 = true;
                e14.setVisibility(view.VISIBLE);
                break;

            case R.id.e29:
                ear[14] = 1;
                check15 = true;
                e15.setVisibility(view.VISIBLE);
                break;
        }
    }

    public void NEXT1() {

        if (!check1 || !check2 || !check3 || !check4 || !check5 || !check6 || !check7 || !check8 || !check8 || !check9 || !check10 || !check11 || !check12 || !check13 || !check14 || !check15) {
            showMessage("Error", "Please enter all values");
            return;
        }

        INSERT =
                "'" + sid + "','" + ear[0] + "','" + e1.getText().toString() + "','" + ear[1] + "','" + e2.getText().toString() + "'," +
                        "'" + ear[2] + "','" + e3.getText().toString() + "','" + ear[3] + "','" + e4.getText().toString() + "'," +
                        "'" + ear[4] + "','" + e5.getText().toString() + "','" + ear[5] + "','" + e6.getText().toString() + "'," +
                        "'" + ear[6] + "','" + e7.getText().toString() + "','" + ear[7] + "','" + e8.getText().toString() + "'," +
                        "'" + ear[8] + "','" + e9.getText().toString() + "','" + ear[9] + "','" + e10.getText().toString() + "'," +
                        "'" + ear[10] + "','" + e11.getText().toString() + "'," +
                        "'" + ear[11] + "','" + e12.getText().toString() + "'," +
                        "'" + ear[12] + "','" + e13.getText().toString() + "'," +
                        "'" + ear[13] + "','" + e14.getText().toString() + "'," +
                        "'" + ear[14] + "','" + e15.getText().toString() + "'," +
                        "'" + e16.getText().toString() + "'";

        d_base.execSQL("INSERT INTO ear VALUES (" + INSERT + ")");
        showMessage("Success", "Record added");
        Intent S = new Intent(this, ENTActivity.class);
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

    public void backIntent(){
        Intent back = new Intent(this,UpdateActivity.class);
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

