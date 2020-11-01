package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import model.CurrencyModel;
import model.MyCurrency;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import views.CurrencyPane;
import views.CurrencyParentPane;

public class AllEventHandlers {
    public static void onRefresh() {
        try{
            launcher.refreshPane();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void onAdd(){
        String check=null;
        try{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add currency");
            dialog.setContentText("Currency code: ");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            Optional<String> code = dialog.showAndWait();
            if(code.isPresent()){
                ArrayList<MyCurrency> currency_list = launcher.getCurrency();
                MyCurrency c = new MyCurrency(code.get().toUpperCase());
                String checkAPI = String.format("https://free.currconv.com/api/v7/currencies?apiKey=%s","d07c6a9abebb7924e70d");
                check = IOUtils.toString(new URL(checkAPI), Charset.defaultCharset());
                JSONObject allCurrencyAPI = new JSONObject(check);
                JSONObject value = allCurrencyAPI.getJSONObject("results");
                if(value.has(c.getShortCode())){
                    ArrayList<CurrencyModel> c_list = FetchData.fetch_range(c.getShortCode());
                    c.setHistorical(c_list);
                    c.setCurrent(c_list.get(c_list.size()-1));
                    currency_list.add(c);
                    launcher.setCurrency(currency_list);
                    launcher.refreshPane();
                }else {
                    Alert a =new Alert(Alert.AlertType.WARNING);
                    a.setTitle("not found!");
                    a.setHeaderText("sorry!");
                    a.setContentText("no such a currency you looking for");
                    a.show();
                }
            }
        } catch (InterruptedException | ExecutionException | IOException | JSONException e){
            e.printStackTrace();
        }
    }

    public static void onDelete(String shortCode) {
        try{
            ArrayList<MyCurrency> currency_list = launcher.getCurrency();
            int index =-1;
            for(int i=0;i<currency_list.size();i++){
                if(currency_list.get(i).getShortCode().equals(shortCode)){
                    index =i;
                    break;
                }
            }
            if(index!=-1){
                currency_list.remove(index);
                launcher.setCurrency(currency_list);
                launcher.refreshPane();
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public static void onWatch(String shortCode) {
        try {
            ArrayList<MyCurrency> currency_list = launcher.getCurrency();
            int index= -1;
            for(int i=0;i<currency_list.size();i++){
                if(currency_list.get(i).getShortCode().equals(shortCode)){
                    index =i;
                    break;
                }
            }
            if(index!=-1){
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Watch");
                dialog.setContentText("Rate: ");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> retrieveRate = dialog.showAndWait();
                if(retrieveRate.isPresent()){
                    double rate =Double.parseDouble(retrieveRate.get());
                    currency_list.get(index).setWatch(true);
                    currency_list.get(index).setWatchRate(rate);
                    launcher.setCurrency(currency_list);
                    launcher.refreshPane();
                }
                launcher.setCurrency(currency_list);
                launcher.refreshPane();
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public static void unWatch(String shortCode) {
        ArrayList<MyCurrency> c_list =launcher.getCurrency();
        int index= -1;
        for(int i=0;i<c_list.size();i++){
            if(c_list.get(i).getShortCode().equals(shortCode)){
                index =i;
                break;
            }
        }
        if(index!=-1){
            c_list.get(index).setWatch(false);
            c_list.get(index).setWatchRate(0.0);
            try {
                launcher.refreshPane();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
