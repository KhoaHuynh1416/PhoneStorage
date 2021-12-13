/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.NhanvienDTO;
import DAO.JDBCConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author pc
 */
public class NhanvienDAO {
    public static ArrayList<NhanvienDTO> showAll(){
        ArrayList<NhanvienDTO> arr= new ArrayList<NhanvienDTO>();
        String sql="Select * from nhanvien";
        try{
            Connection act=JDBCConnection.getConnection();
            Statement stmt=act.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                NhanvienDTO nv=new NhanvienDTO();
                nv.setmanv(rs.getString("MaNhanVien"));
                nv.settennv(rs.getString("TenNhanVien"));
                nv.setaddress(rs.getString("DiaChi"));
                nv.setemail(rs.getString("Email"));
                nv.setsdt(rs.getString("SDT"));
                nv.setmatk(rs.getString("MaTaiKhoan"));
                nv.setluong(rs.getString("Luong"));
                arr.add(nv);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return arr;
    }
    public static void addnv(NhanvienDTO nv){
         Connection act=JDBCConnection.getConnection();
         String sql="INSERT INTO NHANVIEN (MANHANVIEN,TENNHANVIEN,DIACHI,EMAIL,SDT,MATAIKHOAN,LUONG) VALUES (?,?,?,?,?,?,?)";
         try{
             PreparedStatement ps=act.prepareStatement(sql);
             ps.setString(1, nv.getmanv());
             ps.setString(2, nv.gettennv());
             ps.setString(3, nv.getaddress());
             ps.setString(4, nv.getemail());
             ps.setString(5, nv.getsdt());
             ps.setString(6, nv.getmatk());
             ps.setString(7, nv.getluong());
             ps.executeUpdate();
         }
         catch(SQLException ex){
             ex.printStackTrace();
         }
    }
    public static void editnv(NhanvienDTO nv){
        Connection act=JDBCConnection.getConnection();
        String sql="Update Nhanvien Set TenNhanVien=?,DiaChi=?,Email=?,SDT=?,Luong=? Where MaNhanVien=?";
        try{
            PreparedStatement ps= act.prepareStatement(sql);
             ps.setString(1, nv.gettennv());
             ps.setString(2, nv.getaddress());
             ps.setString(3, nv.getemail());
             ps.setString(4, nv.getsdt());
             ps.setString(5, nv.getluong());
             ps.setString(6, nv.getmanv());
             ps.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }        
    }
    public static void delnv(String manv){
        try{
        Connection act=JDBCConnection.getConnection();
        String sql="Delete from Nhanvien where MaNhanVien=?";
        PreparedStatement ps= act.prepareStatement(sql);
        ps.setString(1,manv);
        ps.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public static ArrayList<NhanvienDTO> findnv(String attri,String manv){
        ArrayList<NhanvienDTO> arr= new ArrayList<NhanvienDTO>();
        String sql="Select * from nhanvien where "+attri+" like '%"+manv+"%'";
        try{
            Connection act=JDBCConnection.getConnection();
            Statement stmt=act.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                NhanvienDTO nv=new NhanvienDTO();
                nv.setmanv(rs.getString("MaNhanVien"));
                nv.settennv(rs.getString("TenNhanVien"));
                nv.setaddress(rs.getString("DiaChi"));
                nv.setemail(rs.getString("Email"));
                nv.setsdt(rs.getString("SDT"));
                nv.setmatk(rs.getString("MaTaiKhoan"));
                nv.setluong(rs.getString("Luong"));
                arr.add(nv);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return arr;
    }
    public static ArrayList<NhanvienDTO> findluong(String num){
        ArrayList<NhanvienDTO> arr= new ArrayList<NhanvienDTO>();
        String sql="Select * from nhanvien where Luong"+num;
        try{
            Connection act=JDBCConnection.getConnection();
            Statement stmt=act.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                NhanvienDTO nv=new NhanvienDTO();
                nv.setmanv(rs.getString("MaNhanVien"));
                nv.settennv(rs.getString("TenNhanVien"));
                nv.setaddress(rs.getString("DiaChi"));
                nv.setemail(rs.getString("Email"));
                nv.setsdt(rs.getString("SDT"));
                nv.setmatk(rs.getString("MaTaiKhoan"));
                nv.setluong(rs.getString("Luong"));
                arr.add(nv);
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return arr;
    }
}
