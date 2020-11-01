package views;

import controller.AllEventHandlers;
import controller.DrawGraph;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.MyCurrency;
import java.util.concurrent.*;

public class CurrencyPane extends BorderPane{
    private MyCurrency currency;
    private Button watch;
    private Button delete;
    private Button unWatch;
    public CurrencyPane(MyCurrency currency){
        this.watch = new Button("Watch");
        this.delete = new Button("Delete");
        this.unWatch = new Button("unwatch");
        this.delete.setOnAction(event -> AllEventHandlers.onDelete(currency.getShortCode()));
        this.watch.setOnAction(event -> AllEventHandlers.onWatch(currency.getShortCode()));
        this.unWatch.setOnAction(event -> AllEventHandlers.unWatch(currency.getShortCode()));
        this.setPadding(new Insets(0));
        this.setPrefSize(640,300);
        this.setStyle("-fx-background-color: lightgreen");
        try{
            this.refreshPane(currency);
        }catch (ExecutionException e){
            System.out.println("encounter with "+e);
        }catch (InterruptedException e){
            System.out.println("encounter with"+e);
        }

    }
    public void refreshPane(MyCurrency currency)throws ExecutionException,InterruptedException{
        this.currency = currency;
        FutureTask futureTask = new FutureTask<VBox>(new DrawGraph(currency));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(futureTask);
        VBox currencyGraph = (VBox) futureTask.get();
        futureTask = new FutureTask<Pane>(new InfoPane(currency));
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(futureTask);
        Pane CurrencyInfo = (Pane) futureTask.get();
        futureTask = new FutureTask<Pane>(new TopArea(watch,unWatch,delete));
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(futureTask);
        Pane topArea = (HBox)futureTask.get();
        this.setTop(topArea);
        this.setLeft(CurrencyInfo);
        this.setCenter(currencyGraph);
    }
    /*public Pane genInfoPane(){
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
    }*/
    /*private HBox genTopArea(){
        HBox topArea = new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch,delete,unWatch);
        ((HBox) topArea).setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }*/

}
