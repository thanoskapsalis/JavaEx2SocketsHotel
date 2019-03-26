

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client_Handler {

    Booking booking;
    String flag;
    String tosearch;
    String date;
    String result;


    public Client_Handler(Booking booking, String flag) {
        System.out.println("Socket Created");
        this.booking = booking;
        this.flag = flag;
        run();

    }

    public Client_Handler(String tosearch, String date,String flag) {
        System.out.println("Socket Created");
        this.tosearch = tosearch;
        this.flag=flag;
        this.date=date;
        run();
    }

    public void run() {
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


                if (flag.equals("INSERT")) {
                    System.out.println("Sending item to Server");
                    writer.write("INSERT");
                    writer.newLine();
                    writer.flush();
                    os.writeObject(booking);
                    System.out.println("Waiting for ID");
                    int id = reader.read();
                    System.out.println("ID Created: " + id);
                    JOptionPane.showMessageDialog(null, "Η κράτησή σας ολοκληρώθηκε!\n" + "Ο μοναδικός αριθμός κράτησης είναι ID: " + id);
                }


                if (flag.equals("SEARCH")) {
                    System.out.println("Searching the server for reservations");
                    writer.write("SEARCH");
                    writer.newLine();
                    writer.flush();
                    System.out.println("Sending key");
                    Booking booking1 = new Booking(tosearch,date);
                    os.writeObject(booking1);
                    Booking booking =(Booking) is.readObject();
                    if(booking==null)
                    {
                        JOptionPane.showMessageDialog(null, "Δεν υπάρχει κράτηση σε αυτό το όνομα η αυτήν την ημερομηνία");
                    }
                    else
                    {
                        System.out.println(booking.toString());
                        result=booking.toString();

                    }


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

    public String getResult()
    {
        return  result;
    }




}
