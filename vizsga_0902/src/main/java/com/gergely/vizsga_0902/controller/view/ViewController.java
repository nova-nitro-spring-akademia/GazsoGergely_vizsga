package com.gergely.vizsga_0902.controller.view;

import com.gergely.vizsga_0902.controller.CardDTO;
import com.gergely.vizsga_0902.controller.GyujtemenyDTO;
import com.gergely.vizsga_0902.controller.mapper.CardDTOMapper;
import com.gergely.vizsga_0902.controller.mapper.GyujtemenyDTOMapper;
import com.gergely.vizsga_0902.data.GyujtemenyRepository;
import com.gergely.vizsga_0902.service.Card;
import com.gergely.vizsga_0902.service.CardService;
import com.gergely.vizsga_0902.service.Gyujtemeny;
import com.gergely.vizsga_0902.service.GyujtemenyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class ViewController {

    CardDTOMapper cardDTOMapper;

    CardService cardService;

    GyujtemenyDTOMapper gyujtemenyDTOMapper;

    GyujtemenyService gyujtemenyService;

    public ViewController(CardDTOMapper cardDTOMapper, CardService cardService, GyujtemenyDTOMapper gyujtemenyDTOMapper, GyujtemenyService gyujtemenyService) {
        this.cardDTOMapper = cardDTOMapper;
        this.cardService = cardService;
        this.gyujtemenyDTOMapper = gyujtemenyDTOMapper;
        this.gyujtemenyService = gyujtemenyService;
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
//        Set<Card> filteredCardSet = cardService.filterOutLowValuedCards(cardSet, lowerBound);

        Set<Card> filteredCardSet = cardService.findAllWithLowerBound(lowerBound);
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

        Gyujtemeny gyujtemeny = gyujtemenyDTOMapper.fromGyujtemenyDTO(gyujtemenyDTO);

        Gyujtemeny savedGyujtemeny = gyujtemenyService.save(gyujtemeny);

        return "redirect:/";
    }


    @GetMapping("/showassignform/{id}")
    public String showAssignEmployeeToDepartmentForm(@PathVariable Long id, Model model){

        Card card = cardService.findById(id);
        CardDTO cardDTO = cardDTOMapper.tocardDTO(card);
        model.addAttribute(cardDTO);

        Set<Gyujtemeny> gyujtemenySet = gyujtemenyService.findAll();
        Set<GyujtemenyDTO> gyujtemenyDTOSet = gyujtemenyDTOMapper.toGyujtemenyDTOSet(gyujtemenySet);

        model.addAttribute("cardDTO", cardDTO);
        model.addAttribute("gyujtemenyDTOSet", gyujtemenyDTOSet);

        return  "assign-form";
    }

    @PostMapping("/assigncardtogyujtemeny")
    public String assignCardToGyujtemeny(
            @ModelAttribute("cardDTO") CardDTO cardDTO,
            @RequestParam(value = "gyujtemeny_id") int gyujtemeny_id
    ){

        CardDTO cardDTOToAssign = cardDTOMapper.tocardDTO(cardService.findById(cardDTO.getId()));

        GyujtemenyDTO gyujtemenyDTO = gyujtemenyDTOMapper.toGyujtemenyDTO(gyujtemenyService.finddById(gyujtemeny_id));

        gyujtemenyDTO.assignCardDTO(cardDTOToAssign);

        gyujtemenyService.saveWithCards(gyujtemenyDTOMapper.fromGyujtemenyDTOWithCards(gyujtemenyDTO));

        return "redirect:/";
    }

    @GetMapping("/showcarddetails/{id}")
    public String showCardDetails(@PathVariable Long id, Model model){

        Card card = cardService.findById(id);
        CardDTO cardDTO = cardDTOMapper.tocardDTO(card);


        Set<Gyujtemeny> gyujtemenySet = gyujtemenyService.findAllWithCards();
        Set<GyujtemenyDTO> gyujtemenyDTOSet = gyujtemenyDTOMapper.toGyujtemenyDTOSetWithCards(gyujtemenySet);

        model.addAttribute("cardDTO", cardDTO);
        model.addAttribute("gyujtemenyDTOSet", gyujtemenyDTOSet);

        return "card-details";
    }

    @PostMapping("/savecarddetails")
    public String saveCardDetails(
            @ModelAttribute("cardDTO") CardDTO cardDTO,
            @RequestParam(value = "gyujtemeny_id") int gyujtemeny_id
    ){

        GyujtemenyDTO gyujtemenyDTO = gyujtemenyDTOMapper.toGyujtemenyDTO(gyujtemenyService.finddById(gyujtemeny_id));

        gyujtemenyDTO.assignCardDTO(cardDTO);

        Gyujtemeny gyujtemenyToSave = gyujtemenyDTOMapper.fromGyujtemenyDTOWithCards(gyujtemenyDTO);
        gyujtemenyService.saveWithCards(gyujtemenyToSave);

        return "redirect:/";
    }



}
