package entities;

import entities.Employee;
import entities.Item;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-28T15:08:55")
@StaticMetamodel(PurchaseReq.class)
public class PurchaseReq_ { 

    public static volatile SingularAttribute<PurchaseReq, String> preqStatus;
    public static volatile SingularAttribute<PurchaseReq, Date> prepdate;
    public static volatile SingularAttribute<PurchaseReq, Integer> prnum;
    public static volatile CollectionAttribute<PurchaseReq, Item> itemCollection;
    public static volatile SingularAttribute<PurchaseReq, Employee> requestorId;
    public static volatile SingularAttribute<PurchaseReq, String> comments;
    public static volatile SingularAttribute<PurchaseReq, Employee> preparedbyId;

}