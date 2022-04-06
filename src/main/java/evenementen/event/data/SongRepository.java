package evenementen.event.data;

import evenementen.event.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
//    Optional<Song> findById(int id);
}
