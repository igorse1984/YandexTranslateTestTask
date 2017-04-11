package ru.igorsharov.yand_test;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 *  класс для получения списка поддерживаемых для меревода языков
 */

public class GetJSONLangs extends GetJSON {

    private static final String LOG_TAG = "GetJSONLang";
    private static final String URL = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?";

    protected JSONObject fetchItems() {
        JSONObject jsonObjLangsAnswer = null;
        try {
            // компоновка url запроса
            String url = Uri.parse(URL)
                    .buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    // выбран русский язык для ответа со списком языков
                    .appendQueryParameter("ui", "ru")
                    .build().toString();
            jsonObjLangsAnswer = new JSONObject(getJSONString(url));


        } catch (IOException ioe) {
            Log.e(LOG_TAG, "ОШИБКА ЗАГРУЗКИ ДАННЫХ", ioe);
        } catch (JSONException joe) {
            Log.e(LOG_TAG, "ОШИБКА ПОЛУЧЕНИЯ JSON", joe);
        }
        return jsonObjLangsAnswer;
    }



}
