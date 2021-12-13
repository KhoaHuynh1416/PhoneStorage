/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChinhanhDAO;
import DTO.ChinhanhDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author pc
 */
public class ChinhanhBUS {
    
    //Sử dụng kết nối CSDL
    private ChinhanhDAO khachHangDAO;
    
    //Lưu trữ để tránh lấy lại nhiều lần tăng tốc độ xử lý
    private ArrayList<ChinhanhDTO> danhSachChiNhanh;
    
    public ChinhanhBUS() {
        khachHangDAO = new ChinhanhDAO();
        danhSachChiNhanh = khachHangDAO.getDanhSachChiNhanh();
    }
    
    //Thêm khách hàng mới từ view
    public void addChiNhanh(Vector khachHangMoi) {
        
        //thêm mã khách hàng mới
        setMaChiNhanhMoi(khachHangMoi);
        
        //Thêm vào CSDL
        khachHangDAO.addChiNhanh(toChiNhanhDTO(khachHangMoi));
        
        //Thêm vào danh sách hiện tại
        danhSachChiNhanh.add(toChiNhanhDTO(khachHangMoi));
    }
    
    //Xóa khách hàng 
    public void removeChiNhanh(Vector khachHang) {
        
        //Lấy mã khách hàng
        String maChiNhanh = khachHang.get(0).toString();
        
        //Xóa trong CSDL
        khachHangDAO.deleteChiNhanh(maChiNhanh);
        
        //Xóa trong danh sách hiện tại
        danhSachChiNhanh.remove(toChiNhanhDTO(khachHang));
    }
    
    //Chỉnh sửa thông tin khách hàng
    public void updateChiNhanh(Vector khachHangCu, Vector khachHangMoi) {
        
        //Ép kiểu sang DTO
        ChinhanhDTO khachHangMoiDTO = toChiNhanhDTO(khachHangMoi);
        ChinhanhDTO khachHangCuDTO = toChiNhanhDTO(khachHangCu);
        
        //Update trong CSDL
        khachHangDAO.upadateChiNhanh(khachHangMoiDTO);
        
        //Tìm chỉ số trong danh sách hiện tại
        int index = danhSachChiNhanh.indexOf(khachHangCuDTO);
        
        //Update trong danh sách hiện tại
        danhSachChiNhanh.set(index, khachHangMoiDTO);
    }
    
    //Lấy thông tin khách hàng theo mã
    public Vector<Vector> getChiNhanhByMaChiNhanh(String maChiNhanh) {
        
        //Tạo danh sách lưu kết quả
        Vector<Vector> danhSachChiNhanh = new Vector<>();
        
        //Xử lý khi chỉ nhập số
        while (maChiNhanh.length() < 3) {
            maChiNhanh = "0" + maChiNhanh;
        }
        if (maChiNhanh.length() == 3) {
            maChiNhanh = "KH" + maChiNhanh;
        }
        
        //Tìm kiếm trong danh sách khách hàng hiện tại
        for (ChinhanhDTO khachHangDTO : this.danhSachChiNhanh) {
            
            //Khách hàng có mã trùng với mã cần tìm thì đưa vào danh sách kết quả
            if (khachHangDTO.getMaChiNhanh().equals(maChiNhanh)) {
                danhSachChiNhanh.add(toVectorData(khachHangDTO));
            }
        }
        
        return danhSachChiNhanh;
    }
    
    //Lấy danh sách khách hàng hiện tại trả về kiểu Vector
    public Vector<Vector> getDanhSachChiNhanh() {
        Vector<Vector> danhSachChiNhanhKQ = new Vector<>();
        for (ChinhanhDTO khachHangDTO : danhSachChiNhanh) {
            Vector row = toVectorData(khachHangDTO);
            danhSachChiNhanhKQ.add(row);
        }
        return danhSachChiNhanhKQ;
    }
    
    //Ép kiểu từ DTO -> Vector
    public Vector toVectorData(ChinhanhDTO khachHang) {
        Vector result = new Vector();
        result.add(khachHang.getMaChiNhanh());
        result.add(khachHang.getTenChiNhanh());
        result.add(khachHang.getDiaChi());
        result.add(khachHang.getEmail());
        result.add(khachHang.getSDT());
        return result;
    }
    
    //Ép kiểu từ Vector -> DTO
    public ChinhanhDTO toChiNhanhDTO(Vector khachHang) {
        ChinhanhDTO result = new ChinhanhDTO();
        result.setMaChiNhanh(khachHang.get(0).toString());
        result.setTenChiNhanh(khachHang.get(1).toString());
        result.setDiaChi(khachHang.get(2).toString());
        result.setEmail(khachHang.get(3).toString());
        result.setSDT(khachHang.get(4).toString());
        return result;
    }
    
    //Thêm mã mới cho khách hàng mới
    private void setMaChiNhanhMoi(Vector khachHangMoi) {
        
        //Tạo mã mới
        String maChiNhanhMoi = createMaChiNhanhMoi();
        
        //Thêm vào vị trí mã
        khachHangMoi.set(0, maChiNhanhMoi);
    }
    
