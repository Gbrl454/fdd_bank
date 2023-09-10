package dtec.bank.api.utils;

public enum Pais {
    BRA("Brasil", Idioma.PT, -3, Moeda.BRL),
    USA("Estados Unidos da America", Idioma.EN, -4, Moeda.USD);

    private final String nome;
    private final Idioma idioma;
    private final int utc;

    private final Moeda moeda;

    Pais(String nome, Idioma idioma, int utc, Moeda moeda) {
        this.nome = nome;
        this.idioma = idioma;
        this.utc = utc;
        this.moeda = moeda;
    }

    public String getNome() {
        return nome;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public int getUtc() {
        return utc;
    }

    public Moeda getMoeda() {
        return moeda;
    }
}
