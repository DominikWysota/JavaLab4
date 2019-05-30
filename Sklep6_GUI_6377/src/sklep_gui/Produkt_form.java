package sklep_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dominik Wysota 6377
 */
public class Produkt_form extends JPanel implements ActionListener {

    JLabel lnazwa = new JLabel("Nazwa");                    
    JTextField nazwa = new JTextField(15);      
    JLabel lcena = new JLabel("Cena");
    JTextField cena = new JTextField(15);
    JLabel lpromocja = new JLabel("Promocja");
    JTextField promocja = new JTextField(15);
    JLabel lproducent = new JLabel("Producent");
    JTextField producent = new JTextField(15);
    JLabel ldata = new JLabel("Data");
    JTextField data = new JTextField(15);
    JButton dodaj_produkt = new JButton("Dodaj produkt");    

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(lnazwa);
        add(nazwa);
        add(lcena);
        add(cena);
        add(lpromocja);
        add(promocja);
        add(lproducent);
        add(producent);
        add(ldata);
        add(data);
        dodaj_produkt.addActionListener(this);
        add(dodaj_produkt);
    }

    public String content_validate(JTextField val, int typ) {
        String s = val.getText();
        if (s.equals("") || s.length() > 15) {
            JOptionPane.showMessageDialog(this, "Lancuch danych jest dluzszy niz 15 lub jest pusty");
            return null;
        } else {
            s = s.replaceAll(" ", "_");
            val.setText(s);
        }
        if (typ == 1) {
            try {
                Float.parseFloat(s);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Blad formatu danych liczbowych");
                return null;
            }
        }
        return s;
    }

    public String[] form_produkt() {
        if (content_validate(nazwa, 0) == null)
        {
            return null;
        }
        if (content_validate(cena, 1) == null)
        {
            return null;
        }
        if (content_validate(promocja, 1) == null)
        {
            return null;
        }
        if (content_validate(producent, 0) == null) 
        {
            return null;
        }
        String dane[] = {(String) nazwa.getText(), (String) cena.getText(), (String) promocja.getText(), producent.getText()};
        return dane;
    }

    public Date data() {
        if (content_validate(data, 0) == null)
        {
            return null;
        }
        int rok, miesiac, dzien;
        String data1 = data.getText();
        try {
            String[] data2 = data1.split("-");
            rok = Integer.parseInt(data2[2]);
            miesiac = Integer.parseInt(data2[1]);
            dzien = Integer.parseInt(data2[0]);
        } catch (PatternSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Blad formatu daty");
            return null;
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(rok, miesiac - 1, dzien);
        return gc.getTime();
    }

    @Override
    public void actionPerformed(ActionEvent evt){
        String[] dane = form_produkt();
        if (dane == null) {
            return;
        }
        Date data_ = data();
        if (data_ == null) {
            return;
        }
        try {
            GUI_main.getFacade().utworz_produkt(dane, data_);
        } catch (RemoteException ex) {
            Logger.getLogger(Produkt_form.class.getName()).log(Level.SEVERE, null, ex);
        }                                              
    }                                           
}
