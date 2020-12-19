package model;

public class Category {
    private String name;

    /****
     *
     * Category constructor creates Category object from input [no id input is needed]
     * @param name (string) category name also used as a unique value in the category table in DB
     *
     ****/
    public Category(String name) {
        //validation on name ( not repeaing not a number )
        // fetch data about category

        // a function in the controller that calls derbydb and invokes the checkIfCategoryExist

        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}


