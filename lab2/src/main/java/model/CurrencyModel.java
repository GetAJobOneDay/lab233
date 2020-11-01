package model;

public class CurrencyModel {
    private Double rate;
    private String date;
    public CurrencyModel(Double rate,String date){
        this.date=date;
        this.rate=rate;
    }
    public Double getRate(){return rate;}
    public String getTimeStamp(){return date;}
    @Override
    public String toString(){
        return String.format("%s %.4f",date,rate);
    }
}
