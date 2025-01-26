package com.kacwol.itemShop.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Offer offer;

    @ManyToOne
    private User user;

    private LocalDateTime dateTime;

    public Item(Offer offer, User user) {
        this.offer = offer;
        this.user = user;
        dateTime = LocalDateTime.now();
    }
}
