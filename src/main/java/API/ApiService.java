package API;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import model.JsonStandardApiResponse;
import model.JsonStandardApiResponseAdapter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private final HttpClient client;
    private final Gson gson;

    public ApiService() {
        this.client = HttpClient.newHttpClient();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(JsonStandardApiResponse.class, new JsonStandardApiResponseAdapter())
                .setFieldNamingPolicy(FieldNamingPolicy
                        .LOWER_CASE_WITH_UNDERSCORES)
                .serializeNulls()
                .create();
    }

    public Gson getGson() {
        return gson;
    }

    public String requesting(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IllegalStateException("Erro na resposta da ApiUri: " + response.statusCode() + ", Resposta: " + response.body());
        }
        try {
            return response.body();
        } catch (JsonSyntaxException e) {
            throw new IllegalStateException("Erro de sintaxe no JSON: " + e.getMessage() + ", Resposta JSON: " + response.body());
        }
    }
}

