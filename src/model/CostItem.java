package model;

import java.sql.Date;

public class CostItem {

    private int id;
    private Date date;
    private Category category;
    private Currency currency;
    private double sum;
    private String description = "";

    // for data back from DB
    public CostItem(int id, Date date, String category, String currency, double sum, String description) throws CostManagerException {
        this.setId(id);
        this.setDate(date);
        this.setCategory(category);
        this.setCurrency(currency);
        this.setSum(sum);
        this.setDescription(description);
    }

    //for creating new by user
    public CostItem(Date date, String category, String currency, double sum, String description) throws CostManagerException {
        this.setId(-1);
        this.setDate(date);
        this.setCategory(category);
        this.setCurrency(currency);
        this.setSum(sum);
        this.setDescription(description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) throws CostManagerException {
        if (sum < 0) {
            this.sum = 0;
            throw new CostManagerException("invalid sum!");
        }
        this.sum = sum;
    }

    public String getCurrency() {
        return currency.which();
    }

    public void setCurrency(String cur) {
        switch (cur) {
            case "ILS":
                this.currency = Currency.ILS;
                break;
            case "USD":
                this.currency = Currency.USD;
                break;
            case "EURO":
                this.currency = Currency.EURO;
                break;
            case "GBP":
                this.currency = Currency.GBP;
                break;
//            default:
//                this.currency = Currency.ILS;
//                break;
        }
    }

    public String getCategory() {
        return category.toString();
    }

    public void setCategory(String s) {
        this.category = new Category(s);
        this.category = category;
    }

    @Override
    public String toString() {
        return "CostItem: {" +
                "id=" + id +
                ", date=" + date +
                ", sum=" + sum +
                ", currency=" + currency +
                ", description='" + description +
                ", category=" + category + '}';
    }
}

