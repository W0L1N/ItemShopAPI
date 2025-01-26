package com.kacwol.itemShop.application;

import com.kacwol.itemShop.domain.model.dto.OfferDto;
import com.kacwol.itemShop.domain.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/all")
    public List<OfferDto> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/my")
    public List<OfferDto> getMyOffers() {
        return offerService.getMyOffers();
    }

    @PostMapping("/buy")
    public void buyOffer(@RequestParam Long offerId) {
        offerService.buyOffer(offerId);
    }

    @PostMapping("/create")
    public void createOffer(@RequestBody OfferDto dto) {
        offerService.createOffer(dto);
    }
}
