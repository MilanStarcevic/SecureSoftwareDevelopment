package com.zuehlke.securesoftwaredevelopment.domain;

public class Role {
    private final int id;
    private final String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
