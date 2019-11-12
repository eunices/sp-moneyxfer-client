package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String amount = intent.getStringExtra(MainActivity.AMOUNT);
        String recipient = intent.getStringExtra(MainActivity.RECIPIENT);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.displayAmountSent);
        textView.setText(getString(R.string.currency_sgd) + " " + amount);
        TextView textView2 = findViewById(R.id.displayRecipient);
        textView2.setText("to " + recipient);

    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }



}
