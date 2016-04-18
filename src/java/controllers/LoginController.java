/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:52:01 AM  : Apr 17, 2016
 */
package controllers;

import entities.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import mediators.UserJpaController;
import services.Authentication;

/**
 *
 * @author kelli
 */
@Named(value = "loginController")
@RequestScoped
public class LoginController {

    private String userName;
    private String passwordEntry;
    private String outcomePage;
    private final Logger logger;
    private FacesContext context;

    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction ut;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginController() {
        logger = Logger.getLogger("LoginAudit");
        context = FacesContext.getCurrentInstance();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordEntry() {
        return passwordEntry;
    }

    public void setPasswordEntry(String password) {
        this.passwordEntry = password;
    }

    public String getOutcomePage() {
        return outcomePage;
    }

    public void setOutcomePage(String outcomePage) {
        this.outcomePage = outcomePage;
    }

    /*
     Login validator
     1. Get username and password entered
     2. Generate  hash from the password value
     3. Fetch stored username and password values from db
     4. Compare entered username text with db, same for pswd hashes
     */
    public String isLoginEntryValid() {
        String retrievedPass, retrievedSalt;
        String enteredPassword = getPasswordEntry();
        UserJpaController ujc = new UserJpaController(ut, emf);
        List<User> users = ujc.findUserEntities();
        for (User user : users) {
            retrievedPass = user.getUserPass();
            retrievedSalt = user.getSaltVal();
            if (Authentication.validatePasswordHash(enteredPassword, retrievedPass, retrievedSalt)) {
                //put user in session context
                context.getExternalContext().getSessionMap().put("loggedInUser", user);
                logger.log(Level.INFO, "User is: {0}",user.getUserName());
                outcomePage = "PG_HOME";
            } else {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Wrong Username or Password", "Wrong Username or Password");
                context.addMessage("frmLogin:tx_password",fm);
            }
        }
        return outcomePage;
    }
    
    /* Logout by invalidating user session */
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "PG_LOGOUT";
    }
}
