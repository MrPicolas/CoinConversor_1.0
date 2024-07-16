package API;

public class ApiUri {
    private String apiKey = "5f8a268841b210c7d3f22afa";
    public String getApiExchangedStandard() {
        return "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
    }

    public String getApiExchangedSupportedCoins() {
        return "https://v6.exchangerate-api.com/v6/" + apiKey + "/codes";
    }
}
