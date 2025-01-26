package com.kacwol.itemShop.application;

import com.kacwol.itemShop.domain.model.dto.ItemDto;
import com.kacwol.itemShop.domain.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/all")
    public List<ItemDto> getAll() {
        return itemService.getAllItems();
    }

}
