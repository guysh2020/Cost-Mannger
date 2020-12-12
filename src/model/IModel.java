package model;

public interface IModel {

    public void AddCost() throws CostMannagerException;
    public void DeleteCost() throws CostMannagerException;
    public void AddCategory() throws CostMannagerException;

    public void GeneratePieSummary() throws CostMannagerException;
    public void GenerateRegularSummary() throws CostMannagerException;

    public void SortItems() throws CostMannagerException;

//    public void fromDB() throws CostMannagerException;
//    public void toDB() throws CostMannagerException;
}
