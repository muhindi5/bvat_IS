/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 3:03:21 PM  : Apr 25, 2016
 */
package controllers;

import entities.Employee;
import entities.TravelAdvanceRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import mediators.EmployeeJpaController;
import mediators.TravelAdvanceRequestJpaController;

/**
 *
 * @author kelli
 */
@Named(value = "tadvController")
@RequestScoped
public class TravelAdvanceRequestController {

    

    
    private TravelAdvanceRequest travelAdvReq;
    private TravelAdvanceRequestJpaController tadvJpaController;
    private EmployeeJpaController empJpaController;
    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;
    /**
     * Creates a new instance of TravelAdvanceRequest
     */
    
    /**
     * Creates a new instance of TravelAdvanceRequestController
     */
    @PostConstruct
    public void initialize(){
        Logger.getLogger("SugarWife").log(Level.INFO, "Inside post construct");
        tadvJpaController = new TravelAdvanceRequestJpaController(utx, emf);
        empJpaController = new EmployeeJpaController(utx, emf);
        travelAdvReq = new TravelAdvanceRequest();
        Employee empl = new Employee();
        travelAdvReq.setRequestor(empl);
    }
    
    public TravelAdvanceRequestController() {
    }

    public TravelAdvanceRequest getTravelAdvReq() {
        return travelAdvReq;
    }

    public void setTravelAdvReq(TravelAdvanceRequest travelAdvReq) {
        this.travelAdvReq = travelAdvReq;
    }
    
    /** save the request but no submission */
    public void save(){
        
    }
    
    /** trash this request */
    public void discard(){
        
    }
    
    /**save record and submit the request for review */
    public void saveTravelAdvanceRequest(){
        Logger.getLogger("TvaClass").log(Level.INFO,"Inside save Advance");
//        Employee requestor = empJpaController.findEmployee(travelAdvReq.getRequestor().getEmployeeId());
//        Employee preparedBy = empJpaController.findEmployee(3);
//        travelAdvReq.setRequestor(requestor);
//        travelAdvReq.setPreparedBy(preparedBy);
//        travelAdvReq.setStatus("IN_REVIEW_FIN");
//        try {
//            tadvJpaController.create(travelAdvReq);
//        } catch (Exception ex) {
//            Logger.getLogger(TravelAdvanceRequestController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
