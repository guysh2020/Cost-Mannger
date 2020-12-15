package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IModel {

    public boolean addCost(CostItem cost) throws CostManagerException;
    public boolean deleteCost(int id) throws CostManagerException, SQLException;
    public boolean addCategory(Category c) throws CostManagerException;

    // check if we want a date of util or date of sql || if dates not defined bring all
//    public void getCostsBetweenDates(Date start, Date end) throws CostManagerException;
//    public void getAllCosts() throws CostManagerException;

    public ArrayList<CostItem> getCosts(Date start, Date end) throws CostManagerException;
    public ArrayList<CostItem> getCosts(String query) throws CostManagerException;
    public ArrayList<Category> getCategories() throws CostManagerException;
    public boolean checkIfCategoryExist(Category c) throws CostManagerException;




}


// view-model
//    public void GeneratePieSummary() throws CostManagerException;
//    public void GenerateRegularSummary() throws CostManagerException;
//    public void SortItems() throws CostManagerException;