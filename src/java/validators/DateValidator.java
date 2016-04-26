/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 12:01:12 PM  : Apr 26, 2016
 */
package validators;

import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author kelli
 */
@FacesValidator("validators.DateValidator")
public class DateValidator implements javax.faces.validator.Validator{

    @Override
    public void validate
        (FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null){
            return;
        }
        Date selected = (Date)value;
        if(selected.before(Calendar.getInstance().getTime())){
            FacesMessage dateErrMsg = 
            new FacesMessage("Invalid Date", "Date selected cannot be past");
            throw new ValidatorException(dateErrMsg);
            
        }
    }
    
}
