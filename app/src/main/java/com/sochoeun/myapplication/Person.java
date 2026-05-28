package com.sochoeun.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Person implements Parcelable {
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String house;
    private String street;
    private String sangKat;
    private String khan;
    private String city;

    public Person() {
    }

    protected Person(Parcel in) {
        firstname = in.readString();
        lastname = in.readString();
        phone = in.readString();
        email = in.readString();
        house = in.readString();
        street = in.readString();
        sangKat = in.readString();
        khan = in.readString();
        city = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(firstname);
        parcel.writeString(lastname);
        parcel.writeString(phone);
        parcel.writeString(email);
        parcel.writeString(house);
        parcel.writeString(street);
        parcel.writeString(sangKat);
        parcel.writeString(khan);
        parcel.writeString(city);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSangKat() {
        return sangKat;
    }

    public void setSangKat(String sangKat) {
        this.sangKat = sangKat;
    }

    public String getKhan() {
        return khan;
    }

    public void setKhan(String khan) {
        this.khan = khan;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
