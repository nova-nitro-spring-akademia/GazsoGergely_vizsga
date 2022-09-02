package com.gergely.vizsga_0902.data.mapper;

import com.gergely.vizsga_0902.controller.CardDTO;
import com.gergely.vizsga_0902.data.CardEntity;
import com.gergely.vizsga_0902.service.Card;
import org.springframework.stereotype.Component;

@Component
public class CardEntityMapper {

    public CardEntity toCardEntity(Card card){
        CardEntity cardEntity = new CardEntity();
        cardEntity.setId(card.getId());
        cardEntity.setName(card.getName());
        cardEntity.setType(card.getType());
        cardEntity.setPercent(card.getPercent());
        cardEntity.setPurchasePrice(card.getPurchasePrice());
        cardEntity.setPurchaseYear(card.getPurchaseYear());
        cardEntity.setIssueYear(card.getIssueYear());
        cardEntity.setIssuePrice(card.getIssuePrice());
        return cardEntity;
    }

    public Card fromcardEntity(CardEntity cardEntity){
        Card card = new Card();
        card.setId(cardEntity.getId());
        card.setName(cardEntity.getName());
        card.setType(cardEntity.getType());
        card.setPercent(cardEntity.getPercent());
        card.setPurchasePrice(cardEntity.getPurchasePrice());
        card.setPurchaseYear(cardEntity.getPurchaseYear());
        card.setIssueYear(cardEntity.getIssueYear());
        card.setIssuePrice(cardEntity.getIssuePrice());
        return card;
    }

}
