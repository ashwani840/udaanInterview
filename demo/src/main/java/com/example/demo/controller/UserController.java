package com.example.demo.controller;

import com.example.demo.dto.AdminResponseDto;
import com.example.demo.dto.AssesmentRequestDto;
import com.example.demo.dto.RiskDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RiskService;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    private final RiskService riskServiceImpl;

    public UserController(UserRepository userRepository, RiskService riskServiceImpl) {
        this.userRepository = userRepository;
        this.riskServiceImpl = riskServiceImpl;
    }

    @PostMapping("add-user")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto){
        Optional<User> user = userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber());
        if(user.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already present with the given contact number");
        }

        User newUser = User.builder().userType("Regular")
            .phoneNumber(userRequestDto.getPhoneNumber())
            .name(userRequestDto.getName())
            .pinCode(userRequestDto.getPinCode())
            .build();

        User save = userRepository.save(newUser);

        return ResponseEntity.ok(new UserResponseDto(save.getId()));
    }

    @PostMapping("add-admin-user")
    public ResponseEntity<AdminResponseDto> addAdminUser(@RequestBody UserRequestDto userRequestDto){
        Optional<User> user = userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber());
        if(user.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already present with the given contact number");
        }

        User newUser = User.builder().userType("Admin")
            .phoneNumber(userRequestDto.getPhoneNumber())
            .name(userRequestDto.getName())
            .pinCode(userRequestDto.getPinCode())
            .build();

        User save = userRepository.save(newUser);

        return ResponseEntity.ok(new AdminResponseDto(save.getId()));
    }

    @PostMapping("self-assesment")
    public ResponseEntity<RiskDto> selfAssesment(@RequestBody AssesmentRequestDto assesmentRequestDto){
        Optional<User> user = userRepository.findById(assesmentRequestDto.getUserId());
        if(!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
        }

        int riskPercent = riskServiceImpl.caculateRisk(assesmentRequestDto);
        return ResponseEntity.ok(new RiskDto(riskPercent));
    }

}
