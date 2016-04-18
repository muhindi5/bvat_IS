/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 2:14:01 PM  : Apr 16, 2016
 */
package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kelli
 */
@Named(value = "calenderBean")
@Dependent
public class CalenderController {

    private Date date;
    /**
     * Creates a new instance of CalenderBean
     */
    public CalenderController() {
        
    }
    
//    private void onDateSelect(SelectEvent sEvent){
//        FacesContext context = FacesContext.getCurrentInstance();
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO),
//                "Date Selected",df.format(sEvent.getObject()));
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
