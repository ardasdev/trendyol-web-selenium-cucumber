package utils;

import model.ElementModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ElementReader {
    private static final Map<String, ElementModel> locatorMap = new HashMap<>();

    static {
        try (InputStream is = ElementReader.class.getClassLoader().getResourceAsStream("elements.json")) {
            if (is == null) throw new RuntimeException("elements.json not found!");
            JSONArray array = new JSONArray(new JSONTokener(is));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                ElementModel m = new ElementModel();
                m.setKey(obj.getString("key"));
                m.setValue(obj.getString("value"));
                m.setType(obj.getString("type"));
                locatorMap.put(m.getKey(), m);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load elements.json", e);
        }
    }

    public static ElementModel get(String key) {
        if (!locatorMap.containsKey(key)) throw new RuntimeException("Element not found: " + key);
        return locatorMap.get(key);
    }
}