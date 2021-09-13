package com.indsys.iLabelBackEnd.authentication.repository;

import com.indsys.iLabelBackEnd.authentication.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    UserProfile findByEmail(String email);

}
