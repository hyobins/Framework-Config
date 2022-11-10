package com.mobigen.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
public class StartListener implements ApplicationListener<ApplicationStartedEvent> {
    @Value("${common.application.service.config-server-manager-url}") String url;
    @Value("${common.application.service.config-server-manager-path}") String path;
    public static String resourceFile = "";

    public void init() throws IOException {
        ClassPathResource resource = new ClassPathResource(resourceFile);
        InputStream inputStream = new FileInputStream(resource.getFile());
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);

        PropertyManager.enrollProperties(data, url, path);
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("======== Starting Properties Enroll Process ========");
        try {
            init();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("======== Starting SpringBoot Application ===========");
    }

}
