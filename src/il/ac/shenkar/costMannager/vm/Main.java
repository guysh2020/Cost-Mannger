/**
 * Guy Sharir: 310010244
 * Ido Betesh: 307833822
 */

package il.ac.shenkar.costMannager.vm;

import il.ac.shenkar.costMannager.model.CostManagerException;
import il.ac.shenkar.costMannager.model.DerbyDBModel;
import il.ac.shenkar.costMannager.model.IModel;
import il.ac.shenkar.costMannager.view.IView;
import il.ac.shenkar.costMannager.view.View;



public class Main {

    public static void main(String[] args) throws CostManagerException {

        IView v = new View();
        IViewModel vm = new ViewModel();
        IModel model = new DerbyDBModel();

        v.setViewModel(vm);
        vm.setView(v);
        vm.setModel(model);
    }
}
