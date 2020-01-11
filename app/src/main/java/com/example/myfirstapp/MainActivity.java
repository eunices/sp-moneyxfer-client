package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public static final String AMOUNT = "com.example.myfirstapp.AMOUNT";
    public static final String RECIPIENT = "com.example.myfirstapp.RECIPIENT";

    // Firebase stuff
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    // TextView
    EditText amount;
    EditText recipient;
    String amountStr;
    Float amountFloat;
    String recipientStr;
    String mEmail;
    Date today;
    String todayStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUtil.openChildReference("transactions");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;

        // initialize variables
        amount = findViewById(R.id.sendMoneyAmount);
        recipient = findViewById(R.id.sendMoneyTo);

    }

    /** Called when the user taps the Send button */
    public void sendMoney(View view) {

        Intent intent = new Intent(this, DisplayMessageActivity.class);

        recipientStr = recipient.getText().toString();
        mEmail = SharedPreferencesUtils.getEmail(MainActivity.this);
        todayStr = DateUtil.dateToString(new Date());

        if (recipientStr.contains("@")) {

            try {
                amountStr = amount.getText().toString();
                amountFloat = Float.parseFloat(amountStr);

                TransactionModel txn = new TransactionModel(mEmail, recipientStr, amountFloat, todayStr);
                mDatabaseReference.push().setValue(txn);

                // Send to next activity
                intent.putExtra(AMOUNT, amountStr);
                intent.putExtra(RECIPIENT, recipientStr);
                Toast.makeText(MainActivity.this, "Sending money",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);

                // Clean
                recipient.setText("");
                amount.setText("");

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
