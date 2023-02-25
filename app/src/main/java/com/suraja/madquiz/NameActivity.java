package com.suraja.madquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {
    private Button btnStart;
    private EditText etEntername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        btnStart = findViewById(R.id.btnStart);
        etEntername= findViewById(R.id.etEntername);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(meroValidation()){
                    String name= etEntername.getText().toString();

                    Intent intent= new Intent(NameActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private boolean meroValidation() {
        if(etEntername.getText().toString().trim().isEmpty()){
            etEntername.setError("Enter Your Name");
            return false;
        }
        return true;

    }


}
