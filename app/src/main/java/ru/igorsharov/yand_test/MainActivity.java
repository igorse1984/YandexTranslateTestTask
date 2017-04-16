package ru.igorsharov.yand_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    ArrayList<HashMap> historyAl = new ArrayList();

    Map map = new HashMap<>();
    String strIn;
    String selectedLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        sp = (Spinner) findViewById(R.id.spinner);

        // запрос списка поддерживаемых для перевода языков и их загрузка в spinner
        new StartParsingLangs().execute();
        // выбор в Spinner-е

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                selectedLang = map.get(sp.getSelectedItem()).toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!et.getText().toString().equals("")) {
                    // если есть текст, введенный до нажатия кнопки
                    strIn = et.getText().toString();
                } else
                    tv.setText("Пусто");

                // запрос на перевод
                new StartParsingTranslate().execute(strIn, selectedLang);
            }
        });
    }

//    public void onMyButtonClick(View view) {
//
//        if (et.getText().toString().equals("")) {
//            // Здесь код, если EditText пуст
//            Toast.makeText(this, "Текстовое поле пусто", Toast.LENGTH_SHORT).show();
//        } else {
//            // если есть текст, введенный до нажатия кнопки
//            strIn = et.getText().toString();
//        }
//
//        // выбор в Spinner-е
//        String selectedLang = map.get(sp.getSelectedItem()).toString();
//        // запрос на перевод
//        new StartParsingTranslate().execute(strIn, selectedLang);
//
//    }

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


    private class StartParsingTranslate extends AsyncTask<String, String, Void> {
        String translate;
        HashMap<String, String> historyHm = new HashMap();

        @Override
        protected Void doInBackground(String... params) {
            // EditText -> params[0]
            String langType = new GetJSONDetect().fetchItems(params[0]);
            translate = new GetJSONTranslate().fetchItems(params[0], params[1], langType).toString();
            historyHm.put(params[0], translate);
            historyAl.add(historyHm);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tv.setText(translate);
        }
    }

}