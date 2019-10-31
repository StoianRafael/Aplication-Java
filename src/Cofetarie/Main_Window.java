package Cofetarie;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Image;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bugs Bunny
 */
public class Main_Window extends javax.swing.JFrame {

    /**
     * Creates new form Cofetarie
     */
    public Main_Window() {
        initComponents();
        Show_Produs_In_JTable();
        Show_Produs_In_JTable2();
    }

    String ImgPath = null;
    int pos = 0;
    Connection con = null;

    // crearea conexiunii la baza de date din access
    public Connection getConnection() {

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://torturi_db.accdb");
            return con;
        } catch (Exception ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Not Connected");

        }
        return null;
    }

    //Verificarea campurilor de introducere
    public boolean checkInputs() {
        if (txt_name.getText() == null || txt_price.getText() == null || txt_greutate.getText() == null) {
            return false;
        } else {
            try {
                Float.parseFloat(txt_price.getText());
                Float.parseFloat(txt_greutate.getText());
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    //Redimensionare imaginilor intorduse
    public ImageIcon ResizeImage(String ImagePath, byte[] pic) {
        ImageIcon myImage = null;
        if (ImagePath != null) {
            myImage = new ImageIcon(ImagePath);
        } else {
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }
    
    //Display Data In JTable:
    //1-Fill Arraylist with the data
    public ArrayList<Produs> getProdusList() {
        ArrayList<Produs> produsList = new ArrayList<Produs>();
        Connection con = getConnection();
        String query = "SELECT * FROM torturi";
        Statement st;
        ResultSet rs;
        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Produs produs;
            while (rs.next()) {
                produs = new Produs(rs.getInt("id"), rs.getString("name"), Float.parseFloat(rs.getString("price")), Float.parseFloat(rs.getString("greutate")), rs.getBytes("image"));
                produsList.add(produs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produsList;
    }

    //2-Populate The Jtable pentru cofetar
    public void Show_Produs_In_JTable() {
        ArrayList<Produs> list = getProdusList();
        DefaultTableModel model = (DefaultTableModel) JTable_Produs.getModel();

        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getGreutate();

            model.addRow(row);
        }
    }
 //2-Populate The Jtable pentru client
    public void Show_Produs_In_JTable2() {
        ArrayList<Produs> list = getProdusList();
        DefaultTableModel model = (DefaultTableModel) JTable_Produs2.getModel();

        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getGreutate();
            model.addRow(row);
        }
    }
    //afisarea informatiilor din tabel in capurile respective pentru cofetar
    public void ShowItem(int index) {
        txt_id.setText(Integer.toString(getProdusList().get(index).getId()));
        txt_name.setText(getProdusList().get(index).getName());
        txt_price.setText(Float.toString(getProdusList().get(index).getPrice()));
        txt_greutate.setText(Float.toString(getProdusList().get(index).getGreutate()));
        lbl_image.setIcon(ResizeImage(null, getProdusList().get(index).getImage()));

    }
    //afisarea informatiilor din tabel in capurile respective pentru client
    public void ShowItem2(int index) {

        txt_name2.setText(getProdusList().get(index).getName());
        txt_price2.setText(Float.toString(getProdusList().get(index).getPrice()));
        txt_greutate2.setText(Float.toString(getProdusList().get(index).getGreutate()));
        lbl_image2.setIcon(ResizeImage(null, getProdusList().get(index).getImage()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Login = new javax.swing.JPanel();
        username_text = new javax.swing.JTextField();
        password_text = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Client = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Produs2 = new javax.swing.JTable();
        Btn_buy = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_name2 = new javax.swing.JTextField();
        txt_price2 = new javax.swing.JTextField();
        lbl_image2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cos_de_cumparaturi = new javax.swing.JTextField();
        txt_greutate2 = new javax.swing.JTextField();
        txt_cantitate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Btn_tot_tortul = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Cofetar = new javax.swing.JPanel();
        Btn_insert = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable_Produs = new javax.swing.JTable();
        Btn_Choose_Image = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        Btn_Previous = new javax.swing.JButton();
        Btn_Next = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();
        lbl_image = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_greutate = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new java.awt.CardLayout());

        Login.setLayout(null);

        username_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_textActionPerformed(evt);
            }
        });
        Login.add(username_text);
        username_text.setBounds(410, 250, 396, 36);

        password_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_textActionPerformed(evt);
            }
        });
        Login.add(password_text);
        password_text.setBounds(410, 380, 396, 36);

        jButton1.setText("Login Client");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Login.add(jButton1);
        jButton1.setBounds(90, 530, 230, 50);

        jButton2.setText("Exit");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Login.add(jButton2);
        jButton2.setBounds(520, 630, 150, 50);

        jButton5.setText("Login Cofetar");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Login.add(jButton5);
        jButton5.setBounds(850, 520, 230, 50);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Username");
        Login.add(jLabel1);
        jLabel1.setBounds(240, 250, 130, 36);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Password");
        Login.add(jLabel2);
        jLabel2.setBounds(250, 378, 121, 40);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background2.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        Login.add(jLabel7);
        jLabel7.setBounds(0, -20, 1180, 850);

        jPanel1.add(Login, "card2");

        Client.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Client.setLayout(null);

        JTable_Produs2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nume", "Pret", "Greutate"
            }
        ));
        JTable_Produs2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_Produs2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Produs2);

        Client.add(jScrollPane1);
        jScrollPane1.setBounds(657, 41, 453, 403);

        Btn_buy.setText("Buy");
        Btn_buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_buyActionPerformed(evt);
            }
        });
        Client.add(Btn_buy);
        Btn_buy.setBounds(22, 695, 123, 37);

        jButton7.setText("Checkout");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        Client.add(jButton7);
        jButton7.setBounds(850, 530, 142, 43);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nume");
        Client.add(jLabel5);
        jLabel5.setBounds(43, 41, 71, 38);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Pret");
        Client.add(jLabel10);
        jLabel10.setBounds(43, 116, 64, 37);

        txt_name2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_name2ActionPerformed(evt);
            }
        });
        Client.add(txt_name2);
        txt_name2.setBounds(161, 43, 381, 42);

        txt_price2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_price2ActionPerformed(evt);
            }
        });
        Client.add(txt_price2);
        txt_price2.setBounds(161, 115, 381, 46);

        lbl_image2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Client.add(lbl_image2);
        lbl_image2.setBounds(161, 286, 134, 103);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Image");
        Client.add(jLabel12);
        jLabel12.setBounds(43, 316, 86, 35);

        cos_de_cumparaturi.setText("0");
        cos_de_cumparaturi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cos_de_cumparaturiActionPerformed(evt);
            }
        });
        Client.add(cos_de_cumparaturi);
        cos_de_cumparaturi.setBounds(952, 471, 106, 43);

        txt_greutate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_greutate2ActionPerformed(evt);
            }
        });
        Client.add(txt_greutate2);
        txt_greutate2.setBounds(161, 196, 381, 45);

        txt_cantitate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cantitateActionPerformed(evt);
            }
        });
        Client.add(txt_cantitate);
        txt_cantitate.setBounds(151, 526, 383, 52);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Greutate");
        Client.add(jLabel3);
        jLabel3.setBounds(43, 193, 92, 45);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel14.setText("Cos de cumparaturi");
        Client.add(jLabel14);
        jLabel14.setBounds(730, 471, 204, 43);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel15.setText("Cantitate");
        Client.add(jLabel15);
        jLabel15.setBounds(37, 527, 96, 42);

        Btn_tot_tortul.setText("Tot");
        Btn_tot_tortul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_tot_tortulActionPerformed(evt);
            }
        });
        Client.add(Btn_tot_tortul);
        Btn_tot_tortul.setBounds(151, 625, 96, 33);

        jButton3.setText("First");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Client.add(jButton3);
        jButton3.setBounds(43, 439, 120, 34);

        jButton4.setText("Previous");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        Client.add(jButton4);
        jButton4.setBounds(192, 439, 89, 34);

        jButton6.setText("Next");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        Client.add(jButton6);
        jButton6.setBounds(310, 439, 91, 34);

        jButton8.setText("Last");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        Client.add(jButton8);
        jButton8.setBounds(430, 439, 112, 34);

        jButton10.setText("Exit");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        Client.add(jButton10);
        jButton10.setBounds(990, 695, 112, 37);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tort_cu_capsuni-1440x900.jpg"))); // NOI18N
        jLabel11.setText("jLabel11");
        Client.add(jLabel11);
        jLabel11.setBounds(-10, -5, 1170, 800);

        jPanel1.add(Client, "card3");

        Cofetar.setForeground(new java.awt.Color(0, 102, 102));
        Cofetar.setLayout(null);

        Btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.jpg"))); // NOI18N
        Btn_insert.setText("Insert tort");
        Btn_insert.setIconTextGap(15);
        Btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_insertActionPerformed(evt);
            }
        });
        Cofetar.add(Btn_insert);
        Btn_insert.setBounds(170, 630, 120, 35);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID:");
        Cofetar.add(jLabel4);
        jLabel4.setBounds(50, 73, 72, 42);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Name:");
        Cofetar.add(jLabel6);
        jLabel6.setBounds(50, 155, 72, 39);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Image:");
        Cofetar.add(jLabel8);
        jLabel8.setBounds(30, 440, 72, 39);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Price:");
        Cofetar.add(jLabel9);
        jLabel9.setBounds(50, 247, 72, 39);

        txt_name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        txt_name.setPreferredSize(new java.awt.Dimension(55, 40));
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });
        Cofetar.add(txt_name);
        txt_name.setBounds(134, 153, 419, 42);

        txt_price.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        txt_price.setPreferredSize(new java.awt.Dimension(55, 40));
        txt_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_priceActionPerformed(evt);
            }
        });
        Cofetar.add(txt_price);
        txt_price.setBounds(134, 245, 419, 42);

        txt_id.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        txt_id.setEnabled(false);
        txt_id.setPreferredSize(new java.awt.Dimension(55, 40));
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        Cofetar.add(txt_id);
        txt_id.setBounds(134, 73, 419, 42);

        JTable_Produs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Greutate"
            }
        ));
        JTable_Produs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProdusMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTable_Produs);

        Cofetar.add(jScrollPane2);
        jScrollPane2.setBounds(720, 50, 453, 403);

        Btn_Choose_Image.setText("Choose Image");
        Btn_Choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Choose_ImageActionPerformed(evt);
            }
        });
        Cofetar.add(Btn_Choose_Image);
        Btn_Choose_Image.setBounds(271, 472, 120, 30);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.jpg"))); // NOI18N
        jButton9.setText("Update");
        jButton9.setIconTextGap(15);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        Cofetar.add(jButton9);
        jButton9.setBounds(530, 630, 141, 35);

        Btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/first.jpg"))); // NOI18N
        Btn_First.setText("First");
        Btn_First.setIconTextGap(15);
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });
        Cofetar.add(Btn_First);
        Btn_First.setBounds(50, 710, 180, 35);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.jpg"))); // NOI18N
        jButton11.setText("Delete");
        jButton11.setIconTextGap(15);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        Cofetar.add(jButton11);
        jButton11.setBounds(340, 630, 141, 35);

        Btn_Previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/previous.jpg"))); // NOI18N
        Btn_Previous.setText("Previous");
        Btn_Previous.setIconTextGap(15);
        Btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PreviousActionPerformed(evt);
            }
        });
        Cofetar.add(Btn_Previous);
        Btn_Previous.setBounds(280, 710, 116, 35);

        Btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/next.jpg"))); // NOI18N
        Btn_Next.setText("Next");
        Btn_Next.setIconTextGap(15);
        Btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NextActionPerformed(evt);
            }
        });
        Cofetar.add(Btn_Next);
        Btn_Next.setBounds(440, 710, 116, 35);

        Btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/last.jpg"))); // NOI18N
        Btn_Last.setText("Last");
        Btn_Last.setIconTextGap(15);
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });
        Cofetar.add(Btn_Last);
        Btn_Last.setBounds(600, 710, 180, 35);

        lbl_image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Cofetar.add(lbl_image);
        lbl_image.setBounds(130, 440, 123, 97);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Greutate");
        Cofetar.add(jLabel13);
        jLabel13.setBounds(29, 339, 92, 59);

        txt_greutate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_greutateActionPerformed(evt);
            }
        });
        Cofetar.add(txt_greutate);
        txt_greutate.setBounds(133, 349, 414, 49);

        jButton12.setText("Exit");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        Cofetar.add(jButton12);
        jButton12.setBounds(1020, 710, 100, 40);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tort_cu_capsuni-1440x900.jpg"))); // NOI18N
        jLabel16.setText("jLabel16");
        Cofetar.add(jLabel16);
        jLabel16.setBounds(0, 0, 1380, 810);

        jPanel1.add(Cofetar, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1195, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //  buton pentru iesirea din aplicatie
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      //buton login pentru clientii aplicatiei care ii directioneaza catre panel-ul respectiv
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql;
        sql = "select * from Login WHERE Username=? and Password=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username_text.getText());
            pst.setString(2, password_text.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                CardLayout a;
                a = (CardLayout) (jPanel1.getLayout());
                a.show(jPanel1, "card3");
                Show_Produs_In_JTable2();

            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void Btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_insertActionPerformed
        // inserarea unui nou produs nou in lista
        if (checkInputs() && ImgPath != null) {

            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT iNTO torturi (name,price,greutate,image)" + "values(?,?,?,?)");
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_price.getText());
                ps.setString(3, txt_greutate.getText());

                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(4, img);

                ps.executeUpdate();
                Show_Produs_In_JTable();
                JOptionPane.showMessageDialog(null, "Data Insered");
            } catch (Exception ex) {
                // Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
        }


    }//GEN-LAST:event_Btn_insertActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // actualizarea informatiilor produsului selectat
        if (checkInputs() && txt_id.getText() != null) {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            //update without image
            if (ImgPath == null) {
                try {
                    UpdateQuery = "UPDATE torturi SET name= ?,price=?,greutate=?" + " WHERE id=?";
                    ps = con.prepareStatement(UpdateQuery);

                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    ps.setString(3, txt_greutate.getText());

                    ps.setInt(4, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                    Show_Produs_In_JTable();
                    JOptionPane.showMessageDialog(null, "ProductUpdate");

                } catch (SQLException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            } //update with image
            else {
                try {
                    InputStream img = new FileInputStream(new File(ImgPath));
                    UpdateQuery = "UPDATE torturi SET name= ?,price=?,greutate=?,image=?," + " WHERE id=?";
                    ps = con.prepareStatement(UpdateQuery);

                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    ps.setString(3, txt_greutate.getText());
                    ps.setBlob(4, img);
                    ps.setInt(5, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                    Show_Produs_In_JTable();
                    JOptionPane.showMessageDialog(null, "ProductUpdate");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
        // vizualizarea primului produs din lista
        pos = 0;
        ShowItem(pos);

    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // stergerea unui produs din lista
        if (!txt_id.getText().equals("")) {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM torturi WHERE id= ?");
                int id = Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                Show_Produs_In_JTable();
                JOptionPane.showMessageDialog(null, "Produs Deleted");
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Produs Not Deleted");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Produs Not Deleted :No Id To Delete");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void Btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PreviousActionPerformed
        // vizualizarea produsului anterior din lista
        pos--;
        if (pos <= 0) {
            pos = 0;
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn_PreviousActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed
        // vizualizarea urmatorului produs din lista 
        pos++;
        if (pos >= getProdusList().size()) {
            pos = getProdusList().size() - 1;
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn_NextActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
        // vizualizarea ultimului produs din lista
        pos = getProdusList().size() - 1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void Btn_Choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Choose_ImageActionPerformed
        // buton pentru selectarea unei imagini 
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path, null));
            ImgPath = path;
        } else {
            System.out.println("No File Selected");
        }
    }//GEN-LAST:event_Btn_Choose_ImageActionPerformed

    private void username_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_textActionPerformed

    private void JTable_ProdusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProdusMouseClicked
        // TODO add your handling code here:
        int index = JTable_Produs.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_ProdusMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // buton pentru logarea cofetarului in panel-ul pentru administrator
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql;
        sql = "select * from Login WHERE Username=? and Password=? and cofetar=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username_text.getText());
            pst.setString(2, password_text.getText());
            pst.setString(3, "da");
            rs = pst.executeQuery();

            if (rs.next()) {
                CardLayout a;
                a = (CardLayout) (jPanel1.getLayout());
                a.show(jPanel1, "card4");

            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void Btn_buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_buyActionPerformed
        // buton pentru cumpararea torturilor
        PreparedStatement pst;
        float total, pret, greutate, cantitate, greutatetotala;
        total = Float.parseFloat(cos_de_cumparaturi.getText());
        pret = Float.parseFloat(txt_price2.getText());
        greutate = Float.parseFloat(txt_greutate2.getText());
        cantitate = Float.parseFloat(txt_cantitate.getText());
        if (Float.compare(greutate, cantitate) == 1) {
            total = total + pret * cantitate;
            cos_de_cumparaturi.setText(Float.toString(total));
            String update = "Update torturi SET greutate=? WHERE name=?";
            try {
                pst = con.prepareStatement(update);
                greutatetotala = greutate - cantitate;
                pst.setString(1, Float.toString(greutatetotala));
                pst.setString(2, txt_name2.getText());
                pst.executeUpdate();

                Show_Produs_In_JTable2();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "da");
            }
        } else {
            if (Float.compare(greutate, cantitate) == 0) {
                total = total + pret * cantitate;
                cos_de_cumparaturi.setText(Float.toString(total));
                String Delete = "Delete FROM torturi WHERE name=?";
                try {

                    pst = con.prepareStatement(Delete);
                    pst.setString(1, txt_name2.getText());
                    pst.executeUpdate();

                    Show_Produs_In_JTable2();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "nu");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cantitatea este prea mare!");
            }
        }

        Show_Produs_In_JTable2();
    }//GEN-LAST:event_Btn_buyActionPerformed

    private void JTable_Produs2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_Produs2MouseClicked
        // TODO add your handling code here:
        int index = JTable_Produs2.getSelectedRow();
        ShowItem2(index);
    }//GEN-LAST:event_JTable_Produs2MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // Finalizare comanda 
        JOptionPane.showMessageDialog(null, "Ati cumparat torturi in valoare de " + cos_de_cumparaturi.getText() + "LEI");
        cos_de_cumparaturi.setText("0");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txt_name2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_name2ActionPerformed
        // JTextField pentru afisarea numelui tortului
    }//GEN-LAST:event_txt_name2ActionPerformed

    private void txt_greutate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_greutate2ActionPerformed
        // JTextField pentru afisarea greutatii tortului
    }//GEN-LAST:event_txt_greutate2ActionPerformed

    private void cos_de_cumparaturiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cos_de_cumparaturiActionPerformed
        // JTextField pentru afisarea pretului total
    }//GEN-LAST:event_cos_de_cumparaturiActionPerformed

    private void Btn_tot_tortulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_tot_tortulActionPerformed
        // buton pentru selectarea intregului tort 
        txt_cantitate.setText(txt_greutate2.getText());
    }//GEN-LAST:event_Btn_tot_tortulActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // vizualizarea primului produs din lista
        pos = 0;
        ShowItem2(pos);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // vizualizarea ultimului produs din lista
        pos = getProdusList().size() - 1;
        ShowItem2(pos);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // vizualizarea urmatorului produs din lista
        pos++;
        if (pos >= getProdusList().size()) {
            pos = getProdusList().size() - 1;
        }
        ShowItem2(pos);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // vizualizarea produsului anterior din lista
        pos--;
        if (pos <= 0) {
            pos = 0;
        }
        ShowItem2(pos);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // buton pentru iesirea din aplicatie
        System.exit(0);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // buton pentru iesirea din aplicatie
        System.exit(0);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void password_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password_textActionPerformed

    private void txt_price2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_price2ActionPerformed
        // JTextField pentru afiarea pretului tortului
    }//GEN-LAST:event_txt_price2ActionPerformed

    private void txt_cantitateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cantitateActionPerformed
        // JTextField pentru afisarea cantitatii cumparate din tort
    }//GEN-LAST:event_txt_cantitateActionPerformed

    private void txt_greutateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_greutateActionPerformed
        // JTextField pentru afisarea si introducerea greutatii tortului
    }//GEN-LAST:event_txt_greutateActionPerformed

    private void txt_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_priceActionPerformed
        // JTextField pentru afisarea si introducerea pretului tortului
    }//GEN-LAST:event_txt_priceActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // JTextField pentru afisarea si introducerea numelui tortului
    }//GEN-LAST:event_txt_nameActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // JTextField pentru afisarea ID-ului tortului
    }//GEN-LAST:event_txt_idActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Choose_Image;
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Next;
    private javax.swing.JButton Btn_Previous;
    private javax.swing.JButton Btn_buy;
    private javax.swing.JButton Btn_insert;
    private javax.swing.JButton Btn_tot_tortul;
    private javax.swing.JPanel Client;
    private javax.swing.JPanel Cofetar;
    private javax.swing.JTable JTable_Produs;
    private javax.swing.JTable JTable_Produs2;
    private javax.swing.JPanel Login;
    private javax.swing.JTextField cos_de_cumparaturi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lbl_image2;
    private javax.swing.JPasswordField password_text;
    private javax.swing.JTextField txt_cantitate;
    private javax.swing.JTextField txt_greutate;
    private javax.swing.JTextField txt_greutate2;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_name2;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_price2;
    private javax.swing.JTextField username_text;
    // End of variables declaration//GEN-END:variables
}
