package model.tests;

import model.Category;
import model.CostItem;
import model.CostManagerException;
import model.DerbyDBModel;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DerbyDBModelTest {
    private DerbyDBModel derby;

    @org.junit.Before
    public void setUp() throws Exception, CostManagerException {
        derby = new DerbyDBModel();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        derby = null;
    }

//    @org.junit.Test
//    public void shutDown() {
//    }
//
//    @org.junit.Test
//    public void setUpDerby() {
//    }
//
//    @org.junit.Test
//    public void addCost() {
//    }
//
//    @org.junit.Test
//    public void deleteCost() {
//    }

    @org.junit.Test
    public void getCosts() throws CostManagerException {
        ArrayList<CostItem> expected = new ArrayList<CostItem>();
        expected.add(new CostItem(1, Date.valueOf("2019-10-12"), "gas", "ILS", 33.7, "good burger"));
        expected.add(new CostItem(2, Date.valueOf("2019-09-12"), "food", "USD", 10.7, "good burger"));
        expected.add(new CostItem(3, Date.valueOf("2019-09-01"), "pharmacy", "USD", 10.7, "voltaren"));
        expected.add(new CostItem(4, Date.valueOf("2020-12-20"), "pharmacy", "USD", 20.0, "voltaren"));

        ArrayList<CostItem> actual = this.derby.getCosts("");

        assertEquals(expected,actual);
    }


    @org.junit.Test
    public void testGetCosts() throws CostManagerException {

        ArrayList<CostItem> expected = new ArrayList<CostItem>();
        expected.add(new CostItem(1, Date.valueOf("2019-10-12"), "gas", "ILS", 33.7, "good burger"));

        ArrayList<CostItem> actual = this.derby.getCosts(Date.valueOf("2019-10-10"), Date.valueOf("2019-10-20"));

        assertEquals(expected,actual);
    }

//    @org.junit.Test
//    public void addCategory() throws CostManagerException {
//
//    }
//
//    @org.junit.Test
//    public void deleteCategory() throws CostManagerException {
//
//    }

    @org.junit.Test
    public void getCategories() throws CostManagerException {
        ArrayList<Category> expected = new ArrayList<>();
        expected.add(new Category("food"));
        expected.add(new Category("pharmacy"));
        expected.add(new Category("gas"));

        ArrayList<Category> actual = derby.getCategories();

        assertEquals(expected,actual);
    }

    @org.junit.Test
    public void checkIfCategoryExist() throws CostManagerException {
        boolean expected = true;
        boolean actual = derby.checkIfCategoryExist(new Category("gas"));

        assertEquals(expected,actual);
    }
}