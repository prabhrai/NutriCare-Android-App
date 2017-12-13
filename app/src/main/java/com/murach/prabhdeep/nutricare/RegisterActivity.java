package com.murach.prabhdeep.nutricare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends Activity {
    private RequestQueue queue;
    String registerURL = "https://nutricare.xyz/php/register_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // on cancel , go back to login
        Button cancelButton = (Button) findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        // ------------------------------------------------------------------------------------------------ register

        Button registerButton = (Button) findViewById(R.id.btnRegisterSubmit);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Click event trigger here
                String res = "";
                final Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
//                startActivity(intent);
                queue = Volley.newRequestQueue(getApplicationContext());
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, registerURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response,
                                        Toast.LENGTH_SHORT).show();
//                                res = response;
//                                startActivity(intent);
                                if (response.trim().equalsIgnoreCase("success")) {
//                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "Success",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("---------- error"," error.toString() ");
                    }
                }

                ) { // query params
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Username", "test111@gmail.com");
                        params.put("fname", "test1111");
                        params.put("lname", "test1111");
                        params.put("height", "1222");
                        params.put("weight", "1222");
                        params.put("goal", "fsfasfdsf");
                        params.put("goal_weight", "1222");
                        params.put("address", "dfasdfasfsadfdasf");
                        params.put("pwd", "DFasdfasdfadsfadsfsad");
                        params.put("ini_bmi", "1111");

//                        params.put("username", "test");
//                        params.put("password", "test");

                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }

}





