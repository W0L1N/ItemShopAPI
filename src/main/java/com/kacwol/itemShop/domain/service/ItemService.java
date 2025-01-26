package com.kacwol.itemShop.domain.service;

import com.kacwol.itemShop.domain.model.Item;
import com.kacwol.itemShop.domain.model.Offer;
import com.kacwol.itemShop.domain.model.User;
import com.kacwol.itemShop.domain.model.dto.ItemDto;
import com.kacwol.itemShop.infrastructure.ItemRepo;
import com.kacwol.itemShop.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepo itemRepo;

    private final UserService userService;

    public List<ItemDto> getAllItems() {
        User user = userService.getAuthenticatedUser();
        return itemRepo.findAllByUserId(user.getId()).stream().map(ItemDto::new).toList();
    }

    public void addItem(User user, Offer offer) {
        Item item = new Item(offer, user);
        itemRepo.save(item);
    }
}
