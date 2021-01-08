package view;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import model.Category;
import model.CostItem;

public interface IView {

    public void displayPieChart(Map map);
    public void createHomePage();
    public void addExpense();
    public void deleteExpense(ArrayList<CostItem> data);
    public void getDatesForSummary(boolean isChart);
    public void generateSummary(String[] params);
    public void getCategoryForSummary();
    public void addCategory();


//    public void setViewModel(IViewModel vm);
}