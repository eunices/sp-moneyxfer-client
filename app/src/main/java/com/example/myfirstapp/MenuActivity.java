package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity {

    String mEmail;
    TextView textView;

    String URL ;
    RequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // load email
        mEmail = SharedPreferencesUtils.getEmail(MenuActivity.this);
        textView = findViewById(R.id.textView2);
        textView.setText("Hello " + mEmail + ", welcome!");

        // make api call
        try {
            makeApiCallImage();
            makeApiCallQuote();
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.logout_menu:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MenuActivity.this, "Logging out", Toast.LENGTH_SHORT).show();
                finish();
                Intent nextActivity = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(nextActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void makeApiCallImage() throws JSONException {
        URL = "http://shibe.online/api/shibes";

        params = new RequestParams();
        params.put("count", 1);
        params.put("urls", "true");

        HttpUtil.get(URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                ImageView imageView = findViewById(R.id.welcomeImage);

                try {
                    String link = json.getString(0);
                    Picasso.get().load(link).into(imageView);
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }
        });
    }

    public void makeApiCallQuote() {
        URL = "https://quote-garden.herokuapp.com/quotes/random";
        HttpUtil.get(URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                TextView tView = findViewById(R.id.inspirationQuotes);
                try {
                tView.setText("Inspiration of the day: " + json.get("quoteText").toString());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }
        });
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
