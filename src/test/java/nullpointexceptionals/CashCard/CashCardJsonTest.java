package nullpointexceptionals.CashCard;

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

    
}
