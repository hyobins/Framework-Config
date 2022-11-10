package com.mobigen.framework.config;

import java.util.*;

public class YamlKeys {
    private Set<String> keys = new HashSet<>();
    private Map<String, String> mapList = new HashMap<>();

    YamlKeys(Map<?, ?> data) {
        getPropertyKeys(data, null);
    }

    public void getPropertyKeys(final Map<?, ?> data, String path) {
        if (path == null)
            path = "";

        for (Object key : data.keySet()) {
            String base = path;
            final Object value = data.get(key);
            if (key instanceof String) {
                if (Objects.equals(base, "")) {
                    base = (String) key;
                } else {
                    base = base + "." + key;
                }
            }
            if (value instanceof Map) {
                getPropertyKeys((Map<?, ?>) value, base);
            } else {
                keys.add(base);
                mapList.put(base, value.toString());
            }
        }
    }

    Map<String, String> getMaps() {
        return mapList;
    }

}
