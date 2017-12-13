package com.murach.prabhdeep.nutricare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jkps on 12/10/2017.
 */

public class SplashActivity extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Start home activity

        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

        // close splash activity

        finish();

    }


}
