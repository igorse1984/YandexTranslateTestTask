package ru.igorsharov.yand_test;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Button btn;
    ArrayList<String[]> historyAl = new ArrayList();

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
        btn = (Button) findViewById(R.id.button);
        btn.setVisibility(View.VISIBLE);

        // запрос списка поддерживаемых для перевода языков и их загрузка в spinner
        new StartParsingLangs().execute();
        // выбор в Spinner-е

        if (btn.getVisibility() == View.GONE) {
            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    System.out.println("[beforeTextChanged] text: " + s + " start: " + start + " count: " + count + " after: " + after);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    System.out.println("[onTextChanged] text: " + s + " start: " + start + " count: " + count);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!et.getText().toString().equals("")) {
                        selectedLang = map.get(sp.getSelectedItem()).toString();
                        // если есть текст, введенный до нажатия кнопки
                        strIn = et.getText().toString();
                        // запрос на перевод
                        new StartParsingTranslate().execute(strIn, selectedLang);
                    } else
                        tv.setText("Пусто");
                }
            });
        }
    }

    // обработка нажатий кнопки перевода
    public void onMyButtonClick(View view) {

        if (et.getText().toString().equals("")) {
            // Здесь код, если EditText пуст
            Toast.makeText(this, "Текстовое поле пусто", Toast.LENGTH_SHORT).show();
        } else {
            // если есть текст, введенный до нажатия кнопки
            strIn = et.getText().toString();
            // выбор в Spinner-е
            String selectedLang = map.get(sp.getSelectedItem()).toString();
            // запрос на перевод
            new StartParsingTranslate().execute(strIn, selectedLang);
        }
    }

    public void onMyButtonClickActivity(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("HISTORY_LIST", historyAl);
        startActivity(intent);
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
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_custom_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);

        }
    }


    private class StartParsingTranslate extends AsyncTask<String, String, Void> {
        String[] historyTranslate = new String[3];

        @Override
        protected Void doInBackground(String... params) {
            // EditText -> params[0]
            String langType = new GetJSONDetect().fetchItems(params[0]);
            historyTranslate[0] = params[0];
            historyTranslate[1] = new GetJSONTranslate().fetchItems(params[0], params[1], langType).toString();
            // флаг избранного
            historyTranslate[2] = "";
            historyAl.add(historyTranslate);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tv.setText(historyTranslate[1]);
        }
    }

}