package com.kotak.assignment.controller;

import com.kotak.assignment.annotation.ListingResponse;
import com.kotak.assignment.service.CryptoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping(value = "/crypto/history")
public class CryptoHistoryController {

    @Autowired
    private CryptoHistoryService cryptoHistoryService;

    @GetMapping("")
    @ListingResponse
    public Object cryptoHistory(@PageableDefault(size = 20,page = 1) Pageable pageable, Map<String,Object> filters){
        Object from = filters.get("from");
        Object to = filters.get("to");
        //TODO add validation on from and to date format
        if(from==null){
            if(to==null){
                from = LocalDateTime.now().minusMonths(3);
                to = LocalDateTime.now();
            }else{
                to = LocalDateTime.parse(to.toString(), DateTimeFormatter.ofPattern("DD-mm-yyyy"));
                from = ((LocalDateTime)to).minusMonths(3);
            }
        }else{
            from = LocalDateTime.parse(to.toString(), DateTimeFormatter.ofPattern("DD-mm-yyyy"));
            to = LocalDateTime.now();
        }

        filters.put("from",from);
        filters.put("to",to);
        return cryptoHistoryService.listing(pageable,filters);
    }
}
