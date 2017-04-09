package ru.igorsharov.yand_test;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetJSONTranslate extends GetJSON{
    private static final String LOG_TAG = "GetJSONTranslate";
    private static final String URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";

    @Override
    protected String fetchItems(String str) {
        String translate = null;
        try {
            // компоновка url запроса
            String url = Uri.parse(URL)
                    .buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    .appendQueryParameter("text", str)
                    .appendQueryParameter("lang", "ru-en")
                    .build().toString();

            translate = jsonParser(getJSONString(url));



            // test output
            System.out.println("JSON answer: " + getJSONString(url));


        } catch (IOException ioe) {
            Log.e(LOG_TAG, "ОШИБКА ЗАГРУЗКИ ДАННЫХ", ioe);
        } catch (JSONException joe) {
            Log.e(LOG_TAG, "ОШИБКА ПОЛУЧЕНИЯ JSON", joe);
        }
        return translate;
    }

    @Override
    protected String jsonParser(String jsonString) throws JSONException {

        JSONObject jsonBody = new JSONObject(jsonString);
        String translate = jsonBody.getString("text");

        return removeExtraChar(translate);
    }

    // удаление лишних символов из строкового ответа
    public static String removeExtraChar(String s) {

        return s.substring(2, s.length() - 2);
    }
}
