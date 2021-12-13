/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChitietphieunhapDTO;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class ChitietphieunhapDAO {
    String host = "localhost";
    String user = "root";
    String password = "";
    String db = "phonestorage";   
    
    MyConnection MyConn;
    
    public ChitietphieunhapDAO() throws Exception{
            MyConn = new MyConnection(host,user,password,db);
    }

public ArrayList docDSCTPN() throws Exception{
    ArrayList dsctpn = new ArrayList<>();

    String qry = "Select * From chitietphieunhap";
    ResultSet rs = MyConn.excuteQuery(qry);

    while(rs.next()){
        ChitietphieunhapDTO pn = new ChitietphieunhapDTO();
        pn.setMaPN(rs.getString(1));
        pn.setMaSP(rs.getString(2));
        pn.setSoLuong(rs.getInt(3));
        pn.setDonGia(rs.getInt(4));
        pn.setThanhTien(rs.getInt(5));
        dsctpn.add(pn);
    }
        return dsctpn;
}

public void them (ChitietphieunhapDTO ctpn) throws Exception{
    String qry = "Insert Into chitietphieunhap Values('";
    qry += ctpn.getMaPN() + "','";
    qry += ctpn.getMaSP() + "','";
    qry += ctpn.getSoLuong() + "','";
    qry += BigInteger.valueOf(ctpn.getDonGia()) + "','";
    qry += BigInteger.valueOf(ctpn.getThanhTien()) + "');";
    
    MyConn.excuteUpdate(qry);
}

public void capnhapSLSanPham(ChitietphieunhapDTO ctpn) throws Exception{
    String qry = "Update sanpham SET ";
    qry += "soluong = soluong + '" + ctpn.getSoLuong() + "'";
    qry += " where masp = '" + ctpn.getMaSP() + "'";
    MyConn.excuteUpdate(qry);
}

public void xoa (ChitietphieunhapDTO ctpn) throws Exception{
        String qry = "Delete from chitietphieunhap where maphieunhap ='" + ctpn.getMaPN() +"' AND masanpham ='" + ctpn.getMaSP() + "'";
        MyConn.excuteUpdate(qry);
}

public void xoa (String mapn) throws Exception{
        String qry = "Delete from chitietphieunhap where maphieunhap ='" + mapn + "'";
        MyConn.excuteUpdate(qry);
}

public void sua (ChitietphieunhapDTO ctpn) throws Exception{
    String qry = "Update chitietphieunhap SET ";
    qry += "soluongnhap = '" + ctpn.getSoLuong() + "',";
    qry += "thanhtien = '" + ctpn.getThanhTien()+ "'";
    qry += " where maphieunhap ='" + ctpn.getMaPN() + "' AND masanpham = '" + ctpn.getMaSP() +"'";
    
    MyConn.excuteUpdate(qry);
}
}