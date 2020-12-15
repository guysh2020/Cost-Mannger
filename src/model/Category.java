package model;

public class Category {
    private String name;

    public Category(String name) {
        //validation on name ( not repeaing not a number )
        // fetch data about category


        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
