package entities;

import entities.Employee;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T20:29:04")
@StaticMetamodel(TravelAdvanceRequest.class)
public class TravelAdvanceRequest_ { 

    public static volatile SingularAttribute<TravelAdvanceRequest, String> reqNotes;
    public static volatile SingularAttribute<TravelAdvanceRequest, String> status;
    public static volatile SingularAttribute<TravelAdvanceRequest, Date> submitDate;
    public static volatile SingularAttribute<TravelAdvanceRequest, BigDecimal> requestAmount;
    public static volatile SingularAttribute<TravelAdvanceRequest, Integer> tradvNum;
    public static volatile SingularAttribute<TravelAdvanceRequest, String> travelType;
    public static volatile SingularAttribute<TravelAdvanceRequest, Date> toDate;
    public static volatile SingularAttribute<TravelAdvanceRequest, Employee> preparedBy;
    public static volatile SingularAttribute<TravelAdvanceRequest, String> travelPurpose;
    public static volatile SingularAttribute<TravelAdvanceRequest, Employee> requestor;
    public static volatile SingularAttribute<TravelAdvanceRequest, String> destination;
    public static volatile SingularAttribute<TravelAdvanceRequest, String> country;
    public static volatile SingularAttribute<TravelAdvanceRequest, Date> fromDate;

}