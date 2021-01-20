package model;

import view.IView;
import view.View;
import vm.IViewModel;
import vm.ViewModel;

import java.sql.Date;
import java.util.ArrayList;

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
