package nullpointexceptionals.CashCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import java.net.URI;
import java.security.Principal;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

// import main.java.nullpointexceptionals.CashCard;

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
      return cashCardRepository.findById(requestedId).orElse(null);
   }

   // Test mapping to get by requested ID only
   @GetMapping("/{requestedId}")
   public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
      CashCard cashCard = findCashCard(requestedId);
      if (cashCard != null) {
         return ResponseEntity.ok(cashCard);
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // Find all cards by owner
   @GetMapping("/owner/{owner}")
   public ResponseEntity<List<CashCard>> getCashCardsByOwner(@PathVariable String owner) {
      List<CashCard> cashCards = cashCardRepository.findByOwner(owner);
      if (cashCards.isEmpty()) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(cashCards);
   }

}
