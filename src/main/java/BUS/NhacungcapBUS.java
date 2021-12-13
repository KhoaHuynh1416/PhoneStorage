/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author pc
 */
public class NhacungcapBUS {
    private ArrayList<NhaCungCapDTO> danhSachNhaCungCap;
    private NhaCungCapDAO nhaCungCapDAO;
    
    
    public NhacungcapBUS() {
        nhaCungCapDAO = new NhaCungCapDAO();
        danhSachNhaCungCap = nhaCungCapDAO.getDanhSachNhaCungCap();
    }
    
    
    
    public ArrayList<NhaCungCapDTO> getDanhSachNCC() {
        return danhSachNhaCungCap;
    }
    
    public Vector<Vector> getDanhSachNhaCungCap() {
        Vector<Vector> data = new Vector<>();
        for (NhaCungCapDTO nhaCungCap : danhSachNhaCungCap) {
            Vector row = convertToVector(nhaCungCap);
            data.add(row);
        }
        return data;
    }
    
    private Vector convertToVector(NhaCungCapDTO nhaCungCap) {
        Vector data = new Vector();
        data.add(nhaCungCap.getMaNCC());
        data.add(nhaCungCap.getTenNCC());
        data.add(nhaCungCap.getDiaChi());
        data.add(nhaCungCap.getSDT());
        return data;
    }
    
    public void add(Vector newData) {
        String maNhaCungCapMoi = getMaNhaCungCapMoi();
        String tenNhaCungCapMoi = newData.get(1).toString();
        String diaChiNhaCungCapMoi = newData.get(2).toString();
        String SDTNhaCungCapMoi = newData.get(3).toString();
        NhaCungCapDTO nhaCungCapMoi = new NhaCungCapDTO(maNhaCungCapMoi, tenNhaCungCapMoi, diaChiNhaCungCapMoi, SDTNhaCungCapMoi);
        nhaCungCapDAO.add(nhaCungCapMoi);
        danhSachNhaCungCap.add(nhaCungCapMoi);
    }
    
    public void delete(int index) {
        String maNhaCungCap = getMaNhaCungCapByIndex(index);
        nhaCungCapDAO.deleteById(maNhaCungCap);
        danhSachNhaCungCap.remove(index);
    }
    
    public void update(Vector newData, int index) {
        String maNhaCungCapMoi = getMaNhaCungCapByIndex(index);
        String tenNhaCungCapMoi = newData.get(1).toString();
        String diaChiNhaCungCapMoi = newData.get(2).toString();
        String SDTNhaCungCapMoi = newData.get(3).toString();
        NhaCungCapDTO nhaCungCapMoi = new NhaCungCapDTO(maNhaCungCapMoi, tenNhaCungCapMoi, diaChiNhaCungCapMoi, SDTNhaCungCapMoi);
        nhaCungCapDAO.update(nhaCungCapMoi);
        danhSachNhaCungCap.set(index, nhaCungCapMoi);
    }
    
    public String getMaNhaCungCapCuoi() {
        int numberOfNhaCC = danhSachNhaCungCap.size();
        return danhSachNhaCungCap.get(numberOfNhaCC - 1).getMaNCC();
    }
    
    public String getMaNhaCungCapMoi() {
        String maNhaCungCapCuoi = getMaNhaCungCapCuoi();
        String duoiMaCuoi = maNhaCungCapCuoi.substring(3, 6);
        int num = Integer.parseInt(duoiMaCuoi);
        ++num;
        String duoiMaMoi = num + "";
        while (duoiMaMoi.length() < 3) {
            duoiMaMoi = "0" + duoiMaMoi;
        }
        String maNhaCungCapMoi = "ncc" + duoiMaMoi;
        return maNhaCungCapMoi;
    }
    
    public String getMaNhaCungCapByIndex(int index) {
        String maNhaCungCap = danhSachNhaCungCap.get(index).getMaNCC();
        return maNhaCungCap;
    }
    
    public NhaCungCapDTO getNhaCungCapByMaNCC(String maNhaCungCap) {
        while (maNhaCungCap.length() < 3) {
            maNhaCungCap = "0" + maNhaCungCap;
        }
        if (maNhaCungCap.length() == 3) {
            maNhaCungCap = "ncc" + maNhaCungCap;
        }
        NhaCungCapDTO result = null;
        for (NhaCungCapDTO nhaCungCap : danhSachNhaCungCap) {
            if (nhaCungCap.getMaNCC().equals(maNhaCungCap.trim())) {
                result = nhaCungCap;
            }
        }
        return result;
    }
    
    public Vector getNhaCungCapByMaNhaCungCap(String maNhaCungCap) {
        if (getNhaCungCapByMaNCC(maNhaCungCap) == null) {
            return null;
        }
        return convertToVector(getNhaCungCapByMaNCC(maNhaCungCap));
    }
    
    public NhaCungCapDTO getNhaCCBySDT(String SDT) {
        NhaCungCapDTO result = null;
        for (NhaCungCapDTO nhaCungCap : danhSachNhaCungCap) {
            if (nhaCungCap.getSDT().contains(SDT.trim())) {
                result = nhaCungCap;
            }
        }
        return result;
    }
    
    public Vector getNhaCungCapBySDT(String SDT) {
        if (getNhaCCBySDT(SDT) == null) {
            return null;
        }
        return convertToVector(getNhaCCBySDT(SDT));
    }
    
    private boolean isCorrect(String tenNhaCungCap, NhaCungCapDTO nhaCungCap) {
        String tenNCC = nhaCungCap.getTenNCC().toLowerCase();
        tenNhaCungCap = tenNhaCungCap.toLowerCase().trim();
        for (int i = 0; i < tenNhaCungCap.length(); ++i) {
            if (tenNCC.indexOf(Character.toString(tenNhaCungCap.charAt(i))) < 0) {
                return false;
            }
        }
        return true;
    }
    
    public ArrayList<NhaCungCapDTO> getDanhSachNCCByTen(String tenNhaCungCap) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        for (NhaCungCapDTO nhaCungCap : danhSachNhaCungCap) {
            if (isCorrect(tenNhaCungCap, nhaCungCap)) {
                result.add(nhaCungCap);
            }
        }
        if (result.size() == 0) {
            return null;
        }
        return result;
    }
    
    public Vector<Vector> getDanhSachNhaCungCapByTen(String tenNhaCungCap) {
        Vector<Vector> result = new Vector<>();
        for (NhaCungCapDTO nhaCungCap : danhSachNhaCungCap) {
            if (isCorrect(tenNhaCungCap, nhaCungCap)) {
                Vector row = convertToVector(nhaCungCap);
                result.add(row);
            }
        }
        if (result.size() == 0) {
            return null;
        }
        
        return result;
    }
    

}
