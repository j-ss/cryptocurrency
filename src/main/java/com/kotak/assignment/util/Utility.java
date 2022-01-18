package com.kotak.assignment.util;

import com.kotak.assignment.controller.model.request.Currency;
import com.kotak.assignment.controller.model.response.GenericResponse;
import com.kotak.assignment.entity.CryptoCurrency;
import org.apache.tomcat.jni.Local;

import java.util.Locale;

public class Utility {

    public static CryptoCurrency convertToJpaCryptoCurrency(Currency currency){
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
//        cryptoCurrency.setHighestPrice(currency.getPrice());
        cryptoCurrency.setCryptoType(currency.getCryptoType());
//        cryptoCurrency.setLowestPrice(currency.getPrice());
        cryptoCurrency.setCurrentPrice(currency.getPrice());
        cryptoCurrency.setId(currency.getId());
        cryptoCurrency.setMarketCap(currency.getMarketCap());
        cryptoCurrency.setVolume(currency.getVolume());
        return cryptoCurrency;
    }

    public static Currency convertJpaToResponseCurrency(CryptoCurrency cryptoCurrency){
        Currency currency = new Currency();
        currency.setId(cryptoCurrency.getId());
        currency.setCryptoType(cryptoCurrency.getCryptoType());
        currency.setMarketCap(cryptoCurrency.getMarketCap());
        currency.setVolume(cryptoCurrency.getVolume());
        currency.setPrice(cryptoCurrency.getCurrentPrice());
        currency.setHighestPrice(cryptoCurrency.getHighestPrice());
        currency.setLowestPrice(cryptoCurrency.getLowestPrice());
        return currency;
    }

    public static <T> GenericResponse<T> buildGenericResponse(T data, String message,String error,String path){
        return new GenericResponse<>(message,data,error,path);
    }
}
