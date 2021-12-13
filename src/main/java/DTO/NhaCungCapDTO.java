/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author pc
 */
public class NhaCungCapDTO {
    private String maNCC;
    private String tenNCC;
    private String diaChi;
    private String SDT;

    public NhaCungCapDTO(String maNCC, String tenNCC, String diaChi, String SDT) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.SDT = SDT;
    }

    public NhaCungCapDTO() {
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    
    public void display() {
        System.out.println(maNCC + "  " + tenNCC + "  " + diaChi + "  " + SDT);
    }
    
}
