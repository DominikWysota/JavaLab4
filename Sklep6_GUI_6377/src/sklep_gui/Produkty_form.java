package sklep_gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dominik Wysota 6377
 */
public class Produkty_form extends JPanel {

    private JTable tabela_produktow;                                               
    MyTableModel model;                                                             
    JComboBox lista_produktow;                                                      

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model = new MyTableModel();                                               
        tabela_produktow = new JTable(model);                                      
        table_content();                                                                
        tabela_produktow.setPreferredScrollableViewportSize(new Dimension(800, 100));
        tabela_produktow.setFillsViewportHeight(true);
        tabela_produktow.getSelectionModel().addListSelectionListener(new RowListener());
        add(new JScrollPane(tabela_produktow));                                 
        JLabel lprodukty = new JLabel("Produkty");
        add(lprodukty);
        lista_produktow = new JComboBox();
        add(lista_produktow);
    }

    void table_content() {                                                     
        ArrayList<ArrayList<String>> produkty = null;
        try {
            produkty = GUI_main.getFacade().items();
        } catch (RemoteException ex) {
            Logger.getLogger(Produkty_form.class.getName()).log(Level.SEVERE, null, ex);
        }
model.setData(produkty);
    }

    private void list_content(ArrayList<ArrayList<String>> col, JComboBox list) {
        ArrayList<String> s;                                                   
        list.removeAllItems();
        Iterator<ArrayList<String>> iterator = col.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            list.addItem(s);
        }
    }

    void print_produkty() {                                                    
        ArrayList<ArrayList<String>> help3 = null;
        try {
            help3 = GUI_main.getFacade().items(); // pobranie danych produktow metoda items
        } catch (RemoteException ex) {
            Logger.getLogger(Produkty_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (help3 != null) {
            list_content(help3, lista_produktow);			           //wypelnianie listy typu JComboBox danymi produktow
}
    }

    private class RowListener implements ListSelectionListener {               

        @Override
        public void valueChanged(ListSelectionEvent event) {                   
            if (event.getValueIsAdjusting()) {                                 
                return;
            }
            print_produkty();                                                   
        }
    }

    class MyTableModel extends AbstractTableModel {                            

        private final String[] columnNames = {"Id produktu", "Nazwa",
            "Cena", "Promocja", "Cena brutto", "Data", "Producent"};                     
        private ArrayList<ArrayList<String>> data;                            

        public void setData(ArrayList<ArrayList<String>> val) {               
            data = val;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;                                       
        }

        @Override
        public int getRowCount() {
            return data.size();                                              
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row).get(col);                                     
        }

        @Override                                                          
        public String getColumnName(int col) {                                
            return columnNames[col];
        }
    }
}
