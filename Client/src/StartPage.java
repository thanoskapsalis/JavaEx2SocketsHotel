import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JFrame {

    public StartPage() {
        super("Αρχική Σελίδα");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Καλώς Ορίσατε στο Ξενοδοχείο μας");
        add(label);

        JButton booking = new JButton("Κράτηση");
        add(booking);


        JButton search = new JButton("Αναζήτηση");
        add(search);

        JButton cancel = new JButton("Ακύρωση");
        add(cancel);


        booking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking_UI booking1 = new Booking_UI();
                booking1.setVisible(true);
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search_UI search_ui = new Search_UI();
                search_ui.setVisible(true);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete_UI delete_ui = new Delete_UI();
                delete_ui.setVisible(true);
            }
        });


    }


}
