/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaiSanPhamDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class LoaiSanPhamDAO {
    
    private Connection connection = null;
    
    public LoaiSanPhamDAO() {
        connection = (Connection) JDBCConnection.getConnection();
    }
    
    public ArrayList<LoaiSanPhamDTO> getDataFromDatabase() {
        ArrayList<LoaiSanPhamDTO> danhSachLoaiSanPham = new ArrayList<LoaiSanPhamDTO>();
        String query = "SELECT * FROM loaisanpham";
         try {
             PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
             while(resultSet.next()) {
                 String maLoai = resultSet.getString("MaLoai");
                 String tenLoai = resultSet.getString("TenLoai");
                 LoaiSanPhamDTO loaiSanPham = new LoaiSanPhamDTO(maLoai, tenLoai);
                 danhSachLoaiSanPham.add(loaiSanPham);
             }
         } catch (SQLException ex) {
             Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        return danhSachLoaiSanPham;
    }
    
    public void add(LoaiSanPhamDTO loaiSanPham) {
        String query = "INSERT INTO loaisanpham VALUES (?, ?)";
         try {
             PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
             preparedStatement.setString(1, loaiSanPham.getMaLoai());
             preparedStatement.setString(2, loaiSanPham.getTenLoai());
             preparedStatement.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void deleteById(String maLoai) {
        String query = "DELETE FROM loaisanpham WHERE MaLoai = ?";
         try {
             PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
             preparedStatement.setString(1, maLoai);
             
             preparedStatement.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    public LoaiSanPhamDTO searchById(String maLoai) {
        LoaiSanPhamDTO loaiSanPham = null;
        
        String query = "SELECT * FROM loaisanpham WHERE MaLoai = ?";
         try {
             PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
             preparedStatement.setString(1, maLoai);
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
                 String tenLoai = resultSet.getString("TenLoai");
                 loaiSanPham = new LoaiSanPhamDTO(maLoai, tenLoai);
             }
         } catch (SQLException ex) {
             Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return loaiSanPham;
    }
    
    public void update(LoaiSanPhamDTO loaiSanPham) {
        String query = "UPDATE loaisanpham SET TenLoai = ? WHERE MaLoai = ?";
         try {
             PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
             preparedStatement.setString(1, loaiSanPham.getTenLoai());
             preparedStatement.setString(2, loaiSanPham.getMaLoai());
             
             preparedStatement.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
}
