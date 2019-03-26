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


    public Booking(String name, String lastname, String phone, String arrival_date, String leave_date, int room_type, boolean breakfast) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.leave_date = leave_date;
        this.arrival_date = arrival_date;
        this.room_type = room_type;
        this.breakfast = breakfast;
    }

    public Booking(String name, String arrival_date) {
        this.name = name;
        this.arrival_date = arrival_date;
    }

    public String toString() {
        String custom_br;
        String custom_room;
        if (breakfast == true)
            custom_br = "✔";
        else
            custom_br = "✘";

        if (room_type == 1)
            custom_room = "Μονοκλινο";
        else if (room_type == 2)
            custom_room = "Δίκλινο";
        else
            custom_room = "Τρίκλινο";


        return ID + "\t" + name + "\t" + lastname + "\t" + phone + "\t" + arrival_date + "\t" + leave_date + "\t" + custom_room + "\t" + custom_br;
    }


    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getArrival_date() {
        return arrival_date;
    }
}
