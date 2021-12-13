/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PhieunhapDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class PhieunhapDAO1 {
    public List<PhieunhapDTO> getAllPhieuNhap() {
        List<PhieunhapDTO> phieunhaps = new ArrayList<>();
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM PHIEUNHAP";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                PhieunhapDTO phieunhap = new PhieunhapDTO();
                
                phieunhap.setMaPN(rs.getString("MAPHIEUNHAP"));
                phieunhap.setNgayNhap(rs.getDate("NGAYNHAP"));
                phieunhap.setMaNCC(rs.getString("MANCC"));
                phieunhap.setMaNV(rs.getString("MANHANVIEN"));              
                phieunhap.setTongTien(rs.getFloat("TONGTIEN"));
                
                phieunhaps.add(phieunhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return phieunhaps;
    }
}
