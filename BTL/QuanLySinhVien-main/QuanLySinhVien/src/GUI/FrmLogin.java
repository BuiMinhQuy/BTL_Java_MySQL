package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import DAO.DBConnection;
import GUI.Admin.FrmManHinhChinh;
import Model.Account;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
public class FrmLogin extends JFrame{
	private static Connection conn = null;
	private String FONT_TYPE;
	private int FONT;
	private int FONT_SIZE;
	private int COMPONENTS_HEIGHT;
	private int BUTTON_HEIGHT;
	private int BUTTON_WIDTH;

	private JPanel pnlMain;
	private JLabel lblUserName;
	private JLabel lblPassWord;
	private JLabel lblPermission;
	private JLabel lblShowPassWord;
	private JLabel lblAdmin;
	private JLabel lblStudent;
	private JLabel lblTeacher;
	private JTextField txtUserName;
	private JPasswordField txtPassWord;
	private JCheckBox cbShowPassWord;
	private JRadioButton rbAdmin;
	private JRadioButton rbStudent;
	private JRadioButton rbTeacher;
	private JButton btnLogin;
	private JButton btnExit;
	
	private FrmLogin frmLG;
	private GUI.Admin.FrmManHinhChinh frmMHCAdmin;
	private GUI.Teacher.FrmManHinhChinh frmMHCTeacher;
	private GUI.Student.FrmManHinhChinh frmMHCStudent;
	private JPanel panelTitle;
	private JLabel lblNewLabel;
	private JPanel panelUsername;
	private JPanel panelPassword;
	private JLabel lblTitle;
	private JLabel lblIcon_UTC2;
	public FrmLogin() {
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JPanel contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 0));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/res/uct2.png"))); 
		getContentPane().setBackground(new Color(255, 255, 255));
		frmLG = this;
		setBackground(new Color(255, 255, 255));
		Init();
		setTitle("Quản Lý Sinh Viên");

		pnlMain = new JPanel();
		pnlMain.setLayout(null);
		pnlMain = (JPanel) getContentPane();
		getContentPane().setLayout(null);

		lblUserName = new JLabel("Tên Đăng Nhâp :");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(213, 379, 177, 40);
		lblUserName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pnlMain.add(lblUserName);

		lblPassWord = new JLabel("Mật Khẩu :");
		lblPassWord.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassWord.setBounds(231, 460, 118, 40);
		lblPassWord.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pnlMain.add(lblPassWord);

		lblPermission = new JLabel("Quyền Truy Cập :");
		lblPermission.setBounds(231, 540, 159, 40);
		lblPermission.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pnlMain.add(lblPermission);
		
		rbAdmin = new JRadioButton();
		rbAdmin.setBounds(400, 540, 20, 40);
		rbAdmin.setBackground(new Color(255, 255, 255));
		rbAdmin.setSelected(true);
		pnlMain.add(rbAdmin);
		
		lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(424, 540, 50, 40);
		lblAdmin.setFont(new Font("Arial", Font.PLAIN, 16));
		pnlMain.add(lblAdmin);
		
		rbStudent = new JRadioButton();
		rbStudent.setBounds(516, 540, 20, 40);
		rbStudent.setBackground(new Color(255, 255, 255));
		pnlMain.add(rbStudent);
		
		lblStudent = new JLabel("Student");
		lblStudent.setBounds(538, 540, 60, 40);
		lblStudent.setFont(new Font("Arial", Font.PLAIN, 16));
		pnlMain.add(lblStudent);

		rbTeacher = new JRadioButton();
		rbTeacher.setBounds(624, 540, 20, 40);
		rbTeacher.setBackground(new Color(255, 255, 255));
		pnlMain.add(rbTeacher);
		
		lblTeacher = new JLabel("Teacher");
		lblTeacher.setBounds(648, 540, 60, 40);
		lblTeacher.setFont(new Font("Arial", Font.PLAIN, 16));
		pnlMain.add(lblTeacher);
		
		// Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(rbAdmin);
        group.add(rbStudent);
        group.add(rbTeacher);
		
		cbShowPassWord = new JCheckBox();
		cbShowPassWord.setBounds(400, 592, 20, 40);
		cbShowPassWord.setBackground(new Color(255, 255, 255));
		cbShowPassWord.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	if(cbShowPassWord.isSelected()) {
            		txtPassWord.setEchoChar((char)0);
            	}
            	else {
            		txtPassWord.setEchoChar('*');
            	}
            }
		});
		pnlMain.add(cbShowPassWord);

		lblShowPassWord = new JLabel("Hiển Thị Mật Khẩu");
		lblShowPassWord.setBounds(424, 592, 174, 40);
		lblShowPassWord.setFont(new Font("Arial", Font.PLAIN, 16));
		pnlMain.add(lblShowPassWord);

		btnLogin = new JButton("Đăng Nhập ");
		btnLogin.setMnemonic(KeyEvent.VK_ENTER);
		btnLogin.setFocusPainted(false);
		btnLogin.setBorder(null);
		btnLogin.setBounds(231, 665, 217, 55);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 0, 83));
		btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account ac = new Account();
				String userName = txtUserName.getText();
				String passWord = String.valueOf(txtPassWord.getPassword());
				int permission = 0;
				if(rbAdmin.isSelected()) {
					permission = 0;
				}
				else if(rbStudent.isSelected()) {
					permission = 1;
				}
				else if(rbTeacher.isSelected()) {
					permission = 2;
				}
				else {
					JOptionPane.showMessageDialog(pnlMain, "Vui lòng chọn quyền trước khi đăng nhập",  "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
				}
				try {
					if(ac.checkLogin(userName, passWord, permission, conn)) {
						switch(permission) {
						case 0:
							frmMHCAdmin = new GUI.Admin.FrmManHinhChinh(frmLG, conn, userName);
							frmMHCAdmin.setVisible(true);
							setVisible(false);
							break;
						case 1:
							frmMHCStudent = new GUI.Student.FrmManHinhChinh(frmLG, conn, userName);
							frmMHCStudent.setVisible(true);
							setVisible(false);
							break;
						default:
							frmMHCTeacher = new GUI.Teacher.FrmManHinhChinh(frmLG, conn, userName);
							frmMHCTeacher.setVisible(true);
							setVisible(false);
							break;
						}
					}
					else {
						JOptionPane.showMessageDialog(pnlMain, "Tài khoản, mật khẩu hoặc quyền đăng nhập không đúng! Vui long kiểm tra lại!",  "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(Exception ex) {
					ex.printStackTrace();
					
					JOptionPane.showMessageDialog(pnlMain, ex.getMessage(),  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pnlMain.add(btnLogin);
		
		btnExit = new JButton("Thoát");
		btnExit.setMnemonic(KeyEvent.VK_1);
		btnExit.setFocusPainted(false);
		btnExit.setBorder(null);
		btnExit.setBounds(516, 665, 217, 55);
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(0, 0, 83));
		btnExit.setFont(new Font("Arial", Font.BOLD, 18));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(pnlMain, "Bạn có muốn thoát!", "Thông tin", JOptionPane.OK_CANCEL_OPTION);
				if(choose == JOptionPane.OK_OPTION) {
					setVisible(false);
				}
			}
		});
		pnlMain.add(btnExit);
		
		// Frame Size
		setSize(950, 780);
		//setBounds(0, 0, FRM_WIDTH, FRM_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		panelTitle = new JPanel();
		panelTitle.setBounds(-15, 0, 978, 94);
		panelTitle.setBackground(new Color(0, 0, 83));
		getContentPane().add(panelTitle);
		panelTitle.setLayout(null);
		
		lblTitle = new JLabel("ĐẠI HỌC GIAO THÔNG VẬN TẢI PHÂN HIỆU TẠI TP.HCM");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(8, 0, 962, 94);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 29));
		panelTitle.add(lblTitle);
		
		panelUsername = new JPanel();
		panelUsername.setBounds(400, 372, 308, 55);
		getContentPane().add(panelUsername);
		panelUsername.setLayout(null);
		
				txtUserName = new JTextField();
				txtUserName.setBounds(0, 0, 308, 55);
				panelUsername.add(txtUserName);
				txtUserName.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						if(txtUserName.getText().equals("Username")) {
							txtUserName.setText("");
						}
						else {
							txtUserName.selectAll();
						}
					}
					@Override
					public void focusLost(FocusEvent e) {
						if(txtUserName.getText().equals("")) {
							txtUserName.setText("Username");
						}
					}
				});
				txtUserName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				txtUserName.setText("UserName");
				txtUserName.setFont(new Font("Arial", Font.PLAIN, 18));
		
		panelPassword = new JPanel();
		panelPassword.setBounds(400, 455, 308, 55);
		panelPassword.setLayout(null);
		getContentPane().add(panelPassword);
		
		txtPassWord = new JPasswordField(10);
