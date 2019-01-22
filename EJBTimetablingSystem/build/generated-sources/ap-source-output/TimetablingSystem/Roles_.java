package TimetablingSystem;

import TimetablingSystem.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-13T12:04:36")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, Integer> accesslevel;
    public static volatile SingularAttribute<Roles, String> name;
    public static volatile SingularAttribute<Roles, Integer> id;
    public static volatile CollectionAttribute<Roles, Users> usersCollection;

}