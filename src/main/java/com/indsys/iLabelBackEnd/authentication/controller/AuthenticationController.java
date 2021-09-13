package com.indsys.iLabelBackEnd.authentication.controller;

import com.indsys.iLabelBackEnd.authentication.model.AuthenticationRequest;
import com.indsys.iLabelBackEnd.authentication.model.AuthenticationResponse;
import com.indsys.iLabelBackEnd.authentication.model.UserProfile;
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

        return ResponseEntity.ok(new AuthenticationResponse("Successful authentication for user : " +
                        "" + authenticationRequest.getEmail() + " " + generatedToken
                )
        );
    }

    @GetMapping("/api/authExample")
    private String example(){
        return "Yes you are authenticated " + SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/api/nosecurity")
    private ResponseEntity ex(){
        return ResponseEntity.ok(new AuthenticationResponse("That's how i behave without security"));
    }

}
