package com.murach.prabhdeep.nutricare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import java.lang.String;

import static android.R.attr.data;
import static android.R.attr.name;
import static android.R.id.home;
import static android.media.CamcorderProfile.get;

//import static com.android.volley.toolbox.Volley.newRequestQueue;


public class WeightLogActivity extends Activity {
    String weightURL = "https://nutricare.xyz/php/get_users_log_android.php";
    private RequestQueue queue2;
    private RequestQueue queue3;
    ArrayList<UserWeightLog> UWL_list = new ArrayList<UserWeightLog>();

    LinearLayout ll;



    private ListView itemsListView;

    // temporary string to show the parsed response
    private String jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightlog);
        itemsListView = (ListView) findViewById(R.id.itemsListView);


        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");



        final UserWeightDB db = new UserWeightDB(this);
        db.TrimDB();

        final Intent intent_add_log = new Intent(getApplicationContext(), AddLogActivity.class);

        Button btn_add = (Button) findViewById(R.id.btnAddUserWeightLog);
        btn_add.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                           intent_add_log.putExtra("username",username);
                                           startActivity(intent_add_log);

                                       }

                                   });

        StringBuilder sb = new StringBuilder();
//        final UserWeightLog uwl = new UserWeightLog("name111", "352311452345", 1121.11, 111.11);
//        long insertId = db.insertUserWeightLog(uwl);
//        if (insertId > 0) {
//            sb.append("Row inserted! Insert Id: " + insertId + "\n");
//        }
//        Toast.makeText(getApplicationContext(), "DB Status " + insertId, Toast.LENGTH_SHORT).show();

        Button btnn = (Button) findViewById(R.id.btnGetUserWeight);
        btnn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Click event trigger here
                queue3 = Volley.newRequestQueue(getApplicationContext());
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, weightURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                Toast.makeText(getApplicationContext(), response,
//                                        Toast.LENGTH_SHORT).show();
//                                JSONArray j = new JSONArray(response.t) ;
                                try {
                                    JSONArray array = new JSONArray(response);
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject object = array.getJSONObject(i);
                                        String username = object.getString("username");
                                        String date = object.getString("date");
                                        String weight = object.getString("weight");
                                        String bmi = object.getString("bmi");


//                                        String username = person.getString("username");
//                                        String date = person.getString("date");
//                                        String weight = person.getString("weight");
//                                        String bmi = person.getString("bmi");
                                        UserWeightLog ulog = new UserWeightLog();

                                        ulog.setUsername(username);
                                        ulog.setDate(date);
                                        ulog.setWeight(Double.parseDouble(weight));
                                        ulog.setBmi(Double.parseDouble(bmi));
                                        UWL_list.add(ulog);

                                        long insertID_ = db.insertUserWeightLog(ulog);
//                                        Toast.makeText(getApplicationContext(), insertID_ + " " , Toast.LENGTH_LONG).show();

//                                        Toast.makeText(getApplicationContext(), username + " " + date + " " + weight + " " + bmi,
//                                                Toast.LENGTH_LONG).show();

                                    }





//                                    // create a List of Map<String, ?> objects
//                                    ArrayList<HashMap<String, String>> data =
//                                            new ArrayList<HashMap<String, String>>();
//                                    for (UserWeightLog item : UWL_list) {
//                                        HashMap<String, String> map = new HashMap<String, String>();
//                                        map.put("date", item.getDate());
//                                        map.put("weight", item.getWeight().toString());
//                                        map.put("bmi", item.getBmi().toString());
//
//                                        data.add(map);
//                                    }
//
////                                // create the resource, from, and to variables
//                                    int resource = R.layout.listview_item;
//                                    String[] from = {"date", "weight","bmi"};
//                                    int[] to = { R.id.listDateTextView, R.id.listWeightTextView , R.id.listBMITextView};
////
//                                    // create and set the adapter
//                                    SimpleAdapter adapter =
//                                            new SimpleAdapter(getApplicationContext(), data, resource, from, to);
//
//                                    itemsListView.setAdapter(adapter);

                                    populateListView(db);
                                }
                                catch (Exception e){

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


                        params.put("username", username);

                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                queue3.add(stringRequest);


            }
        });

    }



    private void populateListView(UserWeightDB db){


        ll = (LinearLayout) findViewById(R.id.listViewHeader);
        ll.setVisibility(View.VISIBLE);

        // create a List of Map<String, ?> objects
        ArrayList<UserWeightLog> db_List = db.getUserWeightLog();


        ArrayList<HashMap<String, String>> data =
                new ArrayList<HashMap<String, String>>();
        for (UserWeightLog item : db_List) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date", item.getDate());
            map.put("weight", item.getWeight().toString());
            map.put("bmi", item.getBmi().toString());

            data.add(map);
        }



        // create the resource, from, and to variables
        int resource = R.layout.listview_item;
        String[] from = {"date", "weight","bmi"};
        int[] to = { R.id.listDateTextView, R.id.listWeightTextView , R.id.listBMITextView};
//
        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(getApplicationContext(), data, resource, from, to);

        itemsListView.setAdapter(adapter);

    }

}



