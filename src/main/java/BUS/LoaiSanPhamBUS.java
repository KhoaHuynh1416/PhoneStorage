/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.LoaiSanPhamDAO;
import DTO.LoaiSanPhamDTO;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author pc
 */
public class LoaiSanPhamBUS {
    
    private ArrayList<LoaiSanPhamDTO> danhSachLoaiSanPham;
    private LoaiSanPhamDAO loaiSanPhamDAO;
    
    public LoaiSanPhamBUS() {
        loaiSanPhamDAO = new LoaiSanPhamDAO();
        danhSachLoaiSanPham = loaiSanPhamDAO.getDataFromDatabase();
    }
    
    public ArrayList<LoaiSanPhamDTO> getDanhSachLoaiSanPham() {
        return danhSachLoaiSanPham;
    }
    
    public void addLoaiSanPham(Vector data) {
        LoaiSanPhamDTO loaiSanPhamMoi = getThongTinLoaiSanPham(data);
        loaiSanPhamDAO.add(loaiSanPhamMoi);
        danhSachLoaiSanPham.add(loaiSanPhamMoi);
    }
    
    public void deleteLoaiSanPham(int index) {
        String maLoai = danhSachLoaiSanPham.get(index).getMaLoai();
        loaiSanPhamDAO.deleteById(maLoai);
        danhSachLoaiSanPham.remove(index);
    }
    
    public void updateLoaiSanPham(Vector data, int index) {
        LoaiSanPhamDTO loaiSanPhamMoi = getThongTinLoaiSanPham(data);
        loaiSanPhamDAO.update(loaiSanPhamMoi);
        danhSachLoaiSanPham.set(index, loaiSanPhamMoi);
    }
    

    
    public String getIdAt(int index) {
        return danhSachLoaiSanPham.get(index).getMaLoai();
    }
    
    public String getLastId() {
        int size = danhSachLoaiSanPham.size();
        return danhSachLoaiSanPham.get(size - 1).getMaLoai();
    }
    
    public String getNextId() {
        String lastId = getLastId();
        int count = Integer.parseInt(lastId.substring(4, 6));
        ++count;
        String end = count + "";
        if (count < 10) {
            end = "0" + count;
        }
        return "loai" + end;
    }
    
    public LoaiSanPhamDTO getLoaiSanPhamById(String maLoai) {
        if (maLoai.length() == 2) {
            maLoai = "loai" + maLoai;
        } 
        else {
            if (maLoai.length() == 1) {
                maLoai = "loai" + "0" + maLoai;
            }
        }
        LoaiSanPhamDTO result = null;
        for (LoaiSanPhamDTO loaiSanPham : danhSachLoaiSanPham) {
            if (loaiSanPham.getMaLoai().equals(maLoai)) {
                result = loaiSanPham;
            }
        }
        return result;
    }
    
    public boolean isCorrect(String tenLoai, LoaiSanPhamDTO loaiSanPham) {
        String tenLoaiSP = loaiSanPham.getTenLoai().toLowerCase();
        tenLoai = tenLoai.toLowerCase();
        for (int i = 0; i < tenLoai.length(); ++i) {
            if (tenLoaiSP.indexOf(Character.toString(tenLoai.charAt(i))) < 0) {
                return false;
            }
        }
        return true;
    }
    
    public ArrayList<LoaiSanPhamDTO> getDanhSachLoaiSanPhamSearchByName(String tenLoai) {
        ArrayList<LoaiSanPhamDTO> result = new ArrayList<>();
        for (LoaiSanPhamDTO loaiSanPham : danhSachLoaiSanPham) {
            if (isCorrect(tenLoai, loaiSanPham)) {
                result.add(loaiSanPham);
            }
        }
        
        if (result.size() == 0) {
            return null;
        }
        return result;
    }
    

    
    
    public LoaiSanPhamDTO getThongTinLoaiSanPham(Vector data) {
        String maLoai = data.get(0).toString();
        String tenLoai = data.get(1).toString();
        LoaiSanPhamDTO loaiSanPhamMoi = new LoaiSanPhamDTO(maLoai, tenLoai);
        return loaiSanPhamMoi;
    }
    
}
