package com.gergely.vizsga_0902.controller.api;

import com.gergely.vizsga_0902.controller.CardDTO;
import com.gergely.vizsga_0902.controller.mapper.CardDTOMapper;
import com.gergely.vizsga_0902.service.Card;
import com.gergely.vizsga_0902.service.CardService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    CardDTOMapper cardDTOMapper;

    CardService cardService;

    public ApiController(CardDTOMapper cardDTOMapper, CardService cardService) {
        this.cardDTOMapper = cardDTOMapper;
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public CardDTO savecard(@RequestBody CardDTO cardDTO){
        Card savedCard = cardService.save(cardDTOMapper.fromcardDTO(cardDTO));
        return cardDTOMapper.tocardDTO(savedCard);
    }

    @GetMapping("/cards")
    public Set<CardDTO> getAllCard(){
        Set<Card> cardSet = cardService.findAll();
        return cardDTOMapper.toCardDTOSet(cardSet);

    }

    @GetMapping("/cards/{id}")
    public CardDTO getEmployee(@PathVariable Long id){
        Card card = cardService.findById(id);
        return cardDTOMapper.tocardDTO(card);
    }

    @PutMapping("/cards/{id}")
    public CardDTO updateCard(@PathVariable Long id, @RequestBody CardDTO cardDTO){
        Card card = cardService.findById(id);
        //ezen a ponton tudjuk hogy létezik,így updateeljhetjuk

        Card updatedCard = cardService.update(cardDTOMapper.fromcardDTO(cardDTO));
        return cardDTOMapper.tocardDTO(updatedCard);
    }

    @DeleteMapping("/cards/{id}")
    public CardDTO deleteCard(@PathVariable Long id){
        Card deletedCard = cardService.deleteById(id);
        return cardDTOMapper.tocardDTO(deletedCard);
    }

}
