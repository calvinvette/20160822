package com.nextgened.dnd.diceroller;

import org.hibernate.validator.constraints.Email;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlType;

// JPA Annotations  RDBMS to JavaBean ORM
// JAXB Annotations
// JSR-303 Bean Validation Annotations
/*
    Sample JSON:
 {
 "firstName":"Harry",
 "customerId":"1",
 "workAddress":"1",
 "lastName":"Potter",
 "homeAddress":"2",
 "phoneNumber":"+44 0206 987-1234"
 }

*/
//@XmlType
//@XmlRootElement
//@Entity
//@Table(name="UserTable")
public class User implements Serializable {
    @Min(1)
    @Max(99999999999L)
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name="cid")
    private Long customerId = -1L;

    @Size(min = 2, max = 100)
    @Pattern(regexp = "[A-Z,a-z \\-\\']*")
    private String firstName;

    @Size(min = 2, max = 100)
    @Pattern(regexp = "[A-Z,a-z \\-\\']*")
    private String lastName;

    private String phoneNumber;
    private Long workAddress;
    private Long homeAddress;

    @Email
    private String email;

//    @CreditCardNumber
//    private String creditCard;

    private Date birthDate;
    private Date createdDate;
    private Date lastUpdated;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Long workAddress) {
        this.workAddress = workAddress;
    }

    public Long getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Long homeAddress) {
        this.homeAddress = homeAddress;
    }
}
