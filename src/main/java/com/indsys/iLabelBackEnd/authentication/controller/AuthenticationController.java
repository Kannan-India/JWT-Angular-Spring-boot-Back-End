package com.indsys.iLabelBackEnd.authentication.controller;

import com.indsys.iLabelBackEnd.authentication.model.AuthenticationRequest;
import com.indsys.iLabelBackEnd.authentication.model.AuthenticationResponse;
import com.indsys.iLabelBackEnd.authentication.model.DateExample;
import com.indsys.iLabelBackEnd.authentication.model.UserProfile;
import com.indsys.iLabelBackEnd.authentication.repository.DateExampleRepository;
import com.indsys.iLabelBackEnd.authentication.repository.UserProfileRepository;
import com.indsys.iLabelBackEnd.authentication.service.JwtUtils;
import com.indsys.iLabelBackEnd.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private DateExampleRepository dateExampleRepository;

    @PostMapping("/api/register")
    private ResponseEntity registerUser(
            @RequestBody UserProfile userProfile
            )
    {

        try{
            userProfileRepository.insert(userProfile);
        }catch (Exception e){
            return ResponseEntity.ok(
                    new AuthenticationResponse("Error during registering user" + userProfile.getUserName())
            );
        }

        return ResponseEntity.ok(
                new AuthenticationResponse("Registration successful for user : " + userProfile.getUserName())
        );
    }

    @PostMapping("/api/auth")
    private ResponseEntity authenticateUser(
            @RequestBody AuthenticationRequest authenticationRequest
    ){

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(), authenticationRequest.getPassword()
                    )
            );
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Can't authenticate the user : " +
                    "" + authenticationRequest.getEmail()
                    )
            );
        }

        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());

        String generatedToken = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(generatedToken)
        );
    }

    @GetMapping("/api/validity")
    private boolean checkValidity(){
        return true;
    }

    @GetMapping("/api/authExample")
    private String example(){
        return "Yes you are authenticated " + SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/api/nosecurity")
    private ResponseEntity ex(){
        return ResponseEntity.ok(new AuthenticationResponse("That's how i behave without security"));
    }

    @GetMapping("/api/user-profile")
    private ResponseEntity<List<UserProfile>> getUsers(){
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        return ResponseEntity.ok(userProfiles);
    }
//
//    @PostMapping("/api/dateExample")
//    private void dateExamplePost(){
//
//    }

    @PostMapping("/api/dateExample")
    private DateExample dateExample(@RequestBody DateExample dateExample){
        dateExample.setDate(new Date());

        return dateExampleRepository.insert(dateExample);
//        Date date1 = formatter.format(new Date());
        //return date in the following format
        //Sat Sep 25 11:24:56 IST 2021
//        System.out.println(date1);

    }
}
