package com.gergely.vizsga_0902.controller.view;

import com.gergely.vizsga_0902.controller.CardDTO;
import com.gergely.vizsga_0902.controller.GyujtemenyDTO;
import com.gergely.vizsga_0902.controller.mapper.CardDTOMapper;
import com.gergely.vizsga_0902.service.Card;
import com.gergely.vizsga_0902.service.CardService;
import com.gergely.vizsga_0902.service.Gyujtemeny;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/customcardlistform")
    public String customCardListForm(Model model){
        int lowerBound=0;
        model.addAttribute("lowerBound", lowerBound);
        return "custom-card-list-form";
    }

    @PostMapping("/showcustomcardlistform")
    public String customCardList(
            @RequestParam(value = "lowerBound") int lowerBound,
            Model model
    ){
        Set<Card> cardSet = cardService.findAll();
        Set<Card> filteredCardSet = cardService.filterOutLowValuedCards(cardSet, lowerBound);

        Set<CardDTO> cardDTOSet = cardDTOMapper.toCardDTOSet(filteredCardSet);
        model.addAttribute("cardDTOSet", cardDTOSet);
        model.addAttribute("cardService", cardService);
        return "card-list";
    }

    @GetMapping("/gyujtemenyform")
    public String gyujtemenyForm(Model model){
        GyujtemenyDTO gyujtemenyDTO = new GyujtemenyDTO();
        model.addAttribute("gyujtemenyDTO", gyujtemenyDTO);
        return "gyujtemeny-form";
    }

    @PostMapping("/savegyujtemeny")
    public String saveGyujtemeny(@ModelAttribute("gyujtemenyDTO") GyujtemenyDTO gyujtemenyDTO){

        return "redirect:/";
    }



}
