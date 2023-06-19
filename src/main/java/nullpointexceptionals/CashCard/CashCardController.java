package nullpointexceptionals.CashCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.security.Principal;

import org.springframework.web.util.UriComponentsBuilder;

// import main.java.nullpointexceptionals.CashCard;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {
   private CashCardRepository cashCardRepository;
   private final TransactionRepository transactionRepository;
   private AuthUserRepository authUserRepository;

   public CashCardController(CashCardRepository cashCardRepository, TransactionRepository transactionRepository,
         AuthUserRepository authUserRepository) {
      this.cashCardRepository = cashCardRepository;
      this.transactionRepository = transactionRepository;
      this.authUserRepository = authUserRepository;
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

   // Find by Owner and ID
   @GetMapping("/owner/{owner}/{id}")
   public ResponseEntity<CashCard> findByOwnerAndId(@PathVariable String owner, @PathVariable Long id) {
      CashCard cashCard = cashCardRepository.findByOwnerAndId(owner, id);
      if (cashCard != null) {
         return ResponseEntity.ok(cashCard);
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // Delete by Owner and ID
   @DeleteMapping("/owner/{owner}/{id}")
   public ResponseEntity<?> deleteById(@PathVariable String owner, @PathVariable Long id) {
      CashCard cashCard = cashCardRepository.findByOwnerAndId(owner, id);
      if (cashCard != null) {
         cashCardRepository.delete(cashCard);
         return ResponseEntity.noContent().build();
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // Update by Owner and ID
   @PutMapping("/owner/{owner}/{id}")
   public ResponseEntity<?> findByOwnerAndId(@PathVariable String owner, @PathVariable Long id,
         @RequestBody CashCard cashCardUpdate) {
      CashCard cashCard = cashCardRepository.findByOwnerAndId(owner, id);
      if (cashCard != null) {
         double updatedAmount = cashCard.amount() + cashCardUpdate.amount();
         CashCard updatedCashCard = new CashCard(cashCard.id(), updatedAmount, cashCard.owner());
         cashCardRepository.save(updatedCashCard);
         return ResponseEntity.noContent().build();
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // Create by Owner
   @PostMapping
   public ResponseEntity<?> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb) {
      CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
      URI locationOfNewCashCard = ucb
            .path("/owner/{owner}")
            .buildAndExpand(savedCashCard.id())
            .toUri();
      return ResponseEntity.created(locationOfNewCashCard).build();
   }

   // // TRANSACTIONS

   @GetMapping("/{id}/transactions")
   public ResponseEntity<List<Transaction>> getTransactionsByCashCardId(@PathVariable Long id) {
      List<Transaction> transactions = transactionRepository.findByCashCardId(id);
      if (transactions.isEmpty()) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(transactions);
   }

}
