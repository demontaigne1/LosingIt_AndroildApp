package com.example.sheilatupker.losingit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.sheilatupker.losingit";
    DatabaseHelper myDB;


    @Override //this satisfies the superclass
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        myDB.clearAllLData();
        //The information below is being inserted into the database
        boolean isInserted = myDB.insertData(1, "four sticks of butter", "poundsticksofbutter");
        myDB.insertData(2, "baby hamster", "poundhamster");
        myDB.insertData(10, "baby panda", "panda");


    }


     //Called When the user taps the Go! button

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.wlost);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

}


}
