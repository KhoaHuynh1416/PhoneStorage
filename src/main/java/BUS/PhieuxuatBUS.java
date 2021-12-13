
package BUS;

import DAO.PhieuxuatDAO;
import DTO.PhieuxuatDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author XuanNguyen
 */
public class PhieuxuatBUS {
    
    private  PhieuxuatDAO hoadonDAO;
    
    public PhieuxuatBUS(){
        hoadonDAO = new PhieuxuatDAO();
    }
    
    public List<PhieuxuatDTO> getAllPhieuxuat(){
        return hoadonDAO.getAllHoaDon();
    }
    
    public void insertPhieuxuat(PhieuxuatDTO hoadon){
        hoadonDAO.insertPhieuxuat(hoadon);
    }
    public void deletePhieuxuat(String maPX){
        hoadonDAO.deletePhieuxuat(maPX);
    }
 
    public PhieuxuatDTO getPhieuxuatByMaPhieuxuat(String maPX){
        return hoadonDAO.getPhieuxuatByMaPhieuxuat(maPX);
    }
    
    public List<PhieuxuatDTO> getPhieuxuatByMaCN(String maCN){
        return hoadonDAO.getPhieuxuatByMaCN(maCN);
    }
    
    public List<PhieuxuatDTO> getPhieuxuatByDate(String tuNgay, String denNgay){
        return hoadonDAO.getPhieuxuatByDate(tuNgay, denNgay);
    }
}

