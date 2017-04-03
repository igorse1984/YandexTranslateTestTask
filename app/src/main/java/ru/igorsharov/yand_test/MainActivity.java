package ru.igorsharov.yand_test;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new StartParsing().execute();
    }


    public class StartParsing extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            new GetJSON().fetchItems();
            return null;
        }

    }
}