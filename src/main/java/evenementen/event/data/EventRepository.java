package evenementen.event.data;

import evenementen.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
//    Optional<Playlist> findByUsernameAndId(String username, int id);

}
