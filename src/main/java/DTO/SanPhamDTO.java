/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author PC
 */
public class SanPhamDTO {
    String MaSP;
    String TenSP;
    int SoLuong;
    float DonGia;
    String MaLoai;
    String MaNSX;
    
    public SanPhamDTO(){
        MaSP = "";
        TenSP = "";
        SoLuong = 0;
        DonGia = 0;
        MaLoai = "";
        MaNSX = "";
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getMaNSX() {
        return MaNSX;
    }

    public void setMaNSX(String MaNSX) {
        this.MaNSX = MaNSX;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getSoLuong(){       
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) throws Exception {
        if(SoLuong < 0){
            throw new Exception("Số lượng phải là số dương!");
        }
        else
            this.SoLuong = SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }
}
                                                                                                                                                          