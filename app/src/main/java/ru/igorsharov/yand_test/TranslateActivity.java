package ru.igorsharov.yand_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.igorsharov.yand_test.JSON.GetJSONDetect;
import ru.igorsharov.yand_test.JSON.GetJSONLangs;
import ru.igorsharov.yand_test.JSON.GetJSONTranslate;
import ru.igorsharov.yand_test.JSON.JSONHelper;

public class TranslateActivity extends Fragment implements View.OnClickListener {

    EditText et;
    TextView tv;
    Spinner sp;
    Button btn;
    ArrayList<TranslatedTextObject> historyAl = new ArrayList<>();

    Map map = new HashMap<>();
    String strIn;
    String selectedLang;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_translate, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        et = (EditText) view.findViewById(R.id.editText);
        tv = (TextView) view.findViewById(R.id.textView);
        sp = (Spinner) view.findViewById(R.id.spinner);
        btn = (Button) view.findViewById(R.id.button);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(this);

        // запрос списка поддерживаемых для перевода языков и их загрузка в spinner
        new StartParsingLangs().execute();

        // выбор способа взаимодействия с пользователем для отправки на перевод
        if (btn.getVisibility() == View.GONE)

        {
            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
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

    @Override
    public void onClick(View v) {
        if (!et.getText().toString().equals("")) {
            selectedLang = map.get(sp.getSelectedItem()).toString();
            // если есть текст, введенный до нажатия кнопки
            strIn = et.getText().toString();
            // запрос на перевод
            new StartParsingTranslate().execute(strIn, selectedLang);

        } else
            tv.setText("Пусто");
    }


    /**
     * парсинг перевода
     */
    private class StartParsingTranslate extends AsyncTask<String, Void, Void> {
        TranslatedTextObject tt = new TranslatedTextObject();

        @Override
        protected Void doInBackground(String... params) {
            // определение языка
            String langType = new GetJSONDetect().fetchItems(params[0]);
            // EditText -> params[0]
            tt.setRequestedText(params[0]);
            tt.setTranslatedText(new GetJSONTranslate().fetchItems(params[0], params[1], langType));
            // флаг избранного
            tt.setFavourite(false);
            TranslatedTextObject.translate.add(tt);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tv.setText(tt.getTranslatedText());
            //
        }
    }


    /**
     * парсинг доступных для перевода языков
     */
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
            // выбор в Spinner-е
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_custom_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);

        }
    }
}