/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 4:00:27 PM  : Apr 26, 2016
 */
package controllers;

import entities.TravelAdvanceRequest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import mediators.TravelAdvanceRequestJpaController;

/**
 *
 * @author kelli
 */
@Named(value = "travelAdvRequestsList")
@RequestScoped
public class TravelAdvRequestsList {

    private TravelAdvanceRequestJpaController jpaController;
    private TravelAdvanceRequest travelAdvRequest;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction ut;
    private List<TravelAdvanceRequest> tar;
    
    @PostConstruct
    public void init(){
        travelAdvRequest = new TravelAdvanceRequest();
        jpaController = new TravelAdvanceRequestJpaController(ut, emf);
        tar = new ArrayList<>();
        getListing();
    }
    
    public TravelAdvRequestsList() {
    }

    public List<TravelAdvanceRequest> getTar() {
        return tar;
    }

    public void setTar(List<TravelAdvanceRequest> tar) {
        this.tar = tar;
    }

    public TravelAdvanceRequest getTravelAdvRequest() {
        return travelAdvRequest;
    }

    public void setTravelAdvRequest(TravelAdvanceRequest travelAdvRequest) {
        this.travelAdvRequest = travelAdvRequest;
    }
    
    private  void getListing(){
        tar = jpaController.findTravelAdvanceRequestEntities();
    }
}
