package principal;

import API.ApiService;
import API.ApiUri;
import com.google.gson.Gson;
import model.JsonStandardApiResponse;
import model.SupportedCoins;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Menu {
    static List<String> keys = SupportedCoins.getPortugueseCoins();
    static List<String> values = SupportedCoins.getCurrencyCode();
    static Map<String, String> map = new HashMap<>();
    static ApiService apiService = new ApiService();
    static Gson gson = apiService.getGson();
    static StringBuilder sb = new StringBuilder();
    static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        String[] menu = {"\n Selecione a opção desejada.\n",
                "1. Moedas dos BRICS+.",
                "2. Moedas do G7.",
                "3. Lista completa de moedas suportadas.",
                "4. Requisitar atualização das taxas.",
                "5. Sair."};
        for (String o : menu) {
            sb.append(o).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void brics() throws IOException, InterruptedException {
        List<String> listBrics = Arrays.asList(
                "real brasileiro",
                "rublo russo",
                "Rupia indiana",
                "Yuan Chinês",
                "Rand sul-africano",
                "Libra egípcia",
                "Peso Argentino",
                "Birr etíope",
                "Rial saudita",
                "Dirham dos Emirados Árabes Unidos",
                "Rial iraniano"
        );
        StringBuilder sb = new StringBuilder();
        int code = 1;

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));

        }
        for (String o : listBrics) {
            sb.append(code).append(". ").append(o)
                    .append(" - ").append(map.get(o)).append("\n");
            code++;
        }


        System.out.println(sb.toString());
        System.out.println("\nQual moeda você possui?");
        System.out.println("Sua escolha: ");

        int choiceBase = scanner.nextInt();
        scanner.nextLine();
        System.out.println("você escolheu: " + listBrics.get(choiceBase - 1) + " - " + map.get(listBrics.get(choiceBase - 1)));

        try (FileReader reader = new FileReader("src/main/resources/jsonStandard.txt")) {
            JsonStandardApiResponse response = gson.fromJson(reader, JsonStandardApiResponse.class);
            String baseCode = response.base_code();
            Map<String, Double> listConversionRates = response.conversion_rates();
            double baseCodeRate = listConversionRates.get(baseCode);
            double baseCoinRate = listConversionRates.get(map.get(listBrics.get(choiceBase - 1)));

            System.out.println("\nPara qual moeda você quer converter? ");
            System.out.println("Sua escolha: ");

            int choiceTarget = scanner.nextInt();

            double targetCoinRate = listConversionRates.get(map.get(listBrics.get(choiceTarget - 1)));
            System.out.println("você escolheu: " + listBrics.get(choiceTarget - 1) + " - " + map.get(listBrics.get(choiceTarget - 1)));
            System.out.println("Digite a quantia em, " + listBrics.get(choiceBase - 1) + " - " + map.get(listBrics.get(choiceBase - 1)) + ", para converter em " + listBrics.get(choiceTarget - 1) + " - " + map.get(listBrics.get(choiceTarget - 1)));

            double valor = scanner.nextDouble();

            double cambio = valor * ((targetCoinRate / baseCoinRate));
            DecimalFormat df = new DecimalFormat("#.##");

            // Formata o número usando o formato especificado
            String formatted = df.format(cambio);

            Currency currency = Currency.getInstance(String.valueOf(map.get(listBrics.get(choiceTarget - 1))));
            System.out.println("\n Sua quantia convertida é: ");
            System.out.println(formatted + " " + currency.getSymbol());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void showCoinMenu(Scanner scanner) {


    }

    public static void G7() {

        List<String> listG7 = Arrays.asList(
                "Dólar estadunidense",
                "Libra esterlina do Reino Unido",
                "Euro da União Europeia",
                "Dólar canadense",
                "Iene japonês"
        );
        StringBuilder sb = new StringBuilder();
        int code = 1;

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));

        }
        for (String o : listG7) {
            sb.append(code).append(". ").append(o)
                    .append(" - ").append(map.get(o)).append("\n");
            code++;
        }
        System.out.println(sb.toString());

        System.out.println("\nQual moeda você possui?");
        System.out.println("Sua escolha: ");

        int choiceBase = scanner.nextInt();

        System.out.println("você escolheu: " + listG7.get(choiceBase - 1) + " - " + map.get(listG7.get(choiceBase - 1)));
        try (FileReader reader = new FileReader("src/main/resources/jsonStandard.txt")) {
            JsonStandardApiResponse response = gson.fromJson(reader, JsonStandardApiResponse.class);
            String baseCode = response.base_code();
            Map<String, Double> listConversionRates = response.conversion_rates();
            double baseCodeRate = listConversionRates.get(baseCode);
            double baseCoinRate = listConversionRates.get(map.get(listG7.get(choiceBase - 1)));

            System.out.println("\nPara qual moeda você quer converter? ");
            System.out.println("Sua escolha: ");

            int choiceTarget = scanner.nextInt();

            double targetCoinRate = listConversionRates.get(map.get(listG7.get(choiceTarget - 1)));
            System.out.println("você escolheu: " + listG7.get(choiceTarget - 1) + " - " + map.get(listG7.get(choiceTarget - 1)));
            System.out.println("Digite a quantia em, " + listG7.get(choiceBase - 1) + " - " + map.get(listG7.get(choiceBase - 1)) + ", para converter em " + listG7.get(choiceTarget - 1) + " - " + map.get(listG7.get(choiceTarget - 1)));

            double valor = scanner.nextDouble();

            double cambio = valor * ((targetCoinRate / baseCoinRate));
            DecimalFormat df = new DecimalFormat("#.##");

            // Formata o número usando o formato especificado
            String formatted = df.format(cambio);

            Currency currency = Currency.getInstance(String.valueOf(map.get(listG7.get(choiceTarget - 1))));
            System.out.println("\n Sua quantia convertida é: ");
            System.out.println(formatted + " " + currency.getSymbol());


        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.nextLine();
    }

    public static void completList() {
        for (int i = 0; i < keys.size(); i++) {
            System.out.println((i + 1) + ". " + keys.get(i) + " - " + values.get(i));
            map.put(keys.get(i), values.get(i));
        }
        System.out.println("\nQual moeda você possui?");
        System.out.println("Sua escolha: ");

        int choiceBase = scanner.nextInt();

        System.out.println("você escolheu: " + keys.get(choiceBase - 1) + " - " + values.get(choiceBase - 1));

        try (FileReader reader = new FileReader("src/main/resources/jsonStandard.txt")) {
            JsonStandardApiResponse response = gson.fromJson(reader, JsonStandardApiResponse.class);
            String baseCode = response.base_code();
            Map<String, Double> listConversionRates = response.conversion_rates();
            double baseCodeRate = listConversionRates.get(baseCode);
            double baseCoinRate = listConversionRates.get(values.get(choiceBase - 1));

            System.out.println("\nPara qual moeda você quer converter? ");
            System.out.println("Sua escolha: ");


            int choiceTarget = scanner.nextInt();

            double targetCoinRate = listConversionRates.get(map.get(keys.get(choiceTarget - 1)));
            System.out.println("você escolheu: " + keys.get(choiceTarget - 1) + " - " + map.get(keys.get(choiceTarget - 1)));
            System.out.println("Digite a quantia em, " + keys.get(choiceBase - 1) + " - " + map.get(keys.get(choiceBase - 1)) + ", para converter em " + keys.get(choiceTarget - 1) + " - " + map.get(keys.get(choiceTarget - 1)));

            double valor = scanner.nextDouble();

            double cambio = valor * ((targetCoinRate / baseCoinRate));
            DecimalFormat df = new DecimalFormat("#.##");

            // Formata o número usando o formato especificado
            String formatted = df.format(cambio);

            Currency currency = Currency.getInstance(String.valueOf(map.get(keys.get(choiceTarget - 1))));
            System.out.println("\n Sua quantia convertida é: ");
            System.out.println(formatted + " " + currency.getSymbol());


        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.nextLine();
    }

    public static void updatingRates() {
        ApiUri urlStandard = new ApiUri();
        String url = urlStandard.getApiExchangedStandard();

        try {
            ApiService req = new ApiService();
            String jsonStandard = req.requesting(url);
            FileWriter escrita = new FileWriter("C:/Users/nicol/Desktop/CoinConversor - Copia/src/main/resources/jsonStandard.txt");
            escrita.write(jsonStandard);
            escrita.close();
            if (!jsonStandard.isEmpty()) {
                System.out.println("Requisição sucedida! Taxas de câmbio atualizadas.");
            } else {
                System.out.println("Algo deu errado!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static boolean checkFileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
