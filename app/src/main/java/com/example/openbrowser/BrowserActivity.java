package com.example.openbrowser;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BrowserActivity extends ActionBarActivity {

    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // To keep this example simple, we allow network access
        // in the user interface thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_browser);
        Intent intent = getIntent();
        TextView text = (TextView) findViewById(R.id.textView);
        // To get the action of the intent use
        String action = intent.getAction();
        if (!action.equals(Intent.ACTION_VIEW)) {
            throw new RuntimeException("Should not happen");
        }
        // To get the data use
        Uri data = intent.getData();
        URL url;
        try {
            url = new URL(data.getScheme(), data.getHost(), data.getPath());
            BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                text.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


       // button = (Button) findViewById(R.id.startintent);
        //button.setOnClickListener(this);
    }

   /* public void onClick(View view) {
        intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.vogella.com"));
        startActivity(intent);
    }*/

}
