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


public class SkinActivity extends ActionBarActivity {

    //Declaring sid -> studentID(must)
    String sid;
    //Declaring EditText in the Activity
    EditText Scabtext, Pityaltext, Phrynotext, Pedicultext, Pityvertext, Imptext, Paputext, Tintext, Miltext, Viraltext, Other;
    //Declaring Checks used in the Activity
    boolean check1, check2, check3, check4, check5, check6, check7, check8, check9, check10 = false;
    //Declaring Variable
    private int[] checkarr = new int[50];
    //Declaring DB
    SQLiteDatabase d_base;
    //DB Query
    private String INSERT;
    private String TABLE =
            "student_id VARCHAR[20],sca_var INTEGER[1],sca_com VARCHAR[140]," +
                    "pa_var INTEGER[1] ,pa_com VARCHAR[140]," +
                    "ph_var INTEGER[1] ,ph_com VARCHAR[140]," +
                    "pe_var INTEGER[1] ,pe_com VARCHAR[140]," +
                    "pi_var INTEGER[1] ,pi_com VARCHAR[140]," +
                    "im_var INTEGER[1] ,im_com VARCHAR[140]," +
                    "pap_var INTEGER[1] ,pap_com VARCHAR[140]," +
                    "cor_var INTEGER[1] ,cor_com VARCHAR[140]," +
                    "mil_var INTEGER[1] ,mil_com VARCHAR[140]," +
                    "viral_var INTEGER[1] ,viral_com VARCHAR[140]," +
                    "others VARCHAR[140]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

        //Invoking StudentID Dialog box
        studentidDialog();
        //Initialization
        Scabtext = (EditText) findViewById(R.id.editText1);
        Pityaltext = (EditText) findViewById(R.id.editText2);
        Phrynotext = (EditText) findViewById(R.id.editText12);
        Pedicultext = (EditText) findViewById(R.id.editText4);
        Pityvertext = (EditText) findViewById(R.id.editText5);
        Imptext = (EditText) findViewById(R.id.editText6);
        Paputext = (EditText) findViewById(R.id.editText7);
        Tintext = (EditText) findViewById(R.id.editText8);
        Miltext = (EditText) findViewById(R.id.editText9);
        Viraltext = (EditText) findViewById(R.id.editText10);
        Other = (EditText) findViewById(R.id.editText11);
        //Opening DB
        d_base = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        d_base.execSQL("CREATE TABLE IF NOT EXISTS skin (" + TABLE + ")");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_skin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_skin:
                NEXT();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        backDialog();
    }

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
        //Toast.makeText(getApplicationContext(),sid,Toast.LENGTH_LONG).show();
    }

    //Method to handle radio Button selection
    public void onRadiobuttonselected(View v) {
        switch (v.getId()) {
            case R.id.RBScab1:
                Scabtext.setVisibility(v.VISIBLE);
                checkarr[0] = 1;
                check1 = true;
                break;
            case R.id.RBScab2:
                Scabtext.setVisibility(v.GONE);
                checkarr[0] = 0;
                check1 = true;
                break;

            case R.id.RBPityal1:
                Pityaltext.setVisibility(v.VISIBLE);
                checkarr[1] = 1;
                check2 = true;
                break;
            case R.id.RBPityal2:
                Pityaltext.setVisibility(v.GONE);
                checkarr[1] = 0;
                check2 = true;
                break;

            case R.id.RBPhryno1:
                Phrynotext.setVisibility(v.VISIBLE);
                checkarr[2] = 1;
                check3 = true;
                break;
            case R.id.RBPhryno2:
                Phrynotext.setVisibility(v.GONE);
                checkarr[2] = 0;
                check3 = true;
                break;

            case R.id.RBPedicul1:
                Pedicultext.setVisibility(v.VISIBLE);
                checkarr[3] = 1;
                check4 = true;
                break;
            case R.id.RBPedicul2:
                Pedicultext.setVisibility(v.GONE);
                checkarr[3] = 0;
                check4 = true;
                break;

            case R.id.RBPcolor1:
                Pityvertext.setVisibility(v.VISIBLE);
                checkarr[4] = 1;
                check5 = true;
                break;
            case R.id.RBPcolor2:
                Pityvertext.setVisibility(v.GONE);
                checkarr[4] = 0;
                check5 = true;
                break;

            case R.id.RBImp1:
                Imptext.setVisibility(v.VISIBLE);
                checkarr[5] = 1;
                check6 = true;
                break;
            case R.id.RBImp2:
                Imptext.setVisibility(v.GONE);
                checkarr[5] = 0;
                check6 = true;
                break;

            case R.id.RBPapu1:
                Paputext.setVisibility(v.VISIBLE);
                checkarr[6] = 1;
                check7 = true;
                break;
            case R.id.RBPapu2:
                Paputext.setVisibility(v.GONE);
                checkarr[6] = 0;
                check7 = true;
                break;

            case R.id.RBTin1:
                Tintext.setVisibility(v.VISIBLE);
                checkarr[7] = 1;
                check8 = true;
                break;
            case R.id.RBTin2:
                Tintext.setVisibility(v.GONE);
                checkarr[7] = 0;
                check8 = true;
                break;


            case R.id.RBMil1:
                Miltext.setVisibility(v.VISIBLE);
                checkarr[8] = 1;
                check9 = true;
                break;
            case R.id.RBMil2:
                Miltext.setVisibility(v.GONE);
                checkarr[8] = 0;
                check9 = true;
                break;


            case R.id.RBVir1:
                Viraltext.setVisibility(v.VISIBLE);
                checkarr[9] = 1;
                check10 = true;
                break;
            case R.id.RBVir2:
                Viraltext.setVisibility(v.GONE);
                checkarr[9] = 0;
                check10 = true;
                break;

        }
    }

    //Method to create new student entry
    public void NEXT() {
        if (!check1 || !check2 || !check3 || !check4 || !check5 || !check6 || !check7 || !check8 || !check8 || !check9 || !check10) {
            showMessage("Error", "Please enter all values");
            return;
        }

        INSERT =
                "'" + sid + "','" + checkarr[0] + "','" + Scabtext.getText().toString() + "'," +
                        "'" + checkarr[1] + "','" + Pityaltext.getText().toString() + "'," +
                        "'" + checkarr[2] + "','" + Phrynotext.getText().toString() + "'," +
                        "'" + checkarr[3] + "','" + Pedicultext.getText().toString() + "'," +
                        "'" + checkarr[4] + "','" + Pityvertext.getText().toString() + "'," +
                        "'" + checkarr[5] + "','" + Imptext.getText().toString() + "'," +
                        "'" + checkarr[6] + "','" + Paputext.getText().toString() + "'," +
                        "'" + checkarr[7] + "','" + Tintext.getText().toString() + "'," +
                        "'" + checkarr[8] + "','" + Miltext.getText().toString() + "'," +
                        "'" + checkarr[9] + "','" + Viraltext.getText().toString() + "'," +
                        "'" + Other.getText().toString() + "'";

        d_base.execSQL("INSERT INTO skin VALUES(" + INSERT + ")");
        showMessage("Success", "Record added");
        Intent S = new Intent(this, SkinActivity.class);
        startActivity(S);
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

    //Method to change intent to root activity
    public void backIntent() {
        Intent back = new Intent(this, UpdateActivity.class);
        startActivity(back);
    }

    //Method to create the dialog box
    //@params title and message
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}

