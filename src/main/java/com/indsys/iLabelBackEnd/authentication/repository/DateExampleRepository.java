package com.indsys.iLabelBackEnd.authentication.repository;

import com.indsys.iLabelBackEnd.authentication.model.DateExample;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DateExampleRepository extends MongoRepository<DateExample, String> {

}
