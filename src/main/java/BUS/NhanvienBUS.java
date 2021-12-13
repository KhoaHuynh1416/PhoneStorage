/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DAO.NhanvienDAO;
import DTO.NhanvienDTO;
import java.util.ArrayList;
/**
 *
 * @author pc
 */
public class NhanvienBUS {
    public ArrayList<NhanvienDTO> showAll(){
        return NhanvienDAO.showAll();
    }
    public void addnv(NhanvienDTO nv){
        NhanvienDAO.addnv(nv);
    }
    public void editnv(NhanvienDTO nv){
        NhanvienDAO.editnv(nv);
    }
    public void delnv(String manv){
        NhanvienDAO.delnv(manv);
    }
    public ArrayList<NhanvienDTO> findnv(String attri,String manv){
        return NhanvienDAO.findnv(attri,manv);
    }
    public ArrayList<NhanvienDTO> findluong(String luong){
        return NhanvienDAO.findluong(luong);
    }
}
