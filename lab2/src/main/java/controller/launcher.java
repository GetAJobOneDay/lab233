package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.MyCurrency;
import org.json.JSONException;
import views.CurrencyPane;
import views.CurrencyParentPane;
import views.TopPane;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class launcher extends Application {
    private static Stage mainStage;
    private static Scene mainScene;
    private static FlowPane mainPane;
    private static TopPane topPane;
    private static CurrencyParentPane currencyParentPane;
    private static ArrayList<MyCurrency> currency;

    public static void setCurrency(ArrayList<MyCurrency> currency) {
        launcher.currency =currency;
    }

    public static ArrayList<MyCurrency> getCurrency() {
        return launcher.currency;
    }

    @Override
    public void start(Stage primaryStage) throws ExecutionException, InterruptedException, JSONException {
        launcher.mainStage = primaryStage;
        launcher.mainStage.setTitle("Currency Watcher");
        launcher.mainStage.setResizable(false);
        Initialize.initialize_app();
        initMainPane();
        mainScene = new Scene(mainPane);
        launcher.mainStage.setScene(mainScene);
        launcher.mainStage.show();
        RefreshTask r= new RefreshTask();
        Thread th = new Thread(r);
        th.setDaemon(true);
        th.start();

    }
    public void initMainPane() throws ExecutionException, InterruptedException {
        mainPane = new FlowPane();
        topPane = new TopPane();
        currencyParentPane = new CurrencyParentPane(launcher.currency);
        mainPane.getChildren().add(topPane);
        mainPane.getChildren().add(currencyParentPane);
    }
    public static void refreshPane() throws ExecutionException, InterruptedException {
        topPane.refreshPane();
        currencyParentPane.refreshPane(currency);
        mainStage.sizeToScene();
    }
    public static void main(String[] args){
        launch(args);
    }
}
