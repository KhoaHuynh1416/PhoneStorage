
package DAO;

import DTO.ChitietphieuxuatDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author xuannguyen
 */
public class ChitietphieuxuatDAO {
    public void addChitietPhieuxuat(ChitietphieuxuatDTO chitietPX){
        Connection connection = JDBCConnection.getConnection();
        String sql = "INSERT INTO CHITIETPHIEUXUAT (MASP, MAPX, SOLUONG, DONGIA, THANHTIEN) VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, chitietPX.getMasp());
            preparedStatement.setString(2, chitietPX.getMapx());
            preparedStatement.setInt(3, chitietPX.getSoluongmua());
            preparedStatement.setFloat(4, chitietPX.getDongia());
            preparedStatement.setFloat(5, chitietPX.getThanhtien());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public List<ChitietphieuxuatDTO> getCTPXByMaPX(String maPX) {
        List<ChitietphieuxuatDTO> chitietPXs = new ArrayList<>();
        
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "SELECT * FROM CHITIETPHIEUXUAT WHERE MAPX = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maPX);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                ChitietphieuxuatDTO chitietPX = new ChitietphieuxuatDTO();
                
                chitietPX.setMapx(rs.getString("MAPX"));
                chitietPX.setMasp(rs.getString("MASP"));
                chitietPX.setSoluongmua(rs.getInt("SOLUONG"));
                chitietPX.setThanhtien(rs.getFloat("THANHTIEN"));              
                chitietPX.setDongia(rs.getFloat("DONGIA"));
                
                chitietPXs.add(chitietPX);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return chitietPXs;
    }
    
    public void deleteCTPX(String maPX){
        Connection connection = JDBCConnection.getConnection();
        
        String sql = "DELETE FROM CHITIETPHIEUXUAT WHERE MAPX = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maPX);
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
