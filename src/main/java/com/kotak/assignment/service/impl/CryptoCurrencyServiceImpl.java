package com.kotak.assignment.service.impl;

import com.kotak.assignment.controller.model.request.Currency;
import com.kotak.assignment.entity.CryptoCurrency;
import com.kotak.assignment.enums.CryptoType;
import com.kotak.assignment.exception.EntityNotFoundException;
import com.kotak.assignment.repositories.CryptoCurrencyRepository;
import com.kotak.assignment.service.CryptoCurrencyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.kotak.assignment.util.Utility.convertJpaToResponseCurrency;
import static com.kotak.assignment.util.Utility.convertToJpaCryptoCurrency;

@Component
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Override
    @Transactional
    public Currency addCurrency(Currency currency) {
        CryptoCurrency cryptoCurrency = convertToJpaCryptoCurrency(currency);
        cryptoCurrency.setLowestPrice(currency.getPrice());
        cryptoCurrency.setHighestPrice(currency.getPrice());
        cryptoCurrencyRepository.save(cryptoCurrency);
        return convertJpaToResponseCurrency(cryptoCurrency);
    }

    @Override
    @Transactional
    public Currency updateCurrency(Currency currency) {
        Optional<CryptoCurrency> currencyOptional = cryptoCurrencyRepository.findById(currency.getId());
        if(currencyOptional.isPresent()){
            CryptoCurrency jpaCryptoCurrency = currencyOptional.get();
            CryptoCurrency cryptoCurrency = convertToJpaCryptoCurrency(currency);
            if(cryptoCurrency.getCurrentPrice().compareTo(jpaCryptoCurrency.getHighestPrice())>0){
                cryptoCurrency.setHighestPrice(cryptoCurrency.getCurrentPrice());
            }else{
                cryptoCurrency.setHighestPrice(jpaCryptoCurrency.getHighestPrice());
            }

            if(cryptoCurrency.getCurrentPrice().compareTo(jpaCryptoCurrency.getLowestPrice())<0){
                cryptoCurrency.setLowestPrice(cryptoCurrency.getCurrentPrice());
            }else{
                cryptoCurrency.setLowestPrice(jpaCryptoCurrency.getLowestPrice());
            }
            BeanUtils.copyProperties(cryptoCurrency,jpaCryptoCurrency);
            return convertJpaToResponseCurrency(jpaCryptoCurrency);
        }else{
            throw new EntityNotFoundException("No crypto currency exists for id "+ currency.getId());
        }
    }

    @Override
    @Transactional
    public void deleteCurrency(Integer id) {
        //allow soft delete instead of hard
        cryptoCurrencyRepository.deleteById(id);
    }

    @Override
    public Page<CryptoCurrency> listing(Pageable pageable, Map<String, Object> filters) {
        filters.entrySet().removeIf(p-> ObjectUtils.isEmpty(p.getValue()));
        Page<CryptoCurrency> page = cryptoCurrencyRepository.findAll((Specification<CryptoCurrency>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String,Object> entry: filters.entrySet()) {
                switch (entry.getKey()){
                    case "cryptoType":{
                        predicates.add(criteriaBuilder.equal(root.get(entry.getKey()), CryptoType.valueOf(entry.getValue().toString())));
                        break;
                    }
                    case "priceGreaterThan":{
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("currentPrice"),(BigDecimal)entry.getValue()));
                        break;
                    }
                    case "priceLessThan":{
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("currentPrice"),(BigDecimal)entry.getValue()));
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        },pageable);
        return page;
    }
}
