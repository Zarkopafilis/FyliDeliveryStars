package fyli.gr.fylideliverystars.provider;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zarkopafilis on 3/2/2016.
 */
public class ShopListProvider {

    String shopType;
    boolean deliveryOnly;
    List<Shop> shopsCached;


    public ShopListProvider(String shopType, boolean deliveryOnly){
        this.shopType = shopType;
        this.deliveryOnly = deliveryOnly;
    }

    public List<String> generateShopList(Context context){
        List<String> shopList = new ArrayList<String>();

        List<Shop> shops = CSVParser.getShops(shopType, context);
        shopsCached = shops;

        if(deliveryOnly) {
            for (Shop shop : shops) {
                if(shop.isDoingDelivery){
                    shopList.add(shop.name);
                }
            }
        }else{//take-away -> add everything
            for (Shop shop : shops) {
                shopList.add(shop.name);
            }
        }


        return shopList;
    }

    public Shop getCachedShopFromList(int index){
        if(shopsCached != null) {
            return shopsCached.get(index);
        }
        return null;
    }

}
