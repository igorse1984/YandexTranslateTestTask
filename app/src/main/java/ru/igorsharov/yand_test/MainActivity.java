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
    ArrayList al = new ArrayList();
    int count = 0;

    public void onMyButtonClick(View view) {
        // сохраняем текст, введенный до нажатия кнопки в переменную
        String strIn = et.getText().toString();
        // отправляем полученную строку в обработку
        new StartParsing().execute(strIn);
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

            // params[0]: EditText -> strIn -> AsyncTask

//            al.add(new GetJSONTranslate().fetchItems(params[0]));
            al.add(new GetJSONLangs().fetchItems(params[0]));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tv.setText(al.get(count).toString());
            count++;
        }
    }
}