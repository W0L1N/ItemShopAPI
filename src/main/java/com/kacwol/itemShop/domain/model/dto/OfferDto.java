package com.kacwol.itemShop.domain.model.dto;

import com.kacwol.itemShop.domain.model.Offer;
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

    public OfferDto(Offer offer) {
        this.id = offer.getId();
        this.name = offer.getName();
        this.description = offer.getDescription();
        this.image = offer.getImage();
        this.price = offer.getPrice();
        this.status = offer.getStatus();
    }
}
