package GUI.Teacher;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.InitGUI;
import GUI.Admin.FrmManHinhChinh;
import GUI.Teacher.FrmPersonalInformation;

import javax.swing.JScrollBar;

import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Model.Account;
import Model.Admin;
import Model.Teacher;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class FrmPersonalInformation  extends JInternalFrame{
	private static Connection conn = null;
	private static String userName;
	private static String ID;
	private static SimpleDateFormat formatter;

	private String FONT_TYPE;
	private int FONT;
	private int FONT_SIZE;
	private int COMPONENTS_HEIGHT;
	private int BUTTON_HEIGHT;
	private int BUTTON_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCREEN_WIDTH;

	private static JPanel contentPane;
	private static JTextField txtID;
	private static JTextField txtName;
	private static JTextField txtEmail;
	private static JTextField txtPhone;
	private static JTextField txtAddress;
	private static JTextField txtUserName;
	private static JTextField txtPermission;
	private static JTextField txtDateCreate;

	private static JButton btnUpdate;
	private static JButton btnSave;
	private static JButton btnCancel;
	private static JLabel lblAid;
	private static JTextField txtAID;

	public FrmPersonalInformation(Connection conn, String userName) {
		setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.userName = userName;
		Init();
		FrmPersonalInformation.conn = conn;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, SCREEN_WIDTH, SCREEN_HEIGHT);
		FrmPersonalInformation.conn = conn;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1178, 713);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblID = new JLabel("ID :");
		lblID.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblID.setBounds(18, 70, 104, 37);
		contentPane.add(lblID);

		JLabel lblName = new JLabel("Họ Và Tên :");
		lblName.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblName.setBounds(18, 214, 104, 37);
		contentPane.add(lblName);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblEmail.setBounds(18, 419, 104, 37);
		contentPane.add(lblEmail);

		JLabel lblPhone = new JLabel("Số Điện Thoại :");
		lblPhone.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblPhone.setBounds(18, 489, 139, 37);
		contentPane.add(lblPhone);

		JLabel lblAddress = new JLabel("Địa Chỉ :");
		lblAddress.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblAddress.setBounds(18, 557, 104, 37);
		contentPane.add(lblAddress);

		JLabel lblUserName = new JLabel("UserName :");
		lblUserName.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblUserName.setBounds(18, 277, 104, 37);
		contentPane.add(lblUserName);

		JLabel lblPermission = new JLabel("Loại Tài Khoản :");
		lblPermission.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblPermission.setBounds(18, 352, 139, 37);
		contentPane.add(lblPermission);

		JLabel lblDateCreate = new JLabel("Ngày Tạo :");
		lblDateCreate.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblDateCreate.setBounds(18, 623, 104, 37);
		contentPane.add(lblDateCreate);

		txtID = new JTextField();
		txtID.setBackground(Color.WHITE);
		txtID.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtID.setBounds(180, 70, 818, 37);
		txtID.setEditable(false);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBackground(Color.WHITE);
		txtName.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtName.setColumns(10);
		txtName.setBounds(180, 209, 818, 37);
		contentPane.add(txtName);

		txtEmail = new JTextField();
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtEmail.setColumns(10);
		txtEmail.setBounds(180, 419, 818, 37);
		contentPane.add(txtEmail);

		txtPhone = new JTextField();
		txtPhone.setBackground(Color.WHITE);
		txtPhone.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtPhone.setColumns(10);
		txtPhone.setBounds(180, 489, 818, 37);
		contentPane.add(txtPhone);

		txtAddress = new JTextField();
		txtAddress.setBackground(Color.WHITE);
		txtAddress.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtAddress.setColumns(10);
		txtAddress.setBounds(180, 557, 818, 37);
		contentPane.add(txtAddress);

		txtUserName = new JTextField();
		txtUserName.setBackground(Color.WHITE);
		txtUserName.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtUserName.setEditable(false);
		txtUserName.setColumns(10);
		txtUserName.setBounds(180, 279, 818, 37);
		contentPane.add(txtUserName);

		txtPermission = new JTextField();
		txtPermission.setBackground(Color.WHITE);
		txtPermission.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtPermission.setEditable(false);
		txtPermission.setColumns(10);
		txtPermission.setBounds(180, 352, 818, 37);
		contentPane.add(txtPermission);

		txtDateCreate = new JTextField();
		txtDateCreate.setBackground(Color.WHITE);
		txtDateCreate.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtDateCreate.setEditable(false);
		txtDateCreate.setColumns(10);
		txtDateCreate.setBounds(180, 623, 818, 37);
		contentPane.add(txtDateCreate);

		btnUpdate = new JButton("Sửa");
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnUpdate.setBounds(1033, 489, 120, 40);
		btnUpdate.setIcon(new ImageIcon("resources/update.jpg"));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update();
			}
		});
		ImageIcon iconup = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconfix.png"));
		Image icup = iconup.getImage();
		Image newiconup = icup.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconup = new ImageIcon(newiconup);
		btnUpdate.setIcon(iconup);
		contentPane.add(btnUpdate);

		btnSave = new JButton("Lưu");
		btnSave.setEnabled(false);
		btnSave.setFocusPainted(false);
		btnSave.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnSave.setBackground(Color.WHITE);
		btnSave.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnSave.setBounds(1033, 557, BUTTON_WIDTH, BUTTON_HEIGHT);
		btnSave.setIcon(new ImageIcon("resources/save.jpg"));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save();
			}
		});
		ImageIcon iconsave = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconsave.png"));
		Image icsave = iconsave.getImage();
		Image newiconsave = icsave.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconsave = new ImageIcon(newiconsave);
		btnSave.setIcon(iconsave);
		contentPane.add(btnSave);

		btnCancel = new JButton("Hủy");
		btnCancel.setEnabled(false);
		btnCancel.setFocusPainted(false);
		btnCancel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnCancel.setBounds(1033, 623, BUTTON_WIDTH, BUTTON_HEIGHT);
		btnCancel.setIcon(new ImageIcon("resources/cancel.png"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		ImageIcon iconcancel = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconclose.png"));
		Image iccancel = iconcancel.getImage();
		Image newiconcancel = iccancel.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconcancel = new ImageIcon(newiconcancel);
		btnCancel.setIcon(iconcancel);
		contentPane.add(btnCancel);
		
		lblAid = new JLabel("ID Admin :");
		lblAid.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblAid.setBounds(18, 136, 104, 37);
		contentPane.add(lblAid);
		
		txtAID = new JTextField();
		txtAID.setBackground(Color.WHITE);
		txtAID.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtAID.setEnabled(false);
		txtAID.setColumns(10);
		txtAID.setBounds(180, 139, 818, 37);
		contentPane.add(txtAID);
		
		JPanel panelTTCN = new JPanel();
		panelTTCN.setLayout(null);
		panelTTCN.setBounds(0, 0, 1178, 47);
		contentPane.add(panelTTCN);
		
		JLabel lblTTCN = new JLabel("THÔNG TIN CÁ NHÂN");
		lblTTCN.setHorizontalAlignment(SwingConstants.CENTER);
		lblTTCN.setFont(new Font("Arial", Font.BOLD, 28));
		lblTTCN.setBounds(388, 0, 409, 47);
		panelTTCN.add(lblTTCN);
		Load();
		ID = txtID.getText();
	}
	public void Init() {
		InitGUI init = new InitGUI();
		this.FONT_TYPE = init.getFONT_TYPE();
		this.FONT = init.getFONT();
		this.FONT_SIZE = init.getFONT_SIZE();
		this.COMPONENTS_HEIGHT = init.getCOMPONENTS_HEIGHT();
		this.BUTTON_HEIGHT = init.getBUTTON_HEIGHT();
		this.BUTTON_WIDTH = init.getBUTTON_WIDTH();
		this.SCREEN_WIDTH = init.getSCREEN_WIDTH();
		this.SCREEN_HEIGHT=init.getSCREEN_HEIGHT();
	}
	public static void Load() {
		txtName.setEditable(false);;
		txtEmail.setEditable(false);
		txtPhone.setEditable(false);
		txtAddress.setEditable(false);

		btnUpdate.setEnabled(true);;
		
		Account ac = new Account();
		try {
			ac = Account.findAccountofUserName(userName, conn);
		}catch(ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(ac.getAid());
		Teacher tc = new Teacher();
		try {
			tc = Teacher.findTeacherofAID(ac.getAid(), conn);
		}catch(ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtID.setText(tc.getId());
		txtName.setText(tc.getName());
		txtEmail.setText(tc.getEmail());
		txtPhone.setText(tc.getPhone());
		txtAddress.setText(tc.getAddress());
		txtAID.setText(tc.getAid());
		txtUserName.setText(ac.getUserName());
		switch(ac.getPermission()) {
		case 0:
			txtPermission.setText("Admin");
			break;
		case 1:
			txtPermission.setText("Sinh viên");
			break;
		default:
			txtPermission.setText("Giảng viên");
			break;
		}
		formatter = new SimpleDateFormat("dd/MM/yyyy");  
		txtDateCreate.setText(formatter.format(ac.getDateOfCreate()));
	}
	public static void Update() {
		txtName.setEditable(true);;
		txtEmail.setEditable(true);
		txtPhone.setEditable(true);
		txtAddress.setEditable(true);

		btnUpdate.setEnabled(false);
		btnSave.setEnabled(true);;
		btnCancel.setEnabled(true);
	}
	public static void Save() {
		try {
			Teacher tc = Teacher.findTeacher(ID, conn);
			tc.setName(txtName.getText());
			tc.setEmail(txtEmail.getText());
			tc.setPhone(txtPhone.getText());
			tc.setAddress(txtAddress.getText());
			Teacher.Edit(tc, conn);
			JOptionPane.showMessageDialog(contentPane, "Cập nhật thông tin thành công!",  "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
		} catch(ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Load();
	}
	public static void Cancel() {
		Load();
	}
}
