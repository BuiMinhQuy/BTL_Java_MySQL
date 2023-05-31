package GUI.Student;

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

import GUI.FrmLogin;
import GUI.InitGUI;
import GUI.Student.*;
import GUI.Teacher.FrmTeachCourse_ClassList;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.themes.FlatMacLightLaf;


public class FrmManHinhChinh extends JFrame {
	private static int SCREEN_HEIGHT;
	private static int SCREEN_WIDTH;

	private static Connection conn;
	private static String userName;

	private static JPanel pnlMain;
	private static JMenuBar menuBar;
	private static JMenu mnTaiKhoan;
	private static JMenuItem mnItemTaiKhoan;
	private static JMenuItem mnItemDoiMatKhau;
	private static JMenuItem mnItemThoat;
	private static JMenuItem mnItemDanhSachHocPhan;
	private static JMenuItem mnItemDangKyHocPhan;
	private static JMenuItem mnItemTraCuuHocPhan;
	private static JMenuItem mnItemDanhSachLopHocPhanHoc;
	private static JMenuItem mnItemBangDiem;
	private static JDesktopPane desktopPane;

	private static FrmLogin frmLG;
	private static FrmManHinhChinh frmMHC;
	private static FrmPersonalInformation frmTTCN;
	private static FrmCourseList frmCL;
	private static FrmCourseRegister frmCR;
	private static FrmCourseSearch frmCS;
	private static FrmStudyCourse_ClassList frmSCSS;
	private static FrmTranscript frmTS;
	private JPanel panelDSHP;
	private JPanel panelDKHP;
	private JPanel panelTCHP;
	private JPanel panelDSHPDH;
	private JPanel panelBD;
	private JButton btnDSHP;
	private JButton btnDKHP;
	private JButton btnTCHP;
	private JButton btnDHHPDH;
	private JButton btnBD;
	private JLabel lblIconDSHP;
	private JLabel lblIconDKHP;
	private JLabel lblIconTCHP;
	private JLabel lblIconDSHPDH;
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
		this.frmMHC = frmMHC;
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
				int choose = JOptionPane.showConfirmDialog(pnlMain, "Bạn có muốn đăng xuất ?", "Thông tin",
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
		panelDSHP.setBounds(0, 384, 319, 60);
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
					frmCL = new FrmCourseList(conn);
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
				btnDSHP.setBackground(new Color(173 ,216, 230));
				panelDSHP.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelDSHP.setBackground(new Color(255,255,153));
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

		panelDKHP = new JPanel();
		panelDKHP.setBackground(new Color(255, 255, 153));
		panelDKHP.setBounds(0, 443, 319, 60);
		Menupanel.add(panelDKHP);
		panelDKHP.setLayout(null);

		btnDKHP = new JButton("Đăng Ký Học Phần");
		btnDKHP.setFocusPainted(false);
		btnDKHP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnDKHP.setRequestFocusEnabled(false);
		btnDKHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmCR == null || frmCR.isClosed()) {
					frmCR = new FrmCourseRegister(userName, conn);
					desktopPane.add(frmCR);
				}
				frmCR.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmCR.setResizable(false);
				frmCR.setVisible(true);
			}
		});
		btnDKHP.setFont(new Font("Arial", Font.BOLD, 16));
		btnDKHP.setBounds(0, 0, 319, 61);
		panelDKHP.add(btnDKHP);
		btnDKHP.setContentAreaFilled(false);
		btnDKHP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDKHP.setBackground(new Color(173 ,216, 230));
				panelDKHP.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelDKHP.setBackground(new Color(255,255,153));
				btnDKHP.setBackground(Color.WHITE);
			}
		});
		
		
		lblIconDKHP = new JLabel("");
		lblIconDKHP.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDKHP.setBounds(0, 0, 66, 60);
		ImageIcon iconDKHP = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconteacher.png"));
		Image icDKHP = iconDKHP.getImage();
		Image newiconDKHP = icDKHP.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconDKHP = new ImageIcon(newiconDKHP);
		lblIconDKHP.setIcon(iconDKHP);
		panelDKHP.add(lblIconDKHP);

		panelTCHP = new JPanel();
		panelTCHP.setBackground(new Color(255, 255, 153));
		panelTCHP.setBounds(0, 503, 319, 61);
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
					frmCS = new FrmCourseSearch(conn);
					desktopPane.add(frmCS);
				}
				frmCS.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmCS.setResizable(false);
				frmCS.setVisible(true);
			}
		});
		btnTCHP.setFont(new Font("Arial", Font.BOLD, 16));
		btnTCHP.setBounds(0, 0, 319, 61);
		panelTCHP.add(btnTCHP);
		btnTCHP.setContentAreaFilled(false);
		btnTCHP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnTCHP.setBackground(new Color(173 ,216, 230));
				panelTCHP.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelTCHP.setBackground(new Color(255,255,153));
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

		panelDSHPDH = new JPanel();
		panelDSHPDH.setBackground(new Color(255, 255, 153));
		panelDSHPDH.setBounds(0, 563, 319, 60);
		Menupanel.add(panelDSHPDH);
		panelDSHPDH.setLayout(null);

		btnDHHPDH = new JButton("Học Phần Đang Học");
		btnDHHPDH.setFocusPainted(false);
		btnDHHPDH.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnDHHPDH.setRequestFocusEnabled(false);
		btnDHHPDH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if (frmSCSS == null || frmSCSS.isClosed()) {
					frmSCSS = new FrmStudyCourse_ClassList(userName, conn);
					desktopPane.add(frmSCSS);
				}
				frmSCSS.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmSCSS.setResizable(false);
				frmSCSS.setVisible(true);
			}
		});
		btnDHHPDH.setFont(new Font("Arial", Font.BOLD, 16));
		btnDHHPDH.setBounds(0, 0, 319, 60);
		panelDSHPDH.add(btnDHHPDH);
		btnDHHPDH.setContentAreaFilled(false);
		btnDHHPDH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDHHPDH.setBackground(new Color(173 ,216, 230));
				panelDSHPDH.setBackground(new Color(173 ,216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelDSHPDH.setBackground(new Color(255,255,153));
				btnDHHPDH.setBackground(Color.WHITE);
			}
		});
		
		lblIconDSHPDH = new JLabel("");
		lblIconDSHPDH.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDSHPDH.setBounds(0, 0, 66, 60);
		ImageIcon iconDSHPDH = new ImageIcon(FrmManHinhChinh.class.getResource("/res/icontranscript.png"));
		Image icDSHPDH = iconDSHPDH.getImage();
		Image newiconDSHPDH = icDSHPDH.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		iconDSHPDH = new ImageIcon(newiconDSHPDH);
		lblIconDSHPDH.setIcon(iconDSHPDH);
		panelDSHPDH.add(lblIconDSHPDH);


		panelBD = new JPanel();
		panelBD.setBackground(new Color(255, 255, 153));
		panelBD.setBounds(0, 622, 319, 61);
		Menupanel.add(panelBD);
		panelBD.setLayout(null);

		btnBD = new JButton("Bảng Điểm Sinh Viên");
		btnBD.setFocusPainted(false);
		btnBD.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnBD.setRequestFocusEnabled(false);
		btnBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame frmChild : desktopPane.getAllFrames()) {
					frmChild.dispose();
				}
				if(frmTS == null || frmTS.isClosed()) {
					frmTS = new FrmTranscript(userName, conn);
					desktopPane.add(frmTS);
				}
				frmTS.setBounds(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);
				frmTS.setResizable(false);
				frmTS.setVisible(true);
			}
		});
		btnBD.setFont(new Font("Arial", Font.BOLD, 16));
		btnBD.setBounds(0, 0, 319, 61);
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
		lblIconUTC2.setBounds(0, 0, 319, 260);
		Menupanel.add(lblIconUTC2);
		lblIconUTC2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUTC2.setIcon(logo_login_mhc);
		
		JLabel lblCMSV = new JLabel("Chào Mừng Sinh Viên");
		lblCMSV.setHorizontalAlignment(SwingConstants.CENTER);
		lblCMSV.setForeground(new Color(255, 255, 204));
		lblCMSV.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblCMSV.setBounds(0, 249, 319, 60);
		Menupanel.add(lblCMSV);

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
		frmCR = null;
		frmCS = null;
		frmSCSS = null;
		frmTS = null;
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
