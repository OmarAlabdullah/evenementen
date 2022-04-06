package evenementen.event.data;

import evenementen.event.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
//    Optional<Playlist> findByUsernameAndId(String username, int id);
}
