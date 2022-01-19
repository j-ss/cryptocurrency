package com.kotak.assignment.repositories;

import com.kotak.assignment.entity.CryptoHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoHistoryRepository extends JpaRepository<CryptoHistory,Integer>, JpaSpecificationExecutor<CryptoHistory> {
}
