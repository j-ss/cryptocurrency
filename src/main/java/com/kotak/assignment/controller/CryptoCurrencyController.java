package com.kotak.assignment.controller;


import com.kotak.assignment.annotation.ListingResponse;
import com.kotak.assignment.controller.model.request.Currency;
import com.kotak.assignment.service.CryptoCurrencyService;
import com.kotak.assignment.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(name = "/crypto/currencies")
public class CryptoCurrencyController {

    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    @PostMapping("")
    public ResponseEntity<?> addCurrency(@Valid @RequestBody Currency currency){
        currency.setId(null);
        return new ResponseEntity<>(Utility.buildGenericResponse(
                cryptoCurrencyService.addCurrency(currency),"Created Successfully",null,null),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurrency(@PathVariable Integer id,@Valid @RequestBody Currency currency){
        currency.setId(id);
        return new ResponseEntity<>(Utility.buildGenericResponse(
                cryptoCurrencyService.updateCurrency(currency),"Updated Successfully",null,null),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable Integer id){
        cryptoCurrencyService.deleteCurrency(id);
        return new ResponseEntity<>(Utility.buildGenericResponse(null
               ,"Deleted Successfully",null,null),
                HttpStatus.OK);
    }

    @ListingResponse
    @GetMapping("")
    public Object listing(@PageableDefault(size = 20,page = 1,direction = Sort.Direction.ASC,sort = "updatedAt") Pageable pageable, Map<String,Object> filters){
            return cryptoCurrencyService.listing(pageable,filters);
    }
}
