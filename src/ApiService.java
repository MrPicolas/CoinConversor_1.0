package CoinConversor.src;

import com.google.gson.*;

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
                .setFieldNamingPolicy(FieldNamingPolicy
                        .UPPER_CAMEL_CASE)
                .create();
    }

    public String requisitando(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IllegalStateException("Erro na resposta da API: " + response.statusCode() + ", Resposta: " + response.body());
        }

        try {
            return response.body();
        } catch (JsonSyntaxException e) {
            throw new IllegalStateException("Erro de sintaxe no JSON: " + e.getMessage() + ", Resposta JSON: " + response.body());
        }
    }
}



    /*public String Requisitando () throws IOException, InterruptedException {

        API urlApi = new API();
        String ex = urlApi.getApiExchange();
        System.out.println(ex);

        return response.body();

        /*JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        double valorDolar = conversionRates.get("USD").getAsDouble();
        double valorReal = conversionRates.get("BRL").getAsDouble();
        return valorDolar * valorReal; */

        //System.out.println("1 dolar vale: " + valorDolar * valorReal "Reais");


