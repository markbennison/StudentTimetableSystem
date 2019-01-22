package TimetablingSystem;

import TimetablingSystem.Class;
import TimetablingSystem.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-13T12:04:36")
@StaticMetamodel(Modules.class)
public class Modules_ { 

    public static volatile SingularAttribute<Modules, String> modulecode;
    public static volatile SingularAttribute<Modules, Users> leader;
    public static volatile CollectionAttribute<Modules, Class> classCollection;
    public static volatile SingularAttribute<Modules, Integer> id;
    public static volatile SingularAttribute<Modules, String> title;

}