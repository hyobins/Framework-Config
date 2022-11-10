package com.mobigen.framework.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppInfo {
    @JsonProperty("name")
    private String name;

    @JsonProperty("version")
    private String version;
}