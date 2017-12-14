package com.murach.prabhdeep.nutricare;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Calendar;


import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddLogFragment extends Fragment
        implements  OnClickListener {
//    OnEditorActionListener,
    private EditText txtUserWeight;
    private Button btnSubmit;
    private RequestQueue queue4;
    String logURL = "https://nutricare.xyz/php/add_user_log.php";

    private String username;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    // Getting application context
        context = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_log,
                container, false);



        username = getActivity().getIntent().getStringExtra("username");

        Toast.makeText(context, username,
                Toast.LENGTH_SHORT).show();
//        String username2 = getActivity().getIntent().getExtras().getString("username");
//
//        Toast.makeText(context,username2,
//                Toast.LENGTH_SHORT).show();
        btnSubmit = (Button) view.findViewById(R.id.btnSubmitUserLog);

        btnSubmit.setOnClickListener(this);

        // return the View for the layout
        return view;



    }

//
//    @Override
//    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        /*
//        int keyCode = -1;
//        if (event != null) {
//            keyCode = event.getKeyCode();
//        }
//        if (actionId == EditorInfo.IME_ACTION_DONE ||
//            actionId == EditorInfo.IME_ACTION_NEXT ||
//            actionId == EditorInfo.IME_ACTION_UNSPECIFIED ||
//            keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
//            keyCode == KeyEvent.KEYCODE_ENTER) {
//            calculateAndDisplay();
//        }
//
//        */
//        return false;
//    }

    @Override
    public void onClick(View v) {


        Toast.makeText(context, "vvb",
                Toast.LENGTH_SHORT).show();


        switch (v.getId()) {
        case R.id.btnSubmitUserLog:
            final String log_date = GetToday();

            final Intent loginIntent = new Intent(context,LoginActivity.class);
            final Intent weightLogIntent = new Intent(context,WeightLogActivity.class);

            weightLogIntent.putExtra("username",username);

            txtUserWeight = (EditText) getView().findViewById(R.id.txtUserCurrentWeight);
            final String tempWT = txtUserWeight.getText().toString();
            Double userWT = null;
            try{
                 userWT = Double.parseDouble(tempWT);
            }
            catch (NumberFormatException n){
                Toast.makeText(context, "Invalid Input.",
                        Toast.LENGTH_SHORT).show();
            }

            if(tempWT.equalsIgnoreCase("") || userWT == null  ){
                Toast.makeText(context, "Invalid Input.",
                        Toast.LENGTH_SHORT).show();
                return;
            }


//            Toast.makeText(context, "vvdrgfb" + date ,
//                    Toast.LENGTH_SHORT).show();


            queue4 = Volley.newRequestQueue(context);
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, logURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                            Toast.makeText(context, response.trim(),
//                                    Toast.LENGTH_SHORT).show();

                            if (response.trim().equalsIgnoreCase("success")) {

                                Toast.makeText(context, "Log Added Successfully",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(weightLogIntent);
                            }
                            else if (response.trim().equalsIgnoreCase("failure")) {

                                Toast.makeText(context, "Log Addition Failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            ) { // query params
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user",username);
                    params.put("Current_Weight", tempWT);
                    params.put("full_date", log_date);

                    return params;
                }
            };

            // Add the request to the RequestQueue.
            queue4.add(stringRequest);

//            Toast.makeText(context, "",
//                    Toast.LENGTH_SHORT).show();


            break;

        }
    }

    public static String GetToday(){
        Date presentTime_Date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return dateFormat.format(presentTime_Date);
    }


}