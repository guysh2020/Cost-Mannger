/*
 * Guy Sharir: 310010244
 * Ido Betesh: 307833822
 */

package il.ac.shenkar.costMannager.model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * This Interface describes the functionality required by the Model layer of the software
 */

public interface IModel {

    boolean addCost(CostItem cost) throws CostManagerException;
    boolean deleteCost(int id) throws CostManagerException;
    boolean addCategory(Category c) throws CostManagerException;
    ArrayList<CostItem> getCosts(Date start, Date end) throws CostManagerException;
    ArrayList<CostItem> getCosts(String query) throws CostManagerException;
    ArrayList<Category> getCategories() throws CostManagerException;
    boolean checkIfCategoryExist(Category c) throws CostManagerException;
}
