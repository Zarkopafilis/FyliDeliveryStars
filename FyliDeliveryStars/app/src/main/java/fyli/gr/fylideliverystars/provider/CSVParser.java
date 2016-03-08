package fyli.gr.fylideliverystars.provider;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Zarkopafilis on 3/2/2016.
 */
public class CSVParser {

    public static List<Shop> getShops(String shopType, Context context){

        List<Shop> shopList = new ArrayList<Shop>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(shopType + ".csv")));
            String line;
            boolean firstTime = true;
           // Log.d("slp" , "reading shops of type :" + shopType);
            while ((line = reader.readLine()) != null) {

                if(firstTime){//skip first line (titles etc)
                    firstTime = false;
                //    Log.d("slp" , "skipping first line");
                    continue;
                }
              //  Log.d("slp" , line);
              //  Log.d("slp" , "parsing shop data");

                String[] rowData = line.split(",");

               // Log.d("slp" , "splitting rowData");
                for(String rowDataSplit: rowData){
               //     Log.d("slp" , rowDataSplit);
                }

                String name = rowData[0];
              //  Log.d("slp" ,"name: " + name);
                String workHours = rowData[1];
               // Log.d("slp ","workHours: " + workHours);
                String telephoneRaw = rowData[2];
               // Log.d("slp ","telephoneRaw: " + telephoneRaw);

                String telephones[] = {telephoneRaw};

                if(telephoneRaw.contains("-")){
                    telephones = telephoneRaw.split("-");
                }
                String location = rowData[3];
               // Log.d("slp ","location: " + location);
                String GPSCoordinates = rowData[4];
               // Log.d("slp ","GPSCoordinates: " + GPSCoordinates);
                int stars = Integer.parseInt(rowData[5]);
               // Log.d("slp ","stars: " + stars);

                String productRaw = rowData[6];
              //  Log.d("slp ","productRaw: " + productRaw);
                String priceRaw = rowData[7];
               // Log.d("slp ","priceRaw: " + priceRaw);

                String[] productList = productRaw.split("|");
                String[] priceList = priceRaw.split("|");

                HashMap<String, String> productPrice = new HashMap<>();

              //  Log.d("slp ", "Building productPrice map");
                /*for(int i = 0; i < Math.max(productList.length, priceList.length); i++){
                    String product = productList[i];
                    String price = priceList[i];
                    Log.d("slp ", "Pair : " + product + "-" + price);
                    productPrice.put(product,price);
                }*/

               // Log.d("slp ", "done");

                String specialOffers = rowData[8];
                //Log.d("slp", "specialOffers:" + specialOffers);
                String critique = rowData[9];
               // Log.d("slp", "critique:" + critique);
                boolean citizenCard = Boolean.valueOf(rowData[10].trim().toLowerCase());
                //Log.d("slp", "citizenCard:" + citizenCard + " original: " + rowData[9]);
                boolean isDoingDelivery = Boolean.valueOf(rowData[11].trim().toLowerCase());
                //Log.d("slp", "isDoingDelivery:" + isDoingDelivery + " original:" + rowData[10]);

                Shop shop = new Shop(name,workHours,telephones,location,GPSCoordinates,stars, productPrice ,specialOffers, critique,citizenCard,isDoingDelivery);
                shopList.add(shop);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return shopList;
        }
    }
}
