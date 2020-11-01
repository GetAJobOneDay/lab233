package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.MyCurrency;

import java.util.concurrent.Callable;

public class InfoPane implements Callable<Pane> {
    private MyCurrency currency;
    public InfoPane(MyCurrency currency){this.currency=currency;}
    @Override
    public Pane call() throws Exception {
        VBox currencyPane = new VBox(10);
        currencyPane.setPadding(new Insets(5,25,5,25));
        currencyPane.setAlignment(Pos.CENTER);
        Label exchangeString = new Label("");
        Label watchString = new Label("");
        exchangeString.setStyle("-fx-font-size: 20");
        watchString.setStyle("-fx-font-size: 14");
        if(this.currency!=null){
            exchangeString.setText(String.format("%s: %.4f",currency.getShortCode(),currency.getCurrent().getRate()));
            if(this.currency.getWatch()){
                watchString.setText(String.format("(Watch @%.4f)",this.currency.getWatchRate()));
            }
        }
        currencyPane.getChildren().addAll(exchangeString,watchString);
        return currencyPane;
    }
}
