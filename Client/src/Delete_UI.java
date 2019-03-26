import javax.swing.*;
import java.awt.*;

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
    }
}
