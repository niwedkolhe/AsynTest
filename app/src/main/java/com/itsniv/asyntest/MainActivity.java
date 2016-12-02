package com.itsniv.asyntest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    private ProgressBar progressBar;
    TextView txt;
    Integer count =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(10);
        btn = (Button) findViewById(R.id.btn);
        btn.setText("Start");
        txt = (TextView) findViewById(R.id.output);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        count =1;
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        switch (view.getId()) {
            case R.id.btn:
                new MyTask().execute(10);
                break;
        }
    }
    public class MyTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {

            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Task Completed";
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
            txt.setText(result);
            btn.setText("Restart");
            Toast.makeText(MainActivity.this,"Task Completed",Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onPreExecute() {
            txt.setText("Task Starting...");
            Toast.makeText(MainActivity.this,"Task Starting...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            txt.setText("Running..."+ values[0]);
            progressBar.setProgress(values[0]);
        }
    }

}
