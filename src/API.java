package CoinConversor.src;

public class API {

    private String moedaBase;
    private String moedaAlvo;

    public void setMoedaAlvo(String moedaA) {
        this.moedaAlvo = moedaA;
    }

    public void setMoedaBase(String moedaB) {
        this.moedaBase = moedaB;
    }

    public String getMoedaBase() {
        return moedaBase;
    }

    public String getMoedaAlvo() {
        return moedaAlvo;
    }

    /*private String apiExchange = "https://v6.exchangerate-api.com/v6/5f8a268841b210c7d3f22afa/pair/"
            + moedaBase + "/" + moedaAlvo + "/";*/

    //private String apiExchange = "https://v6.exchangerate-api.com/v6/5f8a268841b210c7d3f22afa/pair/USD/BRL/";

    public String getApiExchange() {
        return "https://v6.exchangerate-api.com/v6/5f8a268841b210c7d3f22afa/pair/"
                + moedaBase + "/" + moedaAlvo + "/";
    }
}
