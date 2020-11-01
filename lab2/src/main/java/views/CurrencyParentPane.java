package views;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import model.MyCurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CurrencyParentPane extends FlowPane {
    public CurrencyParentPane(ArrayList<MyCurrency> currencies) throws ExecutionException,InterruptedException{
        this.setPadding(new Insets(0));
        refreshPane(currencies);
    }
    public void refreshPane(ArrayList<MyCurrency> currencies)throws ExecutionException,InterruptedException{
        this.getChildren().clear();
        for(int i=0;i<currencies.size();i++){
            CurrencyPane cp = new CurrencyPane(currencies.get(i));
            this.getChildren().add(cp);
        }
    }
}
