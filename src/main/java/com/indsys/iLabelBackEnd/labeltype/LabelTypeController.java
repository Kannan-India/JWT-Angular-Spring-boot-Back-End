package com.indsys.iLabelBackEnd.labeltype;
import com.indsys.iLabelBackEnd.trimType.trimType;
import com.indsys.iLabelBackEnd.trimType.trimTypeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/label-type/")
@CrossOrigin(origins = "*")

public class LabelTypeController {


    @Autowired
    LabelTypeRepositry LabelTypeRepositry;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("add")
    public boolean addlabeltype(@RequestBody LabelType labelType){
        labelType.setCreatedDateTime(new Date());
        LabelTypeRepositry.insert(labelType);
        return true;
    }

    @GetMapping("list")
    public List<LabelType> getlabeltype(){
        return LabelTypeRepositry.findAll();
    }
}
