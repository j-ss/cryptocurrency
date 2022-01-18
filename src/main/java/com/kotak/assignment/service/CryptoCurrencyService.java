package com.kotak.assignment.service;

import com.kotak.assignment.controller.model.request.Currency;
import com.kotak.assignment.entity.CryptoCurrency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CryptoCurrencyService {
    Currency addCurrency(Currency currency);
    Currency updateCurrency(Currency currency);
    void deleteCurrency(Integer id);
    Page<CryptoCurrency> listing(Pageable pageable, Map<String,Object> filters);
}
