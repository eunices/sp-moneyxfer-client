package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewTransactionsActivity extends AppCompatActivity {

    public ArrayList<TransactionModel> transactions;
    public String transactionsStringReceived = "";
    public String transactionsStringSent = "";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Query query;

    private String mEmail;

    TextView received;
    TextView sent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);

        FirebaseUtil.openChildReference("transactions");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;

        mEmail = SharedPreferencesUtils.getEmail(ViewTransactionsActivity.this);

        received = findViewById(R.id.amountReceived);
        sent = findViewById(R.id.amountSent);

        received.setMovementMethod(new ScrollingMovementMethod());
        sent.setMovementMethod(new ScrollingMovementMethod());

        showTransactions();

    }

    public void showTransactions() {

        // query = mDatabaseReference.orderByChild("toID").equalTo(mEmail);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    TransactionModel txn = ds.getValue(TransactionModel.class);

                    if (txn.getToID().equals(mEmail)) {
                        transactionsStringReceived += txn.getTransactionString() + "\n\n";
                    }
                    if (txn.getFromID().equals(mEmail)) {
                        transactionsStringSent += txn.getTransactionString() + "\n\n";
                    }
                }

                received.setText(transactionsStringReceived);
                sent.setText(transactionsStringSent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TEST", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        // query.addValueEventListener(postListener);
        mDatabaseReference.addValueEventListener(postListener);

    }
}

