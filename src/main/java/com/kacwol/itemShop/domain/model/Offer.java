package com.kacwol.itemShop.domain.model;

import com.kacwol.itemShop.domain.model.dto.OfferDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String image;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    @ManyToOne
    private User user;

    public Offer(OfferDto dto, User user) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.image = dto.getImage();
        this.price = dto.getPrice();
        this.status = OfferStatus.OPEN;
        this.user = user;
    }

    public Offer buy() {
        if(status == OfferStatus.CLOSE) {
            throw new RuntimeException();
        }
        status = OfferStatus.CLOSE;
        return this;
    }
}
