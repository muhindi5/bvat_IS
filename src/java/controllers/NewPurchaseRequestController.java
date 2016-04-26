/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 9:41:23 PM  : Apr 19, 2016
 */
package controllers;

import entities.Employee;
import entities.Item;
import entities.PurchaseReq;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import mediators.ItemJpaController;

/**
 *
 * @author kelli
 */
@Named(value = "npreq")
@SessionScoped
public class NewPurchaseRequestController implements Serializable {

    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction ut;
    
    @ManagedProperty(value = "#{empController}")
    private EmployeesController empController;
    
    private Date date;
    private Employee requestor;
    private Employee preparedBy;
    private String comments;
    //private PurchaseReqJpaController prjc;
    private ItemJpaController ijc;
    private PurchaseReq purchaseReq;
    private Item item;
    private List<Item> items;


    @PostConstruct
    public void init(){
        item = new Item();
        items = new ArrayList();
        
        //get the id of this purchase request from db;
    }
    /**
     * Creates a new instance of NewPurchaseRequestController
     */
    public NewPurchaseRequestController() {
        //ijc = new ItemJpaController(ut, emf);
       // prjc = new PurchaseReqJpaController(ut, emf);
    }
    
    public void save(){
        
    }
    
    
    public void saveAndSubmit(){
        
        purchaseReq = new PurchaseReq();
        purchaseReq.setRequestorId(requestor);
        purchaseReq.setPreparedbyId(preparedBy);
        purchaseReq.setComments(comments);
        purchaseReq.setPrepdate(date);
        purchaseReq.setItemCollection(items);
        try {
           // prjc.create(purchaseReq);
            for (Item currItem : items) {
                ijc.create(currItem);
            }
        } catch (Exception ex) {
            Logger.getLogger(NewPurchaseRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void discard(){
        
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getRequestor() {
        return requestor;
    }

    public void setRequestor(Employee requestor) {
        this.requestor = requestor;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Employee getPreparedById() {
        return preparedBy;
    }

    public void setPreparedById(Employee preparedById) {
        this.preparedBy = preparedById;
    }
    
    public void createNew(){
        if(items.contains(item)){
            FacesMessage msg = new FacesMessage("Duplicated","This Item has already been added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            items.add(item);
            item = new Item();
        }
    }
    
    public String reinit(){
        item = new Item();
        return null;
    }
    
    public Item getItem(){
        return item;
    }
    
    public List<Item> getItems(){
        return items;
    }

    public EmployeesController getEmpController() {
        return empController;
    }

    public void setEmpController(EmployeesController empController) {
        this.empController = empController;
    }
    
}
