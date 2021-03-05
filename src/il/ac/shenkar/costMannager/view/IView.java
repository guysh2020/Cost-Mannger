/*
 * Guy Sharir: 310010244
 * Ido Betesh: 307833822
 */

package il.ac.shenkar.costMannager.view;
import java.util.List;
import java.util.Map;
import il.ac.shenkar.costMannager.model.CostItem;
import il.ac.shenkar.costMannager.model.Category;
import il.ac.shenkar.costMannager.vm.IViewModel;

/**
 * This Interface describes the necessery methods needed to run the current il.ac.shenkar.costMannager.model.view
 */
public interface IView {
    void displayPieChart(Map map);
    void showMessage(String message);
    void showItems(List<CostItem> data);
    void setViewModel(IViewModel vm);
    void setCategories(List<Category> categories);
}