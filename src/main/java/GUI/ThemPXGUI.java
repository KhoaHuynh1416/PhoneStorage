/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChitietphieuxuatBUS;
import BUS.PhieuxuatBUS;
import BUS.ChinhanhBUS;
import BUS.NhanvienBUS;
import BUS.Sanpham1BUS;
import BUS.TaikhoanBUS;
import DTO.ChitietphieuxuatDTO;
import DTO.PhieuxuatDTO;
import DTO.ChinhanhDTO;
import DTO.NhanvienDTO;
import DTO.SanPhamDTO;
import DTO.TaikhoanDTO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class ThemPXGUI extends javax.swing.JFrame {

    PhieuxuatDTO phieuxuat;
    ChitietphieuxuatDTO chitietPX;
    PhieuxuatBUS phieuxuatBUS;
    Sanpham1BUS sanphamBUS;
    ChitietphieuxuatBUS chitietphieuxuatBUS;
    ChinhanhBUS khachHangBUS;
    NhanvienBUS nhanvienBUS;
    DefaultTableModel defaultTableModel;
    TaikhoanDTO taikhoan;
    TaikhoanBUS taikhoanBUS;
    List<ChitietphieuxuatDTO> chitietPXs = new ArrayList<>();
    
    public ThemPXGUI(String username) throws Exception {
        
        initComponents();
        this.getContentPane().setBackground(Color.white);
        
        taikhoanBUS = new TaikhoanBUS();
        taikhoan = taikhoanBUS.getTaiKhoanByMaTK(username);
        
        defaultTableModel = new DefaultTableModel(){
            @Override
            // ham ben duoi duoc xay dung de khong cho user edit du lieu, day nhu la mot anonymous
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblSanpham.setModel(defaultTableModel);
        
        defaultTableModel.addColumn("MÃ SẢN PHẨM");
        defaultTableModel.addColumn("TÊN SẢN PHẨM");
        defaultTableModel.addColumn("MÃ LOẠI");
        defaultTableModel.addColumn("SỐ LƯỢNG MUA");
        defaultTableModel.addColumn("ĐƠN GIÁ (Triệu đồng)");
        defaultTableModel.addColumn("THÀNH TIỀN (Triệu đồng)");
        
        loadData();
        
        //Them Popup cho table
        tblSanpham.setComponentPopupMenu(jPopupMenuDSHH);
        
        txtMaPX
                .addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    txtMaCN
                            .requestFocus();
                }
            }
        });
        
        txtMaCN
                .addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    txtMaNV.requestFocus();
                }
            }
        });
        
        txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    comboboxMaSp.requestFocus();
                }
            }
        });
        
        comboboxMaSp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    txtSoluong.requestFocus();
                }
            }
        });
    }
    private void loadData() throws Exception{
        //lấy ngày hiện tại
        txtNgayxuat.setText(String.valueOf(java.time.LocalDate.now()));
        
        sanphamBUS = new Sanpham1BUS();
        List<SanPhamDTO> sanphams = sanphamBUS.getAllSanpham();
        for(SanPhamDTO sanPham : sanphams){
            comboboxMaSp.addItem(sanPham.getMaSP());
        }
         //lấy mã chi nhánh
        khachHangBUS = new ChinhanhBUS();
        List<ChinhanhDTO> khachhangs = khachHangBUS.getAllChiNhanh();
        for(ChinhanhDTO khachhang : khachhangs){
            comboMaCN
                    .addItem(khachhang.getMaChiNhanh());
        }
        
        //lấy mã nhân viên
        nhanvienBUS = new NhanvienBUS();
        List<NhanvienDTO> nhanviens = nhanvienBUS.showAll();
        for(NhanvienDTO nhanvien : nhanviens){
            comboMaNV.addItem(nhanvien.getmanv());
        }
    }
    
    private void loadDataTable(String maSP) throws Exception{
        DefaultTableModel model = (DefaultTableModel) tblSanpham.getModel();
        SanPhamDTO sanpham = new SanPhamDTO();
        sanpham = sanphamBUS.getSanphamBYMaSP(maSP);
        
        model.addRow(new Object[]{sanpham.getMaSP(), sanpham.getTenSP(), sanpham.getMaLoai(), sanpham.getMaNSX(),
            txtSoluong.getText(), sanpham.getDonGia(), txtThanhtien.getText()});
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuDSHH = new javax.swing.JPopupMenu();
        jMenuItemXoa = new javax.swing.JMenuItem();
        jPanel12 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanpham = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnThoat3 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtMaPX = new javax.swing.JTextField();
        txtMaCN = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtNgayxuat = new javax.swing.JTextField();
        comboMaNV = new javax.swing.JComboBox<>();
        comboMaCN = new javax.swing.JComboBox<>();
        lbCheckMaPX = new javax.swing.JLabel();
        lbCheckMaCN = new javax.swing.JLabel();
        lbCheckmaNV = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtTongtien = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        comboboxMaSp = new javax.swing.JComboBox<>();
        txtDongia = new javax.swing.JTextField();
        txtThanhtien = new javax.swing.JTextField();
        txtSoluong = new javax.swing.JTextField();
        btnChonmua = new javax.swing.JButton();
        lbCheckSL = new javax.swing.JLabel();
        lbCheckSP = new javax.swing.JLabel();

        jMenuItemXoa.setText("Xóa");
        jMenuItemXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemXoaActionPerformed(evt);
            }
        });
        jPopupMenuDSHH.add(jMenuItemXoa);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thêm hóa đơn");
        setBackground(new java.awt.Color(153, 255, 153));

        jPanel12.setBackground(new java.awt.Color(204, 255, 204));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 0));
        jLabel26.setText("THÊM PHIẾU XUẤT");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hàng hóa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        tblSanpham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanphamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSanpham);

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnThoat3.setBackground(new java.awt.Color(204, 255, 153));
        btnThoat3.setText("Trở lại");
        btnThoat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoat3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(413, 413, 413)
                .addComponent(btnThem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThoat3)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThoat3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));

        jPanel15.setBackground(new java.awt.Color(204, 255, 153));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chung", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        jLabel29.setText("Mã phiếu: ");

        jLabel30.setText("Ngày xuất:");

        jLabel31.setText("Mã chi nhánh:");

        jLabel32.setText("Mã nhân viên: ");

        txtMaPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPXActionPerformed(evt);
            }
        });

        txtMaCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCNActionPerformed(evt);
            }
        });

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        txtNgayxuat.setEditable(false);
        txtNgayxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayxuatActionPerformed(evt);
            }
        });

        comboMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        comboMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMaNVActionPerformed(evt);
            }
        });

        comboMaCN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        comboMaCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMaCNActionPerformed(evt);
            }
        });

        lbCheckMaPX.setForeground(new java.awt.Color(255, 51, 0));

        lbCheckMaCN.setForeground(new java.awt.Color(255, 51, 0));

        lbCheckmaNV.setForeground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCheckMaPX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaPX, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtMaCN)
                            .addComponent(lbCheckMaCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel30))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCheckmaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtNgayxuat))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboMaCN, 0, 75, Short.MAX_VALUE)
                    .addComponent(comboMaNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtMaPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(lbCheckMaPX, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtMaCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMaCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(lbCheckMaCN, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(lbCheckmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtNgayxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(204, 255, 153));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh toán", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N
        jPanel16.setToolTipText("");

        jLabel34.setText("Tổng tiền: ");

        txtTongtien.setEditable(false);
        txtTongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongtienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(362, 362, 362))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addGap(79, 79, 79)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 102, 0))); // NOI18N

        jLabel21.setText("Mã sản phẩm: ");

        jLabel22.setText("Đơn giá: ");

        jLabel23.setText("Số lượng mua: ");

        jLabel24.setText("Thành tiền: ");

        comboboxMaSp.setMaximumRowCount(50);
        comboboxMaSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn sản phẩm" }));
        comboboxMaSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxMaSpActionPerformed(evt);
            }
        });

        txtDongia.setEditable(false);

        txtThanhtien.setEditable(false);
        txtThanhtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhtienActionPerformed(evt);
            }
        });

        txtSoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoluongActionPerformed(evt);
            }
        });

        btnChonmua.setBackground(new java.awt.Color(204, 255, 153));
        btnChonmua.setText("Chọn mua");
        btnChonmua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonmuaActionPerformed(evt);
            }
        });

        lbCheckSL.setForeground(new java.awt.Color(255, 51, 0));

        lbCheckSP.setForeground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCheckSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboboxMaSp, 0, 145, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbCheckSL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoluong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtThanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel22)
                        .addGap(29, 29, 29)
                        .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 75, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChonmua)
                        .addGap(103, 103, 103))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(comboboxMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCheckSP, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCheckSL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(txtThanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnChonmua))
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.getAccessibleContext().setAccessibleName("Chi tiết phiếu xuất");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoat3ActionPerformed
        // TODO add your handling code here:
        new PhieuxuatGUI(taikhoan.getMaTK()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThoat3ActionPerformed

    private void txtMaCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCNActionPerformed
        // TODO add your handling code here:
        if("".equals(txtMaCN
                .getText()) ){
                lbCheckMaCN
                        .setText("Vui lòng nhập mã chi nhánh!");
            }
        else{
            lbCheckMaCN
                    .setText("");
        }
    }//GEN-LAST:event_txtMaCNActionPerformed

    private void txtTongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongtienActionPerformed

    // cách không cho combobox chọn trước: chọn cho edit -> thêm chữ chọn sản phẩm -> tắt cho edit
    private void comboboxMaSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxMaSpActionPerformed
        // TODO add your handling code here:
        
        SanPhamDTO sanpham = new SanPhamDTO();
        try {
            sanpham = sanphamBUS.getSanphamBYMaSP(String.valueOf(comboboxMaSp.getSelectedItem()));
        } catch (Exception ex) {
            Logger.getLogger(ThemPXGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(sanpham.getSoLuong() != 0){
            txtDongia.setText(String.valueOf(sanpham.getDonGia())); // lấy đơn giá của sản phẩm dc chọn
        }
        else{
            lbCheckSP.setText("Hết hàng!!! Chon sản phẩm khác!!!");
        }

    }//GEN-LAST:event_comboboxMaSpActionPerformed

    private void txtNgayxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayxuatActionPerformed
        // TODO add your handling code here:
        txtNgayxuat.setText(String.valueOf(java.time.LocalDate.now()));
    }//GEN-LAST:event_txtNgayxuatActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        
        
        if("".equals(txtMaPX
                .getText()) || "".equals(txtMaCN
                .getText()) || "".equals(txtMaNV.getText())){
            if("".equals(txtMaPX
                    .getText()) ){
                lbCheckMaPX
                        .setText("Vui lòng nhập mã phiếu xuất!");
            }
            if("".equals(txtMaCN
                    .getText()) ){
                lbCheckMaCN
                        .setText("Vui lòng nhập mã chi nhánh!");
            }
            if("".equals(txtMaNV.getText()) ){
                lbCheckmaNV.setText("Vui lòng nhập mã nhân viên!");
            }
        }
        
        else{
            phieuxuat = new PhieuxuatDTO();
            phieuxuatBUS = new PhieuxuatBUS();
            chitietphieuxuatBUS = new ChitietphieuxuatBUS();
            phieuxuat.setMaPX
        (txtMaPX.getText());
            phieuxuat.setNgayxuat(txtNgayxuat.getText());
            phieuxuat.setMaCN(txtMaCN.getText());
            phieuxuat.setMaNV(txtMaNV.getText());
            phieuxuat.setTongtien(tongtien);
            
            phieuxuatBUS.insertPhieuxuat(phieuxuat);

            for(ChitietphieuxuatDTO CTPX
                    : chitietPXs){
                chitietphieuxuatBUS.addChitietPhieuxuat(CTPX
                ); 
            }

            new PhieuxuatGUI(taikhoan.getMaTK()).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnThemActionPerformed


    float tongtien = 0, tongkm = 0, tientra = 0;
    
    private void tinhTien(){
        tongtien += Float.parseFloat(txtThanhtien.getText());
        
        txtTongtien.setText(String.valueOf(tongtien));
    }
    
    private void suaSoluong(String maSP, int check) throws Exception{
        SanPhamDTO sanpham = new SanPhamDTO();
        sanpham = sanphamBUS.getSanphamBYMaSP(String.valueOf(comboboxMaSp.getSelectedItem()));
        if(check == 1){
            sanpham.setSoLuong(sanpham.getSoLuong() - Integer.parseInt(txtSoluong.getText()));
            sanphamBUS.updateSoluongSP(sanpham);
        }
        else{
            sanpham.setSoLuong(sanpham.getSoLuong() + Integer.parseInt(txtSoluong.getText()));
            sanphamBUS.updateSoluongSP(sanpham);
        }
        
    }
    
    private void themChitiet(){
        chitietPX
                = new ChitietphieuxuatDTO();
        
        chitietPX
                .setMapx(txtMaPX
                        .getText());
        chitietPX
                .setMasp(String.valueOf(comboboxMaSp.getSelectedItem()));
        chitietPX
                .setSoluongmua(Integer.parseInt(txtSoluong.getText()));
        chitietPX
                .setDongia(Float.parseFloat(txtDongia.getText()));
        chitietPX
                .setThanhtien(Float.parseFloat(txtThanhtien.getText()));

        chitietPXs.add(chitietPX);

    }
    
    private void txtSoluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoluongActionPerformed
        // TODO add your handling code here:
        if("".equals(txtSoluong.getText()) ){
                lbCheckSL.setText("Vui lòng nhập số lượng!");
        }
        else{
            lbCheckMaPX.setText("");
            //thành tiền = đơn giá * số lượng
            txtThanhtien.setText(String.valueOf(Float.parseFloat(txtDongia.getText()) * Float.parseFloat(txtSoluong.getText())));
        }
    }//GEN-LAST:event_txtSoluongActionPerformed

    private void txtThanhtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhtienActionPerformed

    private void btnChonmuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonmuaActionPerformed
        // TODO add your handling code here:
        if("".equals(txtSoluong.getText()) || comboboxMaSp.getSelectedIndex() == 0){
            if("".equals(txtSoluong.getText()) ){
                lbCheckSL.setText("Vui lòng nhập số lượng!");
            }
            if(comboboxMaSp.getSelectedIndex() == 0){
                lbCheckSP.setText("Vui lòng chọn mã sản phẩm!");
            }
        }
        else{
        tinhTien();
        
            try {
                loadDataTable(String.valueOf(comboboxMaSp.getSelectedItem()));
            } catch (Exception ex) {
                Logger.getLogger(ThemPXGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                suaSoluong(String.valueOf(comboboxMaSp.getSelectedItem()), 1);
            } catch (Exception ex) {
                Logger.getLogger(ThemPXGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        themChitiet();
        }

    }//GEN-LAST:event_btnChonmuaActionPerformed

    private void jMenuItemXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemXoaActionPerformed
        // TODO add your handling code here:
        int i = tblSanpham.getSelectedRow();
        chitietPXs.remove(i);
        defaultTableModel.removeRow(i);
        try {
            suaSoluong(String.valueOf(comboboxMaSp.getSelectedItem()), 0);
        } catch (Exception ex) {
            Logger.getLogger(ThemPXGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemXoaActionPerformed

    private void tblSanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanphamMouseClicked
        // TODO add your handling code here:
        int i = tblSanpham.getSelectedRow();
        if(i >= 0){
            ChitietphieuxuatDTO chitietPX
                    = new ChitietphieuxuatDTO();
            chitietPX
                    = chitietPXs.get(i);
            //comboboxMaSp.setSelectedIndex(WIDTH);
            txtDongia.setText(String.valueOf(chitietPX
                    .getDongia()));
            txtSoluong.setText(String.valueOf(chitietPX
                    .getSoluongmua()));
            txtThanhtien.setText(String.valueOf(chitietPX
                    .getThanhtien()));
        }
    }//GEN-LAST:event_tblSanphamMouseClicked

    private void comboMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMaNVActionPerformed
        // TODO add your handling code here:
        txtMaNV.setText(String.valueOf(comboMaNV.getSelectedItem()));
    }//GEN-LAST:event_comboMaNVActionPerformed

    private void comboMaCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMaCNActionPerformed
        // TODO add your handling code here:
        txtMaCN
                .setText(String.valueOf(comboMaCN
                        .getSelectedItem()));
    }//GEN-LAST:event_comboMaCNActionPerformed

    private void txtMaPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPXActionPerformed
        // TODO add your handling code here:
        if("".equals(txtMaPX
                .getText()) ){
                lbCheckMaPX
                        .setText("Vui lòng nhập mã phiếu xuất!");
        }
        else{
            lbCheckMaPX
                    .setText("");
        }
    }//GEN-LAST:event_txtMaPXActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
        if("".equals(txtMaNV.getText()) ){
                lbCheckmaNV.setText("Vui lòng nhập mã nhân viên!");
            }
        else{
            lbCheckmaNV.setText("");
        }
    }//GEN-LAST:event_txtMaNVActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ThemPXGUI("AD001").setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(ThemPXGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonmua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat3;
    private javax.swing.JComboBox<String> comboMaCN;
    private javax.swing.JComboBox<String> comboMaNV;
    private javax.swing.JComboBox<String> comboboxMaSp;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JMenuItem jMenuItemXoa;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPopupMenu jPopupMenuDSHH;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbCheckMaCN;
    private javax.swing.JLabel lbCheckMaPX;
    private javax.swing.JLabel lbCheckSL;
    private javax.swing.JLabel lbCheckSP;
    private javax.swing.JLabel lbCheckmaNV;
    private javax.swing.JTable tblSanpham;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtMaCN;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaPX;
    private javax.swing.JTextField txtNgayxuat;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtThanhtien;
    private javax.swing.JTextField txtTongtien;
    // End of variables declaration//GEN-END:variables
}
