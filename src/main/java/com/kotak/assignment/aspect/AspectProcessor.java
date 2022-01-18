package com.kotak.assignment.aspect;

import com.kotak.assignment.controller.model.request.Currency;
import com.kotak.assignment.controller.model.response.GenericResponse;
import com.kotak.assignment.entity.CryptoCurrency;
import com.kotak.assignment.util.Utility;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
public class AspectProcessor {

    @After("@annotation(com.kotak.assignment.annotation.ListingResponse)")
    public Object handleListingResponse(ProceedingJoinPoint joinPoint) throws Throwable{
        Object obj = joinPoint.proceed();
        Map<String,Object> map = new HashMap<>();
        if(obj instanceof Page){
            Page<CryptoCurrency> page = (Page<CryptoCurrency>) obj;
            List<Currency> contents = page.getContent().stream().map(c-> Utility.convertJpaToResponseCurrency(c)).collect(Collectors.toList());
            map.put("data",contents);
            map.put("totalRecords",page.getTotalElements());
            map.put("totalPages",page.getTotalPages());
        }
        GenericResponse<Map> genericResponse = new GenericResponse("Success",map,null,null);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
