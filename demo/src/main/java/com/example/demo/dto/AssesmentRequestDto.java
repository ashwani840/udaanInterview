package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssesmentRequestDto {
    private int userId;
    private String[] symptoms;
    private boolean travelHistory;
    private boolean contactWithCovidPatient;
}
