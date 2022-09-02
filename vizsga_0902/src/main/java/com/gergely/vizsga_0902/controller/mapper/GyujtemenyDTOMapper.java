package com.gergely.vizsga_0902.controller.mapper;

import com.gergely.vizsga_0902.controller.CardDTO;
import com.gergely.vizsga_0902.controller.GyujtemenyDTO;
import com.gergely.vizsga_0902.service.Card;
import com.gergely.vizsga_0902.service.Gyujtemeny;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GyujtemenyDTOMapper {

    CardDTOMapper cardDTOMapper;

    public GyujtemenyDTOMapper(CardDTOMapper cardDTOMapper) {
        this.cardDTOMapper = cardDTOMapper;
    }

    public Gyujtemeny fromGyujtemenyDTO(GyujtemenyDTO gyujtemenyDTO) {
        Gyujtemeny gyujtemeny = new Gyujtemeny();
        gyujtemeny.setId(gyujtemenyDTO.getId());
        gyujtemeny.setName(gyujtemenyDTO.getName());
//        gyujtemeny.setCards(cardDTOMapper.fromCardDTOSet(gyujtemenyDTO.getCards()));
        return gyujtemeny;
    }

    public GyujtemenyDTO toGyujtemenyDTO(Gyujtemeny gyujtemeny) {
        GyujtemenyDTO gyujtemenyDTO = new GyujtemenyDTO();
        gyujtemenyDTO.setId(gyujtemeny.getId());
        gyujtemenyDTO.setName(gyujtemeny.getName());
//        gyujtemeny.setCards(cardDTOMapper.fromCardDTOSet(gyujtemenyDTO.getCards()));
        return gyujtemenyDTO;
    }

    public GyujtemenyDTO toGyujtemenyDTOWithCards(Gyujtemeny gyujtemeny) {
        GyujtemenyDTO gyujtemenyDTO = new GyujtemenyDTO();
        gyujtemenyDTO.setId(gyujtemeny.getId());
        gyujtemenyDTO.setName(gyujtemeny.getName());
        gyujtemenyDTO.setCards(cardDTOMapper.toCardDTOSet(gyujtemeny.getCards()));
        gyujtemeny.setCards(cardDTOMapper.fromCardDTOSet(gyujtemenyDTO.getCards()));
        return gyujtemenyDTO;
    }

    public Set<GyujtemenyDTO> toGyujtemenyDTOSet(Set<Gyujtemeny> gyujtemenySet) {
        Set<GyujtemenyDTO> gyujtemenyDTOSet = gyujtemenySet.stream().map(this::toGyujtemenyDTO).collect(Collectors.toSet());
        return gyujtemenyDTOSet;
    }

    public Set<GyujtemenyDTO> toGyujtemenyDTOSetWithCards(Set<Gyujtemeny> gyujtemenySet) {
        Set<GyujtemenyDTO> gyujtemenyDTOSet = gyujtemenySet.stream().map(this::toGyujtemenyDTOWithCards).collect(Collectors.toSet());
        return gyujtemenyDTOSet;
    }



    public Gyujtemeny fromGyujtemenyDTOWithCards(GyujtemenyDTO gyujtemenyDTO) {
        Gyujtemeny gyujtemeny = new Gyujtemeny();
        gyujtemeny.setId(gyujtemenyDTO.getId());
        gyujtemeny.setName(gyujtemenyDTO.getName());
        gyujtemeny.setCards(cardDTOMapper.fromCardDTOSet(gyujtemenyDTO.getCards()));
        return gyujtemeny;
    }

}
