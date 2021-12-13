
package DAO;

import DTO.TaikhoanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xuannguyen
 */
public class TaikhoanDAO {
    public List<TaikhoanDTO> getAllTaikhoans() {
        List<TaikhoanDTO> taikhoans = new ArrayList<>();
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM TAIKHOAN";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                TaikhoanDTO taikhoan = new TaikhoanDTO();
                
                taikhoan.setMaTK(rs.getString("MATAIKHOAN"));
                taikhoan.setPassword(rs.getString("PASSWORD"));
                taikhoan.setRole(rs.getString("ROLE"));
                taikhoans.add(taikhoan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return taikhoans;
    }
    
    public TaikhoanDTO getTaiKhoanByMaTK(String maTK) {
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM TAIKHOAN WHERE MATAIKHOAN = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maTK);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                TaikhoanDTO taikhoan = new TaikhoanDTO();
                
                taikhoan.setMaTK(rs.getString("MATAIKHOAN"));
                taikhoan.setPassword(rs.getString("PASSWORD"));
                taikhoan.setRole(rs.getString("ROLE"));
                
                return taikhoan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public void addTaikhoan(TaikhoanDTO taikhoan){
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "INSERT INTO TAIKHOAN (MATAIKHOAN, PASSWORD, ROLE) VALUES (?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taikhoan.getMaTK());
            preparedStatement.setString(2, taikhoan.getPassword());
            preparedStatement.setString(3, taikhoan.getRole());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
        
    public void deleteTaikhoan(String maTK){
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "DELETE FROM TAIKHOAN WHERE MATAIKHOAN = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maTK);
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateTaikhoan(TaikhoanDTO taikhoan){
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "UPDATE TAIKHOAN SET PASSWORD = ?, ROLE = ? WHERE MATAIKHOAN = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);       
            preparedStatement.setString(1, taikhoan.getPassword()); 
            preparedStatement.setString(2, taikhoan.getRole());  
            preparedStatement.setString(3, taikhoan.getMaTK());        

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }    
}


