import javax.swing.*;
import java.awt.*;

public class Search_UI extends JFrame {

    public Search_UI()
    {
        super("Αναζήτηση Κράτησης");
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JLabel label = new JLabel("Εισάγετε τον μοναδικό αριθμό κράτησης");
        JTextField tosearch = new JTextField();
        JButton confirm = new JButton("Αναζήτηση");
        add(label);
        add(tosearch);
        add(confirm);
    }
}
