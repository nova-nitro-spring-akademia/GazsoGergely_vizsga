package com.gergely.vizsga_0902.controller.view;

import com.gergely.vizsga_0902.controller.CardDTO;
import com.gergely.vizsga_0902.controller.mapper.CardDTOMapper;
import com.gergely.vizsga_0902.service.Card;
import com.gergely.vizsga_0902.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class ViewController {

    CardDTOMapper cardDTOMapper;

    CardService cardService;

    public ViewController(CardDTOMapper cardDTOMapper, CardService cardService) {
        this.cardDTOMapper = cardDTOMapper;
        this.cardService = cardService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/cardform")
    public String addCard(Model model){
        CardDTO cardDTO = new CardDTO();
        model.addAttribute("cardDTO", cardDTO);
        return "card-form";
    }

    @PostMapping("/savecard")
    public String saveCard(@ModelAttribute("cardDTO") CardDTO cardDTO){
        Card card = cardDTOMapper.fromcardDTO(cardDTO);
        cardService.save(card);
        return "redirect:/cardlist";
    }

    @GetMapping("/cardlist")
    public String cardList(Model model){
        Set<CardDTO> cardDTOSet = cardDTOMapper.toCardDTOSet(cardService.findAll());
        model.addAttribute("cardDTOSet", cardDTOSet);
        model.addAttribute("cardService", cardService);
        return "card-list";

    }

}
