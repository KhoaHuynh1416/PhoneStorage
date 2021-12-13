/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

/**
 *
 * @author PC
 */
public class SanphamBUS {
    private static ArrayList<SanPhamDTO> DSSanPham;

    public static ArrayList<SanPhamDTO> getDSSanPham() {
        return DSSanPham;
    }

    public static void setDSSanPham(ArrayList<SanPhamDTO> DSSanPham) {
        SanphamBUS.DSSanPham = DSSanPham;
    }    
    
    public SanphamBUS(){ DSSanPham = null; }
    
    public void docDSSP() throws Exception{
        SanPhamDAO Data = new SanPhamDAO();
        if(DSSanPham == null) {
            DSSanPham = new ArrayList<>();
        }
    
        DSSanPham = Data.docDSSP();
    }
    
    public void them (SanPhamDTO sp) throws Exception{
        SanPhamDAO Data = new SanPhamDAO();
        Data.them(sp);
        DSSanPham = Data.docDSSP();
    }
    
    public void xoa (int vitri) throws Exception{
        SanPhamDAO Data = new SanPhamDAO();
        DSSanPham = Data.docDSSP();
        SanPhamDTO sp = DSSanPham.get(vitri);
        
        Data.xoa(sp.getMaSP());
        DSSanPham = Data.docDSSP();
    }
    
    public void sua (SanPhamDTO sp) throws Exception{
        SanPhamDAO Data = new SanPhamDAO();
        
        Data.sua(sp);
        DSSanPham = Data.docDSSP();
    }
    
    public ArrayList<SanPhamDTO> timKiemTheoMaTenSP(String key) throws Exception{
        SanPhamDAO Data = new SanPhamDAO();
        ArrayList<SanPhamDTO> Result = new ArrayList<>();

        DSSanPham = Data.docDSSP();
        
        key = key.trim();
        key = key.replaceAll("\\s+"," ");
        key = key.toLowerCase();
        
        for(SanPhamDTO sp : DSSanPham){
            if(sp.getMaSP().toLowerCase().contains(key) || sp.getTenSP().toLowerCase().contains(key))
                Result.add(sp);                
        }
        return Result;
    }
    
    public ArrayList<SanPhamDTO> timkiemTheoHangLoai(String key) throws Exception{
        SanPhamDAO Data = new SanPhamDAO();
        ArrayList<SanPhamDTO> Result = new ArrayList<>();

        DSSanPham = Data.docDSSP();
        
        if("Apple".equals(key)){        
            for(SanPhamDTO sp : DSSanPham){
                if(sp.getMaLoai().equals("loai01"))
                    Result.add(sp);                
            }
        }
        else 
            if("Samsung".equals(key)){        
                for(SanPhamDTO sp : DSSanPham){
                    if(sp.getMaLoai().equals("loai03"))
                        Result.add(sp);                
                }
            }
                else 
            if("Vsmart".equals(key)){        
                for(SanPhamDTO sp : DSSanPham){
                    if(sp.getMaLoai().equals("loai09"))
                        Result.add(sp);                
                }
            }
                else 
            if("Xiaomi".equals(key)){        
                for(SanPhamDTO sp : DSSanPham){
                    if(sp.getMaLoai().equals("loai05"))
                        Result.add(sp);                
                }
            }
                else 
            if("OPPO".equals(key)){        
                for(SanPhamDTO sp : DSSanPham){
                    if(sp.getMaLoai().equals("loai04") || sp.getMaLoai().equals("loai10"))
                        Result.add(sp);                
                }
            }
                else 
            if("Nokia".equals(key)){        
                for(SanPhamDTO sp : DSSanPham){
                    if(sp.getMaLoai().equals("loai02"))
                        Result.add(sp);                
                }
            }
                else 
            if("Khác".equals(key)){        
                for(SanPhamDTO sp : DSSanPham){
                    if(!sp.getMaLoai().equals("loai01") && !sp.getMaLoai().equals("loai02") && !sp.getMaLoai().equals("loai03") && !sp.getMaLoai().equals("loai04") && !sp.getMaLoai().equals("loai05") && !sp.getMaLoai().equals("loai09") && !sp.getMaLoai().equals("loai10"))
                        Result.add(sp);                
                }
            }
                else 
            if("Smartphone".equals(key)){        
                for(SanPhamDTO sp : DSSanPham){
                    if(sp.getMaLoai().equals("loai01"))
                        Result.add(sp);                
                }
            }
                else 
            if("Sạc".equals(key)){        
                for(SanPhamDTO sp : DSSanPham){
                    if(sp.getMaLoai().equals("loai02") || sp.getMaLoai().equals("loai04"))
                        Result.add(sp);                
                }
            }
                else 
            if("Tai nghe".equals(key)){        
                for(SanPhamDTO sp : DSSanPham){
                    if(sp.getMaLoai().equals("loai03"))
                        Result.add(sp);                
                }
            }  
        return Result;
    }
    
