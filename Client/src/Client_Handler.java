import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client_Handler {

    public Client_Handler(Booking booking,String flag) {
        System.out.println("Socket Created");
        try {
            Socket socket = new Socket("localhost", 5555);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.write("START");
            writer.newLine();
            writer.flush();
            String message = reader.readLine();
            if (message.equals("WAITING")) {
                System.out.println("Server Accepted the connection");
                if(flag.equals("INSERT"))
                {
                    System.out.println("Sending item to Server");
                    writer.write("INSERT");
                    writer.newLine();
                    writer.flush();
                    os.writeObject(booking);
                    System.out.println("Waiting for ID");



                }
            } else {
                System.out.println("Server Refused to Connect");
                socket.close();
            }

        } catch (IOException EX) {
            EX.printStackTrace();
        }


    }



}
