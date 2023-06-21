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
public class AuthUserJsonTest {
    
    @Autowired
    private JacksonTester<AuthUser> json;

    @Autowired private JacksonTester<AuthUser[]> jsonList;

    private AuthUser[] authUsers;

    @BeforeEach
    void setUp() {
        authUsers = Arrays.array(
            new AuthUser(1L, "Alice", 1L),
            new AuthUser(2L, "Bob", 2L),
            new AuthUser(3L, "Charlie", 3L),
            new AuthUser(4L, "David", 4L),
            new AuthUser(5L, "Eve", 5L),
            new AuthUser(6L, "Frank", 6L),
            new AuthUser(7L, "Grace", 7L),
            new AuthUser(8L, "Henry", 8L),
            new AuthUser(9L, "Isabella", 9L),
            new AuthUser(10L, "Jack", 10L),
            new AuthUser(11L, "Kate", 11L),
            new AuthUser(12L, "Liam", 12L)
        );
    }

    @Test
    public void authorizedUserSerializationTest() throws IOException {
        AuthUser authUser = authUsers[0];
        assertThat(json.write(authUser)).isStrictlyEqualToJson("authuser.json");
        assertThat(json.write(authUser)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(authUser)).extractingJsonPathNumberValue("@.id")
        .isEqualTo(1);
        assertThat(json.write(authUser)).hasJsonPathStringValue("@.name");
        assertThat(json.write(authUser)).extractingJsonPathStringValue("@.name")
        .isEqualTo("Alice");
        assertThat(json.write(authUser)).hasJsonPathNumberValue("@.cashCardId");
        assertThat(json.write(authUser)).extractingJsonPathNumberValue("@.cashCardId")
        .isEqualTo(1);
    }

    @Test
    public void authorisedUserDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 1,
                    "name": "Alice",
                    "cashCardId": 1
                }
                """;
        assertThat(json.parse(expected)).isEqualTo(new AuthUser(1L, "Alice", 1L));
        assertThat(json.parseObject(expected).id()).isEqualTo(1);
        assertThat(json.parseObject(expected).name()).isEqualTo("Alice");       
        assertThat(json.parseObject(expected).cashCardId()).isEqualTo(1);
    }

    @Test
    void authorizedUsersListSerializationTest() throws IOException {
        assertThat(jsonList.write(authUsers)).isStrictlyEqualToJson("authuserslist.json");
    }

    @Test
    void authorizedUsersListDeserializationTest() throws IOException {
        String expected = """
                [
                    {"id": 1, "name": "Alice", "cashCardId": 1},
                    {"id": 2, "name": "Bob", "cashCardId": 2},
                    {"id": 3, "name": "Charlie", "cashCardId": 3},
                    {"id": 4, "name": "David", "cashCardId": 4},
                    {"id": 5, "name": "Eve", "cashCardId": 5},
                    {"id": 6, "name": "Frank", "cashCardId": 6},
                    {"id": 7, "name": "Grace", "cashCardId": 7},
                    {"id": 8, "name": "Henry", "cashCardId": 8},
                    {"id": 9, "name": "Isabella", "cashCardId": 9},
                    {"id": 10, "name": "Jack", "cashCardId": 10},
                    {"id": 11, "name": "Kate", "cashCardId": 11},
                    {"id": 12, "name": "Liam", "cashCardId": 12}
                ]
                """;
        assertThat(jsonList.parse(expected)).isEqualTo(authUsers);   
    }
}

