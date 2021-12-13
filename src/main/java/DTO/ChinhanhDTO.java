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
public class ChinhanhDTO {
    
    private String maChiNhanh;
    private String tenChiNhanh;
    private String diaChi;
    private String email;
    private String SDT;

    public ChinhanhDTO() {
    }

    public ChinhanhDTO(String maChiNhanh, String tenChiNhanh, String diaChi, String email, String SDT) {
        this.maChiNhanh = maChiNhanh;
        this.tenChiNhanh = tenChiNhanh;
        this.diaChi = diaChi;
        this.email = email;
        this.SDT = SDT;
    }

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

    public String getTenChiNhanh() {
        return tenChiNhanh;
    }

    public void setTenChiNhanh(String tenChiNhanh) {
        this.tenChiNhanh = tenChiNhanh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    
    public void display() {
        System.out.println(maChiNhanh + " " + tenChiNhanh + " " + diaChi + " " + email + " " + SDT);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof ChinhanhDTO)) return false;
        ChinhanhDTO o = (ChinhanhDTO) obj;
        if (!o.getMaChiNhanh().equals(maChiNhanh)) return false;
        if (!o.getTenChiNhanh().equals(tenChiNhanh)) return false;
        if (!o.getDiaChi().equals(diaChi)) return false;
        if (!o.getEmail().equals(email)) return false;
        if (!o.getSDT().equals(SDT)) return false;
        return true;
}
    

    
    
    
    
}
