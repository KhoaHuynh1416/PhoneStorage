/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.PhieuxuatDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author XuanNguyen
 */
public class PhieuxuatDAO {
    
    public List<PhieuxuatDTO> getAllHoaDon() {
        List<PhieuxuatDTO> phieuxuats = new ArrayList<>();
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM PHIEUXUAT";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                PhieuxuatDTO phieuxuat = new PhieuxuatDTO();
                
                phieuxuat.setMaPX(rs.getString("MAPHIEUXUAT"));
                phieuxuat.setNgayxuat(rs.getString("NGAYXUAT"));
                phieuxuat.setMaCN(rs.getString("MACHINHANH"));
                phieuxuat.setMaNV(rs.getString("MANHANVIEN"));              
                phieuxuat.setTongtien(rs.getDouble("TONGTIEN"));
                
                phieuxuats.add(phieuxuat);
            }
        } catch (SQLException e) {
        }
        
        return phieuxuats;
    }
    
    public PhieuxuatDTO getPhieuxuatByMaPhieuxuat(String mapx) {
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM PHIEUXUAT WHERE MAPHIEUXUAT = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mapx);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                PhieuxuatDTO phieuxuat = new PhieuxuatDTO();
                
                phieuxuat.setMaPX(rs.getString("MAPHIEUXUAT"));
                phieuxuat.setNgayxuat(rs.getString("NGAYXUAT"));
                phieuxuat.setMaCN(rs.getString("MACHINHANH"));
                phieuxuat.setMaNV(rs.getString("MANHANVIEN"));              
                phieuxuat.setTongtien(rs.getDouble("TONGTIEN"));
                
                return phieuxuat;
            }
        } catch (SQLException e) {
        }
        
        return null;
    }
    
    public List<PhieuxuatDTO> getPhieuxuatByMaCN(String maCN){
        List<PhieuxuatDTO> phieuxuats = new ArrayList<>();
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM PHIEUXUAT WHERE MACHINHANH = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maCN);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                PhieuxuatDTO phieuxuat = new PhieuxuatDTO();
                
                phieuxuat.setMaPX(rs.getString("MAPHIEUXUAT"));
                phieuxuat.setNgayxuat(rs.getString("NGAYXUAT"));
                phieuxuat.setMaCN(rs.getString("MACHINHANH"));
                phieuxuat.setMaNV(rs.getString("MANHANVIEN"));              
                phieuxuat.setTongtien(rs.getDouble("TONGTIEN"));
                
                phieuxuats.add(phieuxuat);
            }
        } catch (SQLException e) {
        }
        
        return phieuxuats;
    }
    
    public List<PhieuxuatDTO> getPhieuxuatByMaNV(String maNV){
        List<PhieuxuatDTO> phieuxuats = new ArrayList<>();
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM PHIEUXUAT WHERE MANHANVIEN = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maNV);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                PhieuxuatDTO phieuxuat = new PhieuxuatDTO();
                
                phieuxuat.setMaPX(rs.getString("MAPHIEUXUAT"));
                phieuxuat.setNgayxuat(rs.getString("NGAYXUAT"));
                phieuxuat.setMaCN(rs.getString("MACHINHANH"));
                phieuxuat.setMaNV(rs.getString("MANHANVIEN"));              
                phieuxuat.setTongtien(rs.getDouble("TONGTIEN"));
                
                phieuxuats.add(phieuxuat);
            }
        } catch (SQLException e) {
        }
        
        return phieuxuats;
    }

    
    public void insertPhieuxuat(PhieuxuatDTO phieuxuat){
        Connection connection = JDBCConnection.getConnection();
        String sql = "INSERT INTO PHIEUXUAT (MAPHIEUXUAT, NGAYXUAT, MACHINHANH, MANHANVIEN, TONGTIEN) VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phieuxuat.getMaPX());
            preparedStatement.setString(2, phieuxuat.getNgayxuat());
            preparedStatement.setString(3, phieuxuat.getMaCN());
            preparedStatement.setString(4, phieuxuat.getMaNV());
            preparedStatement.setDouble(5, phieuxuat.getTongtien());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
        }
        
    }
    
    public void deletePhieuxuat(String mapx){
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "DELETE FROM PHIEUXUAT WHERE MAPHIEUXUAT = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mapx);
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
        }
    }
    public List<PhieuxuatDTO> getPhieuxuatByDate(String tuNgay, String denNgay){
        List<PhieuxuatDTO> phieuxuats = new ArrayList<>();
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM PHIEUXUAT WHERE NGAYXUAT BETWEEN ? AND ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tuNgay);
            preparedStatement.setString(2, denNgay);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                PhieuxuatDTO phieuxuat = new PhieuxuatDTO();
                
                phieuxuat.setMaPX(rs.getString("MAPHIEUXUAT"));
                phieuxuat.setNgayxuat(rs.getString("NGAYXUAT"));
                phieuxuat.setMaCN(rs.getString("MACHINHANH"));
                phieuxuat.setMaNV(rs.getString("MANHANVIEN"));              
                phieuxuat.setTongtien(rs.getDouble("TONGTIEN"));
                
                phieuxuats.add(phieuxuat);
            }
        } catch (SQLException e) {
        }
        
        return phieuxuats;
    }

}



