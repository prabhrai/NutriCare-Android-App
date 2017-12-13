package com.murach.prabhdeep.nutricare;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

import android.app.Application;
import android.util.Log;

import com.android.volley.*;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.lang.String;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class LoginActivity extends Activity {
    // Instantiate the RequestQueue.


    private RequestQueue queue;
    //    RequestQueue queue = newRequestQueue(getApplicationContext());
    String loginURL = "https://nutricare.xyz/php/validate_user.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Switch remUN = (Switch) findViewById(R.id.rememberLoginSwitch);
        Boolean switchState = remUN.isChecked();

        remUN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // The toggle is enabled
                    Log.d("NUTRICare tag" , "State checked");

                    } else {
                        // The toggle is disabled
                    }
                 }

            }
        );





        // get reference to edittexts for user input
        final EditText usernameET = (EditText) findViewById(R.id.txtUserName);
        final EditText passwordET = (EditText) findViewById(R.id.txtUserPassword);

        // delete
        usernameET.setText("user@gmail.com");
        passwordET.setText("password");


        // ------------------------------------------------------------------------------------------------ get user login

        Button loginButton = (Button) findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Click event trigger here
                final Intent intent = new Intent(getApplicationContext(), WeightLogActivity.class);
//                startActivity(intent);
                queue = Volley.newRequestQueue(getApplicationContext());
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, loginURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response.trim(),
                                        Toast.LENGTH_SHORT).show();

                                if (response.trim().equalsIgnoreCase("success")) {
                                    startActivity(intent);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                ) { // query params
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", usernameET.getText().toString());
                        params.put("password", passwordET.getText().toString());

                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });


        // ------------------------------------------------------------------------------------------------ get register log

        Button registerButton = (Button) findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Click event trigger here
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        });


    } // onCreate

} // activity