//		txtPassWord.setEchoChar((char)0);
		txtPassWord.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPassWord.getText().equals("PassWord")) {
//					txtPassWord.setEchoChar((char)0);
					txtPassWord.setText("");
				}
				else {
					txtPassWord.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPassWord.getText().equals("")) {
//					txtPassWord.setEchoChar((char)0);
					txtPassWord.setText("PassWord");
					
				}
			}
		});
		txtPassWord.setText("PassWord");
		txtPassWord.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtPassWord.setBounds(0, 0, 308, 55);
		panelPassword.add(txtPassWord);
		txtPassWord.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		
		JPanel panelLogo_UTC2 = new JPanel();
		panelLogo_UTC2.setBackground(SystemColor.text);
		panelLogo_UTC2.setBounds(0, 93, 950, 247);
		contentPane.add(panelLogo_UTC2);
		panelLogo_UTC2.setLayout(null);
		
		lblIcon_UTC2 = new JLabel("");
		lblIcon_UTC2.setRequestFocusEnabled(false);
		lblIcon_UTC2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon_UTC2.setBounds(0, 0, 948, 247);
		ImageIcon logo_login_mhc = new ImageIcon(FrmManHinhChinh.class.getResource("/res/uct2.png"));
		Image LgLogin = logo_login_mhc.getImage();
		Image newLgLogin = LgLogin.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		logo_login_mhc = new ImageIcon(newLgLogin);
		panelLogo_UTC2.add(lblIcon_UTC2);
		lblIcon_UTC2.setIcon(logo_login_mhc);
		
		setUndecorated(false);
	}
	public void Init() {
		InitGUI init = new InitGUI();
		this.FONT_TYPE = init.getFONT_TYPE();
		this.FONT = init.getFONT();
		this.FONT_SIZE = init.getFONT_SIZE();
		this.COMPONENTS_HEIGHT = init.getCOMPONENTS_HEIGHT();
		this.BUTTON_HEIGHT = init.getBUTTON_HEIGHT();
		this.BUTTON_WIDTH = init.getBUTTON_WIDTH();
	}
	public static void main(String[] args) {
		try {
			conn = DBConnection.initializeDatabase();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		FrmLogin frmLogin = new FrmLogin();
		frmLogin.setVisible(true);
	}
}
