package com.kotak.assignment.service.impl;

import com.kotak.assignment.entity.CryptoCurrency;
import com.kotak.assignment.entity.CryptoHistory;
import com.kotak.assignment.enums.CryptoType;
import com.kotak.assignment.repositories.CryptoHistoryRepository;
import com.kotak.assignment.service.CryptoHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CryptoHistoryServiceImpl implements CryptoHistoryService {

    @Autowired
    private CryptoHistoryRepository cryptoHistoryRepository;

    @Override
    public Page<CryptoHistory> listing(Pageable pageable, Map<String, Object> filters) {
        log.info("listing...");
        filters.entrySet().removeIf(p-> ObjectUtils.isEmpty(p.getValue()));
        Page<CryptoHistory> page = cryptoHistoryRepository.findAll((Specification<CryptoHistory>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String,Object> entry: filters.entrySet()) {
                switch (entry.getKey()){
                    case "from":{
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), (LocalDateTime) entry.getValue()));
                        break;
                    }
                    case "to":{
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"),(LocalDateTime)entry.getValue()));
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
