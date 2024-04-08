package com.example.demo.ag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ag.model.AGCards;
import com.example.demo.ag.repository.AGCardsRepository;

@RestController
@RequestMapping("/ag")
public class AGCardsController {

    @Autowired
    private AGCardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<AGCards> getCardDetails(@RequestParam int id) {
        List<AGCards> cards = cardsRepository.findByCustomerId(id);
        if (cards != null ) {
            return cards;
        }else {
            return null;
        }
    }
    
    @PostMapping("/save/myCards")
    public AGCards saveCards(@RequestBody AGCards agCards) {
    	return cardsRepository.save(agCards);
    }

}
