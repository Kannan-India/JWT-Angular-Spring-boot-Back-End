package com.indsys.iLabelBackEnd.authentication.service;

import com.indsys.iLabelBackEnd.authentication.model.UserProfile;
import com.indsys.iLabelBackEnd.authentication.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    //here we are using email in place of userName
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserProfile userProfile = userProfileRepository.findByEmail(s);
        if(userProfile == null) {
            throw new
                    UsernameNotFoundException("Bad credentials");
        }
        else{
            return new User(userProfile.getEmail(), userProfile.getPassword(), new ArrayList<>());
        }
    }
}
