package interfejs_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dominik Wysota
 */
public interface Interfejs_RMI extends Remote {
    public void utworz_produkt(String dane[], Date data)throws RemoteException;
    public String[] dane_produktu()throws RemoteException;
    public ArrayList<ArrayList<String>> items()throws  RemoteException;
    
}
