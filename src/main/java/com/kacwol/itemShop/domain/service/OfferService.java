package com.kacwol.itemShop.domain.service;

import com.kacwol.itemShop.domain.model.Offer;
import com.kacwol.itemShop.domain.model.User;
import com.kacwol.itemShop.domain.model.dto.OfferDto;
import com.kacwol.itemShop.infrastructure.OfferRepo;
import com.kacwol.itemShop.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepo repo;

    private final UserService userService;

    private final ItemService itemService;

    public void createOffer(OfferDto dto) {
        User user = userService.getAuthenticatedUser();
        Offer offer = new Offer(dto, user);
        repo.save(offer);
    }

    public List<OfferDto> getAllOffers() {
        User user = userService.getAuthenticatedUser();
        return repo.findAllByUserIdNot(user.getId()).stream().map(OfferDto::new).toList();
    }

    public List<OfferDto> getMyOffers() {
        User user = userService.getAuthenticatedUser();
        return repo.findAllByUserId(user.getId()).stream().map(OfferDto::new).toList();
    }

    public void buyOffer(Long offerId) {
        User user = userService.getAuthenticatedUser();
        Offer offer = repo.findByIdAndUserIdNot(offerId, user.getId()).orElseThrow();
        if(user.getWallet().longValue() < offer.getPrice().longValue()) {
            throw new RuntimeException();
        }
        offer.buy();
        offer = repo.save(offer);
        itemService.addItem(user, offer);
    }
}
