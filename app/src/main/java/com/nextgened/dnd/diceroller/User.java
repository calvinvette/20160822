package com.nextgened.dnd.diceroller;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// JPA Annotations  RDBMS to JavaBean ORM
// JAXB Annotations
// JSR-303 Bean Validation Annotations
public class User implements Serializable {
    @Min(1) @Max(99999999999L)
    private Long customerId = -1L;

    @Size(min=2, max=100)
    @Pattern(regexp = "[A-Z,a-z \\-\\']*")
    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
