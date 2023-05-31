package GUI.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.DefaultDesktopManager;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.FrmLogin;
import GUI.InitGUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Point;
import javax.swing.border.EtchedBorder;

public class FrmManHinhChinh extends JFrame {
	private static int SCREEN_HEIGHT;
	private static int SCREEN_WIDTH;

	private static Connection conn;
	private static String userName;

	private JPanel pnlMain;
	private JMenuBar menuBar;
	private JMenu mnTaiKhoan;
	private JMenuItem mnItemTaiKhoan;
	private JMenuItem mnItemDoiMatKhau;
	private JMenuItem mnItemThoat;
	private JMenu mnQuanLy;
	private JMenuItem mnItemQLTaiKhoan;
	private JMenuItem mnItemQLGiangVien;
	private JMenuItem mnItemQLSinhVien;
	private JMenuItem mnItemQLBangDiem;
	private JMenuItem mnItemQLLop;
	private JMenuItem mnItemQLChiTietLop;
	private static JDesktopPane desktopPane;

	private static FrmLogin frmLG;
	private static FrmManHinhChinh frmMHC;
	private static FrmPersonalInformation frmTTCN;
	private static FrmAccount frmAC;
	private static FrmTeacher frmGV;
	private static FrmStudent frmSV;
	private static FrmTranscript frmTS;
	private static FrmCourse frmCS;
	private static FrmCourse_Class frmCSS;
	private JPanel panelQLTK;
	private JPanel panelQLGV;
	private JPanel panelQLSV;
	private JPanel panelQLBD;
	private JPanel panelQLHP;
	private JButton btnQLGV;
	private JButton btnQLSV;
	private JButton btnQLBD;
	private JButton btnQLHP;
	private JPanel panelQLLHP;
	private JButton btnQLLHP;
	private JLabel lblIconQLGV;
	private JLabel lblIconQLSV;
	private JLabel lblIconQLBD;
	private JLabel lblIconQLHP;
	private JLabel lblIconQLLHP;
	
