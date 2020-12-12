package model;

public interface IModel {

    public void AddCost() throws CostManagerException;
    public void DeleteCost() throws CostManagerException;
    public void AddCategory() throws CostManagerException;

    public void GeneratePieSummary() throws CostManagerException;
    public void GenerateRegularSummary() throws CostManagerException;

    public void SortItems() throws CostManagerException;

//    public void fromDB() throws CostManagerException;
//    public void toDB() throws CostManagerException;
}
