/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 4:00:27 PM  : Apr 26, 2016
 */
package controllers;

import entities.TravelAdvanceRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import mediators.TravelAdvanceRequestJpaController;
import org.primefaces.event.SelectEvent;

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
    
    public void onRowSelect(SelectEvent event){
//        Object key = FacesContext.getCurrentInstance().getAttributes().get("res");
        FacesMessage fmsg = new FacesMessage("Detected", ((TravelAdvanceRequest)event.getObject()).getDestination());
        Logger.getAnonymousLogger().log(Level.INFO, fmsg.getDetail());
        FacesContext.getCurrentInstance().addMessage("messenger", fmsg);
    }
}
