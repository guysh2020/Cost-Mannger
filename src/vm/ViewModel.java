package vm;

import model.Category;
import model.CostItem;
import model.CostManagerException;
import model.IModel;
import view.IView;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ViewModel implements IViewModel {
    private IModel model;
    private IView view;


    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void addCostItem(CostItem cost) {
        try {
            model.addCost(cost);
            view.showMessage("Cost added successfully.");
        } catch (CostManagerException ex) {
            view.showMessage(ex.getMessage());
        }
    }

    @Override
    public void addCategory(Category cat) {
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


    @Override
    public void deleteCostItemsById(int id) {
        try {
            this.model.deleteCost(id);
            this.view.showMessage("Cost item was deleted successfully.");
        } catch (CostManagerException ex) {
            this.view.showMessage(ex.getMessage());
        }
    }

    @Override
    public String[] getCategories() {
        ArrayList<Category> res = null;
        try {
            res = this.model.getCategories();
        } catch (CostManagerException ex) {
            this.view.showMessage(ex.getMessage());
        }

        if(res != null){
            String[] ans = new String[res.size()];

            for(int i = 0 ; i < res.size(); i++)
                ans[i] = res.get(i).toString();

            return ans;
        }
        else
            return new String[0];

    }


    @Override
    public void getCostItems(List<String> params) {

        ArrayList<CostItem> ans = null;
        try {
            if (params.size() == 0) {
                ans = this.model.getCosts("");
            }

            if (params.size() == 1) {
                ans = model.getCosts(params.get(0));
            }

            if (params.size() == 2) {
                ans = this.model.getCosts(Date.valueOf(params.get(0)), Date.valueOf(params.get(1)));
            }

        } catch (CostManagerException ex) {
            this.view.showMessage(ex.getMessage());
        }

        this.view.showItems(ans);

    }

}
