package obiekt_rmi;

/**
 *
 * @author Dominik  Wysota
 */
import interfejs_rmi.Interfejs_RMI;
import java.util.ArrayList;
import java.util.Date;
import warstwa_biznesowa.Fasada_warstwy_biznesowej;


public class Obiekt_RMI implements Interfejs_RMI {
    
    private final Fasada_warstwy_biznesowej fasada = new Fasada_warstwy_biznesowej();
    
    @Override
    public void utworz_produkt(String dane[], Date data){
        fasada.utworz_produkt(dane, data);
    }
    
    @Override
    public String[] dane_produktu(){
        return fasada.dane_produktu();
    }
    
    @Override
    public ArrayList<ArrayList<String>> items(){
        return fasada.items();
    }
}
