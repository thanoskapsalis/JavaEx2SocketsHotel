import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Handler {


    public Handler() {
        System.out.println("Starting Server..........");

        while (true) {
            System.out.println("Accepting Conncections....");

            try {
                ServerSocket server = new ServerSocket(5555, 50);
                Socket socket = server.accept();
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
               //TESTING STUFF//
                Booking booking =(Booking) is.readObject();
                System.out.println(booking.toString());



            } catch (IOException ex) {
                System.out.println("ex");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

}
