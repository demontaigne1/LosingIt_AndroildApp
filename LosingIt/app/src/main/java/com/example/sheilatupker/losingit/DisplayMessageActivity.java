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

        //this will match inputed data to what is in the db
        //if there is a match in the db it will show the msg and show image
        //otherwise an error msg will display
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


    }

    private void showMessage(String results) {
        //this will create an alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //create object
        builder.setCancelable(true);
        builder.setMessage(results);//its an alert dialog so put the text in it, the text being results whatever that might be
        builder.show();//show message

    }

    private void showImage(String results){
        ImageView  imageView2 = (ImageView) findViewById(R.id.imageView2); //ties in with activity display message found under layout1

        int imageResource = getResources().getIdentifier(results, "drawable", this.getPackageName());
        Drawable displayPic = getResources().getDrawable(imageResource, null); //loading the file itself
        imageView2.setImageDrawable(displayPic);


    }

}
