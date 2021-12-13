/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhaCungCapDTO;
//import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author pc
 */
public class NhaCungCapDAO {
    private Connection connection = null;
    
    public NhaCungCapDAO() {
        connection = (Connection) JDBCConnection.getConnection();
    }
    
    public ArrayList<NhaCungCapDTO> getDanhSachNhaCungCap() {
        ArrayList<NhaCungCapDTO> danhSachNhaCungCap = null;
        
        String query = "SELECT * FROM nhacc";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            danhSachNhaCungCap = new ArrayList<NhaCungCapDTO>();
            while(resultSet.next()) {
                String maNCC = resultSet.getString("MaNCC");
                String tenNCC = resultSet.getString("TenNCC");
                String diaChi = resultSet.getString("DiaChi");
                String SDT = resultSet.getString("SDT");
                NhaCungCapDTO nhaCungCap = new NhaCungCapDTO(maNCC, tenNCC, diaChi, SDT);
                danhSachNhaCungCap.add(nhaCungCap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return danhSachNhaCungCap;
    }
    
        public void add(NhaCungCapDTO nhaCungCap) {
        String query = "INSERT INTO nhacc VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, nhaCungCap.getMaNCC());
            preparedStatement.setString(2, nhaCungCap.getTenNCC());
            preparedStatement.setString(3, nhaCungCap.getDiaChi());
            preparedStatement.setString(4, nhaCungCap.getSDT());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void deleteById(String maNCC) {
        String query = "DELETE FROM nhacc WHERE MaNCC = ?";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, maNCC);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void update(NhaCungCapDTO nhaCungCap) {
        String query = "UPDATE nhacc SET TenNCC = ?, DiaChi = ?, SDT = ? WHERE MaNCC = ?";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, nhaCungCap.getTenNCC());
            preparedStatement.setString(2, nhaCungCap.getDiaChi());
            preparedStatement.setString(3, nhaCungCap.getSDT());
            preparedStatement.setString(4, nhaCungCap.getMaNCC());
            
            int i = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public NhaCungCapDTO getNhaCungCapById(String maNCC) {
        NhaCungCapDTO nhaCungCap = null;
        String query = "SELECT * FROM nhacc WHERE MaNCC = ?";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, maNCC);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                String tenNCC = resultSet.getString("TenNCC");
                String diaChi = resultSet.getString("DiaChi");
                String SDT = resultSet.getString("SDT");
                nhaCungCap = new NhaCungCapDTO(maNCC, tenNCC, diaChi, SDT);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nhaCungCap;
    }
    
}