    public ArrayList<SanPhamDTO> timKiemTheoGiaSP(int lower, int higher, ArrayList<SanPhamDTO> Result) throws Exception{
        if(Result.isEmpty()){
        SanPhamDAO Data = new SanPhamDAO();
        DSSanPham = Data.docDSSP();
        for(SanPhamDTO sp : DSSanPham){
            if(higher != 0){
                if(lower < sp.getDonGia() && higher > sp.getDonGia())
                    Result.add(sp);           
            }
            else if(higher == 0){
                if(lower < sp.getDonGia())
                    Result.add(sp);
            }
        }        
        }
        else {
            ArrayList<SanPhamDTO> Temp = new ArrayList<>();
            Temp.addAll(Result);
            
            for(SanPhamDTO sp : Temp){
                if(higher != 0){
                    if(lower > sp.getDonGia() || higher < sp.getDonGia())
                        Result.remove(sp);           
                }
                else if(higher == 0){
                    if(lower > sp.getDonGia())
                        Result.remove(sp);
                }
            }
        }
        return Result;
    }
    
    public SanPhamDTO timkiemSPTheoMaSP(String maSP) throws Exception{
        SanPhamDTO KetQua = new SanPhamDTO();
        SanPhamDAO Data = new SanPhamDAO();
        
        DSSanPham = Data.docDSSP();
        
        for(SanPhamDTO sp : DSSanPham){
            if(maSP.equals(sp.getMaSP()))
                KetQua = sp;                
        }
        return KetQua;
    }
    
    public String taoMaSP() throws Exception{
        
        SanPhamDAO Data = new SanPhamDAO();
        DSSanPham = Data.docDSSP();
        
        if(DSSanPham.isEmpty()){
            return "sp001";
        }
        
        String MaSPCuoi = DSSanPham.get(DSSanPham.size()-1).getMaSP();
        
        String KeyString = "";
        int vitri = 0;
        
        while(vitri < MaSPCuoi.length()){
            if(MaSPCuoi.charAt(vitri) != 's' && MaSPCuoi.charAt(vitri) != 'p'){
                KeyString += MaSPCuoi.charAt(vitri);
            }
            vitri++;
        }
        
        int KeyCode = parseInt(KeyString);
        KeyCode++;
        
        if(KeyCode >= 100){
            KeyString = "sp" + KeyCode;
        }
        else 
            if(KeyCode >=10){
            KeyString = "sp0" + KeyCode;
        }
            else{
                KeyString = "sp00" + KeyCode;
            }
        
        return KeyString;
    }
    
    public boolean validString(String Ten){
        for(int i = 0; i < Ten.length(); i++){
            if(Ten.charAt(i) == (int)Ten.charAt(i)){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean validName(String name) throws Exception{
        SanPhamDAO Data = new SanPhamDAO();
        DSSanPham = Data.docDSSP();
        
        name = name.trim();
        
        for(SanPhamDTO sp : DSSanPham){
            if(sp.getTenSP().equals(name)){
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
    
    public SanPhamDTO getSanphamByMaSP(String maSP) throws Exception{
        SanPhamDAO Data = new SanPhamDAO();
        
        return Data.getSanphamByMaSP(maSP);
    }
    
    
}
