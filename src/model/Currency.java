package model;

public enum Currency {
    ILS, USD, EURO, GBP;

    public String which() {
        return this.toString();
    }
}
