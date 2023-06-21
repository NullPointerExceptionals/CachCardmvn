package nullpointexceptionals.CashCard;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public record Transaction(@Id Long id, Long cashCardId, Double amountAdded, Double amountRemoved,
        LocalDateTime dateCreated) {
}