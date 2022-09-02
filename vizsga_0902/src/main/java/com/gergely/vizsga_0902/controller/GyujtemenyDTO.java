package com.gergely.vizsga_0902.controller;

import com.gergely.vizsga_0902.service.Card;

import java.util.HashSet;
import java.util.Set;

public class GyujtemenyDTO {

    Long id;

    String name;

    Set<CardDTO> cards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }

    public void setCards(Set<CardDTO> cards) {
        this.cards = cards;
    }

    public void assignCardDTO(CardDTO cardDTO){
        if (cards==null){
            cards = new HashSet<CardDTO>();
            cards.add(cardDTO);
        }else{
            cards.add(cardDTO);
        }
    }

    public boolean containsCardDTO(CardDTO cardDTO){
        return cards.contains(cardDTO);
    }
}
