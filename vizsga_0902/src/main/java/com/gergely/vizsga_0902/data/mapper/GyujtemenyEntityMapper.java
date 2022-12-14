package com.gergely.vizsga_0902.data.mapper;

import com.gergely.vizsga_0902.data.CardEntity;
import com.gergely.vizsga_0902.data.GyujtemenyEntity;
import com.gergely.vizsga_0902.service.Card;
import com.gergely.vizsga_0902.service.Gyujtemeny;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GyujtemenyEntityMapper {

    CardEntityMapper cardEntityMapper;

    public GyujtemenyEntityMapper(CardEntityMapper cardEntityMapper) {
        this.cardEntityMapper = cardEntityMapper;
    }

    public GyujtemenyEntity toGyujtemenyEntity(Gyujtemeny gyujtemeny){
        GyujtemenyEntity gyujtemenyEntity = new GyujtemenyEntity();
        gyujtemenyEntity.setId(gyujtemeny.getId());
        gyujtemenyEntity.setName(gyujtemeny.getName());

//        Set<CardEntity> cardEntitySet = gyujtemeny.getCards().stream().map(cardEntityMapper::toCardEntity).collect(Collectors.toSet());
//        gyujtemenyEntity.setCards(cardEntitySet);
        return gyujtemenyEntity;
    }


    public GyujtemenyEntity toGyujtemenyEntityWithCards(Gyujtemeny gyujtemeny){
        GyujtemenyEntity gyujtemenyEntity = new GyujtemenyEntity();
        gyujtemenyEntity.setId(gyujtemeny.getId());
        gyujtemenyEntity.setName(gyujtemeny.getName());

        Set<CardEntity> cardEntitySet = gyujtemeny.getCards().stream().map(cardEntityMapper::toCardEntity).collect(Collectors.toSet());
        gyujtemenyEntity.setCards(cardEntitySet);
        return gyujtemenyEntity;
    }

    public Gyujtemeny fromGyujtemenyEntity(GyujtemenyEntity savedGyujtemenyEntity) {
        Gyujtemeny gyujtemeny = new Gyujtemeny();
        gyujtemeny.setId(savedGyujtemenyEntity.getId());
        gyujtemeny.setName(savedGyujtemenyEntity.getName());

//        Set<CardEntity> cardEntitySet = savedGyujtemenyEntity.getCards();
//        Set<Card> cardSet = cardEntitySet.stream().map(cardEntityMapper::fromcardEntity).collect(Collectors.toSet());
//
//        gyujtemeny.setCards(cardSet);

        return gyujtemeny;
    }


    public Gyujtemeny fromGyujtemenyEntityWithCards(GyujtemenyEntity savedGyujtemenyEntity) {
        Gyujtemeny gyujtemeny = new Gyujtemeny();
        gyujtemeny.setId(savedGyujtemenyEntity.getId());
        gyujtemeny.setName(savedGyujtemenyEntity.getName());

        Set<CardEntity> cardEntitySet = savedGyujtemenyEntity.getCards();
        Set<Card> cardSet = cardEntitySet.stream().map(cardEntityMapper::fromcardEntity).collect(Collectors.toSet());

        gyujtemeny.setCards(cardSet);

        return gyujtemeny;
    }
}
