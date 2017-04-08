package ru.igorsharov.yand_test;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class GetJSON {

    private static final String LOG_TAG = "GetJSON";
    protected static final String API_KEY = "trnsl.1.1.20170325T140225Z.5fb87348c9fc5b7a.56c642ead6f88545a2539c4e67fad415f8d7d87b";


    // вспомогательный метод, HTTP провайдер, отправка запроса, получение ответа
   protected String getJSONString(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        String result = response.body().string();

        return result;
    }

    // основной метод класса, делает запрос на сервер для перевода введеного текста
    abstract protected String fetchItems(String str);

    // сепарируем полученный JSON String
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
