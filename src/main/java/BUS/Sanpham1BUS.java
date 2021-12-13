
package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.List;

public class Sanpham1BUS {
    private SanPhamDAO sanphamDAO;

    public Sanpham1BUS() throws Exception {
        sanphamDAO = new SanPhamDAO();
    }
    
    public SanPhamDTO getSanphamBYMaSP(String maSP) throws Exception{
        return sanphamDAO.getSanphamBYMaSP(maSP);
    }
    
    public List<SanPhamDTO> getAllSanpham() throws Exception{
        return sanphamDAO.getAllSanpham();
    }
    
    public List<String> getMaSanpham() {
        return sanphamDAO.getMaSanpham();
    }
    
    public void updateSoluongSP(SanPhamDTO sanpham){
        sanphamDAO.updateSoluongSP(sanpham);
    }
}
