package nullpointexceptionals.CashCard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDateTime;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

   // Delete by Owner and ID Deletes Transactions
   @DeleteMapping("/owner/{owner}/{id}")
   public ResponseEntity<?> deleteById(@PathVariable String owner, @PathVariable Long id) {
      CashCard cashCard = cashCardRepository.findByOwnerAndId(owner, id);
      if (cashCard != null) {
         List<Transaction> transactions = transactionRepository.findByCashCardId(cashCard.id());
         for (Transaction transaction : transactions) {
            transactionRepository.delete(transaction);
         }

         AuthUser authUser = authUserRepository.findByCashCardId(cashCard.id());
         if (authUser != null) {
            authUserRepository.delete(authUser);
         }

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

         double amountAdded = 0;
         double amountRemoved = 0;
         if (cashCardUpdate.amount() > 0) {
            amountAdded = cashCardUpdate.amount();
         } else if (cashCardUpdate.amount() < 0) {
            amountRemoved = Math.abs(cashCardUpdate.amount());
         }
         LocalDateTime dateCreated = LocalDateTime.now();
         Transaction transaction = new Transaction(null, cashCard.id(), amountAdded, amountRemoved, dateCreated);
         transactionRepository.save(transaction);

         return ResponseEntity.noContent().build();
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // Create CashCard
   @PostMapping
   public ResponseEntity<?> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb) {
      CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
      URI locationOfNewCashCard = ucb
            .path("/owner/{owner}")
            .buildAndExpand(savedCashCard.id())
            .toUri();
      return ResponseEntity.created(locationOfNewCashCard)
            .body("{\"id\": \"" + savedCashCard.id() + "\"}");
   }

   // Create AuthUser
   @PostMapping("/authuser")
   public ResponseEntity<?> createAuthUser(@RequestBody AuthUser newAuthUserRequest, UriComponentsBuilder ucb) {
      AuthUser savedAuthUser = authUserRepository.save(newAuthUserRequest);
      URI locationOfNewAuthUser = ucb
            .path("/authuser/{authuser}")
            .buildAndExpand(savedAuthUser.id())
            .toUri();
      return ResponseEntity.created(locationOfNewAuthUser).build();
   }

   // Auth user GET by name
   @GetMapping("/authuser/{authuser}")
   public ResponseEntity<CashCard> findCardByAuthUser(@PathVariable String authuser) {
      AuthUser authUser = authUserRepository.findByName(authuser);
      if (authUser != null) {
         CashCard cashCard = cashCardRepository.findById(authUser.cashCardId()).orElse(null);
         if (cashCard != null) {
            return ResponseEntity.ok(cashCard);
         } else {
            return ResponseEntity.notFound().build();
         }
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // Auth user GET by cash card id
   @GetMapping("/authuser/id/{id}")
   public ResponseEntity<AuthUser> findAuthUserByCashCardId(@PathVariable Long id) {
      AuthUser authUser = authUserRepository.findByCashCardId(id);
      if (authUser != null) {
         return ResponseEntity.ok(authUser);
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // TRANSACTIONS

   @GetMapping("/{id}/transactions")
   public ResponseEntity<List<Transaction>> getTransactionsByCashCardId(@PathVariable Long id) {
      List<Transaction> transactions = transactionRepository.findByCashCardId(id);
      if (transactions.isEmpty()) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(transactions);
   }

}
