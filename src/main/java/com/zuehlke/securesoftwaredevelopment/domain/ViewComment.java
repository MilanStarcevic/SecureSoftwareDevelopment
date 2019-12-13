package com.zuehlke.securesoftwaredevelopment.domain;

public class ViewComment {
    private String personName;
    private String comment;

    public ViewComment(String personName, String comment) {
        this.personName = personName;
        this.comment = comment;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
