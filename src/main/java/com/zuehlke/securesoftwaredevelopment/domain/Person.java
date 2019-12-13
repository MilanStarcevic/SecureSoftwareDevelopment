package com.zuehlke.securesoftwaredevelopment.domain;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String personalNumber;
    private String streetNumber;

    public Person(int id, String firstName, String lastName, String personalNumber, String streetNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.streetNumber = streetNumber;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
