package com.pes.mlab.newhealthcareapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

//import android.support.v7.app.ActionBarActivity;

public class MainActivity extends Activity {
    //Declaring Buttons
    Button add, update, sync;
    public static boolean check_internet = true;

    private class GetXMLTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            String trial = params[0];

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost;
            // In a POST request, we don't pass the values in the UL.
            //Therefore we use only the web page URL as the parameter of the HttpPost argument
            httpPost = new HttpPost("http://192.168.1.6/trial.php");

            System.out.println("j=" + trial);


            // Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
            //uniquely separate by the other end.
            //To achieve that we use BasicNameValuePair
            //Things we need to pass with the POST request
            BasicNameValuePair usernameBasicNameValuePair = new BasicNameValuePair("j", trial);
            //System.out.println(trial);

            // We add the content that we want to pass with the POST request to as name-value pairs
            //Now we put those sending details to an ArrayList with type safe of NameValuePair
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
            nameValuePairList.add(usernameBasicNameValuePair);

            try {
                // UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs.
                //This is typically useful while sending an HTTP POST request.
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

                // setEntity() hands the entity (here it is urlEncodedFormEntity) to the request.
                httpPost.setEntity(urlEncodedFormEntity);

                try {
                    // HttpResponse is an interface just like HttpPost.
                    //Therefore we can't initialize them
                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    // According to the JAVA API, InputStream constructor do nothing.
                    //So we can't initialize InputStream although it is not an interface
                    InputStream inputStream = httpResponse.getEntity().getContent();

                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder stringBuilder = new StringBuilder();

                    String bufferedStrChunk = null;

                    while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                        stringBuilder.append(bufferedStrChunk);
                    }

                    return stringBuilder.toString();

                } catch (ClientProtocolException cpe) {
                    System.out.println("First Exception caz of HttpResponese :" + cpe);
                    cpe.printStackTrace();
                } catch (IOException ioe) {
                    System.out.println("Second Exception caz of HttpResponse :" + ioe);
                    ioe.printStackTrace();
                }

            } catch (UnsupportedEncodingException uee) {
                System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
                uee.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String output) {
            Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
        }
    }

    //Declaring Database
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (check_internet && INTERNER_CHECK()) {
            showMessage("You have Internet Connection", "Please Sync now");
        }
        check_internet = false;
        db = openOrCreateDatabase("healthcare", Context.MODE_PRIVATE, null);
        add = (Button) findViewById(R.id.add);
        update = (Button) findViewById(R.id.update);
        sync = (Button) findViewById(R.id.sync);
    }

    //To Add Schools and Students(Changing Intent to SchoolAdd Activity)
    public void ADD(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    //To Update Health Details of particular student(Changing Intent to Update Activity
    public void UPDATE(View view) {
        Intent i = new Intent(this, UpdateActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_down_out);
    }

    //To Sync the details to the Cloud(Pushing Data to the Cloud)
    public void SYNC(View view) {
        boolean check = INTERNER_CHECK();
        if (check) {
            String t = "";
            ArrayList<String> tablenames = new ArrayList<String>();
            try {
                JSONObject json_fin = new JSONObject();

                Cursor r = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

                if (r.moveToFirst()) {
                    while (!r.isAfterLast()) {
                        tablenames.add(r.getString(r.getColumnIndex("name")));
                        r.moveToNext();
                    }
                }
                r.close();

                for (int i = 1; i < tablenames.size(); i++) {
                    String q = "SELECT * FROM " + tablenames.get(i);
                    Cursor c = db.rawQuery(q, null);
                    c.moveToFirst();
                    if (c != null) {
                        JSONArray array = new JSONArray();
                        do {
                            JSONObject rows = new JSONObject();

                            for (int l = 0; l < c.getColumnCount(); l++) {
                                String name = c.getColumnName(l);
                                switch (c.getType(l)) {
                                    case Cursor.FIELD_TYPE_STRING:
                                        rows.put(name, c.getString(l));
                                        break;
                                    case Cursor.FIELD_TYPE_INTEGER:
                                        rows.put(name, c.getInt(l));
                                        break;
                                    case Cursor.FIELD_TYPE_FLOAT:
                                        rows.put(name, c.getFloat(l));
                                        break;
                                    case Cursor.FIELD_TYPE_BLOB:
                                        rows.put(name, c.getBlob(l));
                                        break;
                                }
                            }
                            array.put(rows);
                        } while (c.moveToNext());
                        c.close();
                        json_fin.put(tablenames.get(i), array);
                    }
                }

                t = json_fin.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!t.equals("{}")) {
                GetXMLTask get = new GetXMLTask();
                get.execute(new String[]{t});
                /*for (int i = 0; i < tablenames.size(); i++) {
                    db.execSQL("DROP TABLE IF EXISTS " + tablenames.get(i));
                }*/
            }/* else {
                Toast.makeText(getApplicationContext(), "No Entries found", Toast.LENGTH_LONG).show();
            }*/
        } else {
            showMessage("Check Internert Connection", "Try again");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    Boolean isInternetPresent;

    public boolean INTERNER_CHECK() {
        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        return isInternetPresent;
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

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
    }

}
