package evenementen.event.presentation.controller;//package evenementen.event.presentation.controller;
//
//
//import evenementen.event.application.AlbumService;
//import evenementen.event.application.AuthorizationService;
//import evenementen.event.application.PlayerService;
//import evenementen.event.application.utils.RestCallUtils;
//import evenementen.event.domain.SpotifyUserAuthorizationCode;
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/v1/")
//public class HelloController {
//
//    @Autowired
//    AuthorizationService authorizationService;
//
//    @Autowired
//    PlayerService playerService;
//
//    @Autowired
//    AlbumService albumService;
//
//    SpotifyUserAuthorizationCode spotifyUserAuthorizationCode = new SpotifyUserAuthorizationCode();
//
//    String authCode;
//
//    @GetMapping("/authorize")
//    public String authorize() {
//        return authorizationService.grantApplicationAccess();
//    }
//
//    @GetMapping("/token")
//    public JSONObject getToken() {
//        JSONObject response = new JSONObject();
//        if (spotifyUserAuthorizationCode.getCode() == null || spotifyUserAuthorizationCode.getCode().isEmpty()) {
//            response.put("Error", "Application not authorized yet on user's behalf to access his data");
//            return response;
//        }
//        JSONObject result = authorizationService.getToken(spotifyUserAuthorizationCode.getCode());
//        spotifyUserAuthorizationCode.setAccessToken((String) result.get("access_token"));
//        spotifyUserAuthorizationCode.setRefreshToken((String) result.get("refresh_token"));
//        spotifyUserAuthorizationCode.setTokenType((String) result.get("token_type"));
//        result.put("goToRecentlyPlayedLink", "http://localhost:8080/v1/recentlyPlayed");
//        return result;
//    }
//
//    @GetMapping("/recentlyPlayed")
//    public JSONObject getRecentPlayedTracks() {
//        JSONObject response = new JSONObject();
//        if (spotifyUserAuthorizationCode.getAccessToken() == null || spotifyUserAuthorizationCode.getAccessToken().isEmpty()) {
//            response.put("Error", "UserAccessToken not fetched yet");
//            return response;
//        }
//        JSONObject result = playerService.getRecentlyPlayed(spotifyUserAuthorizationCode.getTokenType() + " " + spotifyUserAuthorizationCode.getAccessToken());
//        return result;
//    }
//
//    @GetMapping("/album/{id}")
//    public JSONObject getRecentPlayedTracks(@PathVariable String id) {
//        JSONObject response = new JSONObject();
//        if (spotifyUserAuthorizationCode.getAccessToken() == null || spotifyUserAuthorizationCode.getAccessToken().isEmpty()) {
//            response.put("Error", "UserAccessToken not fetched yet");
//            return response;
//        }
//        JSONObject result = albumService.getById(spotifyUserAuthorizationCode.getTokenType() + " " + spotifyUserAuthorizationCode.getAccessToken(), id);
//        return result;
//    }
//
//    @RequestMapping("/responseFromSpotify")
//    public String authResponse(@RequestParam(required = false) String code, @RequestParam(required = false) String state, @RequestParam(required = false) String error) {
//        if (error != null) {
//            return "<h1>AccessGranted</h1> <br/>  state:" + state + "  error: " + error;
//        }
//        spotifyUserAuthorizationCode.setCode(code);
//        spotifyUserAuthorizationCode.setUsername("user-" + Thread.currentThread().getName());
//        return "<h1>AccessGranted</h1> <br/>  state:" + state + " <br/>  code:" + code + "<br/> <br/> getToken <a href=\"http://localhost:8080/v1/token\">Get Token to interact on user's behalf</a>\n "; // TODO hide code display
//    }
//
//    @GetMapping("/{name}")
//    public String hello(Model model, @PathVariable String name) {
//        RestCallUtils.checkRequestParameterNotNull("username", name);
//        return "Hello World! " + name;
//    }
//}
