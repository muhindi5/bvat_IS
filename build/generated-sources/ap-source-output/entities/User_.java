package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-18T08:36:47")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SingularAttribute<User, String> saltVal;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, String> userPass;

}