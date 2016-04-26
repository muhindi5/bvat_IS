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
import javax.inject.Inject;
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
    private static Logger logger;
    
    
    @PostConstruct
    public void initialize(){
        logger.log(Level.INFO, "called post construct");
        tadvJpaController = new TravelAdvanceRequestJpaController(utx, emf);
        empJpaController = new EmployeeJpaController(utx, emf);
    }
    
    public TravelAdvanceRequestController() {
        logger = Logger.getLogger(TravelAdvanceRequestController.class.getName());
        logger.log(Level.INFO, "inside the constructor");
        travelAdvReq = new TravelAdvanceRequest();
        logger.log(Level.INFO, travelAdvReq.toString());
        Employee empl = new Employee();
        travelAdvReq.setRequestor(empl);
        logger.log(Level.INFO, empl.toString());
    }

    public TravelAdvanceRequest getTravelAdvReq() {
        return travelAdvReq;
    }

    public void setTravelAdvReq(TravelAdvanceRequest travelAdvReq) {
        this.travelAdvReq = travelAdvReq;
    }
    
    /** save the request but no submission */
    public void save(){
        logger.log(Level.INFO,"Inside Save");
    }
    
    /** trash this request */
    public void discard(){
        
    }
    
    /**save record and submit the request for review */
    public void saveTravelAdvanceRequest(){
       logger.log(Level.INFO,"Inside save Advance");
        Employee requestor = empJpaController.findEmployee(travelAdvReq.getRequestor().getEmployeeId());
        logger.log(Level.INFO, requestor.toString());
        Employee preparedBy = empJpaController.findEmployee(3);
        Logger.getAnonymousLogger().log(Level.INFO, preparedBy.toString());
        travelAdvReq.setRequestor(requestor);
        travelAdvReq.setPreparedBy(preparedBy);
        travelAdvReq.setStatus("IN_REVIEW_FIN");
        try {
            tadvJpaController.create(travelAdvReq);
        } catch (Exception ex) {
            Logger.getLogger(TravelAdvanceRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
