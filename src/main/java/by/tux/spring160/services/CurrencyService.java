package by.tux.spring160.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class CurrencyService {

    public double getUsdPrice(int bynPrice) {
        double usdPrice = 0;
        try{
            URL url = new URL("https://www.nbrb.by/api/exrates/rates/usd?parammode=2");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            double course =
                    Double.parseDouble(jsonObject.get("Cur_OfficialRate").toString());
            usdPrice = bynPrice/course;
        }
        catch (Exception e){
            return usdPrice;
        }
        return usdPrice;
    }

    public double getEurPrice(int bynPrice){
        double eurPrice = 0;
        try {
            URL url = new URL("https://www.nbrb.by/api/exrates/rates/eur?parammode=2");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            double course =
                    Double.parseDouble(jsonObject.get("Cur_OfficialRate").toString());
            eurPrice = bynPrice / course;
        }
        catch (Exception e){
                return eurPrice;
            }
            return eurPrice;
    }
}
