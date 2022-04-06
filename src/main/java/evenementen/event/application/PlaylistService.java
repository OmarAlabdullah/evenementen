package evenementen.event.application;

import evenementen.event.application.data.PlaylistData;
import evenementen.event.application.utils.RestCallUtils;
import evenementen.event.application.utils.SpotifyToken;
import evenementen.event.configs.SpotifyConnectionConfig;
import evenementen.event.data.PlaylistRepository;
import evenementen.event.domain.Playlist;
import evenementen.event.domain.exception.PlaylistAlreadyExistsException;
import evenementen.event.domain.exception.PlaylistNotFoundException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Transactional
@Service
public class PlaylistService {
    private PlaylistRepository playlistRepository;

    static final String CLIENTID = "aeec6cb7dc8c411fbc5d31dcb4bb1e2e";
    static final String CLIENTSECRET = "f87776f131ed46baba07004c80afa5ec";
    static final String REDIRECTURL = "http://localhost8080/events"; //whiltelisted set inside spotify

    RestTemplate restTemplate;

    @Autowired
    SpotifyConnectionConfig spotifyConnectionConfig;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void save(int playlistId, String name, int lengthOfplayList) {

        if (playlistRepository.existsById(playlistId)) {
            throw new PlaylistAlreadyExistsException();
        } else {
            this.playlistRepository.save(new Playlist(playlistId, name, lengthOfplayList));
        }
    }

    public void update(Playlist playlist) {
        this.playlistRepository.save(playlist);
    }


    public void delete(int code) {
        if (!playlistRepository.existsById(code)) {
            throw new PlaylistNotFoundException();
        }
        this.playlistRepository.deleteById(code);
    }

    public List<Playlist> getAllPlaylists() {
        return this.playlistRepository.findAll();

    }

    public PlaylistData findByPlaylistId(int playlistId) {
        Playlist playlist = this.playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException());

        return new PlaylistData(
                playlist.getPlaylistId(),
                playlist.getName(),
                playlist.getLengthOfPlayList());

    }
    public void findByplaylist(String playlistId) throws IOException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        SpotifyToken.getTokens(CLIENTID,REDIRECTURL);
        System.out.println(".........................................");




        System.out.println(playlistId);


        String http = "https://api.spotify.com/v1/playlists/" +playlistId ;

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(http);

        JSONObject entityObj = new JSONObject();
        JSONObject dataObj = new JSONObject();
//        dataObj.addProperty("name", "title");
//        dataObj.addProperty("public", "false");
//        entityObj.add("data", dataObj);

        String dataStringify = "{\n" +
                " name: New Playlist,\n" +
                "  \"public\": false\n" +
                "}";
        StringEntity entity = new StringEntity(dataStringify);
        post.setEntity(entity);
        String accessToken = "BQC4eH3PECVBfDhIxUmNofLSmmBNjr5-a5BTgKmfoL9A0lAmTnbmV7UgtbzsL94Qd42mncuwQFq_LXizMRcIZQpNeeIfu0UzGzJZMo3P-y41Y2WwkXGZz6mGKS0Q1SPzmH576Dqw-d25sM8uKQbiDPm0PhW-6DrkkMWksWxjfjdT";
        post.setHeader("Authorization", "Bearer " + SpotifyToken.getToken());
        post.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = client.execute(post);
        System.out.println(SpotifyToken.getToken());
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        String resp = EntityUtils.toString(response.getEntity());
        JSONObject responseObj = new JSONObject();
        System.out.println(resp);
        SpotifyToken.gett();
        client.close();

//        AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
//        if (response.getType() == AuthenticationResponse.Type.TOKEN) {
//
//            authToken = response.getAccessToken();
////            Log.e("MainActivity","Auth Token: "+authToken.toString());
//        URL url = new URL("https://api.spotify.com/v1/me/playlists");
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        urlConnection.setRequestProperty("Authorization", "Bearer " + SpotifyToken.getToken());
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    }

    public JSONObject getPlaylistItems(String token, String playlistId) throws Exception{

        String createIndexUrl = SpotifyConnectionConfig.getPlaylistUrl() + playlistId;

        // prepare request headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", token);

        // hit request
        ResponseEntity<JSONObject> responseEntity = AuthorizationService.callAction(restTemplate, "getPlaylist", createIndexUrl, GET,
                new HttpEntity<>(null, requestHeaders), JSONObject.class, null);

        // check response
        RestCallUtils.checkResponseCodeExpected(responseEntity, Arrays.asList(NO_CONTENT, OK), "getPlaylist");

        return responseEntity.getBody();



    }
}

//https://accounts.spotify.com/authorize?client_id=aeec6cb7dc8c411fbc5d31dcb4bb1e2e&redirect_uri=http%3A%2F%2Flocalhost8080%2Fevents
