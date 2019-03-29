import java.io.Serializable;


/////Θάνος Καψάλης 321/2015088/////


//Στην κλάση Booking είναι αποθηκευμένες όλες οι μεταβλητές που αφορούν την κράτηση
//Έχουμε επίσης διάφορους Constructors μιας και πολλές φορές θέλουμε να δώσουμε διαφορετικά χαρακτηριστικά στο αντικείμενό μας
//την στιγμή που το ορίζουμε


public class Booking implements Serializable  {
    String flag;
    String name;
    String lastname;
    String phone;
    String arrival_date;
    String leave_date;
    int room_type;
    boolean breakfast;
    int ID;
    long price;

    public Booking(String name,String lastname,String phone,String arrival_date,String leave_date, int room_type,boolean breakfast)
    {
        this.name=name;
        this.lastname=lastname;
        this.phone=phone;
        this.leave_date=leave_date;
        this.arrival_date=arrival_date;
        this.room_type=room_type;
        this.breakfast=breakfast;
    }

    public  Booking(String name,String arrival_date)
    {
        this.name=name;
        this.arrival_date=arrival_date;
    }

    public Booking(int ID)
    {
        this.ID=ID;
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


        return ID+"\t"+name + "\t" + lastname + "\t" + phone + "\t" + arrival_date + "\t\t" + leave_date + "\t" + custom_room + "\t" + custom_br;
    }

    public void setID(int ID)
    {
        this.ID=ID;
    }

    public int getID(){return ID;}

    public String getName(){return name;}

    public String getArrival_date(){return arrival_date;}

    public String getLeave_date(){return  leave_date;}

    public String getLastname(){return lastname;}

    public int getRoom_type(){return  room_type;}

    public boolean get_Breakfast(){return breakfast;}

    public void setflag(String flag){this.flag=flag;}

    public String getFlag(){return flag;}

    public void setPrice(long price){this.price=price;}

    public long getPrice(){return price;}
}
