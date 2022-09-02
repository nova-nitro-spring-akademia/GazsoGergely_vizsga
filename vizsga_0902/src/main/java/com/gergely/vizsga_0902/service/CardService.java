package com.gergely.vizsga_0902.service;

import com.gergely.vizsga_0902.controller.CardDTO;
import com.gergely.vizsga_0902.data.CardEntity;
import com.gergely.vizsga_0902.data.CardEntityRepository;
import com.gergely.vizsga_0902.data.mapper.CardEntityMapper;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardService {

    CardEntityRepository cardEntityRepository;

    CardEntityMapper cardEntityMapper;

    public CardService(CardEntityRepository cardEntityRepository, CardEntityMapper cardEntityMapper) {
        this.cardEntityRepository = cardEntityRepository;
        this.cardEntityMapper = cardEntityMapper;
    }

    public Card save(Card card){
        CardEntity savedEntity = cardEntityRepository.save(cardEntityMapper.toCardEntity(card));
        return cardEntityMapper.fromcardEntity(savedEntity);
    }

    public Set<Card> findAll(){
        List<CardEntity> cardEntityList = cardEntityRepository.findAll();
        Set<Card> cardSet = cardEntityList.stream().map(cardEntityMapper::fromcardEntity).collect(Collectors.toSet());
        return cardSet;
    }

    public Card findById(Long id){
        CardEntity cardEntity = cardEntityRepository.findById(id).orElseThrow();
        return cardEntityMapper.fromcardEntity(cardEntity);
    }

    public Card update(Card card) {
        return save(card);
    }

    public Card deleteById(Long id) {
        Card card = findById(id);
        cardEntityRepository.deleteById(id);
        return card;
    }

    public int estimatedValue(CardDTO cardDTO){
//        kártya becsült értéke = megjelenési évbeni érték * eltelt évek száma * 1,1;
        int estVal = (int) Math.round(cardDTO.getIssuePrice() * (Calendar.getInstance().get(Calendar.YEAR) - cardDTO.getIssueYear()) * 1.1);
        return estVal;
    }

    public int estimatedValue(Card card){
//        kártya becsült értéke = megjelenési évbeni érték * eltelt évek száma * 1,1;
        int estVal = (int) Math.round(card.getIssuePrice() * (Calendar.getInstance().get(Calendar.YEAR) - card.getIssueYear()) * 1.1);
        return estVal;
    }

    public Set<Card> filterOutLowValuedCards(Set<Card> cardSet ,int lowerBound){
        Set<Card> filteredCardSet = cardSet.stream().filter(c -> this.estimatedValue(c) > lowerBound).collect(Collectors.toSet());
        return filteredCardSet;
    }
}
