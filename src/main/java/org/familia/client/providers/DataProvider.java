package org.familia.client.providers;

import org.familia.client.apps.networks.request.user.User;

import java.util.HashMap;
import java.util.Map;

public class DataProvider {
    private Map<String, Object> dataMap;

    public DataProvider() {
        dataMap = new HashMap<>();
    }

    public Object getDataObject(String key) {
        return dataMap.get(key);
    }

    public boolean isAvailableDataObject(String key) {
        if (!dataMap.containsKey(key)) {
            return false;
        }
        if (dataMap.get(key) == null) {
            return false;
        }
        return true;
    }

    public void addDataObject(String key, Object object) {
        dataMap.put(key, object);
    }
}