	public void MyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
	}

	public FrmManHinhChinh(FrmLogin frmLG, Connection conn, String userName) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/res/uct2.png"))); 
		setTitle("Quản Lý Sinh Viên");
		setUndecorated(true);
		FlatMacLightLaf.setup();
		
		
		this.frmLG = frmLG;
		this.frmMHC = frmMHC; // 123
		this.frmTTCN = null;
		this.frmGV = null;
		this.frmSV = null;
		this.userName = userName;
		Init();
		this.conn = conn;
		pnlMain = new JPanel();
		pnlMain.setLayout(null);
		pnlMain = (JPanel) getContentPane();

		desktopPane = new JDesktopPane();
		desktopPane.setBorder(null);
		desktopPane.setBounds(318, 0, 1179, 685);
		setNonMovableDesktopPane(desktopPane);
		getContentPane().add(desktopPane);

		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		menuBar.setBounds(0, 0, 1150, 10);
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.setBackground(new Color(255, 255, 204));
		setJMenuBar(menuBar);

		mnTaiKhoan = new JMenu("Tài Khoản");
		menuBar.add(mnTaiKhoan);

		mnItemTaiKhoan = new JMenuItem("Tài Khoản Của Tôi");
		mnItemTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Load();
			}
		});
		mnTaiKhoan.add(mnItemTaiKhoan);

		mnItemDoiMatKhau = new JMenuItem("Đổi Mật Khẩu");
		mnItemDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmChangePassword frmCP = new FrmChangePassword(frmLG, frmMHC, conn, userName);
				frmCP.setVisible(true);
				dispose();
			}
		});
		mnTaiKhoan.add(mnItemDoiMatKhau);

		mnItemThoat = new JMenuItem("Đăng Xuất");
		mnItemThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(pnlMain, "Bạn có muốn đăng xuất !", "Thông tin",
						JOptionPane.OK_CANCEL_OPTION);
				if (choose == JOptionPane.OK_OPTION) {
					frmLG.setVisible(true);
					setVisible(false);
					dispose();
				}
			}
		});
		mnTaiKhoan.add(mnItemThoat);


		// Frame Size
		setSize(1498, 710);
		// setBounds(0, 0, FRM_WIDTH, FRM_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setUndecorated(true);
		setResizable(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel Menupanel = new JPanel();
		Menupanel.setBackground(new Color(0, 0, 83));
		Menupanel.setBorder(null);
		Menupanel.setBounds(0, 0, 319, 685);
		getContentPane().add(Menupanel);
		Menupanel.setLayout(null);
		ImageIcon logo_login_mhc = new ImageIcon(FrmManHinhChinh.class.getResource("/res/uct2.png"));
		Image LgLogin = logo_login_mhc.getImage();
		Image newLgLogin = LgLogin.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		logo_login_mhc = new ImageIcon(newLgLogin);

		panelQLTK = new JPanel();
		panelQLTK.setBackground(new Color(255, 255, 153));
		panelQLTK.setBounds(0, 329, 319, 60);
		Menupanel.add(panelQLTK);
		panelQLTK.setLayout(null);

		JButton btnQLTK = new JButton("Quản Lý Tài Khoản");
		btnQLTK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnQLTK.setBackground(new Color(173 ,216, 230));
				panelQLTK.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelQLTK.setBackground(new Color(255,255,153));
				btnQLTK.setBackground(Color.WHITE);
			}
		});
		btnQLTK.setFocusPainted(false);
		btnQLTK.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnQLTK.setHorizontalTextPosition(SwingConstants.CENTER);
		btnQLTK.setRequestFocusEnabled(false);
		btnQLTK.setBackground(new Color(255, 255, 153));
		btnQLTK.setBounds(0, 0, 319, 60);
		panelQLTK.add(btnQLTK);
		btnQLTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmAC == null || frmAC.isClosed()) {
					frmAC = new FrmAccount(conn);
					desktopPane.add(frmAC);
					frmAC.setVisible(true);
				}
				frmAC.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmAC.setResizable(false);

			}
		});
		btnQLTK.setFont(new Font("Arial", Font.BOLD, 16));
		btnQLTK.setContentAreaFilled(false);
		
		JLabel lblIconQLTK = new JLabel("");
		lblIconQLTK.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconQLTK.setBounds(0, 0, 66, 60);
		ImageIcon iconQLTK = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconperson.png"));
		Image icQLTK = iconQLTK.getImage();
		Image newiconQLTK = icQLTK.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconQLTK = new ImageIcon(newiconQLTK);
		lblIconQLTK.setIcon(iconQLTK);
		panelQLTK.add(lblIconQLTK);

		panelQLGV = new JPanel();
		panelQLGV.setBackground(new Color(255, 255, 153));
		panelQLGV.setBounds(0, 388, 319, 60);
		Menupanel.add(panelQLGV);
		panelQLGV.setLayout(null);

		btnQLGV = new JButton("Quản Lý Giảng Viên");
		btnQLGV.setFocusPainted(false);
		btnQLGV.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnQLGV.setRequestFocusEnabled(false);
		btnQLGV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmGV == null || frmGV.isClosed()) {
					frmGV = new FrmTeacher(conn);
					desktopPane.add(frmGV);
					frmGV.setVisible(true);
				}
				frmGV.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmGV.setResizable(false);
			}
		});
		btnQLGV.setFont(new Font("Arial", Font.BOLD, 16));
		btnQLGV.setBounds(0, 0, 319, 60);
//		ImageIcon iconQLGV = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconteacher.png"));
//		Image icQLGV = iconQLGV.getImage();
//		Image newiconQLGV = icQLGV.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
//		iconQLGV = new ImageIcon(newiconQLGV);
//		btnQLGV.setIcon(iconQLGV);
		panelQLGV.add(btnQLGV);
		btnQLGV.setContentAreaFilled(false);
		btnQLGV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnQLGV.setBackground(new Color(173 ,216, 230));
				panelQLGV.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelQLGV.setBackground(new Color(255,255,153));
				btnQLGV.setBackground(Color.WHITE);
			}
		});
		
		lblIconQLGV = new JLabel("");
		lblIconQLGV.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconQLGV.setBounds(0, 0, 66, 60);
		ImageIcon iconQLGV = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconteacher.png"));
		Image icQLGV = iconQLGV.getImage();
		Image newiconQLGV = icQLGV.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconQLGV = new ImageIcon(newiconQLGV);
		lblIconQLGV.setIcon(iconQLGV);
		panelQLGV.add(lblIconQLGV);

		panelQLSV = new JPanel();
		panelQLSV.setBackground(new Color(255, 255, 153));
		panelQLSV.setBounds(0, 447, 319, 60);
		Menupanel.add(panelQLSV);
		panelQLSV.setLayout(null);

		btnQLSV = new JButton("Quản Lý Sinh Viên");
		btnQLSV.setFocusPainted(false);
		btnQLSV.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnQLSV.setRequestFocusEnabled(false);
		btnQLSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmSV == null || frmSV.isClosed()) {
					frmSV = new FrmStudent(conn);
					desktopPane.add(frmSV);
					frmSV.setVisible(true);
				}
				frmSV.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmSV.setResizable(false);
			}
		});
		btnQLSV.setFont(new Font("Arial", Font.BOLD, 16));
		btnQLSV.setBounds(0, 0, 319, 60);
