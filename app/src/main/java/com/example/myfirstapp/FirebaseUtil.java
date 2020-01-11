package com.example.myfirstapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<TransactionModel> transactions;

    private FirebaseUtil() {};

    public static void openChildReference(String ref) {
        if (firebaseUtil == null) {
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            transactions = new ArrayList<TransactionModel>();
        }
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }

}
