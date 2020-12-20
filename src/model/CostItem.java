package model;

import java.sql.Date;
import java.util.Objects;

public class CostItem {

    private int id;
    private Date date;
    private Category category;
    private Currency currency;
    private double sum;
    private String description = "";


    @Override
    public int hashCode() {
        return Objects.hash(id, date, category, currency, sum, description);
    }

    /**
     *
     * CostItem constructor creates CostItem object exists from DB
     * @param id (int) holds the unique identifier for each CostItem in the system
     * @param date (Date) holds the day the CostItem object occurred
     * @param category (string) expense kinds
     * @param currency (string) currency type of the expense
     * @param sum (double) expense price
     * @param description (string) expense description
     *
     */
    public CostItem(int id, Date date, String category, String currency, double sum, String description) throws CostManagerException {
        this.setId(id);
        this.setDate(date);
        this.setCategory(category);
        this.setCurrency(currency);
        this.setSum(sum);
        this.setDescription(description);
    }

    /**
     *
     * CostItem constructor creates CostItem object exists from input [no id input is needed]
     * @param date (Date) holds the day the CostItem object occurred
     * @param category (string) expense kinds
     * @param currency (string) currency type of the expense
     * @param sum (double) expense price
     * @param description (string) expense description
     *
     */
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

    /**
     *
     * converts string to Currency object
     *
     */
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
        }
    }

    public String getCategory() {
        return category.toString();
    }

    public void setCategory(String s) {
        this.category = new Category(s);
    }



    /**
     *
     * This method returns the string representation of the class. values separated by comma sign
     *
     */
    @Override
    public String toString() {
        return "CostItem: {" +
                "id=" + id +
                ", date=" + date +
                ", category=" + category +
                ", currency=" + currency +
                ", sum=" + sum +
                ", description=" + description + '}';
    }


     /**
     *
     * This method returns if all values are equal between two CostItem objects
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostItem costItem = (CostItem) o;
        return id == costItem.id &&
                Double.compare(costItem.sum, sum) == 0 &&
                Objects.equals(date, costItem.date) &&
                Objects.equals(category, costItem.category) &&
                currency == costItem.currency &&
                Objects.equals(description, costItem.description);
    }
}

