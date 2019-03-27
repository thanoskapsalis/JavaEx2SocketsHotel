

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client_Handler {

    Booking booking;
    String flag;
    String tosearch;
    String date;
    String result;


    public Client_Handler(Booking booking) {
        System.out.println("Socket Created");
        this.booking = booking;
        this.flag = flag;
        run();

    }

    public void run() {
        try {
            Socket socket = new Socket("localhost", 5555);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            //new Request Waking up the server//
            ServerWake serverWake = new ServerWake();
            os.writeObject(serverWake);
            System.out.println("Request sent waiting for server to reply........");
            ServerWake serverWake1 = (ServerWake) is.readObject();


            if (serverWake1.getResponse_message().equals("WAITING")) {
                System.out.println("Server Accepted the connection");
                os.writeObject(booking);
                System.out.println(booking.getFlag());

                if (booking.getFlag().equals("INSERT")) {
                    System.out.println("Sending item to Server");
                    System.out.println("Waiting for ID");
                    Booking booking = (Booking) is.readObject();
                    System.out.println("ID Created: " + booking.getID());
                    JOptionPane.showMessageDialog(null, "Η κράτησή σας ολοκληρώθηκε!\n" + "Ο μοναδικός αριθμός κράτησης είναι ID: " + booking.getID());
                }


                if (booking.getFlag().equals("SEARCH")) {
                    System.out.println("Searching the server for reservations");
                    System.out.println("Sending key");
                    Booking booking = (Booking) is.readObject();
                    if (booking == null) {
                        JOptionPane.showMessageDialog(null, "Δεν υπάρχει κράτηση σε αυτό το όνομα η αυτήν την ημερομηνία");
                    } else {
                        System.out.println(booking.toString());
                        result = booking.toString();

                    }
                }

                if (booking.getFlag().equals("DELETE")) {
                    System.out.println("Delete item from server");
                    Booking booking = (Booking) is.readObject();
                    if(booking.getFlag().equals("DELETE OK"))
                        JOptionPane.showMessageDialog(null,"Η Κράτηση σας διαγράφηκε με επιτυχία");
                    else
                        JOptionPane.showMessageDialog(null,"Δεν υπάρχει κράτηση με αυτό το ID");
                }




            } else {
                System.out.println("Server Refused to Connect");
                socket.close();
            }

        } catch (IOException EX) {
            EX.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return result;
    }


}
