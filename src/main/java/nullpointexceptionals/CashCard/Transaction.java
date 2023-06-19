package nullpointexceptionals.CashCard;

import org.springframework.data.annotation.Id;

public record Transaction(@Id Long id, Long cashCardId, Double amountAdded, Double amountRemoved) {
}