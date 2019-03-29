import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search_UI extends JFrame {


    //// Θάνος Καψάλης 321/2015088 ////

    //UI αναζήτησης//

    public Search_UI() {
        super("Αναζήτηση Κράτησης");
        setSize(1200, 400);
        setResizable(false);
        setLayout(new GridLayout(6, 1));

        JLabel label = new JLabel("Εισάγετε Όνοματεπώνυμο Κράτησης");
        JLabel date_label = new JLabel("Εισάγετε την ημερομηνία Κράτησης* (dd/mm/yyyy)");
        JTextField date = new JTextField();
        JTextField tosearch = new JTextField();
        JButton confirm = new JButton("Αναζήτηση");
        add(label);
        add(tosearch);
        add(date_label);
        add(date);
        add(confirm);


        JTextArea reservation = new JTextArea();
        reservation.setEditable(false);
        add(reservation);
        reservation.append("ID" + "\t" + "Name" + "\t" + "Surname" + "\t" + "Phone" + "\t" + "Check In" + "\t" + "Check Out" + "\t" + "Room type" + "\t" + "Breakfast" + "\n");


        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Επικοινωνία με τον Handler για αναζήτηση στέλνωντας ένα αντικείμενο με το κατάλληλο flag
                if(date.getText().equals(""))
                    date.setText(null);
                Booking booking= new Booking(tosearch.getText(),date.getText());
                booking.setflag("SEARCH");
                Client_Handler handler = new Client_Handler(booking);
                System.out.println("Result: " +handler.getResult());
                reservation.append(handler.getResult());

            }
        });
    }

}
