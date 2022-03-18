package com.nupen.hapa10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class infoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


    }




    public void GeriDonClicked(View view){
        Intent intentToMain= new Intent(infoActivity.this,GirisActivity.class);
        startActivity(intentToMain);
        finish();
    }
}
