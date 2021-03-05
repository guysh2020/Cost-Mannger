/**
 * Guy Sharir: 310010244
 * Ido Betesh: 307833822
 */

package il.ac.shenkar.costMannager.vm;
import il.ac.shenkar.costMannager.model.Category;
import il.ac.shenkar.costMannager.model.CostItem;
import il.ac.shenkar.costMannager.model.IModel;
import il.ac.shenkar.costMannager.view.IView;
import java.util.List;


/**
 *
 * This Interface describes the necessary functionalities in order to run this applicaition.
 *
 */
public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem cost);
    public void addCategory(Category cat);
    public void deleteCostItemsById(int id);
    public void getCategories();
    public void getCostItems(List<String> params);
    public void getPieChartData(List<String> params);
}
