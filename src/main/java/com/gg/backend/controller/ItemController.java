package com.gg.backend.controller;

import com.gg.backend.entity.Cart;
import com.gg.backend.entity.Item;
import com.gg.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemrepository;

    @GetMapping("/api/items")
    public List<Item> getItems() {
        List<Item> items = itemrepository.findAll();
        return  items;
    }

    @GetMapping("/api/items/{itemId}")
    public ResponseEntity getItemId(String itemId) {

        Item item = itemrepository.findById(itemId);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
