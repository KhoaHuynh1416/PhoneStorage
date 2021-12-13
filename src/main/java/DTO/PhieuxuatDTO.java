
package DTO;


/**
 *
 * @author pc
 */
public class PhieuxuatDTO {
    private String maPX, ngayxuat, maCN, maNV;
    private double tongtien;

    public PhieuxuatDTO() {
        this.maPX = "";
        this.ngayxuat = "";
        this.maCN = "";
        this.maNV = "";
        this.tongtien = 0;
    }

    public PhieuxuatDTO(String maPX, String ngayxuat, String maCN, String maNV, double tongtien) {
        this.maPX = maPX;
        this.ngayxuat = ngayxuat;
        this.maCN = maCN;
        this.maNV = maNV;
        this.tongtien = tongtien;
    }

    public String getMaPX() {
        return maPX;
    }

    public String getNgayxuat() {
        return ngayxuat;
    }

    public String getMaCN() {
        return maCN;
    }

    public String getMaNV() {
        return maNV;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setMaPX(String maPX) {
        this.maPX = maPX;
    }

    public void setNgayxuat(String ngayxuat) {
        this.ngayxuat = ngayxuat;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }   
}
