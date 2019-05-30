package sklep_gui;

import interfejs_rmi.Interfejs_RMI;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
//import warstwa_biznesowa.Fasada_warstwy_biznesowej;

/**
 *
 * @author Dominik Wysota 6377
 */
public class GUI_main implements ActionListener {

    static JPanel cards;       

    static CardLayout cl;


    static Pusty_form card0 = new Pusty_form();         
    static Produkt_form card1 = new Produkt_form();    
    static Produkty_form card2 = new Produkty_form();

    final static String PUSTY = "Pusty";
    final static String PRODUKT = "Produkt form";
    final static String PRODUKTY = "Produkty form";

   // static Fasada_warstwy_biznesowej fasada = new Fasada_warstwy_biznesowej();
    static Interfejs_RMI facade;

//    static public Fasada_warstwy_biznesowej getFacade() {
//        return fasada;
//    }
    static public Interfejs_RMI getFacade(){
        return facade;
    }
    
    static public void RMI(){
        try{
            if(System.getSecurityManager() == null){
           System.setSecurityManager(new SecurityManager());
       }
            Registry registry = LocateRegistry.createRegistry(5002);
            facade = (Interfejs_RMI) registry.lookup("RMI_Sklep");          
        } catch (NotBoundException | RemoteException e){
            System.out.println("Wyjatek klienta 2: " + e);
        }
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();

        menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        menuItem = new JMenuItem(PRODUKT, KeyEvent.VK_P);                                       //możliwosc wyboru opcji za pomocą klawiszy Alt-P
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));   //możliwosc wyboru opcji za pomocą klawiszy Alt-1
        menuItem.addActionListener(this);                                                       // dodanie obsługi zdarzenia kliknięcia na pozycję JMenuItem
        menu.add(menuItem);                                                                     //dodanie pozycji menu do obiektu typu JMenu

        menuItem = new JMenuItem(PRODUKTY);
        menuItem.setMnemonic(KeyEvent.VK_R);                                                    //możliwość wyboru opcji za pomocą klawiszy Alt-R – zamiast w konstruktorze JMenuItem
        menuItem.addActionListener(this);                                                       // dodanie obsługi zdarzenia kliknięcia na pozycję JMenuItem
        menu.add(menuItem);                                                                     //dodanie pozycji menu do obiektu typu JMenu

        menuItem = new JMenuItem(PUSTY);
        menuItem.setMnemonic(KeyEvent.VK_U);                                                    //mozliwosc wyboru opcji za pomocą klawiszy Alt-U
        menuItem.addActionListener(this);                                                       // dodanie obsługi zdarzenia kliknięcia na pozycję JMenuItem
        menu.add(menuItem);                                                                     //dodanie pozycji menu do obiektu typu JMenu menu.addSeparator();                                       
        
        submenu = new JMenu("A submenu");                                                       //wykonanie do podrzędnego obiektu submenu typu JMenuJMenu
        submenu.setMnemonic(KeyEvent.VK_A);                                                     //mozliwosc wyboru opcji za pomocą klawiszy Alt-A

        menuItem = new JMenuItem(PUSTY);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));   //mozliwosc wyboru opcji za pomocą klawiszy Alt-2
        menuItem.addActionListener(this);                                                       // dodanie obsługi zdarzenia kliknięcia na pozycję JMenuItem
        submenu.add(menuItem);                                                                  //dodanie pozycji menu do obiektu typu JMenu

        menuItem = new JMenuItem(PUSTY);
        menuItem.setMnemonic(KeyEvent.VK_S);                                                    //mozliwosc wyboru opcji za pomocą klawiszy Alt-S
        menuItem.addActionListener(this);                                                       // dodanie obsługi zdarzenia kliknięcia na pozycję JMenuItem
        submenu.add(menuItem);                                                                  //dodanie pozycji menu do obiektu typu JMenu
        
        menu.add(submenu);                                                                      //Dodanie do nadrzędnego obiektu typu JMenu(menu) podrzędnego obiektu submenu typu JMenu

        menu = new JMenu("Inne Menu");                                                          //dodanie nowego menu typu JMenu w obiekcie typu JMenuBar
        menu.setMnemonic(KeyEvent.VK_I);                                                        //mozliwosc wyboru opcji za pomocą klawiszy Alt-I
        menuBar.add(menu);                                                                      //dodanie pozycji menu do obiektu typu JMenuBar

        return menuBar;                                                                         //zwrocenie wykonanego komponentu typu JMenuBar
    }

    public Container createContentPane() {
        card1.init();
        card2.init();
        
        cards = new JPanel(new CardLayout());
        cards.add(card0, PUSTY);                        
        cards.add(card1, PRODUKT);                     
        cards.add(card2, PRODUKTY);                  
        
        JPanel p1 = new JPanel();                  
        p1.add(cards, BorderLayout.CENTER);          
        return p1;                  
    }

    public static void updateProdukty_form() {
        card2.table_content();     
        cl.show(cards, PRODUKTY); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        cl = (CardLayout) (cards.getLayout());
        switch (source.getText()) {
            case PRODUKT:
                cl.show(cards, PRODUKT);            
                break;
            case PRODUKTY:
                updateProdukty_form();   
                break;
            case PUSTY:
                cl.show(cards, PUSTY);   
                break;
            default:
                break;
        }
    }

    private static void createAndShowGUI() {  
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);
        GUI_main demo = new GUI_main();
        frame.setJMenuBar(demo.createMenuBar()); 
        frame.setContentPane(demo.createContentPane());
        frame.setVisible(true);  
    }                                                       
                                                                
    public static void main(String[] args) throws Exception {
        RMI();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
}
