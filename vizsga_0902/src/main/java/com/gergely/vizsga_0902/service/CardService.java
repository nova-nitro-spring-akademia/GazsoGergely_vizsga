package com.gergely.vizsga_0902.service;

import com.gergely.vizsga_0902.data.CardEntity;
import com.gergely.vizsga_0902.data.CardEntityRepository;
import com.gergely.vizsga_0902.data.mapper.CardEntityMapper;
import org.springframework.stereotype.Service;

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
}
