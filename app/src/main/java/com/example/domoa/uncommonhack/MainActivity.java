package com.example.domoa.uncommonhack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.textView) TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SparkAPI.makeRequest(this, "setRed", "255");
    }
}
