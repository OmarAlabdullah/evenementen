package evenementen.event.data;

import evenementen.event.domain.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
    Optional<Organizer> findByEmail(String email);
//    Optional<Organizer> findByUsernameAndId(String username, int id);
}
