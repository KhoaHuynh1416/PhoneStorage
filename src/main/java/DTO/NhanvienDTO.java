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
public class NhanvienDTO {
    private String manv;
    private String tennv;
    private String address;
    private String email;
    private String sdt;
    private String matk;
    private String luong;
    public NhanvienDTO(){
        this.manv="";
        this.tennv="";
        this.address="";
        this.email="";
        this.sdt="";
        this.matk="";
        this.luong="";
    }
    public void NhanvienDTO(String manv,String tennv,String address,String email,String sdt,String matk,String luong){
        this.manv=manv;
        this.tennv=tennv;
        this.address=address;
        this.email=email;
        this.sdt=sdt;
        this.matk=matk;
        this.luong=luong;
    }
    public String getmanv(){
        return manv;
    }
    public String gettennv(){
        return tennv;
    }
    public String getaddress(){
        return address;
    }
    public String getemail(){
        return email;
    }
    public String getsdt(){
        return sdt;
    }
    public String getmatk(){
        return matk;
    }
    public String getluong(){
        return luong;
    }
    public void setmanv(String manv){
        this.manv=manv;
    }
    public void settennv(String tennv){
        this.tennv=tennv;
    }
    public void setaddress(String address){
        this.address=address;
    }
    public void setemail(String email){
        this.email=email;
    }
    public void setsdt(String sdt){
        this.sdt=sdt;
    }
    public void setmatk(String matk){
        this.matk=matk;
    }
    public void setluong(String luong){
        this.luong=luong;
    }
}
