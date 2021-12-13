/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import DAO.ChitietphieunhapDAO;
import DTO.ChitietphieunhapDTO;
/**
 *
 * @author PC
 */
public class ChitietphieunhapBUS {
    private static ArrayList<ChitietphieunhapDTO> DSCTPhieuNhap;

    public static ArrayList<ChitietphieunhapDTO> getDSCTPhieuNhap() {
        return DSCTPhieuNhap;
    }

    public static void setDSCTPhieuNhap(ArrayList<ChitietphieunhapDTO> DSCTPhieuNhap) {
        ChitietphieunhapBUS.DSCTPhieuNhap = DSCTPhieuNhap;
    }    
    
    public ChitietphieunhapBUS(){ DSCTPhieuNhap = null; }
    
    public void docDSCTPN() throws Exception{
        ChitietphieunhapDAO Data = new ChitietphieunhapDAO();
        if(DSCTPhieuNhap == null) {
            DSCTPhieuNhap = new ArrayList<>();
        }
    
        DSCTPhieuNhap = Data.docDSCTPN();
    }
    
    public void them (ChitietphieunhapDTO ctpn) throws Exception{
        ChitietphieunhapDAO Data = new ChitietphieunhapDAO();
        Data.them(ctpn);
        Data.capnhapSLSanPham(ctpn);
        DSCTPhieuNhap = Data.docDSCTPN();
    }
    
    public void xoa (int vitri) throws Exception{
        ChitietphieunhapDAO Data = new ChitietphieunhapDAO();
        DSCTPhieuNhap = Data.docDSCTPN();
        ChitietphieunhapDTO ctpn = DSCTPhieuNhap.get(vitri);
        
        Data.xoa(ctpn);
        DSCTPhieuNhap = Data.docDSCTPN();
    }
    
    public void xoa (String mapn) throws Exception{
        ChitietphieunhapDAO Data = new ChitietphieunhapDAO();
        Data.xoa(mapn);
        DSCTPhieuNhap = Data.docDSCTPN();
    }    

    public void xoa (ChitietphieunhapDTO chitiet) throws Exception{
        ChitietphieunhapDAO Data = new ChitietphieunhapDAO();
        Data.xoa(chitiet);
        DSCTPhieuNhap = Data.docDSCTPN();
    }
    
    public void sua (ChitietphieunhapDTO ctpn) throws Exception{
        ChitietphieunhapDAO Data = new ChitietphieunhapDAO();
        
        Data.sua(ctpn);
        DSCTPhieuNhap = Data.docDSCTPN();
    }
    
    public ArrayList<ChitietphieunhapDTO> timKiemTheoMaPNMaSP(String key) throws Exception{
        ChitietphieunhapDAO Data = new ChitietphieunhapDAO();
        ArrayList<ChitietphieunhapDTO> Result = new ArrayList<>();

        DSCTPhieuNhap = Data.docDSCTPN();
        
        for(ChitietphieunhapDTO ctpn : DSCTPhieuNhap){
            if(ctpn.getMaPN().contains(key) || ctpn.getMaPN().contains(key))
                Result.add(ctpn);                
        }
        return Result;
    }
    
    public boolean validString(String Ten){
        for(int i = 0; i < Ten.length(); i++){
            if(Ten.charAt(i) == (int)Ten.charAt(i)){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean validNumber(String Num){
        for(int i = 0; i < Num.length(); i++){
            if(Num.charAt(i) < '0' || Num.charAt(i) > '9'){
                return false;
            }
        }
        
        return parseInt(Num) > 0;        
    }
}
