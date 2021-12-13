/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author PC
 */
public class SanPhamDTOTest{
    
    public SanPhamDTOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMaLoai method, of class SanPhamDTO.
     */
    @Test
    public void testGetMaLoai() {
        SanPhamDTO instance = new SanPhamDTO();
        String expResult = "";
        String result = instance.getMaLoai();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaLoai method, of class SanPhamDTO.
     */
    @Test
    public void testSetMaLoai() {
        String MaLoai = "loai01";
        SanPhamDTO instance = new SanPhamDTO();
        instance.setMaLoai(MaLoai);
        String expResult = "loai01";
        assertEquals(expResult, instance.getMaLoai());
    }

    /**
     * Test of getMaNSX method, of class SanPhamDTO.
     */
    @Test
    public void testGetMaNSX() {
        SanPhamDTO instance = new SanPhamDTO();
        String expResult = "";
        String result = instance.getMaNSX();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaNSX method, of class SanPhamDTO.
     */
    @Test
    public void testSetMaNSX() {
        String MaNSX = "nsx01";
        SanPhamDTO instance = new SanPhamDTO();
        instance.setMaNSX(MaNSX);
        String expResult = "nsx01";
        assertEquals(expResult, instance.getMaNSX());
    }

    /**
     * Test of getMaSP method, of class SanPhamDTO.
     */
    @Test
    public void testGetMaSP() {
        SanPhamDTO instance = new SanPhamDTO();
        String expResult = "";
        String result = instance.getMaSP();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaSP method, of class SanPhamDTO.
     */
    @Test
    public void testSetMaSP() {
        String MaSP = "SP001";
        SanPhamDTO instance = new SanPhamDTO();
        instance.setMaSP(MaSP);
        String expResult = "SP001";
        assertEquals(expResult, instance.getMaSP());
    }

    /**
     * Test of getTenSP method, of class SanPhamDTO.
     */
    @Test
    public void testGetTenSP() {
        SanPhamDTO instance = new SanPhamDTO();
        String expResult = "";
        String result = instance.getTenSP();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTenSP method, of class SanPhamDTO.
     */
    @Test
    public void testSetTenSP() {
        String TenSP = "Nokia";
        SanPhamDTO instance = new SanPhamDTO();
        instance.setTenSP(TenSP);
        String expResult = "Nokia";
        assertEquals(expResult, instance.getTenSP());
    }

    /**
     * Test of getSoLuong method, of class SanPhamDTO.
     */
    @Test
    public void testGetSoLuong() {
        SanPhamDTO instance = new SanPhamDTO();
        int expResult = 0;
        int result = instance.getSoLuong();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSoLuong method, of class SanPhamDTO.
     */
    @Test
    public void testSetSoLuong() throws Exception {
        int SoLuong = 5;
        SanPhamDTO instance = new SanPhamDTO();
        instance.setSoLuong(SoLuong);
        int expResult = 5;
        assertEquals(expResult, instance.getSoLuong());
    }
    
    @Test
    public void testSetSoLuongNegative() throws Exception {
        int SoLuong = -5;
        SanPhamDTO instance = new SanPhamDTO();
        Exception ex = assertThrows(Exception.class, 
                ()->instance.setSoLuong(SoLuong));
    }

    /**
     * Test of getDonGia method, of class SanPhamDTO.
     */
    @Test
    public void testGetDonGia() {
        SanPhamDTO instance = new SanPhamDTO();
        float expResult = 0.0F;
        float result = instance.getDonGia();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDonGia method, of class SanPhamDTO.
     */
    @Test
    public void testSetDonGia() {
        float DonGia = 1000.0F;
        SanPhamDTO instance = new SanPhamDTO();
        instance.setDonGia(DonGia);
        float expResult = 1000.0F;
        assertEquals(expResult, instance.getDonGia());        
    }
    
    @Test
    public void testSetDonGiaNegative() {
        float DonGia = -5.0F;
        SanPhamDTO instance = new SanPhamDTO();
        Exception ex = assertThrows(Exception.class, 
                ()->instance.setDonGia(DonGia));
    }    
    
}
