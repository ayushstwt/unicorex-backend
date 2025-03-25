package com.ayshriv.patientservice.entity;

import com.ayshriv.patientservice.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="patient")
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseEntity {

    @Column(nullable = false,name="first_name")
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true, name="email")
    private String email;

    @Column(nullable = false, unique = true, name="phone_number")
    private String phoneNumber;

    @Column(nullable = false, name="address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "gender")
    private Gender gender;

    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dateOfBirth;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Patient(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy, Boolean isDeleted, Boolean isActive, String firstName, String lastName, String email, String phoneNumber, String address, Gender gender, LocalDate dateOfBirth) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, isDeleted, isActive);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Patient(String firstName, String lastName, String email, String phoneNumber, String address, Gender gender, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Patient()
    {}
}
