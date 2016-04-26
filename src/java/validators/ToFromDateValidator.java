/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 1:22:38 PM  : Apr 26, 2016
 */
package validators;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author kelli
 */
@FacesValidator("validators.ToFromValidator")
public class ToFromDateValidator implements javax.faces.validator.Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
        Logger.getAnonymousLogger().log(Level.INFO,"value is null returning");
            return;
        }
        //get 'from' date in component
        Object fDate = component.getAttributes().get("attrFromDate");
        if(fDate == null){
        Logger.getAnonymousLogger().log(Level.INFO,"fDate is null returning");
            return;
        }
        Date from = (Date)fDate;
        Date to  = (Date)value;
        Logger.getAnonymousLogger().log(Level.INFO,from.toString());
        Logger.getAnonymousLogger().log(Level.INFO,to.toString());
        if(to.before(from)){
            FacesMessage dateRangeErr = new FacesMessage("Date range Error",
                    "End date has to be after start date");
            throw new ValidatorException(dateRangeErr);
        }
        
    }
    
}
