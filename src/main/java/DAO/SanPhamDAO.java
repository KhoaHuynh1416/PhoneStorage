/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import DTO.SanPhamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class SanPhamDAO {
    String host = "localhost";
    String user = "root";
    String password = "";
    String db = "phonestorage";   
    
    MyConnection MyConn;
    
    public SanPhamDAO() throws Exception{
            MyConn = new MyConnection(host,user,password,db);
    }

public ArrayList docDSSP() throws Exception{
    ArrayList dssp = new ArrayList<>();

    String qry = "CALL getSanphamAll()";
    ResultSet rs = MyConn.excuteQuery(qry);

    while(rs.next()){
        SanPhamDTO sp = new SanPhamDTO();
        sp.setMaSP(rs.getString(1));
        sp.setTenSP(rs.getString(2));
        sp.setSoLuong(rs.getInt(3));
        sp.setDonGia(rs.getFloat(4));
        sp.setMaLoai(rs.getString(5));
        dssp.add(sp);
    }
        return dssp;
}

public void them (SanPhamDTO sp) throws Exception{
    String qry = "Insert Into sanpham Values('";
    qry += sp.getMaSP() + "','";
    qry += sp.getTenSP() +"','";
    qry += sp.getSoLuong() + "','";
    qry += sp.getDonGia() + "','";
    qry += sp.getMaLoai() + "')";
    
    MyConn.excuteUpdate(qry);
}

public void xoa (String masp) throws Exception{
        String qry = "Delete from sanpham where masp ='" + masp +"'";
        MyConn.excuteUpdate(qry);
}

public void sua (SanPhamDTO sp) throws Exception{
    String qry = "Update sanpham SET ";
    qry += "tensp = '" + sp.getTenSP() +"',";
    qry += "soluong = '" + sp.getSoLuong() + "',";
    qry += "dongia = '" + sp.getDonGia() + "',";
    qry += "maloai = '" + sp.getMaLoai() + "' where masp ='" + sp.getMaSP() + "'";;
    
    
    MyConn.excuteUpdate(qry);
}

public SanPhamDTO getSanphamByMaSP(String maSP) throws Exception {
        
    String qry = "Select * From sanpham where MaSP ='" + maSP + "'";
    ResultSet rs = MyConn.excuteQuery(qry);        
                    
        try {
            while(rs.next()){
                SanPhamDTO sanpham = new SanPhamDTO();
                
                sanpham.setMaSP(rs.getString("MASP"));
                sanpham.setTenSP(rs.getString("TENSP"));
                sanpham.setSoLuong(rs.getInt("SOLUONG"));
                sanpham.setMaLoai(rs.getString("MALOAI"));              
                sanpham.setDonGia(rs.getFloat("DONGIA"));
                
                return sanpham;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public SanPhamDTO getSanphamBYMaSP(String maSP) throws Exception {
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "CALL getSanphamByMaSP(?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maSP);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                SanPhamDTO sanpham = new SanPhamDTO();
                
                sanpham.setMaSP(rs.getString("MASP"));
                sanpham.setTenSP(rs.getString("TENSP"));
                sanpham.setSoLuong(rs.getInt("SOLUONG"));
                sanpham.setMaLoai(rs.getString("MALOAI"));              
                sanpham.setDonGia(rs.getFloat("DONGIA"));
                
                return sanpham;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    public List<SanPhamDTO> getAllSanpham() throws Exception {
        List<SanPhamDTO> sanphams = new ArrayList<>();
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM SANPHAM";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                SanPhamDTO sanpham = new SanPhamDTO();
                
                sanpham.setMaSP(rs.getString("MASP"));
                sanpham.setTenSP(rs.getString("TENSP"));
                sanpham.setMaLoai(rs.getString("MALOAI"));
                sanpham.setDonGia(rs.getFloat("DONGIA"));              
                sanpham.setSoLuong(rs.getInt("SOLUONG"));
                
                sanphams.add(sanpham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sanphams;
    }
    
    public List<String> getMaSanpham() {
        List<String> sanphams = new ArrayList<>();
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT MASP FROM SANPHAM";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                sanphams.add(rs.getString(sql));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sanphams;
    }
    
    public void updateSoluongSP(SanPhamDTO sanpham){
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "UPDATE SANPHAM SET SOLUONG = ? WHERE MASP = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);       
            preparedStatement.setInt(1, sanpham.getSoLuong());
            preparedStatement.setString(2, sanpham.getMaSP());           

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
}
