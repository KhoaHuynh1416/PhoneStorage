/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChitietphieuxuatDAO;
import DTO.ChitietphieuxuatDTO;
import java.util.List;

/**
 *
 * @author pc
 */
public class ChitietphieuxuatBUS {
    private  ChitietphieuxuatDAO chitietphieuxuatDAO;
    
    public ChitietphieuxuatBUS(){
        chitietphieuxuatDAO = new ChitietphieuxuatDAO();
    }
        
    public void addChitietPhieuxuat(ChitietphieuxuatDTO chitietPX){
        chitietphieuxuatDAO.addChitietPhieuxuat(chitietPX);
    }
    
    public List<ChitietphieuxuatDTO> getCTPXByMaPX(String maPX){
        return chitietphieuxuatDAO.getCTPXByMaPX(maPX);
    }
    
    public void deleteCTPX(String maPX){
        chitietphieuxuatDAO.deleteCTPX(maPX);
    }
}
