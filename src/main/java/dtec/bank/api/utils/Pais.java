package dtec.bank.api.utils;

public enum Pais {
    BRA ("Brasil", "PT", 3),
    USA("Estados Unidos da America", "EN",-2);

    private String nome;
    private String idioma;
    private int utc;

    Pais (String nome, String idioma, int utc) {
        this.nome = nome;
        this.idioma = idioma;
        this.utc = utc;
    }

    public String getNome () {
        return nome;
    }

    public String getIdioma () {
        return idioma;
    }

    public int getUtc () {
        return utc;
    }
}
