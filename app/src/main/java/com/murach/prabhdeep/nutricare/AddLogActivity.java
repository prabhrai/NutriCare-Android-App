package com.murach.prabhdeep.nutricare;

import android.app.Activity;
import android.os.Bundle;

public class AddLogActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     
        
        // set the view for the activity using XML
//        setContentView(R.layout.activity_main);

//        TipCalculatorFragment tcf = (TipCalculatorFragment) getFragmentManager().findFragmentById(R.layout.activity_main);

        System.out.println(" ------------------------ tip calculator fragment working -------------------------------");
        getFragmentManager().beginTransaction().replace(android.R.id.content,new AddLogFragment()).commit();

    }
}