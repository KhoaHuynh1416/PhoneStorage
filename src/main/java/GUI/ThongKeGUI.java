/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChitietphieuxuatBUS;
import BUS.ThongKeChiBUS;
import BUS.PhieuNhapBUS1;
import DTO.PhieunhapDTO;
import DTO.SanPhamDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import javax.swing.ImageIcon;
import BUS.PhieuxuatBUS;
import BUS.ChinhanhBUS;
import BUS.NhanvienBUS;
import BUS.PhieunhapBUS;
import BUS.TaikhoanBUS;
import DTO.PhieuxuatDTO;
import DTO.ChinhanhDTO;
import DTO.NhanvienDTO;
import DTO.TaikhoanDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class ThongKeGUI extends javax.swing.JFrame {

    /**
     * Creates new form ThongKeGUI
     */
    TaikhoanDTO taikhoan;
    TaikhoanBUS taikhoanBUS;
    PhieuxuatBUS hoadonBUS;
    ChinhanhBUS chiNhanhBUS;
    NhanvienBUS nhanvienBUS;
    PhieunhapBUS phieunhapBUS;
    PhieuNhapBUS1 phieunhapBUS2;
    ChitietphieuxuatBUS chitiethoadonBUS;
    DefaultTableModel defaultTableModel, defaultTableModel1, defaultTableModel2;
    
    DefaultTableModel model = new DefaultTableModel();
    public ThongKeGUI(String username) {
        initComponents();
        
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng phiếu xuất", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 102, 0))); // NOI18N
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHÊNH LỆCH THU - CHI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(0, 102, 0))); // NOI18N

        
        lbDay.setText(String.valueOf(java.time.LocalDateTime.now()));
        taikhoanBUS = new TaikhoanBUS();
        taikhoan = taikhoanBUS.getTaiKhoanByMaTK(username);
        lbUsername.setText(username);
        lbRole.setText(taikhoan.getRole());
        
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Theo mã nhân viên - mã chi nhánh", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N
        
        hoadonBUS = new PhieuxuatBUS();
        
        defaultTableModel = new DefaultTableModel(){
            @Override
            // ham ben duoi duoc xay dung de khong cho user edit du lieu, day nhu la mot anonymous
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblPhieuxuat.setModel(defaultTableModel);
        defaultTableModel.addColumn("MAPHIEUXUAT");
        defaultTableModel.addColumn("NGAYXUAT");
        defaultTableModel.addColumn("MACHINHANH");
        defaultTableModel.addColumn("MANHANVIEN");
        defaultTableModel.addColumn("TONGTIEN (triệu đồng)");
        
        defaultTableModel1 = new DefaultTableModel();
        tblTonghoadon.setModel(defaultTableModel1);
        defaultTableModel1.addColumn("MAPHIEUXUAT");
        defaultTableModel1.addColumn("NGAYXUAT");
        defaultTableModel1.addColumn("MACHINHANH");
        defaultTableModel1.addColumn("MANHANVIEN");
        defaultTableModel1.addColumn("TONGTIEN (triệu đồng)");
        
        defaultTableModel2 = new DefaultTableModel();
        tblTongphieunhap.setModel(defaultTableModel2);
        defaultTableModel2.addColumn("MAPHIEUNHAP");
        defaultTableModel2.addColumn("NGAYNHAP");
        defaultTableModel2.addColumn("MANCC");
        defaultTableModel2.addColumn("MANHANVIEN");
        defaultTableModel2.addColumn("TONGTIEN (triệu đồng)");
        
         //lấy mã khách hàng
        chiNhanhBUS = new ChinhanhBUS();
        List<ChinhanhDTO> khachhangs = chiNhanhBUS.getAllChiNhanh();
        for(ChinhanhDTO khachhang : khachhangs){
            comboMaCN.addItem(khachhang.getMaChiNhanh());
        }
        
        //lấy mã nhân viên
        nhanvienBUS = new NhanvienBUS();
        List<NhanvienDTO> nhanviens = nhanvienBUS.showAll();
        for(NhanvienDTO nhanvien : nhanviens){
            comboMaNV.addItem(nhanvien.getmanv());
        }
        
        show(taikhoan.getRole());
        
        lbMore.setComponentPopupMenu(jPopupMenuMore);
    }

    private void show(String role){
        if(role.equals("user")){
            btnNhanvien.setEnabled(false);
            btnNhaphang.setEnabled(false);
            btnTaikhoan.setEnabled(false);
            btnThongke.setEnabled(false);
            btnSanpham.setEnabled(false);
        }
    }
    
    private void loadTableData(PhieuxuatDTO hoadon){
        defaultTableModel.addRow(new Object[]{hoadon.getMaPX(), hoadon.getNgayxuat(), hoadon.getMaCN(),
            hoadon.getMaNV(),hoadon.getTongtien()});
    }
    
    private void loadTableDataPX(PhieuxuatDTO hoadon){
        defaultTableModel1.addRow(new Object[]{hoadon.getMaPX(), hoadon.getNgayxuat(), hoadon.getMaCN(),
            hoadon.getMaNV(),hoadon.getTongtien()});
    }
    
    private void loadTableDataPN(PhieunhapDTO phieunhap){
        defaultTableModel2.addRow(new Object[]{phieunhap.getMaPN(), phieunhap.getNgayNhap(), phieunhap.getMaNCC(),
            phieunhap.getMaNV(),phieunhap.getTongTien()});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPopupMenuMore = new javax.swing.JPopupMenu();
        menuItemQLNCC = new javax.swing.JMenuItem();
        menuItemQLNSX = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Tungay = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        Denngay = new com.toedter.calendar.JDateChooser();
        btnThongke1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhieuxuat = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        rdbThang = new javax.swing.JRadioButton();
        month = new com.toedter.calendar.JMonthChooser();
        rdbQuy = new javax.swing.JRadioButton();
        comboQuy = new javax.swing.JComboBox<>();
        rdbNam = new javax.swing.JRadioButton();
        year = new com.toedter.calendar.JYearChooser();
        btnThongke2 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        comboMaCN = new javax.swing.JComboBox<>();
        rdbMaNV = new javax.swing.JRadioButton();
        rdbMaCN = new javax.swing.JRadioButton();
        comboMaNV = new javax.swing.JComboBox<>();
        btnThongke3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTongthu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        rbtnSP = new javax.swing.JRadioButton();
        rbtnQuy = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_TKChi = new javax.swing.JTable();
        dateFrom = new com.toedter.calendar.JDateChooser();
        dateTo = new com.toedter.calendar.JDateChooser();
        btnThongKe = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_Tong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        btnThongke4 = new javax.swing.JButton();
        month1 = new com.toedter.calendar.JMonthChooser();
        rdbThangLN = new javax.swing.JRadioButton();
        rdbQuyLN = new javax.swing.JRadioButton();
        rdbNamLN = new javax.swing.JRadioButton();
        comboQuy1 = new javax.swing.JComboBox<>();
        year1 = new com.toedter.calendar.JYearChooser();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTongphieunhap = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTongthuloinhuan = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtLoinhuan = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTongchiloinhuan = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTonghoadon = new javax.swing.JTable();
        btnThoat = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnTrangchu = new javax.swing.JButton();
        btnDangxuat = new javax.swing.JButton();
        lbUsername = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();
        lbDay = new javax.swing.JLabel();
        lbMore = new javax.swing.JLabel();
        panelChuyen = new javax.swing.JPanel();
        btnSanpham = new javax.swing.JButton();
        btnNhanvien = new javax.swing.JButton();
        btnKhachhang = new javax.swing.JButton();
        btnThongke = new javax.swing.JButton();
        btnTaikhoan = new javax.swing.JButton();
        btnBanhang = new javax.swing.JButton();
        btnNhaphang = new javax.swing.JButton();

        menuItemQLNCC.setBackground(new java.awt.Color(255, 255, 255));
        menuItemQLNCC.setText("Quản lý nhà cung cấp");
        menuItemQLNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemQLNCCActionPerformed(evt);
            }
        });
        jPopupMenuMore.add(menuItemQLNCC);

        menuItemQLNSX.setBackground(new java.awt.Color(255, 255, 255));
        menuItemQLNSX.setText("Quản lý nhà sản xuất");
        menuItemQLNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemQLNSXActionPerformed(evt);
            }
        });
        jPopupMenuMore.add(menuItemQLNSX);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THỐNG KÊ TỔNG THU", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(0, 102, 0))); // NOI18N

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê theo ngày", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        jLabel6.setText("Từ ngày:");

        Tungay.setDateFormatString("yyyy-MM-dd");

        jLabel7.setText("Đến ngày:");

        Denngay.setDate(new java.util.Date(1606983408000L));
        Denngay.setDateFormatString("yyyy-MM-dd");

        btnThongke1.setBackground(new java.awt.Color(204, 255, 153));
        btnThongke1.setText("Thống kê");
        btnThongke1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongke1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Denngay, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(Tungay, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnThongke1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tungay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Denngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThongke1)
                .addGap(8, 8, 8))
        );

        tblPhieuxuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblPhieuxuat);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê theo tháng - quý - năm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        rdbThang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbThang);
        rdbThang.setText("Tháng");
        rdbThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbThangActionPerformed(evt);
            }
        });

        rdbQuy.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbQuy);
        rdbQuy.setText("Quý");
        rdbQuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbQuyActionPerformed(evt);
            }
        });

        comboQuy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quý một", "Quý hai", "Quý ba", "Quý bốn" }));
        comboQuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboQuyActionPerformed(evt);
            }
        });

        rdbNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbNam);
        rdbNam.setText("Năm");
        rdbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNamActionPerformed(evt);
            }
        });

        btnThongke2.setBackground(new java.awt.Color(204, 255, 153));
        btnThongke2.setText("Thống kê");
        btnThongke2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongke2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdbThang, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(rdbQuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdbNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnThongke2))
                    .addComponent(comboQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(month, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdbThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboQuy)
                    .addComponent(rdbQuy))
                .addGap(7, 7, 7)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThongke2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdbNam))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Theo mã nhân viên - mã khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        comboMaCN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã chi nhánh" }));
        comboMaCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMaCNActionPerformed(evt);
            }
        });

        rdbMaNV.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdbMaNV);
        rdbMaNV.setText("Mã nhân viên");
        rdbMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMaNVActionPerformed(evt);
            }
        });

        rdbMaCN.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdbMaCN);
        rdbMaCN.setText("Mã chi nhánh");
        rdbMaCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMaCNActionPerformed(evt);
            }
        });

        comboMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã nhân viên" }));
        comboMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMaNVActionPerformed(evt);
            }
        });

        btnThongke3.setBackground(new java.awt.Color(204, 255, 153));
        btnThongke3.setText("Thống kê");
        btnThongke3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongke3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdbMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdbMaCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboMaCN, 0, 124, Short.MAX_VALUE)
                    .addComponent(comboMaNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThongke3)
                .addGap(84, 84, 84))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbMaNV)
                    .addComponent(comboMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdbMaCN)
                    .addComponent(comboMaCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThongke3)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Tổng doanh thu:");

        txtTongthu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTongthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongthuActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("(Triệu đồng)");

        btnRefresh.setText("Làm mới");

        btnXuat.setText("Xuất ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 145, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongthu, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuat)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRefresh)
                            .addComponent(btnXuat)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTongthu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tổng thu", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THỐNG KÊ TỔNG CHI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(0, 102, 0))); // NOI18N

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));

        rbtnSP.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbtnSP);
        rbtnSP.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rbtnSP.setForeground(new java.awt.Color(0, 153, 0));
        rbtnSP.setText("Sản phẩm");
        rbtnSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnSPActionPerformed(evt);
            }
        });

        rbtnQuy.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbtnQuy);
        rbtnQuy.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rbtnQuy.setForeground(new java.awt.Color(0, 153, 0));
        rbtnQuy.setText("Quý");
        rbtnQuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnQuyActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Thống kê theo:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnSP)
                .addGap(18, 18, 18)
                .addComponent(rbtnQuy)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnSP)
                    .addComponent(rbtnQuy)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Từ:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Đến:");

        tbl_TKChi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_TKChi);

        btnThongKe.setBackground(new java.awt.Color(204, 255, 153));
        btnThongKe.setText("Thống Kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 371, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThongKe))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnThongKe))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getStyle() | java.awt.Font.BOLD, jLabel5.getFont().getSize()+10));
        jLabel5.setText("Tổng Chi:");

        txt_Tong.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_Tong.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txt_Tong, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Tong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("(Triệu đồng)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(27, 27, 27))))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(67, 67, 67))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tổng chi", jPanel5);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LỢI NHUẬN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(0, 102, 0))); // NOI18N

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        btnThongke4.setBackground(new java.awt.Color(204, 255, 153));
        btnThongke4.setText("Thống kê");
        btnThongke4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongke4ActionPerformed(evt);
            }
        });

        rdbThangLN.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdbThangLN);
        rdbThangLN.setText("Tháng");
        rdbThangLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbThangLNActionPerformed(evt);
            }
        });

        rdbQuyLN.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdbQuyLN);
        rdbQuyLN.setText("Quý");
        rdbQuyLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbQuyLNActionPerformed(evt);
            }
        });

        rdbNamLN.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdbNamLN);
        rdbNamLN.setText("Năm");
        rdbNamLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNamLNActionPerformed(evt);
            }
        });

        comboQuy1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quý một", "Quý hai", "Quý ba", "Quý bốn" }));
        comboQuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboQuy1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdbThangLN)
                .addGap(18, 18, 18)
                .addComponent(month1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(rdbQuyLN, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboQuy1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdbNamLN)
                .addGap(18, 18, 18)
                .addComponent(year1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(btnThongke4)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(month1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(year1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboQuy1)
                        .addComponent(rdbQuyLN)
                        .addComponent(rdbNamLN, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThongke4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                        .addComponent(rdbThangLN, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng phiếu nhập", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 102, 0))); // NOI18N

        tblTongphieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblTongphieunhap);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Tổng thu:");

        txtTongthuloinhuan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Tổng chi:");

        txtLoinhuan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Chênh lệch:");

        txtTongchiloinhuan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("(Triệu đồng)");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongthuloinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongchiloinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLoinhuan, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(22, 22, 22))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongthuloinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel15)
                    .addComponent(txtLoinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtTongchiloinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 102, 0))); // NOI18N

        tblTonghoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblTonghoadon);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(586, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chênh lệch", jPanel14);

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThoat)
                .addGap(38, 38, 38))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThoat)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        btnTrangchu.setBackground(new java.awt.Color(204, 255, 153));
        btnTrangchu.setText("Trang chủ");
        btnTrangchu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangchuActionPerformed(evt);
            }
        });

        btnDangxuat.setBackground(new java.awt.Color(204, 255, 153));
        btnDangxuat.setText("Đăng xuất");
        btnDangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangxuatActionPerformed(evt);
            }
        });

        lbUsername.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbUsername.setForeground(new java.awt.Color(0, 153, 0));

        lbRole.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbRole.setForeground(new java.awt.Color(0, 153, 0));

        lbDay.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbDay.setForeground(new java.awt.Color(0, 102, 0));
        lbDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbMore.setBackground(new java.awt.Color(204, 255, 153));
        lbMore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMore.setText("More...");
        lbMore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMore, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(359, 359, 359)
                .addComponent(lbUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTrangchu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDangxuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbMore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelChuyen.setBackground(new java.awt.Color(204, 255, 204));
        panelChuyen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        btnSanpham.setBackground(new java.awt.Color(204, 255, 153));
        btnSanpham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSanpham.setText("Sản phẩm");
        btnSanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanphamActionPerformed(evt);
            }
        });

        btnNhanvien.setBackground(new java.awt.Color(204, 255, 153));
        btnNhanvien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnNhanvien.setText("Nhân viên");
        btnNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanvienActionPerformed(evt);
            }
        });

        btnKhachhang.setBackground(new java.awt.Color(204, 255, 153));
        btnKhachhang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnKhachhang.setText("Chi nhánh");
        btnKhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachhangActionPerformed(evt);
            }
        });

        btnThongke.setBackground(new java.awt.Color(204, 255, 153));
        btnThongke.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnThongke.setText("Thống kê");
        btnThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongkeActionPerformed(evt);
            }
        });

        btnTaikhoan.setBackground(new java.awt.Color(204, 255, 153));
        btnTaikhoan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnTaikhoan.setText("Tài khoản");
        btnTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaikhoanActionPerformed(evt);
            }
        });

        btnBanhang.setBackground(new java.awt.Color(204, 255, 153));
        btnBanhang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBanhang.setText("Xuất hàng");
        btnBanhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanhangActionPerformed(evt);
            }
        });

        btnNhaphang.setBackground(new java.awt.Color(204, 255, 153));
        btnNhaphang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnNhaphang.setText("Nhập hàng");
        btnNhaphang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaphangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChuyenLayout = new javax.swing.GroupLayout(panelChuyen);
        panelChuyen.setLayout(panelChuyenLayout);
        panelChuyenLayout.setHorizontalGroup(
            panelChuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChuyenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKhachhang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(btnThongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaikhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelChuyenLayout.setVerticalGroup(
            panelChuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuyenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangchuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangchuActionPerformed
        // TODO add your handling code here:
        new MainformGUI(taikhoan.getMaTK()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTrangchuActionPerformed

    private void btnDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangxuatActionPerformed
        // TODO add your handling code here:
        new DangnhapGUI().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDangxuatActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        new MainformGUI(taikhoan.getMaTK()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnSanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanphamActionPerformed
        try {
            // TODO add your handling code here:
            new SanPhamGUI(taikhoan.getMaTK()).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(DSTaikhoanGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_btnSanphamActionPerformed

    private void btnNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanvienActionPerformed
        // TODO add your handling code here:
        new NhanvienGUI(taikhoan.getMaTK()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNhanvienActionPerformed

    private void btnKhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachhangActionPerformed
        // TODO add your handling code here:
        new ChiNhanhGUI(taikhoan.getMaTK()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKhachhangActionPerformed

    private void btnThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongkeActionPerformed
        // TODO add your handling code here:
        new ThongKeGUI(taikhoan.getMaTK()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThongkeActionPerformed

    private void btnTaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaikhoanActionPerformed
        // TODO add your handling code here:
        new DSTaikhoanGUI(taikhoan.getMaTK()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTaikhoanActionPerformed

    private void btnBanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanhangActionPerformed
        // TODO add your handling code here:
        new PhieuxuatGUI(taikhoan.getMaTK()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBanhangActionPerformed

    private void btnNhaphangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaphangActionPerformed
        try {
            // TODO add your handling code here:
            new PhieuNhapGUI(taikhoan.getMaTK()).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(DSTaikhoanGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_btnNhaphangActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        ThongKeChiBUS tkcBUS = new ThongKeChiBUS();
        ArrayList<PhieunhapDTO> DSThongKe = new ArrayList<>();
        try {
            DSThongKe = tkcBUS.thongkeDateFromTo(dateFrom.getDate(), dateTo.getDate());
        } catch (Exception ex) {
            Logger.getLogger(ThongKeChiGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        int TongTien = 0;

        Vector header = new Vector();
        header.add("Mã Phiếu Nhập");
        header.add("Ngày Nhập");
        header.add("Mã Nhà Cung Cấp");
        header.add("Mã Nhân Viên");
        header.add("Tổng Tiền");
        model = new DefaultTableModel(header,0);
        Vector row = new Vector();

        if(!DSThongKe.isEmpty()){
            for(PhieunhapDTO pn : DSThongKe){
                row = new Vector();
                row.add(pn.getMaPN());
                row.add(pn.getNgayNhap());
                row.add(pn.getMaNCC());
                row.add(pn.getMaNV());
                row.add(pn.getTongTien());
                model.addRow(row);
                TongTien += pn.getTongTien();
            }
        }
        txt_Tong.setText(""+TongTien);

        tbl_TKChi.setModel(model);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void rbtnQuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnQuyActionPerformed
        if(rbtnQuy.isSelected()){
            Vector header = new Vector();
            header.add("Quý");
            header.add("Tổng Tiền");
            model = new DefaultTableModel(header,0);

            int[][]DSThongKe = null;
            int Tong = 0;

            ThongKeChiBUS tkcBUS = new ThongKeChiBUS();
            try {
                DSThongKe = tkcBUS.thongkeQuy();
            } catch (Exception ex) {
                Logger.getLogger(ThongKeChiGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(DSThongKe != null){
                for(int i=1;i<=4;i++){
                    Vector row = new Vector();
                    row.add(i);
                    row.add(DSThongKe[i-1][1]);
                    model.addRow(row);
                    Tong += DSThongKe[i-1][1];
                }
            }
            tbl_TKChi.setModel(model);
            txt_Tong.setText(""+Tong);
        }
    }//GEN-LAST:event_rbtnQuyActionPerformed

    private void rbtnSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnSPActionPerformed
        if(rbtnSP.isSelected()){
            Vector header = new Vector();
            header.add("Mã Sản Phẩm");
            header.add("Tên Sản Phẩm");
            header.add("Đơn Giá");
            header.add("Số Lượng Nhập");
            header.add("Tiền Chi");
            model = new DefaultTableModel(header,0);

            ArrayList<SanPhamDTO> DSThongKe = new ArrayList<>();
            int Tong = 0;

            ThongKeChiBUS tkcBUS = new ThongKeChiBUS();
            try {
                DSThongKe = tkcBUS.thongkeSP();
            } catch (Exception ex) {
                Logger.getLogger(ThongKeChiGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(!DSThongKe.isEmpty()){
                for(SanPhamDTO sp : DSThongKe){
                    Vector row = new Vector();
                    row.add(sp.getMaSP());
                    row.add(sp.getTenSP());
                    row.add(sp.getDonGia());
                    row.add(sp.getSoLuong());
                    int SPChi = (int) (sp.getDonGia() * sp.getSoLuong());
                    row.add(SPChi);
                    Tong += SPChi;
                    model.addRow(row);
                }
            }

            tbl_TKChi.setModel(model);
            txt_Tong.setText(""+Tong);
        }
    }//GEN-LAST:event_rbtnSPActionPerformed

    private void txtTongthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongthuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongthuActionPerformed

    private void btnThongke3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongke3ActionPerformed
        // TODO add your handling code here:
        hoadonBUS = new PhieuxuatBUS();
        List<PhieuxuatDTO> hoadons = new ArrayList<>();
        hoadons = hoadonBUS.getAllPhieuxuat();

        if(rdbMaCN.isSelected()){
            defaultTableModel.setRowCount(0);
            String makh = String.valueOf(comboMaCN.getSelectedItem());
            double tongDT = 0;
            for(PhieuxuatDTO hoadon : hoadons){
                if(makh.toUpperCase().equals(hoadon.getMaCN().toUpperCase())){
                    loadTableData(hoadon);
                    tongDT += hoadon.getTongtien();
                }
            }
            txtTongthu.setText(String.valueOf(tongDT));
        }

        if(rdbMaNV.isSelected()){
            defaultTableModel.setRowCount(0);
            String manv = String.valueOf(comboMaNV.getSelectedItem());
            double tongDT = 0;
            for(PhieuxuatDTO hoadon : hoadons){
                if(manv.equals(hoadon.getMaNV().toUpperCase())){
                    loadTableData(hoadon);
                    tongDT += hoadon.getTongtien();
                }
            }
            txtTongthu.setText(String.valueOf(tongDT));
        }
    }//GEN-LAST:event_btnThongke3ActionPerformed

    private void comboMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMaNVActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboMaNVActionPerformed

    private void rdbMaCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMaCNActionPerformed
        // TODO add your handling code here:
        comboMaNV.setEnabled(false);
        comboMaCN.setEnabled(true);
    }//GEN-LAST:event_rdbMaCNActionPerformed

    private void rdbMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMaNVActionPerformed
        // TODO add your handling code here:
        comboMaCN.setEnabled(false);
        comboMaNV.setEnabled(true);
    }//GEN-LAST:event_rdbMaNVActionPerformed

    private void comboMaCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMaCNActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboMaCNActionPerformed

    private void btnThongke2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongke2ActionPerformed
        // TODO add your handling code here:
        hoadonBUS = new PhieuxuatBUS();
        List<PhieuxuatDTO> hoadons = new ArrayList<>();
        hoadons = hoadonBUS.getAllPhieuxuat();

        if(rdbThang.isSelected()){
            defaultTableModel.setRowCount(0);
            String thang = String.valueOf(month.getMonth()+ 1);
            String thangHT;
            double tongDT = 0;
            for(PhieuxuatDTO hoadon : hoadons){
                thangHT = hoadon.getNgayxuat().substring(5, 7);
                if(thangHT.equals(thang)){
                    loadTableData(hoadon);
                    tongDT += hoadon.getTongtien();
                }
            }
            txtTongthu.setText(String.valueOf(tongDT));
        }
        if(rdbQuy.isSelected()){
            int thangHT;
            int choose = comboQuy.getSelectedIndex();
            double tongDT = 0;
            if(choose == 0){
                defaultTableModel.setRowCount(0);
                for(PhieuxuatDTO hoadon : hoadons){
                    thangHT = Integer.parseInt(hoadon.getNgayxuat().substring(5, 7));
                    if(thangHT >= 1 && thangHT <= 3 ){
                        loadTableData(hoadon);
                        tongDT += hoadon.getTongtien();
                    }
                }
            }

            if(choose == 1){
                for(PhieuxuatDTO hoadon : hoadons){
                    defaultTableModel.setRowCount(0);
                    thangHT = Integer.parseInt(hoadon.getNgayxuat().substring(5, 7));
                    if(thangHT >= 4 && thangHT <= 6){
                        loadTableData(hoadon);
                        tongDT += hoadon.getTongtien();
                    }
                }
            }

            if(choose == 2){
                for(PhieuxuatDTO hoadon : hoadons){
                    defaultTableModel.setRowCount(0);
                    thangHT = Integer.parseInt(hoadon.getNgayxuat().substring(5, 7));
                    if(thangHT >= 7 && thangHT <= 9){
                        loadTableData(hoadon);
                        tongDT += hoadon.getTongtien();
                    }
                }
            }

            if(choose == 3){
                for(PhieuxuatDTO hoadon : hoadons){
                    defaultTableModel.setRowCount(0);
                    thangHT = Integer.parseInt(hoadon.getNgayxuat().substring(5, 7));
                    if(thangHT >= 10 && thangHT <= 12){
                        loadTableData(hoadon);
                        tongDT += hoadon.getTongtien();
                    }
                }
            }
            txtTongthu.setText(String.valueOf(tongDT));
        }
        if(rdbNam.isSelected()){
            defaultTableModel.setRowCount(0);
            String nam = String.valueOf(year.getYear());
            String namPX;
            double tongDT = 0;
            for(PhieuxuatDTO hoadon : hoadons){
                namPX = hoadon.getNgayxuat().substring(0, 4);
                if(nam.equals(namPX)){
                    loadTableData(hoadon);
                    tongDT += hoadon.getTongtien();
                }
            }
            txtTongthu.setText(String.valueOf(tongDT));
        }
    }//GEN-LAST:event_btnThongke2ActionPerformed

    private void rdbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNamActionPerformed
        // TODO add your handling code here:
        comboQuy.setEnabled(false);
        month.setEnabled(false);
        year.setEnabled(true);
    }//GEN-LAST:event_rdbNamActionPerformed

    private void comboQuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboQuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboQuyActionPerformed

    private void rdbQuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbQuyActionPerformed
        // TODO add your handling code here:
        month.setEnabled(false);
        year.setEnabled(false);
        comboQuy.setEnabled(true);
    }//GEN-LAST:event_rdbQuyActionPerformed

    private void rdbThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbThangActionPerformed
        // TODO add your handling code here:
        comboQuy.setEnabled(false);
        year.setEnabled(false);
        month.setEnabled(true);
    }//GEN-LAST:event_rdbThangActionPerformed

    private void btnThongke1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongke1ActionPerformed
        // TODO add your handling code here:
        hoadonBUS = new PhieuxuatBUS();
        List<PhieuxuatDTO> hoadons = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String tuNgay, denNgay;
        tuNgay = df.format(Tungay.getDate());
        denNgay = df.format(Denngay.getDate());

        hoadons = hoadonBUS.getPhieuxuatByDate(tuNgay, denNgay);
        double tongDT = 0;
        for(PhieuxuatDTO hoadon : hoadons){
            loadTableData(hoadon);
            tongDT += hoadon.getTongtien();
        }
        txtTongthu.setText(String.valueOf(tongDT));
    }//GEN-LAST:event_btnThongke1ActionPerformed

    private void btnThongke4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongke4ActionPerformed
        // TODO add your handling code here:
        hoadonBUS = new PhieuxuatBUS();
        phieunhapBUS2 = new PhieuNhapBUS1();
        List<PhieuxuatDTO> hoadons = new ArrayList<>();
        List<PhieunhapDTO> phieunhaps = new ArrayList<>();
        hoadons = hoadonBUS.getAllPhieuxuat();
        phieunhaps = phieunhapBUS2.getAllPhieuNhap();
        double loinhuan = 0, tongthu = 0, tongchi = 0;
        String thangPX, thangPN;

        if(rdbThangLN.isSelected()){
            defaultTableModel1.setRowCount(0);
            defaultTableModel2.setRowCount(0);
            String thang = String.valueOf(month1.getMonth()+ 1);
            
            
            //tinh tong doanh thu theo thang
            for(PhieuxuatDTO hoadon : hoadons){
                thangPX = hoadon.getNgayxuat().substring(5, 7);
                if(thangPX.equals(thang)){
                    loadTableDataPX(hoadon);
                    tongthu += hoadon.getTongtien();
                }
            }
            
            //tinh tong chi theo thang
            for(PhieunhapDTO phieunhap : phieunhaps){
                thangPN = String.valueOf(phieunhap.getNgayNhap().getMonth() + 1);
                if(thangPN.equals(thang)){
                    loadTableDataPN(phieunhap);
                    tongchi += phieunhap.getTongTien();
                }
            }
            txtTongthuloinhuan.setText(String.valueOf(tongthu));
            txtTongchiloinhuan.setText(String.valueOf(tongchi));
            txtLoinhuan.setText(String.valueOf(tongthu - tongchi));
                   
        }
        if(rdbQuyLN.isSelected()){
            int thangPhieuxuat, thangPhieunhap;
            int choose = comboQuy1.getSelectedIndex();
            tongthu = 0;
            tongchi = 0;
            loinhuan = 0;
            if(choose == 0){
                defaultTableModel1.setRowCount(0);
                defaultTableModel2.setRowCount(0);
                for(PhieuxuatDTO hoadon : hoadons){
                    thangPhieuxuat = Integer.parseInt(hoadon.getNgayxuat().substring(5, 7));
                    if(thangPhieuxuat >= 1 && thangPhieuxuat <= 3 ){
                        loadTableDataPX(hoadon);
                        tongthu += hoadon.getTongtien();
                    }
                }
                for(PhieunhapDTO phieunhap : phieunhaps){
                    thangPhieunhap = phieunhap.getNgayNhap().getMonth() + 1;
                    if(thangPhieunhap >= 1 && thangPhieunhap <= 3 ){
                        loadTableDataPN(phieunhap);
                        tongchi += phieunhap.getTongTien();
                    }
                }
            }

            if(choose == 1){
                defaultTableModel1.setRowCount(0);
                defaultTableModel2.setRowCount(0);
                for(PhieuxuatDTO hoadon : hoadons){
                    thangPhieuxuat = Integer.parseInt(hoadon.getNgayxuat().substring(5, 7));
                    if(thangPhieuxuat >= 4 && thangPhieuxuat <= 6 ){
                        loadTableDataPX(hoadon);
                        tongthu += hoadon.getTongtien();
                    }
                }
                for(PhieunhapDTO phieunhap : phieunhaps){
                    thangPhieunhap = phieunhap.getNgayNhap().getMonth() + 1;
                    if(thangPhieunhap >= 4 && thangPhieunhap <= 6 ){
                        loadTableDataPN(phieunhap);
                        tongchi += phieunhap.getTongTien();
                    }
                }
            }

            if(choose == 2){
                defaultTableModel1.setRowCount(0);
                defaultTableModel2.setRowCount(0);
                for(PhieuxuatDTO hoadon : hoadons){
                    thangPhieuxuat = Integer.parseInt(hoadon.getNgayxuat().substring(5, 7));
                    if(thangPhieuxuat >= 7 && thangPhieuxuat <= 9 ){
                        loadTableDataPX(hoadon);
                        tongthu += hoadon.getTongtien();
                    }
                }
                for(PhieunhapDTO phieunhap : phieunhaps){
                    thangPhieunhap = phieunhap.getNgayNhap().getMonth() + 1;
                    if(thangPhieunhap >= 7 && thangPhieunhap <= 9 ){
                        loadTableDataPN(phieunhap);
                        tongchi += phieunhap.getTongTien();
                    }
                }
            }

            if(choose == 3){
                defaultTableModel1.setRowCount(0);
                defaultTableModel2.setRowCount(0);
                for(PhieuxuatDTO hoadon : hoadons){
                    thangPhieuxuat = Integer.parseInt(hoadon.getNgayxuat().substring(5, 7));
                    if(thangPhieuxuat >= 10 && thangPhieuxuat <= 12 ){
                        loadTableDataPX(hoadon);
                        tongthu += hoadon.getTongtien();
                    }
                }
                for(PhieunhapDTO phieunhap : phieunhaps){
                    thangPhieunhap = phieunhap.getNgayNhap().getMonth() + 1;
                    if(thangPhieunhap >= 10 && thangPhieunhap <= 12 ){
                        loadTableDataPN(phieunhap);
                        tongchi += phieunhap.getTongTien();
                    }
                }
            }
            txtTongthuloinhuan.setText(String.valueOf(tongthu));
            txtTongchiloinhuan.setText(String.valueOf(tongchi));
            txtLoinhuan.setText(String.valueOf(tongthu - tongchi));
        }
        if(rdbNamLN.isSelected()){
            defaultTableModel1.setRowCount(0);
            defaultTableModel2.setRowCount(0);
            String nam = String.valueOf(year1.getYear());
            String namPX, namPN;
            tongthu = 0;
            tongchi = 0;
            loinhuan = 0;
            for(PhieuxuatDTO hoadon : hoadons){
                namPX = hoadon.getNgayxuat().substring(0, 4);
                if(nam.equals(namPX)){
                    loadTableDataPX(hoadon);
                    tongthu += hoadon.getTongtien();
                }
            }
            for(PhieunhapDTO phieunhap : phieunhaps){
                namPN = phieunhap.getNgayNhap().toString().substring(0, 4);;
                System.out.print(namPN + " ");
                if(nam.equals(namPN) ){
                    loadTableDataPN(phieunhap);
                    tongchi += phieunhap.getTongTien();
                }
            }
            txtTongthuloinhuan.setText(String.valueOf(tongthu));
            txtTongchiloinhuan.setText(String.valueOf(tongchi));
            txtLoinhuan.setText(String.valueOf(tongthu - tongchi));
        }
    }//GEN-LAST:event_btnThongke4ActionPerformed

    private void rdbThangLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbThangLNActionPerformed
        // TODO add your handling code here:
        comboQuy1.setEnabled(false);
        year1.setEnabled(false);
        month1.setEnabled(true);
    }//GEN-LAST:event_rdbThangLNActionPerformed

    private void rdbQuyLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbQuyLNActionPerformed
        // TODO add your handling code here:
        month1.setEnabled(false);
        year1.setEnabled(false);
        comboQuy1.setEnabled(true);
    }//GEN-LAST:event_rdbQuyLNActionPerformed

    private void rdbNamLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNamLNActionPerformed
        // TODO add your handling code here:
        comboQuy1.setEnabled(false);
        month1.setEnabled(false);
        year1.setEnabled(true);
    }//GEN-LAST:event_rdbNamLNActionPerformed

    private void comboQuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboQuy1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboQuy1ActionPerformed

    private void menuItemQLNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemQLNCCActionPerformed
        // TODO add your handling code here:
        new NhaCungCapGUI(taikhoan.getMaTK()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuItemQLNCCActionPerformed

    private void menuItemQLNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemQLNSXActionPerformed

    }//GEN-LAST:event_menuItemQLNSXActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ThongKeGUI("AD001").setVisible(true);
//            }
//        });
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Denngay;
    private com.toedter.calendar.JDateChooser Tungay;
    private javax.swing.JButton btnBanhang;
    private javax.swing.JButton btnDangxuat;
    private javax.swing.JButton btnKhachhang;
    private javax.swing.JButton btnNhanvien;
    private javax.swing.JButton btnNhaphang;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSanpham;
    private javax.swing.JButton btnTaikhoan;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnThongke;
    private javax.swing.JButton btnThongke1;
    private javax.swing.JButton btnThongke2;
    private javax.swing.JButton btnThongke3;
    private javax.swing.JButton btnThongke4;
    private javax.swing.JButton btnTrangchu;
    private javax.swing.JButton btnXuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> comboMaCN;
    private javax.swing.JComboBox<String> comboMaNV;
    private javax.swing.JComboBox<String> comboQuy;
    private javax.swing.JComboBox<String> comboQuy1;
    private com.toedter.calendar.JDateChooser dateFrom;
    private com.toedter.calendar.JDateChooser dateTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenuMore;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDay;
    private javax.swing.JLabel lbMore;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JMenuItem menuItemQLNCC;
    private javax.swing.JMenuItem menuItemQLNSX;
    private com.toedter.calendar.JMonthChooser month;
    private com.toedter.calendar.JMonthChooser month1;
    private javax.swing.JPanel panelChuyen;
    private javax.swing.JRadioButton rbtnQuy;
    private javax.swing.JRadioButton rbtnSP;
    private javax.swing.JRadioButton rdbMaCN;
    private javax.swing.JRadioButton rdbMaNV;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNamLN;
    private javax.swing.JRadioButton rdbQuy;
    private javax.swing.JRadioButton rdbQuyLN;
    private javax.swing.JRadioButton rdbThang;
    private javax.swing.JRadioButton rdbThangLN;
    private javax.swing.JTable tblPhieuxuat;
    private javax.swing.JTable tblTonghoadon;
    private javax.swing.JTable tblTongphieunhap;
    private javax.swing.JTable tbl_TKChi;
    private javax.swing.JTextField txtLoinhuan;
    private javax.swing.JTextField txtTongchiloinhuan;
    private javax.swing.JTextField txtTongthu;
    private javax.swing.JTextField txtTongthuloinhuan;
    private javax.swing.JTextField txt_Tong;
    private com.toedter.calendar.JYearChooser year;
    private com.toedter.calendar.JYearChooser year1;
    // End of variables declaration//GEN-END:variables
}
