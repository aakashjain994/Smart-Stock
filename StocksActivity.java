package com.codingblocks.stocks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;


public class StocksActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);
        setTitle("Title");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_batch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences sp = getSharedPreferences("batch_clicked", MODE_PRIVATE);
            String lastBatch = sp.getString("last_batch", "No Batch clicked so far");
            Toast.makeText(this, lastBatch, Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_call) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:9971489388"));
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        } else if (id == R.id.action_web) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://www.codingblocks.com"));
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        } else if (id == R.id.action_search) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://www.codingblocks.com"));
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        }

        return super.onOptionsItemSelected(item);
    }

}