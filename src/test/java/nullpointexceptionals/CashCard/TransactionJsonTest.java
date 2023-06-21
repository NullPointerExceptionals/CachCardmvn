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
            new Transaction(1L, 1L, 50.0, 0.0, null),
            new Transaction(2L, 2L, 0.0, 25.0, null),
            new Transaction(3L, 3L, 75.0, 0.0, null),
            new Transaction(4L, 1L, 500.0, 0.0, null),
            new Transaction(5L, 2L, 0.0, 250.0, null),
            new Transaction(6L, 3L, 750.0, 0.0, null)
        );
    }

    @Test
    public void transactionSerializationTest() throws IOException {
        Transaction transaction = transactions[0];
        assertThat(json.write(transaction)).isStrictlyEqualToJson("transaction.json");
        assertThat(json.write(transaction)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(transaction)).extractingJsonPathNumberValue("@.id")
        .isEqualTo(1);
        assertThat(json.write(transaction)).hasJsonPathNumberValue("@.cashCardId");
        assertThat(json.write(transaction)).extractingJsonPathNumberValue("@.cashCardId")
        .isEqualTo(1);
        assertThat(json.write(transaction)).hasJsonPathNumberValue("@.amountAdded");
        assertThat(json.write(transaction)).extractingJsonPathNumberValue("@.amountAdded")
        .isEqualTo(50.0);
        assertThat(json.write(transaction)).hasJsonPathNumberValue("@.amountRemoved");
        assertThat(json.write(transaction)).extractingJsonPathNumberValue("@.amountRemoved")
        .isEqualTo(0.0);
    }

    @Test
    public void transactionDeserializationTest() throws IOException {
        String expected = """
            {
                "id": 1,
                "cashCardId": 1,
                "amountAdded": 50.0,
                "amountRemoved": 0.0,
                "dateCreated": null
            }
            """;
        assertThat(json.parse(expected)).isEqualTo(new Transaction(1L, 1L, 50.0, 0.0, null));
        assertThat(json.parseObject(expected).id()).isEqualTo(1);
        assertThat(json.parseObject(expected).cashCardId()).isEqualTo(1);
        assertThat(json.parseObject(expected).amountAdded()).isEqualTo(50.0);
        assertThat(json.parseObject(expected).amountRemoved()).isEqualTo(0.0);

    }

    @Test
    void transactionListSerializationTest() throws IOException {
        assertThat(jsonList.write(transactions)).isStrictlyEqualToJson("transactionslist.json");
    }

    @Test
    void transactionsListDeserializationTest() throws IOException {
    String expected="""
         [
            {"id": 1, "cashCardId": 1, "amountAdded": 50.0, "amountRemoved": 0.0, "dateCreated": null},
            {"id": 2, "cashCardId": 2, "amountAdded": 0.0, "amountRemoved": 25.0, "dateCreated": null},
            {"id": 3, "cashCardId": 3, "amountAdded": 75.0, "amountRemoved": 0.0, "dateCreated": null},
            {"id": 4, "cashCardId": 1, "amountAdded": 500.0, "amountRemoved": 0.0, "dateCreated": null},
            {"id": 5, "cashCardId": 2, "amountAdded": 0.0, "amountRemoved": 250.0, "dateCreated": null},
            {"id": 6, "cashCardId": 3, "amountAdded": 750.0, "amountRemoved": 0.0, "dateCreated": null}
         ]
         """;
    assertThat(jsonList.parse(expected)).isEqualTo(transactions);

    }
    
}
