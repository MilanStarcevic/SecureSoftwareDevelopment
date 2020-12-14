package com.zuehlke.securesoftwaredevelopment.domain;

import org.thymeleaf.util.StringUtils;

public class Voucher {
    private static final int VOUCHER_CODE_LENGTH = 6;
    private final int id;
    private final String code;
    private final int discountPercentage;

    public Voucher(int id, String code, int discountPercentage) {
        this.id = id;
        this.code = code;
        this.discountPercentage = discountPercentage;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public static String generateCode() {
        return StringUtils.randomAlphanumeric(VOUCHER_CODE_LENGTH);
    }
}
