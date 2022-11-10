package com.mobigen.framework.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Properties {
    @JsonProperty("propertyKey")
    private String propertyKey;

    @JsonProperty("propertyValue")
    private String propertyValue;

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("label")
    private String label;
}