    //Lấy mã khách hàng cuỗi trong danh sách khách hàng hiện tại
    private String getMaChiNhanhCuoi() {
        
        String maChiNhanhCuoi = null;
        int soLuongChiNhanh = danhSachChiNhanh.size();
        
        //Kiểm tra nếu danh sách khách hàng hiện tại rỗng thì trả về null
        if (soLuongChiNhanh > 0) {
            maChiNhanhCuoi = danhSachChiNhanh.get(soLuongChiNhanh - 1).getMaChiNhanh();
        }
        return maChiNhanhCuoi;
        
    }
    
    //Tạo mã khách hàng mới
    private String createMaChiNhanhMoi() {
        
        //Lấy mã khách hàng cuối
        String maChiNhanhCuoi = getMaChiNhanhCuoi();
        
        //Nếu mã cuối = null thì mã mới là KH001
        if (maChiNhanhCuoi == null) {
            return "KH001";
        }
        
        //Lấy phần đuôi mã
        String duoiMaCuoi = maChiNhanhCuoi.substring(2, 5);
        
        //Ép qua kiểu int
        int stt = Integer.parseInt(duoiMaCuoi);
        
        //Tăng lên 1
        ++stt;
        
        //Tạo ra đuôi mã mới length = 3
        String duoiMaMoi = stt + "";
        while (duoiMaMoi.length() < 3) {            
            duoiMaMoi = "0" + duoiMaMoi;
        }
        
        //Ghép thêm phần đầu mã vào
        return "KH" + duoiMaMoi;
    }
    
    //Xóa khoảng cách
    private String deleteSpace(String string) {
        
        //Trường hợp chuỗi = null
        if (string == null) {
            return null;
        }
        
        //Tạo biến kiểu StringBuilder để lưu kết quả
        StringBuilder result = new StringBuilder();
        
        //Lấy độ dài chuỗi
        int length = string.length();
        char charAtIndex;
        
        //Duyệt qua từng ký tự trong chuỗi
        for (int i = 0; i < length; i++) {
            charAtIndex = string.charAt(i);
            
            //Nếu khác khoảng cách thì đưa vào kết quả
            if (charAtIndex != ' ') {
                result.append(charAtIndex);
            }
        }
        
        //Ép lại sang kiểu String
        return result.toString();
    }
    
//    private boolean tenIsCorrect(String tenChiNhanh, ChiNhanhDTO khachHangDTO) {
//        String tenKH = khachHangDTO.getTenChiNhanh().toLowerCase();
//        tenChiNhanh = deleteSpace(tenChiNhanh);
//        tenChiNhanh = tenChiNhanh.toLowerCase();
//        for (int i = 0; i < tenChiNhanh.length(); ++i) {
//            if (tenKH.indexOf(Character.toString(tenChiNhanh.charAt(i))) < 0) {
//                return false;
//            }
//        }
//        return true;
//    }
    
    
    //Lấy danh sách khách hàng theo tên
    public Vector<Vector> getDanhSachChiNhanhByTen(String tenChiNhanh) {
        
        //Tạo biến kiểu Vector để lưu kết quả
        Vector<Vector> danhSachKetQua = new Vector<>();
        
        //Nếu danh sách khách hàng hiện tại trống thì trả về danh sách rỗng
        if (danhSachChiNhanh.size() == 0) {
            return danhSachKetQua;
        }
        
        //Loại bỏ dấu tiếng Việt
        tenChiNhanh = VNCharacterUtils.removeAccent(tenChiNhanh);
        
        //Xóa khoảng cách và chuyển thành chữ thường
        tenChiNhanh = deleteSpace(tenChiNhanh).toLowerCase();
        
        //Duyệt danh sách khách hàng hiện tại
        for (ChinhanhDTO khachHangDTO : danhSachChiNhanh) {
            
            //Lấy tên từng khách hàng và bỏ dấu tiếng Việt
            String tenKH = VNCharacterUtils.removeAccent(khachHangDTO.getTenChiNhanh());
            
            //Chuyển thành chữ thường
            tenKH = deleteSpace(tenKH).toLowerCase();
            
            //Nếu chứa tên khách hàng cần tìm kiếm thì đưa vào kết quả
            if (tenKH.contains(tenChiNhanh)) {
                Vector khachHang = toVectorData(khachHangDTO);
                danhSachKetQua.add(khachHang);
            }
        }
        
        return danhSachKetQua;
    }
    
