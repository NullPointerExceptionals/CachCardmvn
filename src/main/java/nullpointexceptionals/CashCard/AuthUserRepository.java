package nullpointexceptionals.CashCard;

import org.springframework.data.repository.CrudRepository;

public interface AuthUserRepository extends CrudRepository<AuthUser, Long> {
    AuthUser findByName(String name);
}
