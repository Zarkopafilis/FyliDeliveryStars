package fyli.gr.fylideliverystars.provider;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Zarkopafilis on 3/2/2016.
 */
@SuppressWarnings("serial")
public class Shop implements Serializable{

    String name;
    boolean isDoingDelivery;
    String workHours;
    String GPSCoordinates;
    String location;
    String[] telephones;
    int stars;

    public String getName() {
        return name;
    }

    public boolean isDoingDelivery() {
        return isDoingDelivery;
    }

    public String getWorkHours() {
        return workHours;
    }

    public String getGPSCoordinates() {
        return GPSCoordinates;
    }

    public String getLocation() {
        return location;
    }

    public String[] getTelephones() {
        return telephones;
    }

    public int getStars() {
        return stars;
    }

    public String getSpecialOffers() {
        return specialOffers;
    }

    public boolean isCitizenCard() {
        return citizenCard;
    }

    public String getCritique() {
        return critique;
    }

    public HashMap<String, String> getProductPrice() {
        return productPrice;
    }

    String specialOffers;
    boolean citizenCard;
    String critique;
    HashMap<String, String> productPrice;

    public Shop(String name,
                String workHours,
                String[] telephones,
                String location,
                String GPSCoordinates,
                int stars,
                HashMap<String, String> productPrice,
                String specialOffers,
                String critique,
                boolean citizenCard,
                boolean isDoingDelivery) {

        this.name = name;
        this.workHours = workHours;
        this.GPSCoordinates = GPSCoordinates;
        this.isDoingDelivery = isDoingDelivery;
        this.specialOffers = specialOffers;
        this.citizenCard = citizenCard;
        this.critique = critique;
        this.productPrice = productPrice;
        this.stars = stars;
        this.telephones = telephones;
        this.location = location;
    }
}
