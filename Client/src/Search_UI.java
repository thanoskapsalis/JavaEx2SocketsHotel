import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search_UI extends JFrame {


    public Search_UI() {
        super("Αναζήτηση Κράτησης");
        setSize(1200, 200);
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
        reservation.append("ID" + "\t" + "Όνομα" + "\t" + "Επώνυμο" + "\t" + "Τηλέφωνο" + "\t" + "Ημ.Αφιξης" + "\t" + "Ημ.αναχωρησης" + "\t" + "Τύπος Δωματίου" + "\t" + "Πρωινό" + "\n");


        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client_Handler handler = new Client_Handler(tosearch.getText(),date.getText(),"SEARCH");
                System.out.println("Result: " +handler.getResult());
                reservation.append(handler.getResult());

            }
        });
    }

}
