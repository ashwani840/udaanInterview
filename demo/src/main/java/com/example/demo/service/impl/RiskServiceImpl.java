package com.example.demo.service.impl;

import com.example.demo.dto.AssesmentRequestDto;
import com.example.demo.service.RiskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RiskServiceImpl implements RiskService {
    @Override
    public int caculateRisk(AssesmentRequestDto assesmentRequestDto) {
        int totalSysmptoms = assesmentRequestDto.getSymptoms().length;
        if(!assesmentRequestDto.isTravelHistory() && !assesmentRequestDto.isContactWithCovidPatient() && totalSysmptoms==0){
            return 5;
        } else if((assesmentRequestDto.isTravelHistory() || assesmentRequestDto.isContactWithCovidPatient()) && totalSysmptoms==1 ){
            return 50;
        } else if((assesmentRequestDto.isTravelHistory() || assesmentRequestDto.isContactWithCovidPatient()) && totalSysmptoms==2){
            return 75;
        } else if((assesmentRequestDto.isTravelHistory() || assesmentRequestDto.isContactWithCovidPatient()) && totalSysmptoms>2){
            return 95;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }
    }
}