//		ImageIcon iconQLSV = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconstudent.png"));
//		Image icQLSV = iconQLSV.getImage();
//		Image newiconQLSV = icQLSV.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
//		iconQLSV = new ImageIcon(newiconQLSV);
//		btnQLSV.setIcon(iconQLSV);
		panelQLSV.add(btnQLSV);
		btnQLSV.setContentAreaFilled(false);
		btnQLSV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnQLSV.setBackground(new Color(173 ,216, 230));
				panelQLSV.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelQLSV.setBackground(new Color(255,255,153));
				btnQLSV.setBackground(Color.WHITE);
			}
		});
		
		
		lblIconQLSV = new JLabel("");
		lblIconQLSV.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconQLSV.setBounds(0, 0, 66, 60);
		ImageIcon iconQLSV = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconstudent.png"));
		Image icQLSV = iconQLSV.getImage();
		Image newiconQLSV = icQLSV.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconQLSV = new ImageIcon(newiconQLSV);
		lblIconQLSV.setIcon(iconQLSV);
		panelQLSV.add(lblIconQLSV);

		panelQLBD = new JPanel();
		panelQLBD.setBackground(new Color(255, 255, 153));
		panelQLBD.setBounds(0, 625, 319, 60);
		Menupanel.add(panelQLBD);
		panelQLBD.setLayout(null);

		btnQLBD = new JButton("Quản Lý Bảng Điểm");
		btnQLBD.setFocusPainted(false);
		btnQLBD.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnQLBD.setRequestFocusEnabled(false);
		btnQLBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmTS == null || frmTS.isClosed()) {
					frmTS = new FrmTranscript(conn);
					desktopPane.add(frmTS);
					frmTS.setVisible(true);
				}
				frmTS.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmTS.setResizable(false);
			}
		});
		btnQLBD.setFont(new Font("Arial", Font.BOLD, 16));
		btnQLBD.setBounds(0, 0, 319, 60);
		panelQLBD.add(btnQLBD);
		btnQLBD.setContentAreaFilled(false);
		btnQLBD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnQLBD.setBackground(new Color(173 ,216, 230));
				panelQLBD.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelQLBD.setBackground(new Color(255,255,153));
				btnQLBD.setBackground(Color.WHITE);
			}
		});
		
		lblIconQLBD = new JLabel("");
		lblIconQLBD.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconQLBD.setBounds(0, 0, 66, 60);
		ImageIcon iconQLBD = new ImageIcon(FrmManHinhChinh.class.getResource("/res/icontranscript.png"));
		Image icQLBD = iconQLBD.getImage();
		Image newiconQLBD = icQLBD.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		iconQLBD = new ImageIcon(newiconQLBD);
		lblIconQLBD.setIcon(iconQLBD);
		panelQLBD.add(lblIconQLBD);

		panelQLHP = new JPanel();
		panelQLHP.setBackground(new Color(255, 255, 153));
		panelQLHP.setBounds(0, 506, 319, 60);
		Menupanel.add(panelQLHP);
		panelQLHP.setLayout(null);

		btnQLHP = new JButton("Quản Lý Học Phần");
		btnQLHP.setFocusPainted(false);
		btnQLHP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnQLHP.setRequestFocusEnabled(false);
		btnQLHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmCS == null || frmCS.isClosed()) {
					frmCS = new FrmCourse(conn);
					desktopPane.add(frmCS);
					frmCS.setVisible(true);
				}
				frmCS.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmCS.setResizable(false);
			}
		});
		btnQLHP.setFont(new Font("Arial", Font.BOLD, 16));
		btnQLHP.setBounds(0, 0, 319, 60);
//		ImageIcon iconQLHP = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconterm.png"));
//		Image icQLHP = iconQLHP.getImage();
//		Image newiconQLHP = icQLHP.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
//		iconQLHP = new ImageIcon(newiconQLHP);
//		btnQLHP.setIcon(iconQLHP);
		panelQLHP.add(btnQLHP);
		btnQLHP.setContentAreaFilled(false);
		btnQLHP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnQLHP.setBackground(new Color(173 ,216, 230));
				panelQLHP.setBackground(new Color(255,255,153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelQLHP.setBackground(new Color(255,255,153));
				btnQLHP.setBackground(Color.WHITE);
			}
		});
		
		lblIconQLHP = new JLabel("");
		lblIconQLHP.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconQLHP.setBounds(0, 0, 66, 60);
		ImageIcon iconQLHP = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconterm.png"));
		Image icQLHP = iconQLHP.getImage();
		Image newiconQLHP = icQLHP.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconQLHP = new ImageIcon(newiconQLHP);
		lblIconQLHP.setIcon(iconQLHP);
		panelQLHP.add(lblIconQLHP);

		panelQLLHP = new JPanel();
		panelQLLHP.setLayout(null);
		panelQLLHP.setBackground(new Color(255, 255, 153));
		panelQLLHP.setBounds(0, 565, 319, 60);
		Menupanel.add(panelQLLHP);

		btnQLLHP = new JButton("Quản Lý Lớp Học Phần");
		btnQLLHP.setFocusPainted(false);
		btnQLLHP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnQLLHP.setRequestFocusEnabled(false);
		btnQLLHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmCSS == null || frmCSS.isClosed()) {
					frmCSS = new FrmCourse_Class(conn);
					desktopPane.add(frmCSS);
					frmCSS.setVisible(true);
				}
				frmCSS.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmCSS.setResizable(false);
			}
		});
		btnQLLHP.setFont(new Font("Arial", Font.BOLD, 16));
		btnQLLHP.setBounds(0, 0, 319, 60);
