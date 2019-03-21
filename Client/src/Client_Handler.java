import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client_Handler {

    public Client_Handler(Booking booking)  {
        System.out.println("Socket Created");
        try {
            Socket socket = new Socket("localhost",5555);

            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            os.writeObject(booking);
        } catch (IOException EX) {
            EX.printStackTrace();
        }


    }

}
