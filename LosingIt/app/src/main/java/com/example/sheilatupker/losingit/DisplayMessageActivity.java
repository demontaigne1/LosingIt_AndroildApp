package com.example.sheilatupker.losingit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        DatabaseHelper myDB = new DatabaseHelper(this);
        try {
            // Get the Intent that started this activity and extract the string
            Intent intent = getIntent();
            String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            String[] results = myDB.getInfo(message);
            showMessage(results[1]);
            if( results [0] != "") {
                showImage(results[0]);
            }
        }catch(Exception e){
            showMessage(e.toString());
        }


        // Capture the layout's TextView and set the string as its text
        //TextView textView = findViewById(R.id.textView);
        //textView.setText(message);

    }

    private void showMessage(String results) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //create object
        builder.setCancelable(true);//create params
        builder.setMessage(results);
        builder.show();//show it

    }

    private void showImage(String results){
        ImageView  imageView2 = (ImageView) findViewById(R.id.imageView2);

        int imageResource = getResources().getIdentifier(results, "drawable", this.getPackageName());
        Drawable displayPic = getResources().getDrawable(imageResource, null); //loading the file itself
        imageView2.setImageDrawable(displayPic);


        //imageView2.setImageResource(imageResource);

    }

}
