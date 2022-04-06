package evenementen.event.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SpotifyUserAuthorizationCode {

    private String username;
    private String code;
    private String accessToken;
    private String tokenType;
    private String refreshToken;
}
//TODO encrypt and persist this db
