package com.pes.mlab.newhealthcareapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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

    //Declaring Variables
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    //sid -> Student ID (Must)
    static String sid;

    //DB Query
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

    //Boolean to check Radio Buttons are checked
    public boolean check1;

    //Form outputs as String
    String genderString;
    String std;

    //Activity Elements declaration
    EditText name, father, mother, guardian, attnd, grade;
    ImageView studentpic;
    TextView studentID;
    DatePicker dp;
    Spinner standard;

    //Declaring DB
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Invoking Dialog Box for studentID input
        studentidDialog();
        //Toast to print the schoolID Entered in the prev Activity
        Toast.makeText(getApplicationContext(), "School ID: " + SchoolAddActivity.school_id, Toast.LENGTH_SHORT).show();
        //Initializing activity elements
        dp = (DatePicker) findViewById(R.id.age);
        studentID = (TextView) findViewById(R.id.studentID);
        studentpic = (ImageView) findViewById(R.id.studentpic);
        standard = (Spinner) findViewById(R.id.standard);
        //Opening DB
        db = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        //Creating table if not in the DB
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SQL_CREATE_ENTRIES);
        //Initializing Edittext
        name = (EditText) findViewById(R.id.studentName);
        father = (EditText) findViewById(R.id.fatherName);
        mother = (EditText) findViewById(R.id.motherName);
        guardian = (EditText) findViewById(R.id.guardianName);
        attnd = (EditText) findViewById(R.id.attendance);
        grade = (EditText) findViewById(R.id.grade);
        //Setting default date in the Datepicker
        dp.init(1997, 00, 01, null);

        check1=false;

        //ArrayAdapter for Dropdown menu (Spinner)
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.standard, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        standard.setAdapter(adapter1);
        standard.setOnItemSelectedListener(this);

        //Setting image view to default
        studentpic.setImageResource(R.drawable.imageview);
        studentpic.setOnClickListener(new clicker());
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

    @Override
    public void onBackPressed() {
        backDialog();
    }

    //Adding details collected to SQLite
    public void ADD() {

        int day = dp.getDayOfMonth();
        int month = dp.getMonth() + 1;
        int year = dp.getYear();

        String date = year + "-" + month + "-" + day;

        //Have to make if statement efficient attnd appearing on two places
        if (name.getText().toString().trim().length() == 0 ) {
            showMessage("Error", "Please Enter Student Name");
            return;
        } else if (!check1) {
            showMessage("Error", "Please Select Gender");
            return;
        }else if (std.equals("Select..")) {
            showMessage("Error", "Please Select the Standard");
            return;
        }else if (father.getText().toString().trim().length() == 0 && guardian.getText().toString().trim().length() == 0 && mother.getText().toString().trim().length() == 0) {
            showMessage("Error", "Please Enter Father/Mother/Guardian name");
            return;
        }else if (Integer.parseInt(attnd.getText().toString().trim()) > 100 || Integer.parseInt(attnd.getText().toString().trim()) < 0) {
            showMessage("Error", "Please Enter Valid Attendance");
            return;
        }else if (Integer.parseInt(grade.getText().toString().trim()) > 100 || Integer.parseInt(grade.getText().toString().trim()) < 0) {
            showMessage("Error", "Please Enter Valid Percentage");
            return;
        }else {
            //If the required fields are filled then the DB is updated
            db.execSQL("INSERT INTO child VALUES('" + sid + "','" + SchoolAddActivity.school_id + "','" + name.getText() + "','" + date + "','" + genderString + "','" + std + "','" + father.getText() + "','" + mother.getText() + "','" + guardian.getText() + "','" + attnd.getText() + "','" + grade.getText() + "');");
            showMessage("Success", "Record added");
            changeIntent();
        }
    }

    //Method to handle Radio Buttons in the Activity
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

    //Method to create the dialog box
    //@params title and message
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    //Method to create StudentID dialog box
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
                //If canceled the intent returns to parent
                Intent();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Method to create alert dialog when back pressed in middle of the activity
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

    public void showError() {

        Toast.makeText(this, "Enter Student ID", Toast.LENGTH_LONG).show();
    }

    //Method to set studentID in the Activity
    public void setStudentID() {
        studentID.setText(AddActivity.sid);
    }

    //Method to change intent to parent activity
    public void Intent() {
        Intent intent = new Intent(this, SchoolAddActivity.class);
        startActivity(intent);
    }

    //Method to change intent to next activity
    public void changeIntent() {
        Intent setIntent = new Intent(this, socioActivity.class);
        startActivity(setIntent);
    }

    //Method to change intent to root MainActivity
    public void backIntent() {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    //Method to get selected item in the dropdown
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        std = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Method -> Camera Functions
    public void capture() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // start the image capture Intent
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "Images");
        imagesFolder.mkdirs();
        File image = new File(imagesFolder, AddActivity.sid+"_portrait_0"+ ".jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    //Result of Capture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                File imgFile = new File(Environment.getExternalStorageDirectory()+"/Images/" + AddActivity.sid+"_portrait_0"+ ".jpg");
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    Matrix matrix = new Matrix();
                    matrix.postRotate(-90);
                    Bitmap rotatedImg = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
                    myBitmap.recycle();
                    studentpic.setImageBitmap(rotatedImg);
                    //image = encodeTobase64(myBitmap);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
                Toast.makeText(this, "Photo not saved", Toast.LENGTH_SHORT).show();
            } else {
                // Image capture failed, advise user
                Toast.makeText(this, "Photo Capture Failed", Toast.LENGTH_SHORT).show();
            }
        }

    //Method to encode to image to base 64 string for syncing
    public String encodeTobase64(Bitmap image) {
        System.gc();
        if (image == null) return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    //Clicker to invoke the camera when clicked on the ImageView
    class clicker implements ImageView.OnClickListener {

        @Override
        public void onClick(View v) {
            capture();
        }
    }
}
