package ru.igorsharov.yand_test.JSON;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class GetJSON {

    protected static final String LOG_TAG = "GetJSONClass";
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


}
