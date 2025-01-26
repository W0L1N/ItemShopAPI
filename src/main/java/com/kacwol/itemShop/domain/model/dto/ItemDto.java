package com.kacwol.itemShop.domain.model.dto;

import com.kacwol.itemShop.domain.model.Item;
import com.kacwol.itemShop.domain.model.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private String name;

    private String description;

    private String image;

    private BigDecimal price;

    private LocalDateTime dateTime;

    public ItemDto(Item item) {
        Offer offer = item.getOffer();
        this.name = offer.getName();
        this.description = offer.getDescription();
        this.image = offer.getImage();
        this.price = offer.getPrice();
        this.dateTime = item.getDateTime();
    }
}
