package com.murach.prabhdeep.nutricare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.height;


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
                EditText etUserName = (EditText) findViewById(R.id.txtUserName);
                EditText etUserPassword = (EditText) findViewById(R.id.txtUserPassword);
                EditText etUserFirstName = (EditText) findViewById(R.id.txtUserFirstName);
                EditText etUserLastName = (EditText) findViewById(R.id.txtUserLastName);
                EditText etUserHeight = (EditText) findViewById(R.id.txtUserHeight);
                EditText etUserWeight = (EditText) findViewById(R.id.txtUserWeight);
                EditText etUserGoalWeight = (EditText) findViewById(R.id.txtUserGoalWeight);
                EditText etUserAddress = (EditText) findViewById(R.id.txtUserAddress);


                final String txtUserName = etUserName.getText().toString();
                final String txtUserPassword = etUserPassword.getText().toString();
                final String txtUserFirstName = etUserFirstName.getText().toString();
                final String txtUserLastName = etUserLastName.getText().toString();
                final Double txtUserHeight = Double.parseDouble(etUserHeight.getText().toString());
                final Double txtUserWeight = Double.parseDouble(etUserWeight.getText().toString());
                final Double txtUserGoalWeight = Double.parseDouble(etUserGoalWeight.getText().toString());
                final String txtUserAddress = etUserAddress.getText().toString();
                final Double UserBMI = calculateBMI(txtUserHeight,txtUserWeight);
                final String UserGoalType = getGoal(txtUserWeight,txtUserGoalWeight);


                // Click event trigger here
//                String res = "";
                final Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
                queue = Volley.newRequestQueue(getApplicationContext());
                // Request a string response from the provided URL.
                StringRequest registerRequest = new StringRequest(
                        Request.Method.POST, registerURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                Toast.makeText(getApplicationContext(), response,
//                                        Toast.LENGTH_SHORT).show();
//                                res = response;
//                                startActivity(intent);

                                 if (response.trim().equalsIgnoreCase("failure")) {
                                        Toast.makeText(getApplicationContext(), "Registration Failed. Please check input data.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                 else if (response.trim().equalsIgnoreCase("success")) {
//                                    startActivity(intent);
                                     Toast.makeText(getApplicationContext(), "Registration Success.",
                                             Toast.LENGTH_SHORT).show();
                                     startActivity(loginIntent);
                                 }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(),
                                Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Registration Failed. Please check input data.",
                                Toast.LENGTH_SHORT).show();
                        Log.d("---------- error"," error.toString() ");
                    }
                }

                ) { // query params
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Username", txtUserName);
                        params.put("fname",txtUserFirstName );
                        params.put("lname",txtUserLastName );
                        params.put("height", txtUserHeight.toString());
                        params.put("weight",txtUserWeight.toString() );
                        params.put("goal", UserGoalType);
                        params.put("goal_weight", txtUserGoalWeight.toString());
                        params.put("address",txtUserAddress );
                        params.put("pwd",txtUserPassword );
                        params.put("ini_bmi",UserBMI.toString() );

                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(registerRequest);
            }
        });
    }




    private static Double calculateBMI(Double height,Double weight) {

        Double bmi; //

        // height = 190;
        // weight = 200;

        bmi = (703 * weight ) / (height * height);

        return bmi;
    }


    private static String getGoal(Double userWeight,Double goalWeight) {

        String goalType;

        if(userWeight > goalWeight) {
            goalType = "Lose Weight";
        }
        else if (userWeight < goalWeight) {
            goalType = "Gain Weight";
        }
        else {
            goalType = "Maintain Weight";

        }

        return goalType;
    }






}





