package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.Gender;

import java.util.ArrayList;
import java.util.List;

public class GymGoer {
    private int userId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String address;
    private int phone;
    private List<Routine> routines = new ArrayList<>();

    public GymGoer(int phone, String address, Gender gender, String lastName, String firstName, int userId) {
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.lastName = lastName;
        this.firstName = firstName;
        this.userId = userId;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void addToRoutine(Routine routine) {
        routines.add(routine);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
