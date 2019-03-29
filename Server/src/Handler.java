import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


/////Θάνος Καψάλης 321/2015088/////

public class Handler {


    public Handler() {
        System.out.println("Server Started.............");

        while (true) {
            try {
                //Αρχικοποίηση socket καθώς και των ObjectInput/Output Streams
                ServerSocket server = new ServerSocket(5555, 50);
                Socket socket = server.accept();
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                ServerWake serverWake = (ServerWake) is.readObject();

                //Ανταλαγή μυνημάτων εκκίνηση απο την κλάση Serverwake για να αποδεχθεί την σύνδεση απο τον Client

                if (serverWake.getWake_message().equals("START")) {

                    System.out.println("Connection Accepted");
                    os.writeObject(serverWake);

                    //Αφού γίνει το Handshake Περιμένουμε τα διάφορα αιτήματα του Client
                    System.out.println("Recieving from Client");
                    Booking booking = (Booking) is.readObject();
                    System.out.println(booking.getFlag());

                    if (booking.getFlag().equals("INSERT")) {
                        System.out.println("SAVING NEW RESERVATION");
                        Main.bookings.add(booking);
                        int id = create_ID(); //Δημιουργεία id απο την αντιστοιχη συνάρτηση παρακάτω
                        booking.setID(id);
                        booking.setPrice(Price_Calculator(booking)); //Υπολογισμός τιμής με την αντίστοιχη συνάρτηση
                        os.writeObject(booking); //Στέλνουμε το αντικείμενο πίσω με τα νεα του χαρακτηριστικά ID και τιμή
                        System.out.println(booking.toString());
                    }


                    if (booking.getFlag().equals("SEARCH")) {
                        System.out.println("Search request. Ready to recieve Keyword");
                        os.writeObject(Search(booking)); //Επιστρέφουμε το αντικείμενο που επιστρέφει η συνάρτηση της αναζήτησης
                    }

                    if (booking.getFlag().equals("DELETE")) {
                        System.out.println("Delete Request");
                        os.writeObject(Delete(booking));  //Επιστρέφουμε το αντικείμενο που επιστρέφει η συνάρτηση της διαγραφής
                    }


                } else {
                    //Αμα ο client στείλει λάθος request κλείνουμε το socket, τερματίζοντας την σύνδεση
                    System.out.println("Connection Refused due to bad args");
                    socket.close();
                    break;

                }


            } catch (IOException ex) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {


            }


        }
    }

    public int create_ID() {
        Random random = new Random();
        int idnum = random.nextInt(5000);
        return idnum;

    }

    //Προσπελάσει τα αντικείμενα του server μέσω ενος arraylist που βρίσκεται στην main
    //ώστε να μην καθαρίζει κάθε φορά που φτάνει ένα νέο αίτημα στον server
    public Booking Search(Booking booking1) {
        Booking toreturn = null;
        String full_name;
        String tosearch = booking1.getName();
        String date = booking1.getArrival_date();
        for (int i = 0; i < Main.bookings.size(); i++) {
            full_name = Main.bookings.get(i).getName() + " " + Main.bookings.get(i).getLastname();
            System.out.println("Customer:" + full_name);
            System.out.println(Main.bookings.get(i).toString());
            if (full_name.equals(tosearch) || Main.bookings.get(i).getArrival_date().equals(tosearch)) {
                toreturn = Main.bookings.get(i);
                break;
            }
            if (full_name.equals(tosearch) && Main.bookings.get(i).getArrival_date().equals(date)) {
                toreturn = Main.bookings.get(i);
                break;
            } else
                toreturn = null;
        }
        return toreturn;
    }

    //Αναζητά τα αντικείμενα με τα συγκεκριμένα χαρακτηριστικά και επιστρέφει το κατάληλο flag
    public Booking Delete(Booking booking) {
        for (int i = 0; i < Main.bookings.size(); i++) {
            if (Main.bookings.get(i).getID() == booking.getID()) {
                Main.bookings.remove(Main.bookings.get(i));
                booking.setflag("DELETE OK");
                break;
            } else
                booking.setflag("DELETE FAIL");
        }
        return booking;
    }


    //Υπολογίζει την τιμή που πρέπει να πληρώσει ο πελάτης και την επιστρέφει σε μια long
    //Η τιμή αυτή αποθηκεύεται ως χαρακτηριστικό του αντικειμένου
    public long Price_Calculator(Booking booking) throws ParseException {

        //Μετατροπή των String ημερομηνιών σε Date format για να υπολογίζουμε τις μέρες που θα μείνει ο πελάτης στο ξενοδοχείο
        Date arrival = new SimpleDateFormat("dd/MM/yyyy").parse(booking.getArrival_date());
        Date leave = new SimpleDateFormat("dd/MM/yyyy").parse(booking.getLeave_date());

        int room_type = booking.getRoom_type();
        boolean breakfast = booking.get_Breakfast();
        long price = 0;
        long dayspass = ChronoUnit.DAYS.between(arrival.toInstant(), leave.toInstant());

        LocalDate localDate = arrival.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue(); //ελέγχουμε τον μήνα για την τουριστική περίοδο
        System.out.println(month);
        //υποθέτουμε οτι αμα κλείσεις το δωματιο τουρηστική περίοδο θα χρεωθεί ολο με τιμή τουριστικής περιόδου
        //π.χ αμα κλείσουμε 30 Σεπτέμβρη με 2 Οκτώβρη θα χρεωθεί σαν τουριστικό

        if (month >= 6 && month <= 9) {
            if (room_type == 1)
                if (breakfast == true)
                    price = 80 * dayspass + 8 * dayspass;
                else
                    price = 80 * dayspass;

            if (room_type == 2)
                if (breakfast == true)
                    price = 120 * dayspass + 16 * dayspass;
                else
                    price = 120 * dayspass;
            if (room_type == 3)
                if (breakfast == true)
                    price = 150 * dayspass + 24 * dayspass;
                else
                    price = 150 * dayspass;

        } else {
            if (room_type == 1)
                if (breakfast == true)
                    price = 40 * dayspass + 8 * dayspass;
                else
                    price = 40 * dayspass;

            if (room_type == 2)
                if (breakfast == true)
                    price = 70 * dayspass + 16 * dayspass;
                else
                    price = 70 * dayspass;
            if (room_type == 3)
                if (breakfast == true)
                    price = 85 * dayspass + 24 * dayspass;
                else
                    price = 85 * dayspass;

        }


        return price;
    }

}
