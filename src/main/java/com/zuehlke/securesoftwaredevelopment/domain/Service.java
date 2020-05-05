package com.zuehlke.securesoftwaredevelopment.domain;

import java.util.List;

public class Service {
    private final Integer id;
    private final List<String> properties;

    public Service(Integer id, List<String> properties) {
        this.id = id;
        this.properties = properties;
    }

    public Integer getId() {
        return id;
    }

    public List<String> getProperties() {
        return properties;
    }
}
