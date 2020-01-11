package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // load email
        String mEmail = SharedPreferencesUtils.getEmail(MenuActivity.this);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("Hello " + mEmail + ", welcome!");
    }

    public void goToReminderActivity(View view) {
        Intent intent = new Intent(this, ReminderActivity.class);
        startActivity(intent);
    }

    public void goToTransferMoneyActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
