/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.PhieunhapDAO1;
import DTO.PhieunhapDTO;
import java.util.List;

/**
 *
 * @author pc
 */
public class PhieuNhapBUS1 {
    private PhieunhapDAO1 phieunhapDAO1;
    
    public PhieuNhapBUS1(){
        phieunhapDAO1 = new PhieunhapDAO1();
    }
        
    public List<PhieunhapDTO> getAllPhieuNhap() {
        return phieunhapDAO1.getAllPhieuNhap();
    }
}
