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


public class SYS_twoActivity extends ActionBarActivity {

    EditText editText1,editText2,editText3,editText4, editText5, editText6,editText7,editText8,editText9,editText10;
    private int[] b= new int[50];
    boolean check1,check2,check3,check4,check5,check6,check7=false;
    SQLiteDatabase d_base;
    public String INSERT;
    public String TABLE=
            "student_id VARCHAR[20],epi_var INTEGER[1],epi_com VARCHAR[140],"+
                    "n_other VARCHAR[140],"+
                    "behaviour_var INTEGER[1],behaviour_com VARCHAR[140],"+
                    "uti_var INTEGER[1],uti_com VARCHAR[140],"+
                    "g_other VARCHAR[140],"+
                    "vitc_var INTEGER[1],vitc_com VARCHAR[140],"+
                    "vitb_var INTEGER[1],vitb_com VARCHAR[140],"+
                    "oid_var INTEGER[1],oid_com VARCHAR[140],"+
                    "injuries_var INTEGER[1],injuries_com VARCHAR[140],"+
                    "others VARCHAR[140]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_two);
        studentidDialog();

        editText1 = (EditText)findViewById(R.id.SA2EB1);
        editText2 = (EditText)findViewById(R.id.SA2EB2);
        editText3 = (EditText)findViewById(R.id.SA2EB3);
        editText4 = (EditText)findViewById(R.id.SA2EB4);
        editText5 = (EditText)findViewById(R.id.SA2EB5);
        editText6 = (EditText)findViewById(R.id.SA2EB6);
        editText7 = (EditText)findViewById(R.id.SA2EB7);
        editText8 = (EditText)findViewById(R.id.SA2EB8);
        editText9 = (EditText)findViewById(R.id.SA2EB9);
        editText10 = (EditText)findViewById(R.id.SA2EB10);

        d_base = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE,null);
        d_base.execSQL("CREATE TABLE IF NOT EXISTS sys2 ("+TABLE+")");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sys_two, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_sys2:
                next();
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
        final EditText studentID = (EditText)dialogView.findViewById(R.id.studid);
        // Add action buttons

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                sid = studentID.getText().toString();
                //System.out.println(sid);
                if (sid.length() <= 1) {
                    showError();
                    studentidDialog();
                }else{
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

    public void showError(){
        Toast.makeText(this, "Enter Student ID", Toast.LENGTH_LONG).show();
    }

    public void setStudentID(){
        //studentID.setText(AddActivity.sid);
    }

    public void Intent(){

        Intent i = new Intent(this,UpdateActivity.class);
        startActivity(i);

    }


    public void onradioclick(View v) {
        switch (v.getId())
        {
            case R.id.SA2RB1: editText1.setVisibility(v.VISIBLE);b[0]=1;check1=true;
                break;
            case R.id.SA2RB2: editText1.setVisibility(v.GONE);b[0]=0;check1=true;
                break;

            case R.id.SA2RB3: editText3.setVisibility(v.VISIBLE);b[1]=1;check2=true;
                break;
            case R.id.SA2RB4: editText3.setVisibility(v.GONE);b[1]=0;check2=true;
                break;

            case R.id.SA2RB5: editText4.setVisibility(v.VISIBLE);b[2]=1;check3=true;
                break;
            case R.id.SA2RB6: editText4.setVisibility(v.GONE);b[2]=0;check3=true;
                break;

            case R.id.SA2RB7: editText6.setVisibility(v.VISIBLE);b[3]=1;check4=true;
                break;
            case R.id.SA2RB8: editText6.setVisibility(v.GONE);b[3]=0;check4=true;
                break;

            case R.id.SA2RB9: editText7.setVisibility(v.VISIBLE);b[4]=1;check5=true;
                break;
            case R.id.SA2RB10: editText7.setVisibility(v.GONE);b[4]=0;check5=true;
                break;

            case R.id.SA2RB11: editText8.setVisibility(v.VISIBLE);b[5]=1;check6=true;
                break;
            case R.id.SA2RB12: editText8.setVisibility(v.GONE);b[5]=0;check6=true;
                break;

            case R.id.SA2RB13: editText9.setVisibility(v.VISIBLE);b[6]=1;check7=true;
                break;
            case R.id.SA2RB14: editText9.setVisibility(v.GONE);b[6]=0;check7=true;
                break;

            default:    break;

        }
    }

    public void next()
    {
        if (!check1 || !check2 || !check3 || !check4 || !check5 || !check6 || !check7)
        {
            showMessage("Error", "Please enter all values");
            return;
        }

        INSERT =
                "'"+sid+"','"+b[0]+"','"+editText1.getText().toString()+"',"+
                        "'"+editText2.getText().toString()+"',"+
                        "'"+b[1]+"','"+editText3.getText().toString()+"',"+
                        "'"+b[2]+"','"+editText4.getText().toString()+"',"+
                        "'"+editText5.getText().toString()+"',"+
                        "'"+b[3]+"','"+editText6.getText().toString()+"',"+
                        "'"+b[4]+"','"+editText7.getText().toString()+"',"+
                        "'"+b[5]+"','"+editText8.getText().toString()+"',"+
                        "'"+b[6]+"','"+editText9.getText().toString()+"',"+
                        "'"+editText10.getText().toString()+"'";

        d_base.execSQL("INSERT INTO sys2 VALUES("+INSERT+")");
        showMessage("Success", "Record added");
        Intent S= new Intent(this,SYS_twoActivity.class);
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

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}

