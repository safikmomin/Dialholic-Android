package com.example.safik.dialholic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.safik.dialholic.Api.ApiInterface;
import com.example.safik.dialholic.apidata.LoginData;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.JsonObject;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LogIn extends AppCompatActivity {

    Button click;
    TextView tv;
    EditText edit_user;
    EditText password;
    String API = "https://agile-reaches-4488.herokuapp.com";
    public static String id;
    public static String token;
    public static String userEmail;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        click = (Button) findViewById(R.id.signIn);
        tv = (TextView) findViewById(R.id.tv);
        edit_user = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_user.getText().toString();
                String word = password.getText().toString();

                //posting data via restful json object
                JsonObject obj = new JsonObject();
                JsonObject payerReg = new JsonObject();
                payerReg.addProperty("email", email);
                payerReg.addProperty("password", word);
                obj.add("user", payerReg);

                //retrofit for retrieving user data
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface git = retrofit.create(ApiInterface.class);
                Call call = git.login(obj);
                call.enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Response<LoginData> response, Retrofit retrofit) {
                        LoginData model = response.body();
                        if (model == null) {
                            //404 or the response cannot be converted to User.
                            ResponseBody responseBody = response.errorBody();
                            if (responseBody != null) {
                                try {
                                    tv.setText("responseBody = " + responseBody.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                tv.setText("responseBody = null");
                            }
                        } else {
                            //200

                            tv.setText("\nuser :" + model.getUser().getEmail() + "\nWebsite :"
                                    + model.getStatus() + "\nCompany Name :"
                                    + model.getAuthenticationToken());

                            id = model.getUser().getId().toString();
                            token = model.getAuthenticationToken();
                            userEmail = model.getUser().getEmail();

                            update();


                        }


                    }


                    @Override
                    public void onFailure(Throwable t) {
                        tv.setText("t = " + t.getMessage());

                    }
                });

            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void update() {
        if (token != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LogIn Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.safik.dialholic/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LogIn Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.safik.dialholic/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
