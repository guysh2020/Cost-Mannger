/**
 * Guy Sharir: 310010244
 * Ido Betesh: 307833822
 */

package il.ac.shenkar.costMannager.vm;

import il.ac.shenkar.costMannager.model.CostManagerException;
import il.ac.shenkar.costMannager.model.Category;
import il.ac.shenkar.costMannager.model.CostItem;
import il.ac.shenkar.costMannager.model.IModel;
import il.ac.shenkar.costMannager.view.IView;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {
    /**
     * @param model (IModel) model component
     * @param view (IView) view interface
     * @param pool (ExecutorService) threads pool
     */
    private IModel model;
    private IView view;
    private ExecutorService pool;

    public ViewModel() {
        pool = Executors.newFixedThreadPool(15);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    /**
     * This function adds a new CostItem to the DB by passing CostItem from the view to the model.
     */
    @Override
    public void addCostItem(CostItem cost) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCost(cost);
                    view.showMessage("Cost added successfully.");
                } catch (CostManagerException ex) {
                    view.showMessage(ex.getMessage());
                }
            }
        });
    }

    /**
     * This function adds a new Category to the DB by passing Category from the view to the model,
     * also makes sure the Category doesn't exist in the DB.
     */
    @Override
    public void addCategory(Category cat) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    if (model.checkIfCategoryExist(cat)) {
                        throw new CostManagerException("This category already exist in the System.");
                    } else {
                        model.addCategory(cat);
                        view.showMessage("Category added successfully.");
                    }
                } catch (CostManagerException ex) {
                    view.showMessage(ex.getMessage());
                }
            }
        });
    }


    /**
     * This function deletes a cost item using the model function
     */
    @Override
    public void deleteCostItemsById(int id) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteCost(id);
                    view.showMessage("Cost item was deleted successfully.");
                } catch (CostManagerException ex) {
                    view.showMessage(ex.getMessage());
                }
            }
        });
    }


    /**
     * This function retrieves categories data from the model to the view
     */
    @Override
    public void getCategories() {
        System.out.println("in get categories il.ac.shenkar.costMannager.model.vm");
        pool.submit(new Runnable() {
            @Override
            public void run() {
                ArrayList<Category> res = null;
                try {
                    res = model.getCategories();
                    view.setCategories(res);
                } catch (CostManagerException ex) {
                    view.showMessage(ex.getMessage());
                }
            }
        });
    }

    /**
     * This function retrieves cost items data from the model to the view
     */
    @Override
    public void getCostItems(List<String> params) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                ArrayList<CostItem> ans = null;
                try {
                    if (params.size() == 0) {
                        ans = model.getCosts("");
                    }

                    if (params.size() == 1) {
                        ans = model.getCosts(params.get(0));
                    }

                    if (params.size() == 2) {
                        ans = model.getCosts(Date.valueOf(params.get(0)), Date.valueOf(params.get(1)));
                    }

                } catch (CostManagerException ex) {
                    view.showMessage(ex.getMessage());
                }

                view.showItems(ans);
            }
        });
    }

    /**
     * This function gets the data needed for the pie chart from the model and pass it back to view
     */
    @Override
    public void getPieChartData(List<String> params) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                ArrayList<CostItem> costs = null;
                HashMap<String, Double> costsMap = new HashMap<String, Double>();
                try {
                    costs = model.getCosts(Date.valueOf(params.get(0)), Date.valueOf(params.get(1)));
                    costs.sort(Comparator.comparing(CostItem::getCategory));

                    double tmpSum = 0.0;
                    for (CostItem c : costs) {
                        if (costsMap.containsKey(c.getCategory()))
                            tmpSum = costsMap.get(c.getCategory());

                        costsMap.put(c.getCategory(), tmpSum + c.getSum());
                        tmpSum = 0.0;
                    }

                } catch (CostManagerException ex) {
                    view.showMessage(ex.getMessage());
                }

                view.displayPieChart(costsMap);
            }
        });
    }
}
