package ru.igorsharov.yand_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    Spinner sp;
    ArrayList al = new ArrayList();
    Map map = new HashMap<>();

    int count = 0;

    public void onMyButtonClick(View view) {
        // текст, введенный до нажатия кнопки
        String strIn = et.getText().toString();
        // выбор в Spinner-е
        String selected = map.get(sp.getSelectedItem()).toString();
        // запрос на перевод
        new StartParsingTranslate().execute(strIn, selected);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        sp = (Spinner) findViewById(R.id.spinner);
        // запускаем запрос списка поддерживаемых для перевода языков
        new StartParsingLangs().execute();
    }

    private class StartParsingLangs extends AsyncTask<Void, Void, Void> {
        JSONObject jsonLangsObjAnswer;

        @Override
        protected Void doInBackground(Void... params) {
            jsonLangsObjAnswer = new GetJSONLangs().fetchItems();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            List list = new ArrayList();

            try {
                JSONObject jsonLangsObj = jsonLangsObjAnswer.getJSONObject("langs");

                // конвертируем полученный JSON с "langs" в List для передачи в spinner
                list = JSONHelper.toList(jsonLangsObj);
                // конвертируем полученный JSON с "langs" в Map для получения ключа по названию языка
                map = JSONHelper.toMapReverseKey(jsonLangsObj);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);

        }
    }


    private class StartParsingTranslate extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // EditText -> params[0]
            String langType = new GetJSONDetect().fetchItems(params[0]);
            al.add(new GetJSONTranslate().fetchItems(params[0], params[1], langType));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tv.setText(al.get(count).toString());
            count++;
        }
    }

}