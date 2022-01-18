package com.kotak.assignment.controller.model.request;

import com.kotak.assignment.enums.CryptoType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Setter
@Getter
public class Currency {
    private Integer id;
    private CryptoType cryptoType;
    private BigDecimal price;
    private Long volume;
    private Long marketCap;
    @Null
    @Transient
    private BigDecimal lowestPrice;
    @Null
    @Transient
    private BigDecimal highestPrice;
}
