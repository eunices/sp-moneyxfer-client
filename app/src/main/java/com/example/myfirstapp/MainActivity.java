package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfirstapp.LoginActivity;


public class MainActivity extends AppCompatActivity {
    public static final String AMOUNT = "com.example.myfirstapp.AMOUNT";
    public static final String RECIPIENT = "com.example.myfirstapp.RECIPIENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        EditText amount = (EditText) findViewById(R.id.sendMoneyAmount);
        String amountStr = amount.getText().toString();
        EditText recipient = (EditText) findViewById(R.id.sendMoneyTo);
        String recipientStr = recipient.getText().toString();

        if (recipientStr.contains("@")) {
            try {
                float amountFloat = Float.parseFloat(amountStr);
                intent.putExtra(AMOUNT, amountStr);
                intent.putExtra(RECIPIENT, recipientStr);
                startActivity(intent);
            } catch(Exception e) {
                Toast.makeText(MainActivity.this, "Enter a valid number",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Enter a valid email",
                    Toast.LENGTH_LONG).show();
        }
    }

}
