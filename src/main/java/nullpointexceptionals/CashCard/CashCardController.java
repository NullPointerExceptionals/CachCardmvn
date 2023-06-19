package nullpointexceptionals.CashCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
