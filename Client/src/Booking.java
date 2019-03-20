import com.sun.deploy.panel.JreDialog;

import javax.swing.*;
import java.awt.*;

public class Booking extends JFrame {

    public Booking(){
        super("Νέα Κράτηση");
        setSize(600, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,2));


        JLabel name = new JLabel("Όνομα");
        JTextField name_val = new JTextField();
        add(name);
        add(name_val);

        JLabel surrname = new JLabel("Επώνυμο");
        JTextField surrname_val = new JTextField();
        add(surrname);
        add(surrname_val);

        JLabel telephone = new JLabel("Τηλέφωνο");
        JTextField telephone_val = new JTextField();
        add(telephone);
        add(telephone_val);

        JLabel arrival_date = new JLabel("Ημερομηνία Άφηξης");
        JTextField arrival_val = new JTextField();
        add(arrival_date);
        add(arrival_val);

        JLabel leaving_date = new JLabel("Ημερομηνία Αναχώρησης");
        JTextField leaving_val = new JTextField();
        add(leaving_date);
        add(leaving_val);

        JRadioButton single = new JRadioButton("Μονόκλινο");
        JRadioButton doubler = new JRadioButton("Δίκλυνο");
        JRadioButton triple = new JRadioButton("Τρίκλινο");
        add(single);
        add(doubler);
        add(triple);



    }
}
