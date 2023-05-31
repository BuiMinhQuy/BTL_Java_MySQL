package GUI.Teacher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.DefaultDesktopManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import GUI.FrmLogin;
import GUI.InitGUI;
import GUI.Teacher.FrmChangePassword;
import GUI.Student.FrmCourseList;
import GUI.Student.FrmCourseRegister;
import GUI.Student.FrmCourseSearch;
import GUI.Student.FrmStudyCourse_ClassList;
import GUI.Student.FrmTranscript;
import GUI.Teacher.FrmManHinhChinh;
import GUI.Teacher.*;

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
	private JMenu mnDanhMuc;
	private static JMenuItem mnItemDanhSachHocPhan;
	private static JMenuItem mnItemDangKyHocPhan;
	private static JMenuItem mnItemTraCuuHocPhan;
	private static JMenuItem mnItemDanhSachLopHocPhanGiangDay;
	private static JMenuItem mnItemBangDiem;
	private static JDesktopPane desktopPane;

	private static FrmLogin frmLG;
	private static FrmManHinhChinh frmMHC;
	private static FrmPersonalInformation frmTTCN;
	private static GUI.Teacher.FrmCourseList frmCL;
	private static GUI.Teacher.FrmCourseRegister frmCR_t;
	private static GUI.Teacher.FrmCourseSearch frmCS;
	private static FrmTeachCourse_ClassList frmTCSS;
	private static GUI.Teacher.FrmTranscript frmTS_t;
	private JPanel panelDSHP;
	private JPanel panelDKHPGD;
	private JPanel panelTCHP;
	private JPanel panelDSHPDG;
	private JPanel panelBD;
	private JButton btnDSHP;
	private JButton btnDKHPGD;
	private JButton btnTCHP;
	private JButton btnDHHPDG;
	private JButton btnBD;
	private JLabel lblIconDSHP;
	private JLabel lblIconDKHPGD;
	private JLabel lblIconTCHP;
	private JLabel lblIconDSHPDG;
	private JLabel lblIconBD;

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

		this.frmMHC = this;
		this.frmTTCN = null;
		this.frmLG = frmLG;
		this.userName = userName;
		Init();
		this.conn = conn;
		setTitle("Màn hình chính - Giảng viên");
		pnlMain = new JPanel();
		pnlMain.setLayout(null);
		pnlMain = (JPanel) getContentPane();

		desktopPane = new JDesktopPane();
		desktopPane.setBorder(null);
		desktopPane.setBounds(318, 0, 1179, 685);
		setNonMovableDesktopPane(desktopPane);
		getContentPane().add(desktopPane);

		menuBar = new JMenuBar();
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
				int choose = JOptionPane.showConfirmDialog(pnlMain, "Bạn có muốn đăng xuất!", "Thông tin",
						JOptionPane.OK_CANCEL_OPTION);
				if (choose == JOptionPane.OK_OPTION) {
					frmLG.setVisible(true);
					setVisible(false);
				}
			}
		});
		mnTaiKhoan.add(mnItemThoat);

		JPanel Menupanel = new JPanel();
		Menupanel.setBackground(new Color(0, 0, 83));
		Menupanel.setBorder(null);
		Menupanel.setBounds(0, 0, 319, 685);
		Menupanel.setLayout(null);
		ImageIcon logo_login_mhc = new ImageIcon(FrmManHinhChinh.class.getResource("/res/uct2.png"));
		Image LgLogin = logo_login_mhc.getImage();
		Image newLgLogin = LgLogin.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		logo_login_mhc = new ImageIcon(newLgLogin);
		getContentPane().add(Menupanel);

		panelDSHP = new JPanel();
		panelDSHP.setBackground(new Color(255, 255, 153));
		panelDSHP.setBounds(0, 388, 319, 60);
		Menupanel.add(panelDSHP);
		panelDSHP.setLayout(null);

		JButton btnDSHP = new JButton("Danh Sách Học Phần");
		btnDSHP.setFocusPainted(false);
		btnDSHP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnDSHP.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDSHP.setRequestFocusEnabled(false);
		btnDSHP.setBackground(new Color(255, 255, 255));
		btnDSHP.setBounds(0, 0, 319, 60);
		panelDSHP.add(btnDSHP);
		btnDSHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmCL == null || frmCL.isClosed()) {
					frmCL = new GUI.Teacher.FrmCourseList(conn);
					desktopPane.add(frmCL);
				}
				frmCL.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmCL.setResizable(false);
				frmCL.setVisible(true);
			}
		});
		btnDSHP.setFont(new Font("Arial", Font.BOLD, 16));
		btnDSHP.setContentAreaFilled(false);
		btnDSHP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDSHP.setBackground(new Color(173, 216, 230));
				panelDSHP.setBackground(new Color(173, 216, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelDSHP.setBackground(new Color(255, 255, 153));
				btnDSHP.setBackground(Color.WHITE);
			}
		});

		JLabel lblIconDSHP = new JLabel("");
		lblIconDSHP.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDSHP.setBounds(0, 0, 66, 60);
		ImageIcon iconDSHP = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconperson.png"));
		Image icDSHP = iconDSHP.getImage();
		Image newiconDSHP = icDSHP.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconDSHP = new ImageIcon(newiconDSHP);
		lblIconDSHP.setIcon(iconDSHP);
		panelDSHP.add(lblIconDSHP);

		panelDKHPGD = new JPanel();
		panelDKHPGD.setBackground(new Color(255, 255, 153));
		panelDKHPGD.setBounds(0, 447, 319, 60);
		Menupanel.add(panelDKHPGD);
		panelDKHPGD.setLayout(null);

		btnDKHPGD = new JButton("Đăng Ký Học Phần");
		btnDKHPGD.setFocusPainted(false);
		btnDKHPGD.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnDKHPGD.setRequestFocusEnabled(false);
		btnDKHPGD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmCR_t == null || frmCR_t.isClosed()) {
					frmCR_t = new GUI.Teacher.FrmCourseRegister(userName, conn);
					desktopPane.add(frmCR_t);
				}
				frmCR_t.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmCR_t.setResizable(false);
				frmCR_t.setVisible(true);
			}
		});
		btnDKHPGD.setFont(new Font("Arial", Font.BOLD, 16));
		btnDKHPGD.setBounds(0, 0, 319, 60);
		panelDKHPGD.add(btnDKHPGD);
		btnDKHPGD.setContentAreaFilled(false);
		btnDKHPGD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDKHPGD.setBackground(new Color(173, 216, 230));
				panelDKHPGD.setBackground(new Color(173, 216, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelDKHPGD.setBackground(new Color(255, 255, 153));
				btnDKHPGD.setBackground(Color.WHITE);
			}
		});

		lblIconDKHPGD = new JLabel("");
		lblIconDKHPGD.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDKHPGD.setBounds(0, 0, 66, 60);
		ImageIcon iconDKHP = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconteacher.png"));
		Image icDKHP = iconDKHP.getImage();
		Image newiconDKHP = icDKHP.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconDKHP = new ImageIcon(newiconDKHP);
		lblIconDKHPGD.setIcon(iconDKHP);
		panelDKHPGD.add(lblIconDKHPGD);

		panelTCHP = new JPanel();
		panelTCHP.setBackground(new Color(255, 255, 153));
		panelTCHP.setBounds(0, 506, 319, 60);
		Menupanel.add(panelTCHP);
		panelTCHP.setLayout(null);

		btnTCHP = new JButton("Tra Cứu Học Phần ");
		btnTCHP.setFocusPainted(false);
		btnTCHP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnTCHP.setRequestFocusEnabled(false);
		btnTCHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmCS == null || frmCS.isClosed()) {
					frmCS = new GUI.Teacher.FrmCourseSearch(conn);
					desktopPane.add(frmCS);
				}
				frmCS.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmCS.setResizable(false);
				frmCS.setVisible(true);
			}
		});
		btnTCHP.setFont(new Font("Arial", Font.BOLD, 16));
		btnTCHP.setBounds(0, 0, 319, 60);
		panelTCHP.add(btnTCHP);
		btnTCHP.setContentAreaFilled(false);
		btnTCHP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnTCHP.setBackground(new Color(173, 216, 230));
				panelTCHP.setBackground(new Color(173, 216, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelTCHP.setBackground(new Color(255, 255, 153));
				btnTCHP.setBackground(Color.WHITE);
			}
		});

		lblIconTCHP = new JLabel("");
		lblIconTCHP.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconTCHP.setBounds(0, 0, 66, 60);
		ImageIcon iconTCHP = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconstudent.png"));
		Image icTCHP = iconTCHP.getImage();
		Image newiconTCHP = icTCHP.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconTCHP = new ImageIcon(newiconTCHP);
		lblIconTCHP.setIcon(iconTCHP);
		panelTCHP.add(lblIconTCHP);

		panelDSHPDG = new JPanel();
		panelDSHPDG.setBackground(new Color(255, 255, 153));
		panelDSHPDG.setBounds(0, 565, 319, 60);
		Menupanel.add(panelDSHPDG);
		panelDSHPDG.setLayout(null);

		lblIconDSHPDG = new JLabel("");
		lblIconDSHPDG.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDSHPDG.setBounds(0, 0, 66, 60);
		ImageIcon iconDSHPDH = new ImageIcon(FrmManHinhChinh.class.getResource("/res/icontranscript.png"));
		Image icDSHPDH = iconDSHPDH.getImage();
		Image newiconDSHPDH = icDSHPDH.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		iconDSHPDH = new ImageIcon(newiconDSHPDH);
		lblIconDSHPDG.setIcon(iconDSHPDH);
		panelDSHPDG.add(lblIconDSHPDG);

		btnDHHPDG = new JButton("Học Phần Đang Dạy");
		btnDHHPDG.setBounds(0, 0, 319, 60);
		panelDSHPDG.add(btnDHHPDG);
		btnDHHPDG.setFocusPainted(false);
		btnDHHPDG.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnDHHPDG.setRequestFocusEnabled(false);
		btnDHHPDG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmTCSS == null || frmTCSS.isClosed()) {
					frmTCSS = new FrmTeachCourse_ClassList(userName, conn);
					desktopPane.add(frmTCSS);
				}
				frmTCSS.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmTCSS.setResizable(false);
				frmTCSS.setVisible(true);
			}
		});
		btnDHHPDG.setFont(new Font("Arial", Font.BOLD, 16));
		btnDHHPDG.setContentAreaFilled(false);
		btnDHHPDG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDHHPDG.setBackground(new Color(173 ,216, 230));
				panelDSHPDG.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelDSHPDG.setBackground(new Color(255,255,153));
				btnDHHPDG.setBackground(Color.WHITE);
			}
		});

		panelBD = new JPanel();
		panelBD.setBackground(new Color(255, 255, 153));
		panelBD.setBounds(0, 624, 319, 60);
		Menupanel.add(panelBD);
		panelBD.setLayout(null);

		btnBD = new JButton("Nhập Điểm Sinh Viên");
		btnBD.setFocusPainted(false);
		btnBD.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnBD.setRequestFocusEnabled(false);
		btnBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmTS_t == null || frmTS_t.isClosed()) {
					frmTS_t = new GUI.Teacher.FrmTranscript(userName, conn);
					desktopPane.add(frmTS_t);
				}
				frmTS_t.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmTS_t.setResizable(false);
				frmTS_t.setVisible(true);
			}
		});
		btnBD.setFont(new Font("Arial", Font.BOLD, 16));
		btnBD.setBounds(0, 0, 319, 60);
		panelBD.add(btnBD);
		btnBD.setContentAreaFilled(false);
		btnBD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnBD.setBackground(new Color(173 ,216, 230));
				panelBD.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelBD.setBackground(new Color(255,255,153));
				btnBD.setBackground(Color.WHITE);
			}
		});

		lblIconBD = new JLabel("");
		lblIconBD.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconBD.setBounds(0, 0, 66, 60);
		ImageIcon iconBD = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconterm.png"));
		Image icBD = iconBD.getImage();
		Image newiconBD = icBD.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconBD = new ImageIcon(newiconBD);
		lblIconBD.setIcon(iconBD);
		panelBD.add(lblIconBD);

		JLabel lblIconUTC2 = new JLabel("");
		lblIconUTC2.setBounds(0, 0, 319, 284);
		Menupanel.add(lblIconUTC2);
		lblIconUTC2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUTC2.setIcon(logo_login_mhc);

		JLabel lblCMGV = new JLabel("Chào Mừng Giảng Viên");
		lblCMGV.setHorizontalAlignment(SwingConstants.CENTER);
		lblCMGV.setForeground(new Color(255, 255, 204));
		lblCMGV.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblCMGV.setBounds(0, 270, 319, 60);
		Menupanel.add(lblCMGV);

		// Frame Size
		setSize(1498, 710);
		// setBounds(0, 0, FRM_WIDTH, FRM_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setUndecorated(true);
		setResizable(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		Load();
	}

	public void Load() {
		frmLG = null;
		frmMHC = null;
		frmTTCN = null;
		frmCL = null;
		frmCR_t = null;
		frmCS = null;
		frmTCSS = null;
		frmTS_t = null;
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
