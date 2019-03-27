import java.io.Serializable;

public class ServerWake  implements Serializable {
    String wake_message = "START";
    String response_message = "WAITING";


    public String getWake_message() {
        return wake_message;
    }

    public String getResponse_message() {
        return response_message;
    }

}