package com.zuehlke.securesoftwaredevelopment.config;

public class Entity {
    private final String type;
    private final String id;
    private final String before;
    private final String after;

    public Entity(String type, String id, String before, String after) {
        this.type = type;
        this.id = id;
        this.before = before;
        this.after = after;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", before='" + before + '\'' +
                ", after='" + after + '\'' +
                '}';
    }
}