    //Lấy danh sách theo email
    public Vector<Vector> getDanhSachChiNhanhByEmail(String emailChiNhanh) {
        
        //Tạo biến kiểu Vector để lưu kết quả
        Vector<Vector> danhSachKetQua = new Vector<>();
        
        //Trả về danh sách rỗng nếu danh sách khách hàng hiện tại rỗng
        if (danhSachChiNhanh.size() == 0) {
            return danhSachKetQua;
        }
        
        //Xử lý trường hợp nhập đầy đủ
        if (emailChiNhanh.contains("@")) {
            
            //Duyệt danh sách khách hàng hiện tại
            for (ChinhanhDTO khachHangDTO : danhSachChiNhanh) {
                
                //Lấy email từng khách hàng chuyển thành chữ thường nếu chứa email nhập vào thì thêm vào kết quả
                if (khachHangDTO.getEmail().toLowerCase().contains(emailChiNhanh.toLowerCase())) {
                    danhSachKetQua.add(toVectorData(khachHangDTO));
                }
            }
        }
        
        //Xử lý trường hợp chỉ nhập tên
        else {
            
            //Chuyển thành chữ thường
            emailChiNhanh = emailChiNhanh.toLowerCase();
            
            //Duyệt danh sách khách hàng hiện tại
            for (ChinhanhDTO khachHangDTO : danhSachChiNhanh) {
                
                //Lấy phần tên email từng khách hàng chuyển thành chữ thường nếu chứa tên nhập vào thì thêm vào kết quả
                if (khachHangDTO.getEmail().split("@")[0].toLowerCase().contains(emailChiNhanh)) {
                    
                    //Ép kiểu sang Vector
                    danhSachKetQua.add(toVectorData(khachHangDTO));
                    
                }
            }
        }
        
        return danhSachKetQua;
    }
    
    //Lấy danh sách theo địa chỉ
    public Vector<Vector> getDanhSachChiNhanhByDiaChi(String diaChiChiNhanh) {
        
        //Tạo biến kiểu Vector để lưu kết quả
        Vector<Vector> danhSachKetQua = new Vector<>();
        
        //Trả về danh sách rỗng nếu danh sách khách hàng hiện tại rỗng
        if (danhSachChiNhanh.size() == 0) {
            return danhSachKetQua;
        }
        
        //Bỏ dấu tiếng Việt địa chỉ nhập vào
        diaChiChiNhanh = VNCharacterUtils.removeAccent(diaChiChiNhanh);
        
        //Xóa khoảng cách và chuyển thành chữ thường
        diaChiChiNhanh = deleteSpace(diaChiChiNhanh).toLowerCase();
        
        //Duyệt danh sách khách hàng hiện tại
        for (ChinhanhDTO khachHangDTO : danhSachChiNhanh) {
            
            //Lấy địa chỉ từng khách hàng xóa dấu tiếng Việt
            String diaChi = VNCharacterUtils.removeAccent(khachHangDTO.getDiaChi());
            
            //Xóa khoảng cách và chuyển thành chữ thường
            diaChi = deleteSpace(diaChi).toLowerCase();
            
            //Nếu chứa địa chỉ nhập vào thì ép kiểu sang Vector và thêm vào kết quả
            if (diaChi.contains(diaChiChiNhanh)) {
                
                //Ép kiểu sang Vector
                Vector khachHang = toVectorData(khachHangDTO);
                
                //Thêm vào kết quả
                danhSachKetQua.add(khachHang);
            }
        }
        
        return danhSachKetQua;
    }
    
    //Lấy danh sách khách hàng theo SĐT trả về kiểu Vector
    public Vector<Vector> getDanhSachChiNhanhBySDT(String SDTChiNhanh) {
        
        //Tạo danh sách kết quả để lưu kết quả tìm kiếm có kiểu Vector
        Vector<Vector> danhSachKetQua = new Vector<>();
        
        //Trả về kết quả Vector rỗng nếu danh sách khách hàng hiện tại trống
        if (danhSachChiNhanh.size() == 0) {
            return danhSachKetQua;
        }
        
        //Xóa khoảng cách của chuỗi đưa vào
        SDTChiNhanh = deleteSpace(SDTChiNhanh);
        
        //Duyệt hết danh sách khách hàng
        for (ChinhanhDTO khachHangDTO : danhSachChiNhanh) {
            
            //Khách hàng có SĐT chứa chuỗi đã đưa vào thì thêm vào danh sách kết quả
            if (khachHangDTO.getSDT().contains(SDTChiNhanh)) {
                danhSachKetQua.add(toVectorData(khachHangDTO));
            }
            
        }
        
        return danhSachKetQua;
    }
    
    //Dùng để kiểm tra danh sách khách hàng hiện có
    public void showDanhSachChiNhanh() {
        for (ChinhanhDTO khachHangDTO : danhSachChiNhanh) {
            khachHangDTO.display();
        }
    }
    
    //Dùng để kiểm tra kết quả các chức năng tìm kiếm
    public void showKetQuaTimKiem(Vector<Vector> danhSachKetQua) {
        for (Vector ketQua : danhSachKetQua) {
            toChiNhanhDTO(ketQua).display();
        }
    }
    
    public List<ChinhanhDTO> getAllChiNhanh() {
        return khachHangDAO.getAllChiNhanh();
    }
}
