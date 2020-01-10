package com.example.myfirstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity {
    private ListView listView; //reference to ListView
    private ArrayList<String> list; //Store the reminders
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        listView = findViewById(R.id.reminderList);
        list = new ArrayList<String>();
        //Init the loading of stored array
        try {
            loadReminder();
        } catch (Exception e){
            Toast.makeText(ReminderActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        //links the listView to the adapter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                open(view, position);
            }
        });

    }

    public void open(View view, final int position){
        //*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to delete this?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ReminderActivity.this,
                                "Deleted", Toast.LENGTH_SHORT).show();
                        try {
                            save();
                        } catch (Exception e) {
                            Toast.makeText(ReminderActivity.this,
                                    "Error deleting", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        }

        );

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

//    private void loadReminder() {
//        list.add("Send funds to father");
//        list.add("Send funds to mother");
//
//    }

    private void loadReminder() throws IOException {
        BufferedReader reader = null;
        try {
            InputStream in = getApplicationContext().openFileInput("reminders.json");
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                Log.i("Read", line);
                jsonString.append(line);
            }

            JSONArray jArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            if(jArray.length() > 0) {
                for (int i = 0; i < jArray.length(); i++) {
                    list.add(jArray.getString(i));
                }
                adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, list);
                adapter.notifyDataSetChanged();
            }

        } catch (FileNotFoundException e) {
            // ignore, start afresh
        } catch (JSONException e) {
            // blank list

        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    public void addReminderToList(View view){
        //TextInputEditText txtAdd = findViewById(R.id.txtAdd);
        EditText textToAdd = findViewById(R.id.reminderInput);
        registerForContextMenu(listView);

        list.add(textToAdd.getText().toString());
        adapter.notifyDataSetChanged(); // update the list view

        try {
            save();
            Toast.makeText(ReminderActivity.this,
                    textToAdd.getText().toString()+ " added as item " +
                            list.size(), Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(ReminderActivity.this,
                    "Error saving reminder",Toast.LENGTH_SHORT).show();
        }

}

    private void save() throws IOException, JSONException {
        JSONArray jArray = new JSONArray();

        if(list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                jArray.put(i, list.get(i));
            }
        } else {

        }

        Writer writer = null;
        try {
            OutputStream out = getApplicationContext().openFileOutput(
                    "reminders.json", getApplicationContext().MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            if(list.size() > 0) {
                writer.write(jArray.toString());
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }


    }

}
