package ru.igorsharov.yand_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    ArrayList al;

    public void onMyButtonClick(View view) {
        // сохраняем текст, введенный до нажатия кнопки в переменную
        String strIn = et.getText().toString();
        // отправляем полученную строку в обработку
        new StartParsing().execute(strIn);
//        System.out.println("реакция то вообще есть?: " + al.get(0).toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);


    }


    private class StartParsing extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // params[0] - текст, полученный из EditText

            al = new GetJSON().fetchItems(params[0]);
//            System.out.println("Что есть ArrayList в doInBack: " + al.get(0));
            return null;
        }

        @Override
        protected void onPostExecute(final Void result) {
//            super.onPostExecute(aVoid);
//            System.out.println("что мы получили?: "+ al.get(0));
            tv.setText(al.get(0).toString());
        }
    }
}