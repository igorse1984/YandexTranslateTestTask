package ru.igorsharov.yand_test;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GetJSONLangs extends GetJSON {

    private static final String LOG_TAG = "GetJSONLang";
    private static final String URL = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?";

    protected List<String> fetchItems() {
        List answer = null;
        try {
            // компоновка url запроса
            String url = Uri.parse(URL)
                    .buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    .appendQueryParameter("ui", "ru")
                    .build().toString();

            answer = jsonLangParser(getJSONString(url));


        } catch (IOException ioe) {
            Log.e(LOG_TAG, "ОШИБКА ЗАГРУЗКИ ДАННЫХ", ioe);
        } catch (JSONException joe) {
            Log.e(LOG_TAG, "ОШИБКА ПОЛУЧЕНИЯ JSON", joe);
        }
        return answer;
    }


    protected List<String> jsonLangParser(String jsonString) throws JSONException {
        JSONObject jsonBody = new JSONObject(jsonString);
        JSONObject jsonBodyLangs = jsonBody.getJSONObject("langs");


        return JSONHelper.toList(jsonBodyLangs);
    }





}
