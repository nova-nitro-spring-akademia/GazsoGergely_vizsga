package com.gergely.vizsga_0902.controller.mapper;

import com.gergely.vizsga_0902.controller.CardDTO;
import com.gergely.vizsga_0902.service.Card;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CardDTOMapper {

    public CardDTO tocardDTO(Card card){
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setName(card.getName());
        cardDTO.setType(card.getType());
        cardDTO.setPercent(card.getPercent());
        cardDTO.setPurchasePrice(card.getPurchasePrice());
        cardDTO.setPurchaseYear(card.getPurchaseYear());
        cardDTO.setIssueYear(card.getIssueYear());
        cardDTO.setIssuePrice(card.getIssuePrice());
        return cardDTO;
    }

    public Card fromcardDTO(CardDTO cardDTO){
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setName(cardDTO.getName());
        card.setType(cardDTO.getType());
        card.setPercent(cardDTO.getPercent());
        card.setPurchasePrice(cardDTO.getPurchasePrice());
        card.setPurchaseYear(cardDTO.getPurchaseYear());
        card.setIssueYear(cardDTO.getIssueYear());
        card.setIssuePrice(cardDTO.getIssuePrice());
        return card;
    }

    public Set<CardDTO> toCardDTOSet(Set<Card> cardSet){
        return cardSet.stream().map(this::tocardDTO).collect(Collectors.toSet());
    }

}
