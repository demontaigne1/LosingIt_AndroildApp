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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        myDB.clearAllLData();
        boolean isInserted = myDB.insertData(1, "sticksofbutter", "poundsticksofbutter");
        myDB.insertData(2, "hamster", "poundhamster");
        myDB.insertData(10, "panda", "panda");

        if(isInserted == true){
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(MainActivity.this, "data not Inserted", Toast.LENGTH_LONG).show();


    }

    /**
     * Called When the user taps the Go! button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.wlost);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

}


}
