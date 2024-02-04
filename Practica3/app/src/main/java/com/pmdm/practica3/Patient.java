package com.pmdm.practica3;


public class Patient {
    private String id;
    private String name;
    private String surname;
    private String temperature;
    private String format;
    private String city;
    private String province;

    Patient() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getFormat() {
        return format;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", temperature='" + temperature + '\'' +
                ", format='" + format + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
