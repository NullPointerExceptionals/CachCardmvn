package nullpointexceptionals.CashCard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;





@RestController
@RequestMapping("/cashcards")
public class CashCardController {
   private CashCardRepository cashCardRepository;

   public CashCardController(CashCardRepository cashCardRepository) {
      this.cashCardRepository = cashCardRepository;
   }
   private CashCard findCashCard(Long requestedId) {
        return cashCardRepository.findById(requestedId).orElse(null);
}

@GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        CashCard cashCard = findCashCard(requestedId);
        if (cashCard != null) {
            return ResponseEntity.ok(cashCard);
        } else {
            return ResponseEntity.notFound().build();
        }
   
      }

}
