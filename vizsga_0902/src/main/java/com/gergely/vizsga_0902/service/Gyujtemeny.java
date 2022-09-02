package com.gergely.vizsga_0902.service;

import com.gergely.vizsga_0902.data.CardEntity;

import javax.persistence.OneToMany;
import java.util.Set;

public class Gyujtemeny {

    Long id;

    String name;

    Set<Card> cards;

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

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }
}
