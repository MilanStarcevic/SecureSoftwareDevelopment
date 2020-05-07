package com.zuehlke.securesoftwaredevelopment.domain;

import java.util.List;

public class Service {
    private final Integer id;
    private final Integer personId;
    private final List<String> properties;

    public Service(Integer id, Integer personId, List<String> properties) {
        this.id = id;
        this.personId = personId;
        this.properties = properties;
    }

    public Integer getId() {
        return id;
    }

    public List<String> getProperties() {
        return properties;
    }

    public Integer getPersonId() {
        return personId;
    }
}
