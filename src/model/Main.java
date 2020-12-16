package model;

import java.sql.Date;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws CostManagerException {
        DerbyDBModel d =  new DerbyDBModel();

//        CostItem c = new CostItem(Date.valueOf("2019-10-12"), "gas", "ILS", 33.7, "good burger");
//        d.addCost(c);
        Category cat = new Category("gas");
//
        if(d.checkIfCategoryExist(cat))
            System.out.println("exist");
        else
            System.out.println("dosent exist");

        d.addCategory(cat);

//        ArrayList<CostItem> a = d.getCosts("");
//        ArrayList<Category> b = d.getCategories();
//
//        System.out.println(a.size());
//        System.out.println(b.size());
//
//
//        for( int i = 0 ; i < a.size(); i++ )
//            System.out.println(a.get(i));
//
//        System.out.println("*******************");
//
//        for( int i = 0 ; i < b.size(); i++ )
//            System.out.println(b.get(i));



        d.shutDown();
        System.out.println("SimpleApp finished");
    }



}
