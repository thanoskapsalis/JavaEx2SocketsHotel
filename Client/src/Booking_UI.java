import com.sun.deploy.panel.JreDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking_UI extends JFrame {

    JRadioButton single = new JRadioButton("Μονόκλινο");
    JRadioButton doubler = new JRadioButton("Δίκλυνο");
    JRadioButton triple = new JRadioButton("Τρίκλινο");
    JTextField arrival_val = new JTextField();
    JTextField leaving_val = new JTextField();
    JTextField name_val = new JTextField();
    JTextField surrname_val = new JTextField();
    JTextField telephone_val = new JTextField();
    ButtonGroup group= new ButtonGroup();


    public Booking_UI() {
        super("Νέα Κράτηση");
        setSize(600, 300);
        setResizable(false);
        setLayout(new GridLayout(8, 2));
        group.add(single);
        group.add(doubler);
        group.add(triple);


        //UI ELEMENTS//
        JLabel name = new JLabel("Όνομα");
        add(name);
        add(name_val);

        JLabel surrname = new JLabel("Επώνυμο");
        add(surrname);
        add(surrname_val);

        JLabel telephone = new JLabel("Τηλέφωνο");
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
                try {
                    if (InsertChecksum()) {
                        System.out.println(breakfast.isSelected());
                        Booking booking = new Booking(name_val.getText(), surrname_val.getText(), telephone_val.getText(), arrival_val.getText(), leaving_val.getText(), Room_Checksum(), breakfast.isSelected());
                        //gaining access to socket and then sending objects//
                        booking.setflag("INSERT");
                        Client_Handler handler = new Client_Handler(booking);
                    } else {
                        JOptionPane.showMessageDialog(null, "Λανθασμένη Εισαγωγή στοιχείων πορπσθήστε ξανά");
                    }
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }


            }
        });


    }

    public boolean InsertChecksum() throws ParseException {
        //Χρησιμοποιούμε Custom toreturn αντι να επιστρέφουμε κάθε φορά γιατι αλλιώς θα θέλαμε το finally
        //που δημιουργούσε πρόβλημα στην λειτουργία του προγράμματος
        boolean toreturn = false;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date arrival_date = dateFormat.parse(arrival_val.getText());
            String value;
            Date leaving_date = dateFormat.parse(leaving_val.getText());
            if (name_val.getText().equals("") || surrname_val.equals("") || telephone_val.getText().equals("") || !arrival_val.getText().equals(dateFormat.format(arrival_date)) || !leaving_val.getText().equals(dateFormat.format(leaving_date)) || Room_Checksum() == 0)
                toreturn = false;
            else
                toreturn = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Η ημερομηνία πρέπει να είναι της μορφής dd/MM/yyyy");
        }

        return toreturn;

    }

    public int Room_Checksum() {

        if (single.isSelected()) {
            doubler.setSelected(false);
            triple.setSelected(false);
            return 1;
        } else if (doubler.isSelected()) {
            single.setSelected(false);
            triple.setSelected(false);
            return 2;
        } else if (triple.isSelected()) {
            single.setSelected(false);
            doubler.setSelected(false);
            return 3;
        } else
            return 0;

    }


}
