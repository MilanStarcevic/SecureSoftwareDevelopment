package com.zuehlke.securesoftwaredevelopment.domain;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String personalNumber;
    private String address;

    public Person(int id, String firstName, String lastName, String personalNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String streetNumber) {
        this.address = streetNumber;
    }
}
