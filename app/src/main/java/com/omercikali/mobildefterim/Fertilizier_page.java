package com.omercikali.mobildefterim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Fertilizier_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizier_page);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in,R.anim.turn_anim_out);
    }
}