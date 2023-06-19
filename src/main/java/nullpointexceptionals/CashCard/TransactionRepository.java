package nullpointexceptionals.CashCard;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByCashCardId(Long cashCardId);
}
