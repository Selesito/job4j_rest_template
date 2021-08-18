package ru.job4j.passport.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Objects;

public class Passport {
    private int id;
    private int serial;
    private int number;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passport passport = (Passport) o;
        return id == passport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
