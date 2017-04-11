package ru.igorsharov.yand_test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JSONHelper {


    public static Object toJSON(Object object) throws JSONException {
        if (object instanceof Map) {
            JSONObject json = new JSONObject();
            Map map = (Map) object;
            for (Object key : map.keySet()) {
                json.put(key.toString(), toJSON(map.get(key)));
            }
            return json;
        } else if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable) object)) {
                json.put(value);
            }
            return json;
        } else {
            return object;
        }
    }

    public static boolean isEmptyObject(JSONObject object) {
        return object.names() == null;
    }

    public static Map<String, Object> getMap(JSONObject object, String key) throws JSONException {
        return toMap(object.getJSONObject(key));
    }

    public static Map<String, Object> toMap(JSONObject jsonObject) throws JSONException {
        Map<String, Object> map = new HashMap();
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(jsonObject.get(key)));

        }
        return map;
    }

    // метод для возможности получения ключа языка при выборе из Spinner-а
    public static Map<String, String> toMapReverseKey(JSONObject jsonObject) throws JSONException {
        Map<String, String> map = new HashMap();
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String jsonText = jsonObject.getString(key);
            map.put(jsonText, key);

        }
        return map;
    }

    public static List<String> toList(JSONObject jsonObject) throws JSONException {
        List<String> list = new ArrayList<>();
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            list.add((String) jsonObject.get(key));

        }
        return list;
    }


    public static List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }

    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        } else {
            return json;
        }
    }
}

