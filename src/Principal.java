package CoinConversor.src;

import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws IOException, InterruptedException {

        API api = new API();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite sua moeda: ");
        String moedaB = scanner.nextLine();
        api.setMoedaBase(moedaB);

        System.out.println("Digite a moeda que deseja: ");
        String moedaA = scanner.nextLine();
        api.setMoedaAlvo(moedaA);

        /*Requisicao req = new Requisicao();
        String json = req.Requisitando();
        System.out.println(json);*/

        String ex = api.getApiExchange();
        System.out.println(ex);

        /*Conversor mostra = new Conversor();
        System.out.println("taxa de conversão entre elas: " + mostra.Convertendo());*/

        scanner.close();
        String url = api.getApiExchange();
        System.out.println("URL da API: " + url);

        // Criando instância do serviço de API e fazendo a requisição
        ApiService apiService = new ApiService();

        try {
            String json = apiService.requisitando(url);
            System.out.println(json);
            //System.out.println("Taxa de Conversão: " + json.getConversionRate());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}

