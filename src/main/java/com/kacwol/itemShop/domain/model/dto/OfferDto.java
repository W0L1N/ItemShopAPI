package com.kacwol.itemShop.domain.model.dto;

import com.kacwol.itemShop.domain.model.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OfferDto {

    private Long id;

    private String name;

    private String description;

    private String image;

    private BigDecimal price;

    private OfferStatus status;
}
