package model;

import java.util.ArrayList;

public class MyCurrency {
    private String shortCode;
    private CurrencyModel current;
    private ArrayList<CurrencyModel> arrHistory;
    private boolean isWatch;
    private double watchRate;
    public MyCurrency(String shortCode){
        this.shortCode = shortCode;
        this.isWatch = false;
        this.watchRate =0.0;
    }

    public String getShortCode() {
        return this.shortCode;
    }

    public CurrencyModel getCurrent() {
        return this.current;
    }

    public double getWatchRate() {
        return this.watchRate;
    }

    public boolean getWatch() {
        return this.isWatch;
    }

    public void setHistorical(ArrayList<CurrencyModel> c_list) {
        this.arrHistory = c_list;
    }

    public void setCurrent(CurrencyModel currencyModel) {
        this.current = currencyModel;
    }

    public ArrayList<CurrencyModel> getHistorical() {
        return arrHistory;
    }

    public void setWatchRate(double rate) {
        this.watchRate =rate;
    }

    public void setWatch(boolean b) {
        this.isWatch =b;
    }
}
