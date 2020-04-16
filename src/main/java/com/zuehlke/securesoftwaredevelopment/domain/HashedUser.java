package com.zuehlke.securesoftwaredevelopment.domain;

public class HashedUser {
    private String username;
    private String passwordHash;
    private String salt;
    private String totpKey;

    public HashedUser(String username, String passwordHash, String salt, String totpKey) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.totpKey = totpKey;
    }

    public String getUsername() { return username; }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getTotpKey() {
        return totpKey;
    }

    public void setTotpKey(String totpKey) {
        this.totpKey = totpKey;
    }
}
