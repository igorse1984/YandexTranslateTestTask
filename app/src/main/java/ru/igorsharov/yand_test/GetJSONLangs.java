package ru.igorsharov.yand_test;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class GetJSONLangs extends GetJSON {

    private static final String LOG_TAG = "GetJSONLang";
    private static final String URL = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?";

    @Override
    protected String fetchItems(String str) {
        String answer = null;
        try {
            // компоновка url запроса
            String url = Uri.parse(URL)
                    .buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    .appendQueryParameter("ui", "ru")
                    .build().toString();

            answer = jsonParser(getJSONString(url));


            System.out.println("!!!!!JSON URL: " + url);
            // test output
            System.out.println("!!!!!JSON answer: " + getJSONString(url));


        } catch (IOException ioe) {
            Log.e(LOG_TAG, "ОШИБКА ЗАГРУЗКИ ДАННЫХ", ioe);
        } catch (JSONException joe) {
            Log.e(LOG_TAG, "ОШИБКА ПОЛУЧЕНИЯ JSON", joe);
        }
        return answer;
    }

    @Override
    protected String jsonParser(String jsonString) throws JSONException {
        JSONObject jsonBody = new JSONObject(jsonString);
        String answer = jsonBody.getString("langs");
//        JSONObject jsonLangs = jsonBody.getJSONObject("langs");


        Map jsonMap = JSONHelper.toMap(jsonBody);


//        JSONArray jsonArray = jsonBody.getJSONArray("dirs");
//        for (int i = 0; i < jsonArray.length(); i++) {
//
//            System.out.println("!!!!ARRAY: " + jsonArray.get(i));
//        }
        return jsonMap.toString();
//        return null;
    }



}
