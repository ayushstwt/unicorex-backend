package com.ayshriv.patientservice.dto.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class PatientRequestDTO {

    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phoneNumber;

    @JsonProperty("address")
    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @JsonProperty("gender")
    @NotBlank(message = "Gender is required")
    @Size(max = 10, message = "Gender must not exceed 10 characters")
    private String gender;

    @JsonProperty("date_of_birth")
    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    public @NotBlank(message = "First name is required") @Size(max = 100, message = "First name must not exceed 100 characters") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name is required") @Size(max = 100, message = "First name must not exceed 100 characters") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last name is required") @Size(max = 100, message = "Last name must not exceed 100 characters") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last name is required") @Size(max = 100, message = "Last name must not exceed 100 characters") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Invalid email address") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email address") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Phone number is required") @Size(min = 10, max = 10, message = "Phone number must be 10 digits") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number is required") @Size(min = 10, max = 10, message = "Phone number must be 10 digits") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank(message = "Address is required") @Size(max = 255, message = "Address must not exceed 255 characters") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") @Size(max = 255, message = "Address must not exceed 255 characters") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Gender is required") @Size(max = 10, message = "Gender must not exceed 10 characters") String getGender() {
        return gender;
    }

    public void setGender(@NotBlank(message = "Gender is required") @Size(max = 10, message = "Gender must not exceed 10 characters") String gender) {
        this.gender = gender;
    }

    public @NotBlank(message = "Date of birth is required") String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "Date of birth is required") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PatientRequestDTO(String firstName, String lastName, String email, String phoneNumber, String address, String gender, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public PatientRequestDTO() {
    }
}
