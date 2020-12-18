package com.zuehlke.securesoftwaredevelopment.domain;

public class Service {
    private final Integer id;
    private final Integer personId;
    private final String firstName;
    private final String lastName;
    private final String carModel;
    private final String date;
    private final String email;
    private final Integer voucherId;
    private String voucher;

    public Service(Integer id, Integer personId, String firstName, String lastName, String carModel, String date, String email, Integer voucherId) {
        this.id = id;
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.carModel = carModel;
        this.date = date;
        this.email = email;
        this.voucherId = voucherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getDate() {
        return date;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getEmail() {
        return email;
    }
}
