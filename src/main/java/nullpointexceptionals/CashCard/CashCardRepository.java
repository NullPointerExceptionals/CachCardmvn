package nullpointexceptionals.CashCard;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// import org.springframework.data.repository.PagingAndSortingRepository;

public interface CashCardRepository extends CrudRepository<CashCard, Long> {
    List<CashCard> findByOwner(String owner);

    CashCard findByOwnerAndId(String owner, Long id);

}
