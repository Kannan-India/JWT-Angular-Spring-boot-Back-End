package com.indsys.iLabelBackEnd.labeltype;
import com.indsys.iLabelBackEnd.labeltype.LabelType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LabelTypeRepositry extends MongoRepository<LabelType, String>{

}
