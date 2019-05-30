/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomoc;

/**
 *
 * @author Dominik Wysota 6377
 */
public class Uslugi {

    public static float cena_brutto(float promocja, float cena) {
        return cena * (1 - promocja / 100);
    }

}
