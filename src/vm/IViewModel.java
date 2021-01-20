package vm;
import model.Category;
import model.CostItem;
import model.CostManagerException;
import model.IModel;
import view.IView;

import java.sql.Date;
import java.util.List;

public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem cost);
    public void addCategory(Category cat);
    public void deleteCostItemsById(int id);
    public String[] getCategories();
    public void getCostItems(List<String> params); // params.size() == 0 -> getAllCosts | params.size() == 1 -> getCostsByCategory | params.size() == 2 -> getAllCostsBetweenDates

}
