package model;




/**
 *
 * This Class describes the currencies in our system, used in costs written to thr DB
 *
 */
public enum Currency {
    ILS, USD, EURO, GBP;

    public String which() {
        return this.toString();
    }
}
