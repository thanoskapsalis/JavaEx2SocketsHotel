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
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = reader.readLine();
                System.out.println(message);
                if (message.equals("START")) {
                    System.out.println("Connection Accepted");
                    writer.write("WAITING");
                    writer.newLine();
                    writer.flush();


                    message = reader.readLine();


                    if (message.equals("INSERT")) {
                        System.out.println("Recieving Booking");
                        Booking booking = (Booking) is.readObject();
                        Main.bookings.add(booking);
                        int id = create_ID();
                        writer.write(id);
                        writer.newLine();
                        writer.flush();
                        booking.setID(id);
                        System.out.println(booking.toString());
                    }


                    if (message.equals("SEARCH")) {
                        System.out.println("Search request. Ready to recieve Keyword");
                        String tosearch =reader.readLine();
                        os.writeObject(Search(tosearch));
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

    public Booking Search(String tosearch)
    {
        Booking toreturn = null;
        String full_name;
        for(int i=0; i<Main.bookings.size(); i++)
        {
            full_name=Main.bookings.get(i).getName()+" " + Main.bookings.get(i).getLastname();
            System.out.println("Customer:" + full_name);
            System.out.println(Main.bookings.get(i).toString());
            if(full_name.equals(tosearch) || Main.bookings.get(i).getArrival_date().equals(tosearch)) {
                toreturn = Main.bookings.get(i);
                break;
            }
            else
                toreturn=null;
        }
        return toreturn ;
    }

}
