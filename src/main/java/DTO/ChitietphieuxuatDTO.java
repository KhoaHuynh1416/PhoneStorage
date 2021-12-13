
package DTO;
/**
 *
 * @author xuannguyen
 */
public class ChitietphieuxuatDTO {
    private String masp, mapx;
    private int soluongmua;
    private float dongia, thanhtien;

    public ChitietphieuxuatDTO() {
        this.masp = "";
        this.mapx = "";
        this.soluongmua = 0;
        this.dongia = 0;
        this.thanhtien = 0;
    }

    public ChitietphieuxuatDTO(String masp, String mapx, int soluong, float dongia, float thanhtien) {
        this.masp = masp;
        this.mapx = mapx;
        this.soluongmua = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public String getMasp() {
        return masp;
    }

    public String getMapx() {
        return mapx;
    }

    public int getSoluongmua() {
        return soluongmua;
    }

    public float getDongia() {
        return dongia;
    }

    public float getThanhtien() {
        return thanhtien;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public void setMapx(String mapx) {
        this.mapx = mapx;
    }

    public void setSoluongmua(int soluongmua) {
        this.soluongmua = soluongmua;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public void setThanhtien(float thanhtien) {
        this.thanhtien = thanhtien;
    }
    
    public float thanhTien(){
        return dongia * soluongmua;
    }
}
