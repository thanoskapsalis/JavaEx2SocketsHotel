import com.sun.deploy.panel.JreDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Booking_UI extends JFrame {

    JRadioButton single = new JRadioButton("Μονόκλινο");
    JRadioButton doubler = new JRadioButton("Δίκλυνο");
    JRadioButton triple = new JRadioButton("Τρίκλινο");
    JTextField arrival_val = new JTextField();
    JTextField leaving_val = new JTextField();

    public Booking_UI() {
        super("Νέα Κράτηση");
        setSize(600, 300);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));


        //UI ELEMENTS//
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

        JLabel arrival_date = new JLabel("Ημερομηνία Άφηξης dd/mm/yyyy");
        add(arrival_date);
        add(arrival_val);

        JLabel leaving_date = new JLabel("Ημερομηνία Αναχώρησης dd/mm/yyyy");
        add(leaving_date);
        add(leaving_val);


        add(single);
        add(doubler);
        add(triple);

        JCheckBox breakfast = new JCheckBox("Πρωινό");
        add(breakfast);


        JButton confirm = new JButton("Επικύρωση Κράτησης");
        add(confirm);
        //UI ELEMENTS//

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(breakfast.isSelected());
                Booking booking = new Booking(name_val.getText(), surrname_val.getText(), telephone_val.getText(), arrival_val.getText(), leaving_val.getText(), Room_Checksum(), breakfast.isSelected());
                //gaining access to socket and then sending objects//
                Client_Handler handler = new Client_Handler(booking,"INSERT");

            }
        });




    }

    public int Room_Checksum() {
        if (single.isSelected())
        {
            doubler.setSelected(false);
            triple.setSelected(false);
            return 1;
        }
        else if(doubler.isSelected())
        {
            single.setSelected(false);
            triple.setSelected(false);
            return 2;
        }
        else
        {
            single.setSelected(false);
            doubler.setSelected(false);
            return 3;
        }

    }


}
