package com.indsys.iLabelBackEnd.productColor;

import com.indsys.iLabelBackEnd.authentication.model.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/product-color/")
@CrossOrigin(origins = "*")
public class ProductColorController {

    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("add")
    public boolean addProductColor(@RequestBody ProductColor productColor){
        productColor.setCreatedDateTime(new Date());
        productColorRepository.insert(productColor);
        return true;
    }

    @GetMapping("list")
    public List<ProductColor> getProductList(){
        return productColorRepository.findAll();
    }
}
