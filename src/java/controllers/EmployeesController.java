/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 8:16:00 PM  : Apr 20, 2016
 */
package controllers;

import entities.Employee;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import mediators.EmployeeJpaController;

/**
 *
 * @author kelli
 */
@Named(value = "employeesController")
@SessionScoped
public class EmployeesController implements Serializable {

    private List<Employee> employees;
    private Employee selectedEmployee;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction ut;
    private EmployeeJpaController ejc;
    
    /**
     * Creates a new instance of EmployeesController
     */
    public EmployeesController() {
    }

    public List<Employee> getEmployees() {
        Logger.getLogger(EmployeesController.class.getName()).log(Level.INFO,"creating employees controller");
        ejc = new EmployeeJpaController(ut, emf);
        employees = ejc.findEmployeeEntities();
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
    
    
    
}
