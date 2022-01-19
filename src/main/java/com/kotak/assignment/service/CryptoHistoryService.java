package com.kotak.assignment.service;

import com.kotak.assignment.entity.CryptoHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CryptoHistoryService {
    Page<CryptoHistory> listing(Pageable pageable, Map<String,Object> filters);
}
