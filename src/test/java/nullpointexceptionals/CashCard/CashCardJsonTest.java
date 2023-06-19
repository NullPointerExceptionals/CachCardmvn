package nullpointexceptionals.CashCard;

import java.io.IOException;

import org.assertj.core.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;


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
            new CashCard(1L, 100.0, "Natalie"),
            new CashCard(2L, 200.0, "Domiique"),
            new CashCard(3L, 300.0, "Jeff"));
                
    
    }

    @Test
    public void cashCardSerializationTest() throws IOException {
        CashCard cashCard = cashCards[0];
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("single.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(100.0);
    
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
    
    }

    @Test
    void cashCardListSerializationTest() throws IOException {
    assertThat(jsonList.write(cashCards)).isStrictlyEqualToJson("list.json");

    }

    @Test
    void cashCardListDeserializationTest() throws IOException {
    String expected="""
         [
            { "id": 1, "amount": 100.0, "owner": "Natalie"},
            { "id": 2, "amount": 200.0, "owner": "Domiique" },
            { "id": 3, "amount": 300.0, "owner": "Jeff" }
         ]
         """;
    assertThat(jsonList.parse(expected)).isEqualTo(cashCards);

    }

    
}
