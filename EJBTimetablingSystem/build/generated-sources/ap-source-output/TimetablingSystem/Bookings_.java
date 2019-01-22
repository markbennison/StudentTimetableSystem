package TimetablingSystem;

import TimetablingSystem.Class;
import TimetablingSystem.Rooms;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-13T12:04:36")
@StaticMetamodel(Bookings.class)
public class Bookings_ { 

    public static volatile SingularAttribute<Bookings, Integer> duration;
    public static volatile SingularAttribute<Bookings, Class> classid;
    public static volatile SingularAttribute<Bookings, Integer> id;
    public static volatile SingularAttribute<Bookings, Date> dateandtime;
    public static volatile SingularAttribute<Bookings, Rooms> roomid;

}