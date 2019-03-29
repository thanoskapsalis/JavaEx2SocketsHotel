import java.io.Serializable;


/////Θάνος Καψάλης 321/2015088/////

//Μυνήματα που είναι υπέθυνα για το handshake μεταξύ server και client
public class ServerWake implements Serializable {
    String wake_message = "START";
    String response_message = "WAITING";


    public String getWake_message() {
        return wake_message;
    }

    public String getResponse_message() {
        return response_message;
    }

}