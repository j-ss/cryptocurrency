package com.kotak.assignment.entity;

import com.kotak.assignment.enums.CryptoType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "crypto_currency")
public class CryptoCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "")
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "crypto_type")
    private CryptoType cryptoType;
    @Column(name = "current_price")
    private BigDecimal currentPrice;
    @Column(name = "lowest_price")
    private BigDecimal lowestPrice;
    @Column(name = "highest_price")
    private BigDecimal highestPrice;
    @Column(name = "volume")
    private Long volume;
    @Column(name = "market_cap")
    private Long marketCap;
    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
}
