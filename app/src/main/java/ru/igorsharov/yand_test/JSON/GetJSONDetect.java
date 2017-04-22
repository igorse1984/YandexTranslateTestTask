package ru.igorsharov.yand_test.JSON;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetJSONDetect extends GetJSON{
    private static final String LOG_TAG = "GetJSONDetect";
    private static final String URL = "https://translate.yandex.net/api/v1.5/tr.json/detect?";

    public String fetchItems(String str) {
        String answLangType = null;
        try {
            // компоновка url запроса
            String url = Uri.parse(URL)
                    .buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    .appendQueryParameter("text", str)
                    .build().toString();
//            jsonObjDetectAnswer = new JSONObject(getJSONString(url));
         answLangType =  jsonParser(getJSONString(url));


        } catch (IOException ioe) {
            Log.e(LOG_TAG, "ОШИБКА ЗАГРУЗКИ ДАННЫХ", ioe);
        } catch (JSONException joe) {
            Log.e(LOG_TAG, "ОШИБКА ПОЛУЧЕНИЯ JSON", joe);
        }
        return answLangType;
    }

    // достаем определенный сервером язык из ответа
    protected String jsonParser(String jsonString) throws JSONException {

        JSONObject jsonBody = new JSONObject(jsonString);
        String answ = jsonBody.getString("lang");

        return answ;
    }

}
