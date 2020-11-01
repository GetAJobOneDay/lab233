package controller;

import model.CurrencyModel;
import model.MyCurrency;
import org.json.JSONException;

import java.util.ArrayList;

public class Initialize {
    public static void initialize_app() throws JSONException {
        MyCurrency currency = new MyCurrency("PHP");
        ArrayList<CurrencyModel> c_list1 = FetchData.fetch_range(currency.getShortCode());
        //ArrayList<CurrencyModel> c_list2 = FetchData.fetch_rangeAfter(currency.getShortCode(),6);

        currency.setHistorical(c_list1);
        currency.setCurrent(c_list1.get(c_list1.size()-1));
        ArrayList<MyCurrency> currencies = new ArrayList<>();
        currencies.add(currency);
        launcher.setCurrency(currencies);
    }
}
