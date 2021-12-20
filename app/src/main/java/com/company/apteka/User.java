package com.company.apteka;

public class User {

    private String fio;
    private String phone;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(String fio, String phone) {
        this.fio = fio;
        this.phone = phone;
    }
}
