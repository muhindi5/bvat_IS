/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 3:11:21 PM  : Apr 26, 2016
 */
package services;

import java.util.ArrayList;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author kelli
 */
@Named(value = "countryList")
@ApplicationScoped
public class CountryListProvider {

    
    private ArrayList<String> countryListing = new ArrayList<>();
    private String countryName;
    
    public CountryListProvider() {
    }

    public ArrayList<String> getCountryListing() {
        return countryListing;
    }

    public void setCountryListing(ArrayList<String> countryListing) {
        this.countryListing = countryListing;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    @PostConstruct
    public void createListing(){
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale object = new Locale("", countryCode);
            countryName = object.getDisplayCountry();
            countryListing.add(countryName);
        }
    }
}
