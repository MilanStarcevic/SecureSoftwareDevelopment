package com.zuehlke.securesoftwaredevelopment.domain;

import java.util.Map;

public class Service {
    private final Integer id;
    private final Map<String, String> properties;

    public Service(Integer id, Map<String, String> properties) {
        this.id = id;
        this.properties = properties;
    }

    public Integer getId() {
        return id;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
