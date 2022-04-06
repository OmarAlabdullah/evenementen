package evenementen.event.application.utils;

//import com.google.gson.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SpotifyToken {
    public String accessToken = "";
    public String expiresIn = "";

    public static void gett() throws IOException {

        URL url = new URL(Endpoints.TOKEN);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

        String data = "grant_type=client_credentials&client_id=" + Endpoints.CLIENT_ID + "&client_secret=" + Endpoints.CLIENT_SECRET + "";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        BufferedReader Lines = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String currentLine = Lines.readLine();
        StringBuilder response = new StringBuilder();
        while (currentLine != null) {
            response.append(currentLine).append("\n");
            currentLine = Lines.readLine();
            System.out.println(currentLine);
        }

//        this.accessToken = String.valueOf(JsonParser.parseString(String.valueOf(response)).getAsJsonObject().getAsJsonObject("access_token"));
//        this.expiresIn = String.valueOf(JsonParser.parseString(String.valueOf(response)).getAsJsonObject().getAsJsonObject("expires_in"));

        http.disconnect();
    }

    public static String getToken() throws IOException {
        //create url access point
//        URL url = new URL(Endpoints.CLIENT_SECRET);

        //open http connection to url
        URL url = new URL(Endpoints.TOKEN);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);

        //setup post function and request headers
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization",String.format("Basic %s", "aeec6cb7dc8c411fbc5d31dcb4bb1e2e:f87776f131ed46baba07004c80afa5ec"));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        //set body for posting
        String body = "grant_type=client_credentials";

        //calculate and set content length
        byte[] out = body.getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        conn.setFixedLengthStreamingMode(length);

        //connect to http
        conn.connect();
        //}

        //send bytes to spotify
        try(OutputStream os = conn.getOutputStream()) {
            os.write(out);
        }

        //receive access token
        InputStream result = conn.getInputStream();
        String s = new String(result.readAllBytes());
        System.out.println(s);

        return s;
    }

    public static void getTokens(String CLIENTID, String REDIRECTURL) throws IOException {
        try {
        String url_auth =
                "https://accounts.spotify.com/authorize?"
                        + "client_id="+CLIENTID+"&"
                        + "response_type=code&"
                        + "redirect_uri="+REDIRECTURL+"&"
                        + "scope=user-read-private%20user-read-email&"
                        + "state=34fFs29kd09";

        System.out.println(url_auth);

        URL url = new URL(url_auth);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization",String.format("Basic %s", "aeec6cb7dc8c411fbc5d31dcb4bb1e2e:f87776f131ed46baba07004c80afa5ec"));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        System.out.println(conn.getHeaderField(13));


        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
    } catch (
    MalformedURLException e) {
        e.printStackTrace();
    } catch (
    IOException e) {
        e.printStackTrace();
    }
    }


}
