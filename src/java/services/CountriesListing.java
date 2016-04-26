/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 2:37:45 PM  : Apr 26, 2016
 */
package services;

import java.util.ArrayList;
import java.util.Locale;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author kelli
 */
@Singleton
@Startup
public class CountriesListing {

    private ArrayList<String> countryListing = new ArrayList<>();
    private String countryName;
    
    public void createListing(){
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale object = new Locale("", countryCode);
            countryName = object.getDisplayCountry();
            countryListing.add(countryName);
        }
    }
    
    public ArrayList<String> getCountries(){
        return countryListing;
    }
    
    public static void main(String[] args) {
        CountriesListing cl = new CountriesListing();
        cl.createListing();
        for (String arg : cl.getCountries()) {
            System.out.println(arg+"\n");
        }
    }
}
