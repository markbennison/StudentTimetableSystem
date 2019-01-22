package TimetablingSystem;

import TimetablingSystem.Classlist;
import TimetablingSystem.Modules;
import TimetablingSystem.Roles;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-13T12:04:36")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> forename;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile CollectionAttribute<Users, Modules> modulesCollection;
    public static volatile SingularAttribute<Users, String> surname;
    public static volatile SingularAttribute<Users, Roles> roleid;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile CollectionAttribute<Users, Classlist> classlistCollection;
    public static volatile SingularAttribute<Users, String> username;

}