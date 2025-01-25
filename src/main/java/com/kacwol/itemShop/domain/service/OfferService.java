package com.kacwol.itemShop.domain.service;

import com.kacwol.itemShop.domain.model.dto.OfferDto;
import com.kacwol.itemShop.infrastructure.OfferRepo;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final OfferRepo repo;

    public OfferService(OfferRepo repo) {
        this.repo = repo;
    }

    public void createOffer(OfferDto dto) {

    }
}
