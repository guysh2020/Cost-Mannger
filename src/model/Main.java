package model;

import view.Gui;

import java.sql.Date;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws CostManagerException {

        Gui g = new Gui();
//        g.deleteExpense(new ArrayList<CostItem>());
//      g.deleteExpense(a);
        g.getCategoryForSummary();
//        g.foo();

    }

//        DerbyDBModel d =  new DerbyDBModel();
//
////        CostItem c = new CostItem(Date.valueOf("2020-12-20"), "pharmacy", "USD", 20, "voltaren");
////        d.addCost(c);
//        Category cat = new Category("gas");
//
//        boolean tmp = d.checkIfCategoryExist(cat);
////        d.addCategory(cat);
////
//
//
//        ArrayList<CostItem> a = d.getCosts("");
//        ArrayList<Category> b = d.getCategories();
//
//        for( int i = 0 ; i < a.size(); i++ )
//            System.out.println(a.get(i));
//
//        System.out.println("*******************");
//
//
//
//        if(tmp)
//            System.out.println("exist");
//
//        for( int i = 0 ; i < b.size(); i++ )
//            System.out.println(b.get(i));
//
//
//
//        d.shutDown();
//        System.out.println("SimpleApp finished");
//    }



}
