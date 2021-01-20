package view;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Category;
import model.CostItem;
import vm.IViewModel;

public interface IView {
    
    public void displayPieChart(Map map);
    public void showMessage(String message);
    public void showItems(List<CostItem> data);
    public void setViewModel(IViewModel vm);

    

}