/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Stack;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADmin
 */
public class MyTableModel {
    
    public DefaultTableModel setTable(String[] listColumn, Vector<Vector> data) {
        int numberOfColumn = listColumn.length;
        Vector header = new Vector();
        for (int i = 0; i < numberOfColumn; ++i) {
            header.add(listColumn[i]);
        }
        DefaultTableModel model = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        for (Vector row : data) {
            model.addRow(row);
        }
        return model;
    }
    
}
