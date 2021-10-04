package com.indsys.iLabelBackEnd.trimType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/trimtype/")
@CrossOrigin(origins = "*")

public class trimTypeController {

    @Autowired
    trimTypeRepositry trimTypeRepositry;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("add")
    public boolean addTrimType(@RequestBody trimType trimType){
        trimType.setCreatedDateTime(new Date());
        trimTypeRepositry.insert(trimType);
        return true;
    }

    @GetMapping("list")
    public List<trimType> getTrimType(){
        return trimTypeRepositry.findAll();
    }
}
