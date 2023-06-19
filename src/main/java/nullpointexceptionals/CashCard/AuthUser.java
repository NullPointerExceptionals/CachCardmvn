package nullpointexceptionals.CashCard;

import org.springframework.data.annotation.Id;

public record AuthUser(@Id Long id, String name, Long cash_card_id) {

}
