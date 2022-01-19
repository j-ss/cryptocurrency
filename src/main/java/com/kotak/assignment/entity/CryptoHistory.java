package com.kotak.assignment.entity;

import com.kotak.assignment.enums.Action;
import com.kotak.assignment.enums.CryptoType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "crypto_history")
@EntityListeners(AuditingEntityListener.class)
public class CryptoHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "crypto_id")
    private Integer cryptoId;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "crypto_type")
    private CryptoType cryptoType;
    @Column(name = "current_price")
    private BigDecimal currentPrice;
    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name = "action")
    @Enumerated(value = EnumType.STRING)
    private Action action;
    @Column(name = "volume")
    private Long volume;
    @Column(name = "market_Cap")
    private Long marketCap;
}
