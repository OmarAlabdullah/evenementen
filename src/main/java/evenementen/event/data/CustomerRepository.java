package evenementen.event.data;

import evenementen.event.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String username);

//    Optional<Customer> findByUsernameAndId(String username, int id);
}
