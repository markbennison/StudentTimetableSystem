package TimetablingSystem;

import TimetablingSystem.Bookings;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-13T12:04:36")
@StaticMetamodel(Rooms.class)
public class Rooms_ { 

    public static volatile SingularAttribute<Rooms, String> name;
    public static volatile SingularAttribute<Rooms, String> description;
    public static volatile SingularAttribute<Rooms, Integer> id;
    public static volatile CollectionAttribute<Rooms, Bookings> bookingsCollection;

}