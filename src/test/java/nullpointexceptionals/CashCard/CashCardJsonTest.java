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
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;

    @Autowired
    private JacksonTester<CashCard[]> jsonList;

    private CashCard[] cashCards;

    @BeforeEach
    void setUp() {
        cashCards = Arrays.array(
            new CashCard( 1L,  100.0,  "Natalie"),
            new CashCard( 2L,  200.0,  "Domiique"),
            new CashCard( 3L,  300.0,  "Jeff"),
            new CashCard( 4L, 400.0, "John"),
            new CashCard( 5L, 500.0, "Tim"),
            new CashCard( 6L, 600.0, "Mohamed"),
            new CashCard( 7L, 200.0, "Natalie"),
            new CashCard( 8L, 400.0, "Domiique"),
            new CashCard( 9L, 600.0, "Jeff"),
            new CashCard( 10L, 800.0, "John"),
            new CashCard( 11L, 1000.0, "Tim"),
            new CashCard( 12L, 1200.0, "Mohamed")
        );
    }

    @Test
    public void cashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(1L, 100.0, "Natalie");
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("single.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
             .isEqualTo(100.00);
        assertThat(json.write(cashCard)).hasJsonPathStringValue("@.owner");
        assertThat(json.write(cashCard)).extractingJsonPathStringValue("@.owner")
             .isEqualTo("Natalie");
    }

    @Test
    public void cashCardDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 1,
                    "amount": 100.0,
                    "owner": "Natalie"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new CashCard(1L, 100.0, "Natalie"));
        assertThat(json.parseObject(expected).id()).isEqualTo(1);
        assertThat(json.parseObject(expected).amount()).isEqualTo(100.0);
        assertThat(json.parseObject(expected).owner()).isEqualTo("Natalie");
    
    }

    @Test
    void cashCardListSerializationTest() throws IOException {
    assertThat(jsonList.write(cashCards)).isStrictlyEqualToJson("list.json");

    }

    
}
