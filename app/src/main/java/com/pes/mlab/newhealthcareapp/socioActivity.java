package com.pes.mlab.newhealthcareapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class socioActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    Spinner mandal, familyType, familyHead, education, occupation, income, eduFather, occFather, eduMother, occMother, socioClass;
    String n_mandal, n_familyType, n_familyHead, n_education, n_occupation, n_income, n_eduFather, n_occFather, n_eduMother, n_occMother, n_socioClass;
    EditText village, post, landline, mobile, number, aadhar, com, treatment;
    SQLiteDatabase db;

    boolean check1;
    public String TEXT_TYPE = " VARCHAR";
    public String COMMA_SEP = ",";
    public String LENGTH = "(20)";
    public String SQL_CREATE_ENTRIES = "socio_demographic" + "(" +
            "student_id" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "village" + TEXT_TYPE + "(30)" + COMMA_SEP +
            "post_office" + TEXT_TYPE + "(30)" + COMMA_SEP +
            "mandal" + TEXT_TYPE + "(20)" + COMMA_SEP +
            "landline INT(10)" + COMMA_SEP +
            "mobile INT(10)" + COMMA_SEP +
            "family_type" + TEXT_TYPE + "(15)" + COMMA_SEP +
            "member_no INT(3)" + COMMA_SEP +
            "family_head " + TEXT_TYPE + LENGTH + COMMA_SEP +
            "aadhar_no" + TEXT_TYPE + "(12)" + COMMA_SEP +
            "head_edu" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "head_occ" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "father_edu" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "father_occ" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "mother_edu" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "mother_occ" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "income" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "socio_eco" + TEXT_TYPE + "(3)" + COMMA_SEP +
            "home_std_var INT(1)" + COMMA_SEP +
            "home_std_com" + TEXT_TYPE + "(140)" + COMMA_SEP +
            "treat" + TEXT_TYPE + "(140)" + COMMA_SEP +
            "PRIMARY KEY(student_id));";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socio);
        Toast.makeText(getApplicationContext(), "Student ID: " + AddActivity.sid, Toast.LENGTH_SHORT).show();
        db = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SQL_CREATE_ENTRIES);

        com = (EditText) findViewById(R.id.housingCom);
        village = (EditText) findViewById(R.id.villageName);
        post = (EditText) findViewById(R.id.postOffice);
        landline = (EditText) findViewById(R.id.landlineHome);
        mobile = (EditText) findViewById(R.id.mobileHome);
        number = (EditText) findViewById(R.id.numberOfMembers);
        treatment = (EditText) findViewById(R.id.treatment);
        aadhar = (EditText) findViewById(R.id.aadhar);

        mandal = (Spinner) findViewById(R.id.mandal);
        familyType = (Spinner) findViewById(R.id.familyType);
        familyHead = (Spinner) findViewById(R.id.familyHead);
        education = (Spinner) findViewById(R.id.EducationHead);
        occupation = (Spinner) findViewById(R.id.OccupationHead);
        eduFather = (Spinner) findViewById(R.id.EducationFather);
        occFather = (Spinner) findViewById(R.id.OccupationFather);
        eduMother = (Spinner) findViewById(R.id.EducationMother);
        occMother = (Spinner) findViewById(R.id.OccupationMother);
        income = (Spinner) findViewById(R.id.Income);
        socioClass = (Spinner) findViewById(R.id.socioClass);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mandal, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mandal.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.famiyType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyType.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.famiyHead, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyHead.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.education, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        education.setAdapter(adapter);
        eduFather.setAdapter(adapter);
        eduMother.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.occupation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupation.setAdapter(adapter);
        occFather.setAdapter(adapter);
        occMother.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.income, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        income.setAdapter(adapter);


        adapter = ArrayAdapter.createFromResource(this,
                R.array.socio, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        socioClass.setAdapter(adapter);

        mandal.setOnItemSelectedListener(this);
        familyType.setOnItemSelectedListener(this);
        familyHead.setOnItemSelectedListener(this);
        education.setOnItemSelectedListener(this);
        occupation.setOnItemSelectedListener(this);
        income.setOnItemSelectedListener(this);
        eduFather.setOnItemSelectedListener(this);
        eduMother.setOnItemSelectedListener(this);
        occFather.setOnItemSelectedListener(this);
        occMother.setOnItemSelectedListener(this);
        socioClass.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_socio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_enter:
                ADDNEWSTUDENT();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ADDNEWSTUDENT() {

        boolean a = aadhar.getText().toString().trim().length() == 0 || aadhar.getText().toString().trim().length() == 12;
        if (village.getText().toString().trim().length() == 0 ||
                post.getText().toString().trim().length() == 0 || !check1 ||
                number.getText().toString().trim().length() == 0) {
            showMessage("Error", "Please enter all values");
            return;
        } else if (!a) {
            showMessage("Error", "Please enter a valid Aadhar number");
        } else if (n_mandal.equals("Select..") || n_familyType.equals("Select..") || n_familyHead.equals("Select..") ||
                n_education.equals("Select..") || n_occupation.equals("Select..") || n_eduFather.equals("Select..") ||
                n_occFather.equals("Select..") || n_eduMother.equals("Select..") || n_occMother.equals("Select..") ||
                n_income.equals("Select..") || n_socioClass.equals("Select..")) {
            showMessage("Error", "Complete the fields in dropdown");
            return;
        } else if (!((mobile.getText().toString().trim().length() == 10 && landline.getText().toString().trim().length() == 0)
                || (landline.getText().toString().trim().length() == 10 && mobile.getText().toString().trim().length() == 0)
                || (landline.getText().toString().trim().length() == 10 && mobile.getText().toString().trim().length() == 10)
        )) {
            showMessage("Error", "Please enter a valid Mobile/Landline number");
            return;
        } else {
            db.execSQL("INSERT INTO socio_demographic VALUES('" + AddActivity.sid + "','" + village.getText() + "','" + post.getText() + "','" + n_mandal + "','" + landline.getText() + "','"
                    + mobile.getText() + "','" + n_familyType + "','" + number.getText() + "','" + n_familyHead + "','" + aadhar.getText() + "','" + n_education
                    + "','" + n_occupation + "','" + n_eduFather + "','" + n_occFather + "','" + n_eduMother + "','" + n_occMother + "','" + n_income + "','" + n_socioClass + "','" + health + "','" + com.getText() + "','" + treatment.getText() + "');");
            showMessage("Success", "Record added");
            changeIntent();
        }
    }


    public void changeIntent() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.mandal:
                n_mandal = parent.getItemAtPosition(position).toString();
                break;
            case R.id.familyType:
                n_familyType = parent.getItemAtPosition(position).toString();
                break;
            case R.id.familyHead:
                n_familyHead = parent.getItemAtPosition(position).toString();
                break;
            case R.id.EducationHead:
                n_education = parent.getItemAtPosition(position).toString();
                break;
            case R.id.OccupationHead:
                n_occupation = parent.getItemAtPosition(position).toString();
                break;
            case R.id.EducationFather:
                n_income = parent.getItemAtPosition(position).toString();
                break;
            case R.id.OccupationFather:
                n_eduFather = parent.getItemAtPosition(position).toString();
                break;
            case R.id.EducationMother:
                n_occFather = parent.getItemAtPosition(position).toString();
                break;
            case R.id.OccupationMother:
                n_eduMother = parent.getItemAtPosition(position).toString();
                break;
            case R.id.Income:
                n_occMother = parent.getItemAtPosition(position).toString();
                break;
            case R.id.socioClass:
                n_socioClass = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    int health;

    public void health(View view) {
        switch (view.getId()) {
            case R.id.healthful:
                health = 1;
                check1 = true;
                com.setVisibility(view.GONE);
                break;
            case R.id.NHealthful:
                health = 0;
                check1 = true;
                com.setVisibility(view.VISIBLE);
                break;
        }
    }
}
