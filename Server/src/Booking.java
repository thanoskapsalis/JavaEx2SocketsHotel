import java.io.Serializable;

public class Booking implements Serializable {

    String name;
    String lastname;
    String phone;
    String arrival_date;
    String leave_date;
    int room_type;
    boolean breakfast;
    int ID;


    public Booking(String name, String lastname, String phone, String arrival_date, String leave_date, int room_type, boolean breakfast)
    {
        this.name=name;
        this.lastname=lastname;
        this.phone=phone;
        this.leave_date=leave_date;
        this.arrival_date=arrival_date;
        this.room_type=room_type;
        this.breakfast=breakfast;
    }

    public String toString()
    {
        return name+" "+lastname+" "+phone+" "+arrival_date+" "+leave_date+" "+room_type+" "+breakfast;
    }

    public void setID(int ID)
    {
        this.ID=ID;
    }
}
