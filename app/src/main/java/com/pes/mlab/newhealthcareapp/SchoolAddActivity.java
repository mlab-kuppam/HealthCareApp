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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class SchoolAddActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    //Initialization Block
    public String allocate;
    public static String school_id;
    public String TEXT_TYPE = " VARCHAR";
    public String COMMA_SEP = ",";
    public String LENGTH = "(20)";
    public int criteria;
    public String criteria_comments;
    boolean check1, check2;
    public String SQL_CREATE_ENTRIES = "school" + "(" +
            "school_id" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "mandal" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "block" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "village" + TEXT_TYPE + "(30)" + COMMA_SEP +
            "school_name" + TEXT_TYPE + "(140)" + COMMA_SEP +
            "school_type" + TEXT_TYPE + "(15)" + COMMA_SEP +
            "category" + TEXT_TYPE + "(20)" + COMMA_SEP +
            "name" + TEXT_TYPE + "(50)" + COMMA_SEP +
            "landline INT(10)" + COMMA_SEP +
            "mobile INT(10)" + COMMA_SEP +
            "email" + TEXT_TYPE + "(100)" + COMMA_SEP +
            "crit_var INT(1)" + COMMA_SEP +
            "crit_com" + TEXT_TYPE + "(140)" + COMMA_SEP +
            "PRIMARY KEY(school_id));";
    SQLiteDatabase db;
    Spinner mandal, block, catergories;
    String Mandal, category, nblock;
    String type;

    EditText s_health, village, school, head, landline, mobile, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_add);

        SchoolidDialog();

        db = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SQL_CREATE_ENTRIES);

        mandal = (Spinner) findViewById(R.id.mandal);
        block = (Spinner) findViewById(R.id.block);
        catergories = (Spinner) findViewById(R.id.catergory);
        s_health = (EditText) findViewById(R.id.healthCom);


        village = (EditText) findViewById(R.id.village);
        school = (EditText) findViewById(R.id.school);
        head = (EditText) findViewById(R.id.head);
        landline = (EditText) findViewById(R.id.landline);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.catergories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        catergories.setAdapter(adapter1);
        catergories.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mandal, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mandal.setAdapter(adapter);
        mandal.setOnItemSelectedListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_school_add, menu);
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if (parent.getId() == R.id.mandal) {
            Mandal = parent.getItemAtPosition(pos).toString();
            ArrayAdapter<CharSequence> adapter = null;
            switch (pos) {
                case 0:
                    adapter = ArrayAdapter.createFromResource(this,
                            R.array.vgiri, android.R.layout.simple_spinner_item);
                    break;
                case 1:
                    adapter = ArrayAdapter.createFromResource(this,
                            R.array.rk, android.R.layout.simple_spinner_item);
                    break;
                case 2:
                    adapter = ArrayAdapter.createFromResource(this,
                            R.array.sp, android.R.layout.simple_spinner_item);
                    break;
                case 3:
                    adapter = ArrayAdapter.createFromResource(this,
                            R.array.gp, android.R.layout.simple_spinner_item);
                    break;
                case 4:
                    adapter = ArrayAdapter.createFromResource(this,
                            R.array.k, android.R.layout.simple_spinner_item);
                    break;
            }

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            block.setAdapter(adapter);
            block.setOnItemSelectedListener(this);
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        } else if (parent.getId() == R.id.catergory) {
            category = parent.getItemAtPosition(pos).toString();
        } else if (parent.getId() == R.id.block) {
            nblock = parent.getItemAtPosition(pos).toString();
        }
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void typeSchool(View view) {
        switch (view.getId()) {
            case R.id.radioOne:
                type = "private";
                check1 = true;
                break;

            case R.id.radioTwo:
                type = "government";
                check1 = true;
                break;
        }
    }

    public void schoolHealth(View view) {

        switch (view.getId()) {
            case R.id.met:
                s_health.setVisibility(view.GONE);
                criteria = 1;
                check2 = true;
                break;

            case R.id.nmet:
                s_health.setVisibility(view.VISIBLE);
                criteria = 0;
                check2 = true;
                criteria_comments = s_health.getText().toString();
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_addStudent:
                ADD();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void ADD() {

        if (village.getText().toString().trim().length() == 0 ||
                school.getText().toString().trim().length() == 0 ||
                !check1 || !check2 ||
                head.getText().toString().trim().length() == 0) {
            showMessage("Error", "Please enter all values");
            return;
        } else if (Mandal.equals("Select..") || nblock.equals("Select..") || category.equals("Select..")) {
            showMessage("Error", "Complete the fields in dropdown");
            return;
        } else if (!((mobile.getText().toString().trim().length() == 10 && landline.getText().toString().trim().length() == 0)
                || (landline.getText().toString().trim().length() == 10 && mobile.getText().toString().trim().length() == 0)
                || (landline.getText().toString().trim().length() == 10 && mobile.getText().toString().trim().length() == 10)
        )) {
            showMessage("Error", "Please enter a valid Mobile/Landline number");
            return;
        }

        db.execSQL("INSERT INTO school VALUES('" + school_id + "','" + Mandal + "','" + nblock + "','" + village.getText() + "','" + school.getText() + "','" + type + "','" + category + "','" + head.getText() + "','" + landline.getText() + "','" + mobile.getText() + "','" + email.getText() + "', '" + criteria + "','" + criteria_comments + "');");
        showMessage("Success", "Record added");
        changeIntent();
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void changeIntent() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void SchoolidDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter School ID");
        // Get the layout inflater
        builder.setCancelable(false);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.mydialog, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView);
        final EditText schoolID = (EditText) dialogView.findViewById(R.id.studid);
        // Add action buttons

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                school_id = schoolID.getText().toString();
                if (school_id.length() <= 1) {
                    showError();
                    SchoolidDialog();
                } else {
                    dialog.dismiss();
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
        Toast.makeText(this, "Enter School ID", Toast.LENGTH_SHORT).show();
    }

    public void Intent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    @Override
    public void onBackPressed() {
        backDialog();
    }


}
