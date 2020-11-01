package controller;

import model.CurrencyModel;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class FetchData {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ArrayList<CurrencyModel> fetch_range(String src) throws JSONException {
        String dateEnd = LocalDate.now().format(formatter);
        String dateStart = LocalDate.now().minusDays(6).format(formatter);
        String apiKey = "d07c6a9abebb7924e70d";
        String url_str = String.format("https://free.currconv.com/api/v7/convert?q=%s_THB&compact=ultra&date=%s&endDate=%s&apiKey=%s",src,dateStart,dateEnd,apiKey);
        String dateEnds = LocalDate.now().minusDays(6).format(formatter);
        String dateStarts = LocalDate.now().minusDays(13).format(formatter);
        String url_strr = String.format("https://free.currconv.com/api/v7/convert?q=%s_THB&compact=ultra&date=%s&endDate=%s&apiKey=%s",src,dateStarts,dateEnds,apiKey);
        ArrayList<CurrencyModel> hisList = new ArrayList<>();
        String retrieveJSON =null;
        String retrieveJSON2 =null;

        try{

            retrieveJSON = IOUtils.toString(new URL(url_str), Charset.defaultCharset());
            retrieveJSON2 =IOUtils.toString(new URL(url_strr), Charset.defaultCharset());

        }catch (MalformedURLException e){
            System.out.println("Encounter MalformedUrlException");
        }catch (IOException e){
            System.out.println("Encounter IOException");
        }

            JSONObject jsonObject = new JSONObject(retrieveJSON2).getJSONObject(String.format("%s_THB",src));
            Iterator keysToCopyIterator = jsonObject.keys();
            while(keysToCopyIterator.hasNext()){
                String key = (String) keysToCopyIterator.next();
                Double rate = Double.parseDouble(jsonObject.get(key).toString());
                hisList.add(new CurrencyModel(rate,key));
            }
            jsonObject = new JSONObject(retrieveJSON).getJSONObject(String.format("%s_THB",src));
            keysToCopyIterator = jsonObject.keys();
            while(keysToCopyIterator.hasNext()){
                String key = (String) keysToCopyIterator.next();
                Double rate = Double.parseDouble(jsonObject.get(key).toString());
                hisList.add(new CurrencyModel(rate,key));
            }
            hisList.sort(Comparator.comparing(CurrencyModel::getTimeStamp));
            return  hisList;
    }
    /*public static ArrayList<CurrencyModel> fetch_rangeAfter(String src, int n){
        String dateEnd = LocalDate.now().minusDays(n).format(formatter);
        String dateStart = LocalDate.now().minusDays(13).format(formatter);
        String apiKey = "d07c6a9abebb7924e70d";
        String url_str = String.format("https://free.currconv.com/api/v7/convert?q=%s_THB&compact=ultra&date=%s&endDate=%s&apiKey=%s",src,dateStart,dateEnd,apiKey);
        ArrayList<CurrencyModel> hisList = new ArrayList<>();
        String retrieveJSON =null;
        try{
            retrieveJSON = IOUtils.toString(new URL(url_str), Charset.defaultCharset());
        }catch (MalformedURLException e){
            System.out.println("Encounter MalformedUrlException");
        }catch (IOException e){
            System.out.println("Encounter IOException");
        }
        JSONObject jsonObject = new JSONObject(retrieveJSON).getJSONObject(String.format("%s_THB",src));
        System.out.println(jsonObject);
        Iterator keysToCopyIterator = jsonObject.keys();
        while(keysToCopyIterator.hasNext()){
            String key = (String) keysToCopyIterator.next();
            Double rate = Double.parseDouble(jsonObject.get(key).toString());
            hisList.add(new CurrencyModel(rate,key));
        }
        hisList.sort(Comparator.comparing(CurrencyModel::getTimeStamp));
        return  hisList;
    }*/

}
