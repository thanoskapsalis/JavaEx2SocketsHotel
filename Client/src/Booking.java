import javax.swing.*;
import java.awt.*;

public class Booking extends JFrame {

    public Booking(){
        super("Νέα Κράτηση");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9,3));

    }
}
