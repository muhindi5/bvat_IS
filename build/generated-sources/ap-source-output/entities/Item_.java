package entities;

import entities.PurchaseReq;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-28T15:37:06")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> unitType;
    public static volatile SingularAttribute<Item, String> description;
    public static volatile SingularAttribute<Item, PurchaseReq> preqId;
    public static volatile SingularAttribute<Item, Integer> quantity;
    public static volatile SingularAttribute<Item, BigDecimal> unitPrice;
    public static volatile SingularAttribute<Item, Integer> itemId;
    public static volatile SingularAttribute<Item, BigDecimal> totalPrice;

}