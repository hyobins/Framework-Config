package com.mobigen.framework.config;

import com.mobigen.framework.config.model.AppInfo;
import com.mobigen.framework.config.model.IDSConfig;
import com.mobigen.framework.config.model.Properties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropertyManager {
    public static void enrollProperties(Map<?, ?> properties, String url, String path) {

        Map<String, String> mapList = getProperties(properties);

        AppInfo appInfo = new AppInfo();
        appInfo.setName(mapList.get("config-server.name"));
        appInfo.setVersion(mapList.get("config-server.version"));

        List<Properties> propertiesList = new ArrayList<>();

        for (String key : mapList.keySet()) {
            Properties props = new Properties();
            props.setProfile(mapList.get("config-server.profile"));
            props.setLabel(mapList.get("config-server.label"));
            props.setPropertyKey(key);
            props.setPropertyValue(mapList.get(key));

            propertiesList.add(props);
        }

        IDSConfig idsConfig = new IDSConfig();
        idsConfig.setAppInfo(appInfo);
        idsConfig.setProperties(propertiesList);

        /**
         * Build URL
         */
        URI uri = UriComponentsBuilder
                .fromUriString(url) // config-server-manager URL
                .path(path) // config-server-manager API path
                .encode().build().toUri();

        // Send POST request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(uri, idsConfig , String.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Fail : " + response.getStatusCode());
        }
    }

    public static Map<String,String> getProperties(Map<?, ?> properties) {
        return new YamlKeys(properties).getMaps();
    }

}
