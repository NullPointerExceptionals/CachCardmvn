package nullpointexceptionals.CashCard;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashcardApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
 // Test mapping to get by requested ID only
	@Test
    void shouldReturnACashCardWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1);

        Double amount = documentContext.read("$.amount");
        assertThat(amount).isEqualTo(100.0);

        String owner = documentContext.read("$.owner");
        assertThat(owner).isEqualTo("Natalie");
    }
// Find all cards by owner
	@Test
	void shouldReturnAllCashCardsByOwner() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/owner/Natalie", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		int cashCardCount = documentContext.read("$.length()");
		assertThat(cashCardCount).isEqualTo(2);

		JSONArray ids = documentContext.read("$..id");
		assertThat(ids).containsExactlyInAnyOrder(1, 7);

		JSONArray amounts = documentContext.read("$..amount");
		assertThat(amounts).containsExactlyInAnyOrder(100.0, 200.0);
	}
// Find by Owner and ID
	@Test
	void shouldReturnASingleCashCardByOwner() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/owner/Natalie/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1);

        Double amount = documentContext.read("$.amount");
        assertThat(amount).isEqualTo(100.0);
	}

    @Test
    void shouldNotReturnACashCardWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/1000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }
   // Create by Owner
    @Test
    @DirtiesContext
    void shouldCreateANewCashCard() {
        CashCard newCashCard = new CashCard(null, 250.00, null);
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/owner/Nathan", newCashCard, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCashCard, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        Double amount = documentContext.read("$.amount");
        String owner = documentContext.read("$.owner");

        assertThat(id).isNotNull();
        assertThat(amount).isEqualTo(250.00);
        assertThat(owner).isEqualTo("Nathan");
   
	}
   // Update by Owner and ID
    @Test
	@DirtiesContext
	void shouldUpdateAnExistingCashCard() {
    CashCard cashCardUpdate = new CashCard(null, 100.0, null);
    HttpEntity<CashCard> request = new HttpEntity<>(cashCardUpdate);
    ResponseEntity<Void> response = restTemplate
            .exchange("/cashcards/owner/Natalie/1", HttpMethod.PUT, request, Void.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	ResponseEntity<String> getResponse = restTemplate
          .getForEntity("/cashcards/owner/Natalie/1", String.class);
  	assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
  	DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
  	Number id = documentContext.read("$.id");
  	Double amount = documentContext.read("$.amount");
    String owner = documentContext.read("$.owner");
  	assertThat(id).isEqualTo(1);
  	assertThat(amount).isEqualTo(200.00);
    assertThat(owner).isEqualTo("Natalie");
	
	}

    @Test
	void shouldNotUpdateACashCardThatDoesNotExist() {
    	CashCard unknownCard = new CashCard(null, 100.0, null);
    	HttpEntity<CashCard> request = new HttpEntity<>(unknownCard);
    	ResponseEntity<Void> response = restTemplate
            .exchange("/cashcards/owner/Natalie/1000", HttpMethod.PUT, request, Void.class);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	
	}
   // Delete by Owner and ID
    @Test
	@DirtiesContext
	void shouldDeleteAnExistingCashCard() {
    	ResponseEntity<Void> response = restTemplate
            .exchange("/cashcards/owner/Natalie/1", HttpMethod.DELETE, null, Void.class);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		ResponseEntity<String> getResponse = restTemplate
            .getForEntity("/cashcards/owner/Natalie/1", String.class);
    	assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	
	}
   // TRANSACTIONS
	@Test
	void shouldReturnAllTransactionsFromAnId() {
		ResponseEntity<String> response = restTemplate.getForEntity("/1/transactions", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		int transactionCount = documentContext.read("$.length()");
		assertThat(transactionCount).isEqualTo(2);

		JSONArray ids = documentContext.read("$..id");
		assertThat(ids).containsExactlyInAnyOrder(1, 4);

		JSONArray cashCards = documentContext.read("$..cashCardId");
		assertThat(cashCards).isEqualTo(1);

		JSONArray amounts = documentContext.read("$.amountAdded");
		assertThat(amounts).containsExactlyInAnyOrder(50.0, 500.0);
	}
   // Auth user GET
   @Test
	void shouldReturnASingleCashCardByAuthUser() {
		ResponseEntity<String> response = restTemplate.getForEntity("/authuser/Alice", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1);

        Double amount = documentContext.read("$.amount");
        assertThat(amount).isEqualTo(100.0);
	}
}
