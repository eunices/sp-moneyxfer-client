package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    String mEmail;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // load email
        mEmail = SharedPreferencesUtils.getEmail(MenuActivity.this);
        textView = findViewById(R.id.textView2);
        textView.setText("Hello " + mEmail + ", welcome!");
    }

    public void goToReminderActivity(View view) {
        Intent intent = new Intent(this, ReminderActivity.class);
        startActivity(intent);
    }

    public void goToTransactionsActivity(View view) {
        Intent intent = new Intent(this, ViewTransactionsActivity.class);
        startActivity(intent);
    }

    public void goToTransferMoneyActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
