/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.util.Vector;

/**
 *
 * @author ADmin
 */
public class Test {
    
    public static void main(String[] args) {
        ChinhanhBUS BUS = new ChinhanhBUS();
        Vector<Vector> result = BUS.getChiNhanhByMaChiNhanh("KH009");
        for (Vector vector : result) {
            BUS.toChiNhanhDTO(vector).display();
        }
    }
    
}
