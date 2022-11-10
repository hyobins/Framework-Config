package com.mobigen.framework.config.model;

import lombok.Data;

import java.util.List;

@Data
public class IDSConfig {
    private AppInfo appInfo;
    private List<Properties> properties;
}
