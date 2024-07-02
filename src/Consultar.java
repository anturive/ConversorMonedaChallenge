import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consultar {
    private final API api_key = API.APIKEY;
    private final String url = "https://v6.exchangerate-api.com/v6/"+this.api_key+"/pair/";

    public String Consultar(String base_code, String target_code, Double amount){
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url+base_code+"/"+target_code+"/"+amount))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e ) {
            throw new RuntimeException(e);
        }
    }
}
