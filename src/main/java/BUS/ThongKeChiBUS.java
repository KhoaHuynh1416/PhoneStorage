/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChitietphieunhapDAO;
import DAO.PhieunhapDAO;
import DAO.SanPhamDAO;
import DTO.ChitietphieunhapDTO;
import DTO.PhieunhapDTO;
import DTO.SanPhamDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class ThongKeChiBUS {
    
    public ArrayList<PhieunhapDTO> thongkeDateFromTo (Date from, Date to) throws Exception{
        PhieunhapDAO Data = new PhieunhapDAO();
        
        ArrayList<PhieunhapDTO> DSPhieuNhap = Data.docDSPN();
        ArrayList<PhieunhapDTO> DSThongKe = new ArrayList<>();
        
        for(PhieunhapDTO pn : DSPhieuNhap){
            if(pn.getNgayNhap().before(to) && pn.getNgayNhap().after(from)){
                DSThongKe.add(pn);
            }
        }
        return DSThongKe;
    } 
    
    public int[][] thongkeQuy() throws Exception{
        int[][] DSThongKe = {{1,0},{2,0},{3,0},{4,0}};
        Date DateFrom = new Date();
        Date DateTo = new Date();
        SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        ArrayList<PhieunhapDTO> temp = new ArrayList<>();
        
        for(int i=1; i<=4; i++){
        int Tong = 0;
        
        switch (i){
                case 1: 
                    DateFrom = DateFormat.parse("01-01-2021");
                    DateTo = DateFormat.parse("31-03-2021");
                    break;
                case 2: 
                    DateFrom = DateFormat.parse("01-04-2021");
                    DateTo = DateFormat.parse("30-06-2021");
                    break;                    
                case 3: 
                    DateFrom = DateFormat.parse("01-07-2021");
                    DateTo = DateFormat.parse("30-09-2021");
                    break;            
                case 4: 
                    DateFrom = DateFormat.parse("01-10-2021");
                    DateTo = DateFormat.parse("31-12-2021");
                    break;
                default: 
                    break;            
            }
        temp.addAll(thongkeDateFromTo(DateFrom,DateTo));
        
            if(temp.isEmpty()){
                continue;
            }
            else{
                for(PhieunhapDTO pn : temp){
                    Tong += pn.getTongTien();
                }

                DSThongKe[i-1][1] += Tong;
            }
        }
        return DSThongKe;
    }
    
    public ArrayList<SanPhamDTO> thongkeSP() throws Exception{
        ArrayList<SanPhamDTO> DSThongKe = new ArrayList<>();
        ArrayList<SanPhamDTO> DSSanPham = new ArrayList<>();
        ArrayList<ChitietphieunhapDTO> DSChiTiet = new ArrayList<>();
        SanPhamDAO DataSP = new SanPhamDAO();
        ChitietphieunhapDAO DataCT = new ChitietphieunhapDAO();
        DSSanPham = DataSP.docDSSP();
        DSChiTiet = DataCT.docDSCTPN();
        
        for(SanPhamDTO sp : DSSanPham){
            sp.setSoLuong(0);
            for(ChitietphieunhapDTO ctpn : DSChiTiet){
                if(ctpn.getMaSP().equals(sp.getMaSP()))
                    sp.setSoLuong(sp.getSoLuong()+ctpn.getSoLuong());
            }
            if(sp.getSoLuong()>0){
                DSThongKe.add(sp);
            }
        }
                
        return DSThongKe;
    }
    
}