//		ImageIcon iconQLLHP = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconsectionclass.png"));
//		Image icQLLHP = iconQLLHP.getImage();
//		Image newiconQLLHP = icQLLHP.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
//		iconQLLHP = new ImageIcon(newiconQLLHP);
//		btnQLLHP.setIcon(iconQLLHP);
		panelQLLHP.add(btnQLLHP);
		btnQLLHP.setContentAreaFilled(false);
		btnQLLHP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnQLLHP.setBackground(new Color(173 ,216, 230));
				panelQLLHP.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelQLLHP.setBackground(new Color(255,255,153));
				btnQLLHP.setBackground(Color.WHITE);
			}
		});
		
		lblIconQLLHP = new JLabel("");
		lblIconQLLHP.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconQLLHP.setBounds(0, 0, 66, 60);
		ImageIcon iconQLLHP = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconsectionclass.png"));
		Image icQLLHP = iconQLLHP.getImage();
		Image newiconQLLHP = icQLLHP.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconQLLHP = new ImageIcon(newiconQLLHP);
		lblIconQLLHP.setIcon(iconQLLHP);
		panelQLLHP.add(lblIconQLLHP);

		JLabel lblIconUTC2 = new JLabel("");
		lblIconUTC2.setBounds(0, 0, 319, 260);
		Menupanel.add(lblIconUTC2);
		lblIconUTC2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUTC2.setIcon(logo_login_mhc);
		
		JLabel lblQL_UCT2 = new JLabel("Quản Lý UTC2");
		lblQL_UCT2.setHorizontalAlignment(SwingConstants.CENTER);
		lblQL_UCT2.setForeground(new Color(255, 255, 204));
		lblQL_UCT2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblQL_UCT2.setBounds(0, 238, 319, 60);
		Menupanel.add(lblQL_UCT2);
		Load();
	}

	public void Load() {
		frmLG = null;
		frmMHC = null;
		frmTTCN = null;
		frmAC = null;
		frmGV = null;
		frmSV = null;
		frmTS = null;
		frmCS = null;
		frmCSS = null;
		for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
			frmChild.dispose();
		}
		if (frmTTCN == null || frmTTCN.isClosed()) {
			frmTTCN = new FrmPersonalInformation(conn, userName);
			desktopPane.add(frmTTCN);
		}
		frmTTCN.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		frmTTCN.setResizable(false);
		frmTTCN.setVisible(true);
	}

	public void Init() {
		InitGUI init = new InitGUI();
		SCREEN_HEIGHT = init.getSCREEN_HEIGHT();
		SCREEN_WIDTH = init.getSCREEN_WIDTH();
	}

	public void setNonMovableDesktopPane(JDesktopPane pPane) {
		JDesktopPane mNonMovableDesktopPane;
		mNonMovableDesktopPane = pPane;
		try {
			NonMovableDesktopManager d = (NonMovableDesktopManager) mNonMovableDesktopPane.getDesktopManager();
		} catch (ClassCastException cce) {
			mNonMovableDesktopPane.setDesktopManager(new NonMovableDesktopManager());
		}
	}

	private class NonMovableDesktopManager extends DefaultDesktopManager {
		/**
		 * Moves the visible location of the frame being dragged to the location
		 * specified. The means by which this occurs can vary depending on the dragging
		 * algorithm being used. The actual logical location of the frame might not
		 * change until <code>endDraggingFrame</code> is called.
		 */
		public void dragFrame(JComponent f, int newX, int newY) {
		}

		// implements javax.swing.DesktopManager
		public void beginDraggingFrame(JComponent f) {
		}

		// implements javax.swing.DesktopManager
		public void endDraggingFrame(JComponent f) {
		}
	}
}