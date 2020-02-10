package com.example.inclass04;

// Group Number1 3
// Akshay Popli and Neel Solanki
// MainActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    SeekBar seekbar;
    TextView tv_selectedComplexity;
    Button button;
    TextView tv_min2;
    TextView tv_max2;
    TextView tv_avg2;
    int progress;
    double[] values;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("InClass04");

        seekbar = findViewById(R.id.seekBar);
        tv_selectedComplexity = findViewById(R.id.tv_selectedComplexity);
        button = findViewById(R.id.btn_1);
        tv_min2 = findViewById(R.id.tv_min2);
        tv_max2 = findViewById(R.id.tv_max2);
        tv_avg2 = findViewById(R.id.tv_avg2);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_selectedComplexity.setText(String.valueOf(i) + " Times");
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetValues().execute(progress);
                progressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    class GetValues extends AsyncTask<Integer, Integer, ArrayList<Double>>{

        ArrayList<Double> values= new ArrayList<Double>();

        @Override
        protected ArrayList<Double> doInBackground(Integer... integers) {

            values = HeavyWork.getArrayNumbers(integers[0]);
            Log.d("demo ", String.valueOf(values));
            return values;
        }

        @Override
        protected void onPostExecute(ArrayList<Double> doubles) {
            super.onPostExecute(doubles);
            double avg=0;
            Collections.sort(values);
            Log.d("demo 1", String.valueOf(values));
            tv_min2.setText(String.valueOf(values.get(0)));
            tv_max2.setText(String.valueOf(values.get(values.size()-1)));
            for(double val: values){
                avg += val;
            }

            avg = avg/values.size();
            tv_avg2.setText(String.valueOf(avg));
            progressBar.setVisibility(View.INVISIBLE);

        }
    }
}
