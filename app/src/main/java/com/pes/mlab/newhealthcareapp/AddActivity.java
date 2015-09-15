package com.pes.mlab.newhealthcareapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;


public class AddActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    static String sid;
    public String TEXT_TYPE = " VARCHAR";
    public String COMMA_SEP = ",";
    public String LENGTH = "(20)";
    public String SQL_CREATE_ENTRIES = "child" + "(" +
            "student_id" + TEXT_TYPE + LENGTH + COMMA_SEP +
            "school_id" + TEXT_TYPE + LENGTH + " references school_details(school_id)" + COMMA_SEP +
            "name" + TEXT_TYPE + "(140)" + COMMA_SEP +
            "dob TEXT" + COMMA_SEP +
            "gender" + TEXT_TYPE + "(6)" + COMMA_SEP +
            "standard" + TEXT_TYPE + "(4)" + COMMA_SEP +
            "father_name" + TEXT_TYPE + "(50)" + COMMA_SEP +
            "mother_name" + TEXT_TYPE + "(50)" + COMMA_SEP +
            "guardian_name" + TEXT_TYPE + "(50)" + COMMA_SEP +
            "attendance DECIMAL(4)" + COMMA_SEP +
            "grade DECIMAL(4)" + COMMA_SEP +
            "PRIMARY KEY(student_id));";
    public boolean check1;
    String genderString;
    ImageView studentpic;
    TextView studentID;
    DatePicker dp;
    Spinner standard;
    SQLiteDatabase db;
    EditText name, father, mother, guardian, attnd, grade;
    String std;
    String pid = AddActivity.sid + "_ch";
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        studentidDialog();
        Toast.makeText(getApplicationContext(), "School ID: " + SchoolAddActivity.school_id, Toast.LENGTH_SHORT).show();

        dp = (DatePicker) findViewById(R.id.age);
        studentID = (TextView) findViewById(R.id.studentID);
        studentpic = (ImageView) findViewById(R.id.studentpic);
        standard = (Spinner) findViewById(R.id.standard);

        db = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SQL_CREATE_ENTRIES);


        name = (EditText) findViewById(R.id.studentName);
        father = (EditText) findViewById(R.id.fatherName);
        mother = (EditText) findViewById(R.id.motherName);
        guardian = (EditText) findViewById(R.id.guardianName);
        attnd = (EditText) findViewById(R.id.attendance);
        grade = (EditText) findViewById(R.id.grade);

        dp.init(1997, 00, 01, null);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.standard, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        standard.setAdapter(adapter1);
        standard.setOnItemSelectedListener(this);


        studentpic.setImageResource(R.drawable.imageview);
        studentpic.setOnClickListener(new clicker());
    }

    //Adding details collected to SQLite
    public void ADD() {

        int day = dp.getDayOfMonth();
        int month = dp.getMonth() + 1;
        int year = dp.getYear();

        String date = year + "-" + month + "-" + day;

        if (name.getText().toString().trim().length() == 0 ||
                attnd.getText().toString().trim().length() == 0 ||
                !check1 ||
                grade.getText().toString().trim().length() == 0) {
            showMessage("Error", "Please enter all values");
            return;
        } else if (std.equals("Select..")) {
            showMessage("Error", "Complete the fields in dropdown");
            return;
        } else if (Integer.parseInt(attnd.getText().toString().trim()) > 100 || Integer.parseInt(grade.getText().toString().trim()) > 100
                || Integer.parseInt(grade.getText().toString().trim()) < 0 || Integer.parseInt(attnd.getText().toString().trim()) < 0) {
            showMessage("Error", "Please enter proper percentage");
            return;
        } else if (father.getText().toString().trim().length() == 0 && guardian.getText().toString().trim().length() == 0 && mother.getText().toString().trim().length() == 0) {
            showMessage("Error", "Please enter Father/Mother/Guardian name");
            return;
        } else {
            db.execSQL("INSERT INTO child VALUES('" + sid + "','" + SchoolAddActivity.school_id + "','" + name.getText() + "','" + date + "','" + genderString + "','" + std + "','" + father.getText() + "','" + mother.getText() + "','" + guardian.getText() + "','" + attnd.getText() + "','" + grade.getText() + "');");
            showMessage("Success", "Record added");
            changeIntent();
        }
    }

    public void genderRadio(View view) {

        switch (view.getId()) {
            case R.id.gender1:
                genderString = "male";
                check1 = true;
                break;

            case R.id.gender2:
                genderString = "female";
                check1 = true;
                break;

            case R.id.gender3:
                genderString = "other";
                check1 = true;
                break;
        }
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

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
        studentID.setText(AddActivity.sid);
    }

    public void Intent() {
        Intent intent = new Intent(this, SchoolAddActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        std = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_next:
                ADD();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void capture() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // start the image capture Intent
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "Images");
        imagesFolder.mkdirs();
        File image = new File(imagesFolder, pid + ".jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    public void changeIntent() {
        Intent setIntent = new Intent(this, socioActivity.class);
        startActivity(setIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                File imgFile = new File("/sdcard/MyImages/" + pid + ".jpg");
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    studentpic.setImageBitmap(myBitmap);
                    image = encodeTobase64(myBitmap);
                } else {
                    studentpic.setBackgroundResource(R.drawable.app_icon);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
                Toast.makeText(this, "Photo not saved", Toast.LENGTH_SHORT).show();
            } else {
                // Image capture failed, advise user
                Toast.makeText(this, "Photo Capture Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public String encodeTobase64(Bitmap image) {
        System.gc();

        if (image == null) return null;

        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] b = baos.toByteArray();

        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT); // min minSdkVersion 8

        return imageEncoded;
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

    class clicker implements ImageView.OnClickListener {

        @Override
        public void onClick(View v) {
            capture();
        }
    }
}
