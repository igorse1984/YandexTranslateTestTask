package ru.igorsharov.yand_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    Spinner sp;
    ArrayList al = new ArrayList();
    List list = new ArrayList();
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
        sp = (Spinner) findViewById(R.id.spinner);

        new StartParsingLangs().execute();
    }

    private class StartParsingLangs extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            list = new GetJSONLangs().fetchItems();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);
        }
    }


    private class StartParsing extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            // EditText -> strIn -> AsyncTask -> params[0]
            al.add(new GetJSONTranslate().fetchItems(params[0]));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tv.setText(al.get(count).toString());
            count++;
        }
    }
}