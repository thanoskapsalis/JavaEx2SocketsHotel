import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;


public class Handler {


    public Handler() {
        System.out.println("Server Started.............");

        while (true) {
            try {
                ServerSocket server = new ServerSocket(5555, 50);
                Socket socket = server.accept();
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                ServerWake serverWake= (ServerWake) is.readObject();


                if (serverWake.getWake_message().equals("START")) {

                    System.out.println("Connection Accepted");
                    os.writeObject(serverWake);
                    System.out.println("Recieving from Client");
                    Booking booking = (Booking) is.readObject();
                    System.out.println(booking.getFlag());

                    if (booking.getFlag().equals("INSERT")) {
                        System.out.println("SAVING NEW RESERVATION");
                        Main.bookings.add(booking);
                        int id = create_ID();
                        booking.setID(id);
                        os.writeObject(booking);
                        System.out.println(booking.toString());
                    }


                    if (booking.getFlag().equals("SEARCH")) {
                        System.out.println("Search request. Ready to recieve Keyword");
                        os.writeObject(Search(booking));
                    }

                    if(booking.getFlag().equals("DELETE")){
                        System.out.println("Delete Request");
                        os.writeObject(Delete(booking));
                    }


                } else {
                    System.out.println("Connection Refused due to bad args");
                    socket.close();
                    break;

                }


            } catch (IOException ex) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {


            }


        }
    }

    public int create_ID() {
        //should leave space for checking if the id exists already in another reservation//
        Random random = new Random();
        int idnum = random.nextInt(5000);
        return idnum;

    }

    //Check if name is true and date is false !!!!!!!
    public Booking Search(Booking booking1)
    {
        Booking toreturn = null;
        String full_name;
        String tosearch=booking1.getName();
        String date =booking1.getArrival_date();
        for(int i=0; i<Main.bookings.size(); i++)
        {
            full_name=Main.bookings.get(i).getName()+" " + Main.bookings.get(i).getLastname();
            System.out.println("Customer:" + full_name);
            System.out.println(Main.bookings.get(i).toString());
            if(full_name.equals(tosearch) || Main.bookings.get(i).getArrival_date().equals(tosearch)) {
                toreturn = Main.bookings.get(i);
                break;
            }
            if(full_name.equals(tosearch) && Main.bookings.get(i).getArrival_date().equals(date)) {
                toreturn = Main.bookings.get(i);
                break;
            }
            else
                toreturn=null;
        }
        return toreturn ;
    }

    public Booking Delete(Booking booking)
    {
        for(int i=0; i<Main.bookings.size(); i++)
        {
            if(Main.bookings.get(i).getID()==booking.getID())
            {
                Main.bookings.remove(Main.bookings.get(i));
                booking.setflag("DELETE OK");
                break;
            }else
                booking.setflag("DELETE FAIL");
        }
        return booking;
    }

}
