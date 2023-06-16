package nullpointexceptionals.CashCard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import java.net.URI;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;



import main.java.nullpointexceptionals.CashCard;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/cashcards")
public class CashCardController {
   private CashCardRepository cashCardRepository;

   public CashCardController(CashCardRepository cashCardRepository) {
      this.cashCardRepository = cashCardRepository;
   }
   private CashCard findCashCard(Long requestedId) {
    return cashCardRepository.findByIdAndOwner(requestedId);
}
