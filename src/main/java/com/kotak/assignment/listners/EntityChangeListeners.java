package com.kotak.assignment.listners;


import com.kotak.assignment.entity.CryptoCurrency;
import com.kotak.assignment.entity.CryptoHistory;
import com.kotak.assignment.enums.Action;
import com.kotak.assignment.repositories.CryptoHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Component
@Slf4j
public class EntityChangeListeners {

    @Autowired
    private CryptoHistoryRepository cryptoHistoryRepository;

    @PostUpdate
    private void afterAnyUpdate(CryptoCurrency cryptoCurrency){
        log.info(cryptoCurrency.toString());
        CryptoHistory cryptoHistory = buildCryptoToHistory(cryptoCurrency,Action.UPDATE);
        cryptoHistoryRepository.save(cryptoHistory);
    }

    @PostPersist
    private void afterAnyInsert(CryptoCurrency cryptoCurrency){
            log.info(cryptoCurrency.toString());
            CryptoHistory cryptoHistory = buildCryptoToHistory(cryptoCurrency,Action.INSERT);
            cryptoHistoryRepository.save(cryptoHistory);
    }

    @PostRemove
    private void afterAnyDelete(CryptoCurrency cryptoCurrency){
        log.info(cryptoCurrency.toString());
        CryptoHistory cryptoHistory = buildCryptoToHistory(cryptoCurrency,Action.DELETE);
        cryptoHistoryRepository.save(cryptoHistory);
    }

    private CryptoHistory buildCryptoToHistory(CryptoCurrency cryptoCurrency, Action action){
        CryptoHistory cryptoHistory = new CryptoHistory();
        cryptoHistory.setCryptoId(cryptoCurrency.getId());
        cryptoHistory.setCryptoType(cryptoCurrency.getCryptoType());
        cryptoHistory.setAction(action);
        cryptoHistory.setCurrentPrice(cryptoCurrency.getCurrentPrice());
        cryptoHistory.setVolume(cryptoHistory.getVolume());
        cryptoHistory.setMarketCap(cryptoCurrency.getMarketCap());
        return cryptoHistory;
    }
}
