package TimetablingSystem;

import TimetablingSystem.Bookings;
import TimetablingSystem.Classlist;
import TimetablingSystem.Modules;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-13T12:04:36")
@StaticMetamodel(Class.class)
public class Class_ { 

    public static volatile SingularAttribute<Class, Date> enddate;
    public static volatile SingularAttribute<Class, Integer> id;
    public static volatile SingularAttribute<Class, Date> startdate;
    public static volatile CollectionAttribute<Class, Classlist> classlistCollection;
    public static volatile SingularAttribute<Class, Modules> moduleid;
    public static volatile CollectionAttribute<Class, Bookings> bookingsCollection;

}