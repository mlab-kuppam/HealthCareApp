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
import android.widget.Button;
import android.widget.EditText;


public class EyeActivity extends ActionBarActivity {

    EditText Cvis1, Cvis2, Bit1, Bit2, All1, All2, Night1, Night2, Congp1, Congp2, Congd1, Congd2, Amb1, Amb2, Nys1, Nys2, Fund1, Fund2, Other;
    Button btn1;
    int[] b = new int[50];
    SQLiteDatabase db;
    boolean check1, check2, check3, check4, check5, check6, check7, check8, check9, check10, check11, check12, check13, check14, check15, check16, check17, check18 = false;
    public String SQL_CREATE;
    public String INSERT_QUERY;
    public String INSERT_VALUES;
    public static boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye);


        Cvis1 = (EditText) findViewById(R.id.Text16);
        Cvis2 = (EditText) findViewById(R.id.Text17);
        Bit1 = (EditText) findViewById(R.id.Text1);
        Bit2 = (EditText) findViewById(R.id.Text2);
        All1 = (EditText) findViewById(R.id.Text3);
        All2 = (EditText) findViewById(R.id.Text4);
        Night1 = (EditText) findViewById(R.id.EText3);
        Night2 = (EditText) findViewById(R.id.EText4);
        Congp1 = (EditText) findViewById(R.id.Text5);
        Congp2 = (EditText) findViewById(R.id.Text6);
        Congd1 = (EditText) findViewById(R.id.Text7);
        Congd2 = (EditText) findViewById(R.id.Text8);
        Amb1 = (EditText) findViewById(R.id.Text9);
        Amb2 = (EditText) findViewById(R.id.Text10);
        Nys1 = (EditText) findViewById(R.id.Text11);
        Nys2 = (EditText) findViewById(R.id.Text12);
        Fund1 = (EditText) findViewById(R.id.Text13);
        Fund2 = (EditText) findViewById(R.id.Text14);
        Other = (EditText) findViewById(R.id.Text15);

        SQL_CREATE =
                "student_id VARCHAR[20],vt_r_d VARCHAR[5] ," +
                        "vt_r_n VARCHAR[5] ," +
                        "vt_l_d VARCHAR[5] ," +
                        "vt_l_n VARCHAR[5] ," +
                        "rc_r_sd VARCHAR[5] ," +
                        "rc_r_cd VARCHAR[5] ," +
                        "rc_r_ad VARCHAR[5] ," +
                        "rc_r_sn VARCHAR[5] ," +
                        "rc_r_cn VARCHAR[5] ," +
                        "rc_r_an VARCHAR[5] ," +
                        "rc_l_sd VARCHAR[5] ," +
                        "rc_l_cd VARCHAR[5] ," +
                        "rc_l_ad VARCHAR[5] ," +
                        "rc_l_sn VARCHAR[5] ," +
                        "rc_l_cn VARCHAR[5] ," +
                        "rc_l_an VARCHAR[5] ," +
                        "colour_r_var INTEGER[1] ," +
                        "colour_r_com VARCHAR[140] ," +
                        "colour_l_var INTEGER[1] ," +
                        "colour_l_com VARCHAR[140], " +
                        "bit_r_var INTEGER[1] ," +
                        "bit_r_com VARCHAR[140] ," +
                        "bit_l_var INTEGER[1] , " +
                        "bit_l_com VARCHAR[140] ," +
                        "con_r_var INTEGER[1] ," +
                        "con_r_com VARCHAR[140] ," +
                        "con_l_var INTEGER[1] ," +
                        "con_l_com VARCHAR[140] ," +
                        "bli_r_var INTEGER[1] ," +
                        "bli_r_com VARCHAR[140] ," +
                        "bli_l_var INTEGER[1] ," +
                        "bli_l_com VARCHAR[140] ," +
                        "pto_r_var INTEGER[1] ," +
                        "pto_r_com VARCHAR[140], " +
                        "pto_l_var INTEGER[1] ," +
                        "pto_l_com VARCHAR[140]," +
                        "cat_r_var INTEGER[1] ," +
                        "cat_r_com VARCHAR[140] ," +
                        "cat_l_var INTEGER[1] ," +
                        "cat_l_com VARCHAR[140], " +
                        "amb_r_var INTEGER[1] ," +
                        "amb_r_com VARCHAR[140], " +
                        "amb_l_var INTEGER[1] ," +
                        "amb_l_com VARCHAR[140] ," +
                        "nys_r_var INTEGER[1] ," +
                        "nys_r_com VARCHAR[140] ," +
                        "nys_l_var INTEGER[1] ," +
                        "nys_l_com VARCHAR[140] ," +
                        "fun_r_var INTEGER[1] ," +
                        "fun_r_com VARCHAR[140] ," +
                        "fun_l_var INTEGER[1] ," +
                        "fun_l_com VARCHAR[140], " +
                        "others VARCHAR[140] ";


        db = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS eye (" + SQL_CREATE + ")");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eye, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_eye:
                NEXT1();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onRadiobuttonselected(View v) {
        switch (v.getId()) {
            case R.id.RBCvis1:
                check1 = true;
                b[0] = 1;
                Cvis1.setVisibility(v.VISIBLE);
                break;
            case R.id.RBCvis2:
                check1 = true;
                b[0] = 0;
                Cvis1.setVisibility(v.GONE);
                break;

            case R.id.RBCvis3:
                check2 = true;
                b[1] = 1;
                Cvis2.setVisibility(v.VISIBLE);
                break;
            case R.id.RBCvis4:
                check2 = true;
                b[1] = 0;
                Cvis2.setVisibility(v.GONE);
                break;

            case R.id.RBBit1:
                check3 = true;
                b[2] = 1;
                Bit1.setVisibility(v.VISIBLE);
                break;
            case R.id.RBBit2:
                check3 = true;
                b[2] = 0;
                Bit1.setVisibility(v.GONE);
                break;

            case R.id.RBBit3:
                check4 = true;
                b[3] = 1;
                Bit2.setVisibility(v.VISIBLE);
                break;
            case R.id.RBBit4:
                check4 = true;
                b[3] = 0;
                Bit2.setVisibility(v.GONE);
                break;

            case R.id.RBAll1:
                check5 = true;
                b[4] = 1;
                All1.setVisibility(v.VISIBLE);
                break;
            case R.id.RBAll2:
                check5 = true;
                b[4] = 0;
                All1.setVisibility(v.GONE);
                break;

            case R.id.RBAll3:
                check6 = true;
                b[5] = 1;
                All2.setVisibility(v.VISIBLE);
                break;
            case R.id.RBAll4:
                check6 = true;
                b[5] = 0;
                All2.setVisibility(v.GONE);
                break;

            case R.id.RBNight1:
                check7 = true;
                b[6] = 1;
                Night1.setVisibility(v.VISIBLE);
                break;
            case R.id.RBNight2:
                check7 = true;
                b[6] = 0;
                Night1.setVisibility(v.GONE);
                break;

            case R.id.RBNight3:
                check8 = true;
                b[7] = 1;
                Night2.setVisibility(v.VISIBLE);
                break;
            case R.id.RBNight4:
                check8 = true;
                b[7] = 0;
                Night2.setVisibility(v.GONE);
                break;

            case R.id.RBCongp1:
                check9 = true;
                b[8] = 1;
                Congp1.setVisibility(v.VISIBLE);
                break;
            case R.id.RBCongp2:
                check9 = true;
                b[8] = 0;
                Congp1.setVisibility(v.GONE);
                break;

            case R.id.RBCongp3:
                check10 = true;
                b[9] = 1;
                Congp2.setVisibility(v.VISIBLE);
                break;
            case R.id.RBCongp4:
                check10 = true;
                b[9] = 0;
                Congp2.setVisibility(v.GONE);
                break;

            case R.id.RBCongd1:
                check11 = true;
                b[10] = 1;
                Congd1.setVisibility(v.VISIBLE);
                break;
            case R.id.RBCongd2:
                check11 = true;
                b[10] = 0;
                Congd1.setVisibility(v.GONE);
                break;

            case R.id.RBCongd3:
                check12 = true;
                b[11] = 1;
                Congd2.setVisibility(v.VISIBLE);
                break;
            case R.id.RBCongd4:
                check12 = true;
                b[11] = 0;
                Congd2.setVisibility(v.GONE);
                break;

            case R.id.RBAmb1:
                check13 = true;
                b[12] = 1;
                Amb1.setVisibility(v.VISIBLE);
                break;
            case R.id.RBAmb2:
                check13 = true;
                b[12] = 0;
                Amb1.setVisibility(v.GONE);
                break;

            case R.id.RBAmb3:
                check14 = true;
                b[13] = 1;
                Amb2.setVisibility(v.VISIBLE);
                break;
            case R.id.RBAmb4:
                check14 = true;
                b[13] = 0;
                Amb2.setVisibility(v.GONE);
                break;

            case R.id.RBNys1:
                check15 = true;
                b[14] = 1;
                Nys1.setVisibility(v.VISIBLE);
                break;
            case R.id.RBNys2:
                check15 = true;
                b[14] = 0;
                Nys1.setVisibility(v.GONE);
                break;

            case R.id.RBNys3:
                check16 = true;
                b[15] = 1;
                Nys2.setVisibility(v.VISIBLE);
                break;
            case R.id.RBNys4:
                check16 = true;
                b[15] = 0;
                Nys2.setVisibility(v.GONE);
                break;

            case R.id.RBFun1:
                check17 = true;
                b[16] = 1;
                Fund1.setVisibility(v.GONE);
                break;
            case R.id.RBFun2:
                check17 = true;
                b[16] = 0;
                Fund1.setVisibility(v.VISIBLE);
                break;

            case R.id.RBFun3:
                check18 = true;
                b[17] = 1;
                Fund2.setVisibility(v.GONE);
                break;
            case R.id.RBFun4:
                check18 = true;
                b[17] = 0;
                Fund2.setVisibility(v.VISIBLE);
                break;

        }

    }


    public void NEXT1() {

        if (!check1 || !check2 || !check3 || !check4 || !check5 || !check6 || !check7 || !check8 || !check8 || !check9 || !check10 || !check11 || !check12 || !check13 || !check14 || !check15 || !check16 || !check17 || !check18) {
            showMessage("Error", "Please enter all values");
            return;
        }

        INSERT_VALUES = "'" + EyeContActivity.sideye + "','" + EyeContActivity.vd1 + "/" + EyeContActivity.vd2 + "','" + EyeContActivity.vd3 + "/" + EyeContActivity.vd4 + "','" + EyeContActivity.vn1 + "/" + EyeContActivity.vn2 + "','" + EyeContActivity.vn3 + "/" + EyeContActivity.vn4 + "'," +
                "'" + EyeContActivity.rn1 + "','" + EyeContActivity.rn2 + "','" + EyeContActivity.rn3 + "','" + EyeContActivity.rn4 + "','" + EyeContActivity.rn5 + "','" + EyeContActivity.rn6 + "'," +
                "'" + EyeContActivity.rd1 + "','" + EyeContActivity.rd2 + "','" + EyeContActivity.rd3 + "','" + EyeContActivity.rd4 + "','" + EyeContActivity.rd5 + "','" + EyeContActivity.rd6 + "'," +
                "'" + b[1] + "','" + Cvis2.getText().toString() + "','" + b[0] + "','" + Cvis1.getText().toString() + "'," +
                "'" + b[3] + "','" + Bit2.getText().toString() + "','" + b[2] + "','" + Bit1.getText().toString() + "'," +
                "'" + b[5] + "','" + All2.getText().toString() + "','" + b[4] + "','" + All1.getText().toString() + "'," +
                "'" + b[7] + "','" + Night2.getText().toString() + "','" + b[6] + "','" + Night1.getText().toString() + "'," +
                "'" + b[9] + "','" + Congp2.getText().toString() + "','" + b[8] + "','" + Congp1.getText().toString() + "'," +
                "'" + b[11] + "','" + Congd2.getText().toString() + "','" + b[10] + "','" + Congd1.getText().toString() + "'," +
                "'" + b[13] + "','" + Amb2.getText().toString() + "','" + b[12] + "','" + Amb1.getText().toString() + "'," +
                "'" + b[15] + "','" + Nys2.getText().toString() + "','" + b[14] + "','" + Nys1.getText().toString() + "'," +
                "'" + b[17] + "','" + Fund2.getText().toString() + "','" + b[16] + "','" + Fund1.getText().toString() + "'," +
                "'" + Other.getText().toString() + "'";

        db.execSQL("INSERT INTO eye VALUES (" + INSERT_VALUES + ")");
        showMessage("Success", "Record added");
        Intent S = new Intent(this, EyeContActivity.class);
        startActivity(S);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void NEXT(View view) {
        Intent i = new Intent(this, EyeContActivity.class);
        startActivity(i);
    }

}
