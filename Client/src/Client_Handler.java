

import javax.swing.*;
import java.io.*;
import java.net.Socket;


////Θάνος Καψάλης 321/2015088/////


public class Client_Handler {

    Booking booking;
    String flag;
    String result;


    //Εισαγωγή του αντικειμένου απο την κάθε λειτουργία στον Handler
    public Client_Handler(Booking booking) {
        System.out.println("Socket Created");
        this.booking = booking;
        this.flag = flag;
        run();

    }


    //Συνάρτηση λειτουργίας Handler
    public void run() {
        try {
            Socket socket = new Socket("localhost", 5555);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            //new Request Waking up the server//
            //Handshake//
            ServerWake serverWake = new ServerWake();
            os.writeObject(serverWake);
            System.out.println("Request sent waiting for server to reply........");
            ServerWake serverWake1 = (ServerWake) is.readObject();


            //Άμα ο server απαντήσει με Waiting σημαίνει ότι αποδέχτηκε την σύνδεση και μπορούμε να επικοινωνήσουμε μαζί του
            if (serverWake1.getResponse_message().equals("WAITING")) {
                System.out.println("Server Accepted the connection");
                os.writeObject(booking); //Αποστολή αντικειμένου στον server
                System.out.println(booking.getFlag());


                //Ανάλογα με το flag του αντικειμένου μας Περιμένουμε και το αντίστοιχο αποτέλεσμα απο τον Server
                if (booking.getFlag().equals("INSERT")) { //Στην εισαγωγή περιμένουμε τιμη και id μέσω του αντικειμένου που θα μας επιστρέψει
                    System.out.println("Sending item to Server");
                    System.out.println("Waiting for ID");
                    Booking booking = (Booking) is.readObject();
                    System.out.println("ID Created: " + booking.getID());
                    JOptionPane.showMessageDialog(null, "Η κράτησή σας ολοκληρώθηκε!\n" + "Ο μοναδικός αριθμός κράτησης είναι ID: " + booking.getID()
                            + "\n Το ποσό πληρωμής είναι: " + booking.getPrice() + "€");
                }


                if (booking.getFlag().equals("SEARCH")) { //Στην αναζήτηση περιμένουμε ένα ατνικείμενο με την κράτηση που ζητήσμαμε. Άμα δεν υπάρχει, ο server επιστρέφει null και ο client εμφανίζει το κατάλληλο μύνημα
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

                if (booking.getFlag().equals("DELETE")) {//Περιμένουμε ένα flag που επιβεβαιώνει την διαγραφή απο το server άμα δεν το βρει στέλενει flag αποτυχίας
                    System.out.println("Delete item from server");
                    Booking booking = (Booking) is.readObject();
                    if (booking.getFlag().equals("DELETE OK"))
                        JOptionPane.showMessageDialog(null, "Η Κράτηση σας διαγράφηκε με επιτυχία");
                    else
                        JOptionPane.showMessageDialog(null, "Δεν υπάρχει κράτηση με αυτό το ID");
                }


            } else { //Σε περίπτωση αποτυχημένου Handshake κλείνουμε το socket
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
