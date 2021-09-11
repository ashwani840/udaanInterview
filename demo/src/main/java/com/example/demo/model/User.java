package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 3634647829982296142L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String pinCode;
    private String userType;
    private String covidResult;
    private boolean travelHistory;
    private boolean contactWithUser;

    @Builder
    public User(String name, String phoneNumber, String pinCode, String userType, String covidResult, boolean travelHistory, boolean contactWithUser) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
        this.userType = userType;
        this.covidResult = covidResult;
        this.travelHistory = travelHistory;
        this.contactWithUser = contactWithUser;
    }
}
