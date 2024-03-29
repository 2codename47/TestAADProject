package com.aad.cn47.testaadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private final String SUBJECT = "Personal Info";

    private final String TEXT_TO_SHARE = "PERSONAL INFORMATION \n\n" +
            "Name: Chidi Uwaleke \n" +
            "Phone: 09095966472 \n" +
            "Email: cjy2k1@yahoo.com \n" +
            "Location: 12 Community Road, Ikeja\n" +
            "LinkedIn: https://www.linkedin.com/in/chidi-uwaleke-3769b9a8/";

    private final String TEXT_TO_SHARE2 = "PERSONAL INFORMATION \n\n" +
            "Name: Chidi Uwaleke \n" +
            "Phone: 09095966472 \n" +
            "Email: cjy2k1@yahoo.com \n" +
            "Location: 12 Community Road, Ikeja\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            createShareIntent();
        }

        return super.onOptionsItemSelected(item);
    }

    public void createPhoneIntent(View view){
        //Step 1: Create an Intent with action 'Intent.ACTION_DIAL'
        Intent intent = new Intent(Intent.ACTION_DIAL);

        //Step 2: Add the telephonne number as a data (URI) to the intent using "tel:phone_num";
        Uri uri = Uri.parse("tel:09095966472");
        intent.setData(uri);

        //Step 3: Start the activity
        startActivity(intent);
    }

    public void createMailClientIntent(View view){
        //Step 1: Compose the text to share
        String subject = SUBJECT;
        String body = TEXT_TO_SHARE;

        //Step 2: Create an mailClientIntent with action 'Intent.ACTION_SENDTO'
        Intent mailClientIntent = new Intent(Intent.ACTION_SENDTO);

        //Step 3: Set the intent data ("mailto:recipientEmail")
        Uri datauri = Uri.parse("mailto:cjy2k1@yahoo.com");
        mailClientIntent.setData(datauri);

        //Step 4: Add the text to share as an extra (Intent.EXTRA_TEXT)
        mailClientIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailClientIntent.putExtra(Intent.EXTRA_TEXT, body);

        //Step 5: Check if any Mail Client is available to ACCEPT this intent
        if(mailClientIntent.resolveActivity(getPackageManager()) != null){
            startActivity(mailClientIntent);
        }
        else{
            Toast.makeText(this, "No available client", Toast.LENGTH_LONG).show();
        }
    }

    public void createMapIntent(View view){
        //Step 1: Create the data for this intent using the Long & Lat of the location
        Uri uri = Uri.parse("geo:0,0?q="  + Uri.encode("12 Community Rd, Allen, Ikeja, Lagos"));

        //Step 2: Create a mapIntent with action "Intent.ACTION_VIEW". Pass the data too
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);

        //Step 3: Set the package name of the Map App (i.e the unique name of the app)
        //E.g mapIntent.setPackage("com.google.android.apps.maps");
        mapIntent.setPackage("com.google.android.apps.maps");

        //Step 4: Check if the Map app is available to ACCEPT this intent, if Map app is available, start the Map Activity
        if(mapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(mapIntent);
        }
        else{
            Toast.makeText(this, "No available client", Toast.LENGTH_LONG).show();
        }

    }

    public void createBrowserIntent(View view){
        //Step 1: Create te data for this intent using the Twitter Profile address
        //E.g "https://twitter.com/SirGoingFar"
        Uri uri = Uri.parse("https://www.linkedin.com/in/chidi-uwaleke-3769b9a8/");

        //Step 2: Create a browserIntent with action 'Intent.ACTION_VIEW'
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);

        //Step 3: Set the data of the intent
        browserIntent.setData(uri);

        //Step 4: Start the intent
        startActivity(browserIntent);
    }

    private void createShareIntent() {
        //Step 1: Compose the text to share
        String body2 = TEXT_TO_SHARE2;

        //Step 2: Create the shareIntent with action 'Intent.ACTION_SEND'
        Intent sendIntent = new Intent(Intent.ACTION_SEND);

        //Step 3: Specify the intent type
        //E.g. shareIntent.setType("text/plain");
        sendIntent.setType("text/plain");

        //Step 4: Add the text to share as an extra data (Intent.EXTRA_TEXT)
        sendIntent.putExtra(Intent.EXTRA_TEXT, body2);

        Intent shareIntent = Intent.createChooser(sendIntent, null);

        //Step 5: Start shareIntent
        startActivity(shareIntent);

    }
}
