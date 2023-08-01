package dtec.bank.api.utils;

public enum Moeda {
    BRL("Real", "R$", 1),
    USD("Dólar americano", "US$", 4.79f),
    EUR("Euro", "€", 5.26f),
    GBP("Libra esterlina", "£", 6.11f),
    AUD("Dólar australiano", "AU$", 3.17f),
    CAD("Dólar canadense", "C$", 3.60f),
    CHF("Franco suíço", "SFr", 5.47f),
    JPY("Iene japonês", "¥ ", 0.03f),
    ARS("Peso argentino", "$", 0.02f),
    PEN("Novo sol peruano", "S/.", 1.32f),
    BOB("Bolíviano da Bolívia", "$b", .69f),
    CNY("Yuan chinês", "¥", .67f),
    UYU("Peso uruguaio", "$U", .13f),
    SGD("Dólar de Singapura", "S$", 3.58f),
    TRY("Lira turca", "₺", .18f),
    HKD("Dólar de Hong Kong", "HK$", .61f);

    private String nome;
    private String sibolo;
    private float multiplicador;

    Moeda (String nome, String sibolo, float multiplicador) {
        this.nome = nome;
        this.sibolo = sibolo;
        this.multiplicador = multiplicador;
    }

    public String getNome () {
        return nome;
    }

    public String getSibolo () {
        return sibolo;
    }

    public float getMultiplicador () {
        return multiplicador;
    }
}
