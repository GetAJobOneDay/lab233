package controller;

import javafx.scene.control.Alert;
import model.MyCurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class WatchTask implements Callable<Void> {
    @Override
    public Void call() throws Exception {
        ArrayList<MyCurrency> allCurrency = launcher.getCurrency();
        String found = "";
        for (int i = 0; i < allCurrency.size(); i++) {
            if (allCurrency.get(i).getWatchRate() != 0 && allCurrency.get(i).getWatchRate() < allCurrency.get(i).getCurrent().getRate()) {
                if (found.equals("")) {
                    found = allCurrency.get(i).getShortCode();
                } else {
                    found = found + " and " + allCurrency.get(i).getShortCode();
                }
            }
        }
            if (!found.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                if (found.length() > 3) {
                    alert.setContentText(String.format("%s have become lower than the watch rate", found));
                } else {
                    alert.setContentText(String.format("%s has become greater than the watch rate", found));
                }
                alert.showAndWait();
            }
            return null;
        }
}
