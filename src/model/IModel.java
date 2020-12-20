package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * This Interface describes the functionality required by the Model layer of the software
 *
 */

public interface IModel {

    public boolean addCost(CostItem cost) throws CostManagerException;
    public boolean deleteCost(int id) throws CostManagerException, SQLException;
    public boolean addCategory(Category c) throws CostManagerException;
    public ArrayList<CostItem> getCosts(Date start, Date end) throws CostManagerException;
    public ArrayList<CostItem> getCosts(String query) throws CostManagerException;
    public ArrayList<Category> getCategories() throws CostManagerException;
    public boolean checkIfCategoryExist(Category c) throws CostManagerException;
}
