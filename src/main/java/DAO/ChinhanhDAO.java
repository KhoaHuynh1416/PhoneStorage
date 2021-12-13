/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChinhanhDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ChinhanhDAO {
    
    private Connection connection = null;
    
    public ChinhanhDAO() {
        connection = (Connection) MyJDBCConnection.getConnection();
    }
    
    public ArrayList<ChinhanhDTO> getDanhSachChiNhanh() {
        ArrayList<ChinhanhDTO> danhSachChiNhanh = new ArrayList<>();
        String query = "SELECT * FROM ChiNhanh";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                danhSachChiNhanh.add(toChiNhanhDTO(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChinhanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return danhSachChiNhanh;
    }
    
    public ChinhanhDTO getChiNhanhByMaChiNhanh(String maChiNhanh) {
        ChinhanhDTO ChiNhanhDTO = null;
        String query = "SELECT * FROM ChiNhanh WHERE maChiNhanh = ?";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, maChiNhanh);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ChiNhanhDTO = toChiNhanhDTO(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChinhanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ChiNhanhDTO;
    }
    
    public void addChiNhanh(ChinhanhDTO ChiNhanhMoi) {
        String query = "INSERT INTO ChiNhanh VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            
            preparedStatement.setString(1, ChiNhanhMoi.getMaChiNhanh());
            preparedStatement.setString(2, ChiNhanhMoi.getTenChiNhanh());
            preparedStatement.setString(3, ChiNhanhMoi.getDiaChi());
            preparedStatement.setString(4, ChiNhanhMoi.getEmail());
            preparedStatement.setString(5, ChiNhanhMoi.getSDT());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChinhanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteChiNhanh(String maChiNhanh) {
        String query = "DELETE FROM ChiNhanh WHERE maChiNhanh = ?";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            
            preparedStatement.setString(1, maChiNhanh);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChinhanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void upadateChiNhanh(ChinhanhDTO ChiNhanhMoi) {
        String query = "UPDATE ChiNhanh set TenChiNhanh = ?, DiaChi = ?, Email = ?, SDT = ? WHERE maChiNhanh = ?";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            
            preparedStatement.setString(1, ChiNhanhMoi.getTenChiNhanh());
            preparedStatement.setString(2, ChiNhanhMoi.getDiaChi());
            preparedStatement.setString(3, ChiNhanhMoi.getEmail());
            preparedStatement.setString(4, ChiNhanhMoi.getSDT());
            preparedStatement.setString(5, ChiNhanhMoi.getMaChiNhanh());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChinhanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ChinhanhDTO toChiNhanhDTO(ResultSet resultSet) {
        ChinhanhDTO ChiNhanhDTO = null;
        try {
            String maChiNhanh = resultSet.getString("maChiNhanh");
            String tenChiNhanh = resultSet.getString("TenChiNhanh");
            String diaChi = resultSet.getString("DiaChi");
            String email = resultSet.getString("Email");
            String SDT = resultSet.getString("SDT");
            ChiNhanhDTO = new ChinhanhDTO(maChiNhanh, tenChiNhanh, diaChi, email, SDT);
        } catch (SQLException ex) {
            Logger.getLogger(ChinhanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ChiNhanhDTO;
    }

    public List<ChinhanhDTO> getAllChiNhanh() {
        List<ChinhanhDTO> ChiNhanhs = new ArrayList<>();
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM ChiNhanh";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                ChinhanhDTO ChiNhanh = new ChinhanhDTO();
                
                ChiNhanh.setMaChiNhanh(rs.getString("MAChiNhanh"));
                ChiNhanh.setTenChiNhanh(rs.getString("TENChiNhanh"));
                ChiNhanh.setDiaChi(rs.getString("DIACHI"));
                ChiNhanh.setSDT(rs.getString("EMAIL"));              
                ChiNhanh.setEmail(rs.getString("SDT"));
                
                ChiNhanhs.add(ChiNhanh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ChiNhanhs;
    }

    
}
