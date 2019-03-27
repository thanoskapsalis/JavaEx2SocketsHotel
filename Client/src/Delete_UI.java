import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete_UI extends JFrame {

    public Delete_UI(){
        super("Αναζήτηση Κράτησης");
        setSize(1200, 200);
        setResizable(false);
        setLayout(new GridLayout(3,2));

        JLabel label = new JLabel("Εισάγετε το ID της κράτησης προς διαγραφή");
        JTextField todelete = new JTextField();
        JButton confirm = new JButton("Επιβεβαίωση");
        add(label);
        add(todelete);
        add(confirm);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking booking= new Booking(Integer.parseInt(todelete.getText()));
                booking.setflag("DELETE");
                Client_Handler client_handler=new Client_Handler(booking);

            }
        });
    }
}
