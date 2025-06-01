package ch.fhnw.brew.data.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerID;

    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String street;

    @Min(value = 1000, message = "ZIP code must be at least 1000")
    @Max(value = 9999, message = "ZIP code must be at most 9999")
    private Integer zipCode;

    private String city;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(\\+41|0)[0-9]{9}$", message = "Phone must be a valid Swiss number")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}