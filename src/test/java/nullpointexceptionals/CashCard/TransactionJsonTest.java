package nullpointexceptionals.CashCard;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class TransactionJsonTest {

    @Autowired
    private JacksonTester<Transaction> json;

    @Autowired
    private JacksonTester<Transaction[]> jsonList;

    private Transaction[] transactions;

    @BeforeEach
    void setUp() {
        transactions = Arrays.array(
            new Transaction(1L, 50.0, 0.0),
        );
    }
    
}
