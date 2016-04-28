package entities;

import entities.TravelAdvanceRequest;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-28T15:37:06")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, Integer> employeeId;
    public static volatile SingularAttribute<Employee, String> lname;
    public static volatile CollectionAttribute<Employee, TravelAdvanceRequest> travelAdvanceRequestCollection;
    public static volatile SingularAttribute<Employee, String> designation;
    public static volatile SingularAttribute<Employee, String> fname;
    public static volatile CollectionAttribute<Employee, TravelAdvanceRequest> travelAdvanceRequestCollection1;

}