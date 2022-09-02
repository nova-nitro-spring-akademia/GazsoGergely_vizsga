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

//    @GetMapping("/employees/{id}")
//    public CardDTO getEmployee(@PathVariable Long id){
//
//
//        Employee employee = employeeService.findById(id);
//        return employeeDTOMapper.toEmployeeDTO(employee);
//    }

}
