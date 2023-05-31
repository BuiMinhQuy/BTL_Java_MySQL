package GUI.Admin;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import GUI.InitGUI;
import Model.Account;
import Model.Faculty;
import Model.Account;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class FrmAccount extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection conn = null;
	private SimpleDateFormat formatter;

	private String FONT_TYPE;
	private int FONT;
	private int FONT_SIZE;
	private int COMPONENTS_HEIGHT;
	private int BUTTON_HEIGHT;
	private int BUTTON_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCREEN_WIDTH;

	private static JTextField txtAID;
	private static JTextField txtUserName;
	private static JPasswordField pwfPassword;
	private static JComboBox cbbPermission;
	private static DefaultComboBoxModel cbbPermissionModel;
	private static JDateChooser dtDateofCreate;
	private static ArrayList<Account> lisAccount = new ArrayList<Account>();
	private static String[] columnName = { "ID Tài Khoản", "Tên Đăng Nhập", "Mật Khẩu", "Quyền", "Ngày Tạo" };
	private static DefaultTableModel model = new DefaultTableModel(columnName, 0);
	private static JTable tabAccount = new JTable(
			new DefaultTableModel(new Object[][] {}, new String[] { "ID Tài Khoản", "T\u00EAn \u0110\u0103ng Nh\u1EADp",
					"M\u1EADt Kh\u1EA9u", "Quy\u1EC1n", "Ng\u00E0y T\u1EA1o" }));
	private static JButton btnCancel = new JButton("Hủy");
	private static JButton btnSave = new JButton("Lưu");
	private static JButton btnDelete = new JButton("Xóa");
	private static JButton btnFind = new JButton("Tìm");
	private static JButton btnCreate = new JButton("Thêm");
	private static JButton btnEdit = new JButton("Sửa");
	private static int flag = 0;

	public void Init() {
		InitGUI init = new InitGUI();
		this.FONT_TYPE = init.getFONT_TYPE();
		this.FONT = init.getFONT();
		this.FONT_SIZE = init.getFONT_SIZE();
		this.COMPONENTS_HEIGHT = init.getCOMPONENTS_HEIGHT();
		this.BUTTON_HEIGHT = init.getBUTTON_HEIGHT();
		this.BUTTON_WIDTH = init.getBUTTON_WIDTH();
		this.SCREEN_WIDTH = init.getSCREEN_WIDTH();
		this.SCREEN_HEIGHT = init.getSCREEN_HEIGHT();
	}

	public FrmAccount(Connection conn) {
		setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		FrmAccount.conn = conn;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		Init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1179, 713);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		// contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabAccount.setFont(new Font("Arial", Font.PLAIN, 14));
		tabAccount.setBounds(10, 168, 870, 305);
		tabAccount.setRowHeight(30);
		tabAccount.setShowHorizontalLines(true);
		tabAccount.setShowVerticalLines(true);
		tabAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabAccount.getSelectedRow();
				if (row != -1) {
					txtAID.setText(tabAccount.getValueAt(row, 0).toString());
					txtUserName.setText(tabAccount.getValueAt(row, 1).toString());
					pwfPassword.setText(tabAccount.getValueAt(row, 2).toString());
					int permission = Integer.valueOf(tabAccount.getValueAt(row, 3).toString());
					cbbPermission.setSelectedIndex(permission);
					try {
						Date dt = formatter.parse(tabAccount.getValueAt(row, 4).toString());
						dtDateofCreate.setDate(dt);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		JScrollPane scrollPane = new JScrollPane(tabAccount);
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(2, 50, 819, 633);
		contentPane.add(scrollPane);

//		JScrollPane scrollPane_1 = new JScrollPane();
//		scrollPane.setColumnHeaderView(scrollPane_1);

		JLabel lblAID = new JLabel("ID Tài Khoản :");
		lblAID.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblAID.setBounds(831, 46, 112, 38);
		contentPane.add(lblAID);

		JLabel lblName_Teacher = new JLabel("Tên Đăng Nhập :");
		lblName_Teacher.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblName_Teacher.setBounds(827, 106, 122, 38);
		contentPane.add(lblName_Teacher);

		JLabel lblPassword = new JLabel("Mật Khẩu :");
		lblPassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblPassword.setBounds(827, 168, 122, 38);
		contentPane.add(lblPassword);

		JLabel lblPermission = new JLabel("Quyền :");
		lblPermission.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblPermission.setBounds(827, 228, 122, 38);
		contentPane.add(lblPermission);

		JLabel lblDateCreate = new JLabel("Ngày Tạo :");
		lblDateCreate.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblDateCreate.setBounds(827, 289, 122, 38);
		contentPane.add(lblDateCreate);

		txtAID = new JTextField();
		txtAID.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAID.setBounds(951, 55, 219, 27);
		contentPane.add(txtAID);
		txtAID.setColumns(10);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUserName.setColumns(10);
		txtUserName.setBounds(951, 115, 219, 27);
		contentPane.add(txtUserName);

		pwfPassword = new JPasswordField();
		pwfPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		pwfPassword.setColumns(10);
		pwfPassword.setBounds(951, 177, 219, 27);
		contentPane.add(pwfPassword);

		ArrayList<Permission> permissions = new ArrayList<Permission>();
		permissions.add(new Permission(0, "Admin"));
		permissions.add(new Permission(1, "Sinh Viên"));
		permissions.add(new Permission(2, "Giảng Viên"));
		cbbPermissionModel = new DefaultComboBoxModel();
		for (Permission pms : permissions) {
			cbbPermissionModel.addElement(pms);
		}

		cbbPermission = new JComboBox();
		cbbPermission.setBorder(null);
		cbbPermission.setBackground(Color.WHITE);
		cbbPermission.setModel(cbbPermissionModel);
		cbbPermission.setFont(new Font("Arial", Font.PLAIN, 14));
		cbbPermission.setBounds(951, 234, 219, 27);
		cbbPermission.setRenderer(new PermissionRenderer());
		contentPane.add(cbbPermission);

		dtDateofCreate = new JDateChooser();
		dtDateofCreate.setBackground(Color.WHITE);
		dtDateofCreate.getCalendarButton().setBounds(198, 0, 21, 27);
		dtDateofCreate.setBounds(951, 297, 219, 27);
		contentPane.add(dtDateofCreate);

		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnCancel.setFocusPainted(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFind.setEnabled(true);
				btnCreate.setEnabled(true);
				btnEdit.setEnabled(true);
				btnCancel.setEnabled(false);
				btnSave.setEnabled(false);
				btnDelete.setEnabled(true);
				txtAID.setEnabled(true);
				txtUserName.setEnabled(true);
				load();
			}
		});
		btnCancel.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnCancel.setBounds(1021, 633, BUTTON_WIDTH, BUTTON_HEIGHT);
		// btnCancel.setIcon(new ImageIcon("resources/cancel.png"));
		ImageIcon iconcancel = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconclose.png"));
		Image iccancel = iconcancel.getImage();
		Image newiconcancel = iccancel.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconcancel = new ImageIcon(newiconcancel);
		btnCancel.setIcon(iconcancel);
		contentPane.add(btnCancel);

		btnSave.setBackground(Color.WHITE);
		btnSave.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnSave.setFocusPainted(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag == 0)
					Add();
				else if (flag == 1)
					Edit();

				btnFind.setEnabled(true);
				btnCreate.setEnabled(true);
				btnEdit.setEnabled(true);
				btnCancel.setEnabled(false);
				btnSave.setEnabled(false);
				btnDelete.setEnabled(true);
			}
		});
		btnSave.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnSave.setBounds(1021, 571, BUTTON_WIDTH, BUTTON_HEIGHT);
		// btnSave.setIcon(new ImageIcon("resources/save.jpg"));
		ImageIcon iconsave = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconsave.png"));
		Image icsave = iconsave.getImage();
		Image newiconsave = icsave.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconsave = new ImageIcon(newiconsave);
		btnSave.setIcon(iconsave);
		contentPane.add(btnSave);

		btnDelete.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFocusPainted(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Del();
			}
		});
		btnDelete.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnDelete.setBounds(859, 633, BUTTON_WIDTH, BUTTON_HEIGHT);
		// btnDelete.setIcon(new ImageIcon("resources/delete.png"));
		ImageIcon icondelete = new ImageIcon(FrmManHinhChinh.class.getResource("/res/icondelete.png"));
		Image icdelete = icondelete.getImage();
		Image newicondelete = icdelete.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		icondelete = new ImageIcon(newicondelete);
		btnDelete.setIcon(icondelete);
		contentPane.add(btnDelete);

		btnEdit.setFocusPainted(false);
		btnEdit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnEdit.setBackground(Color.WHITE);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				txtAID.setEnabled(false);
				txtUserName.setEnabled(false);
				btnFind.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnCancel.setEnabled(true);
				btnSave.setEnabled(true);
				btnCreate.setEnabled(false);
			}
		});
		btnEdit.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnEdit.setBounds(859, 571, BUTTON_WIDTH, BUTTON_HEIGHT);
		ImageIcon iconedit = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconfix.png"));
		Image icedit = iconedit.getImage();
		Image newiconedit = icedit.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconedit = new ImageIcon(newiconedit);
		btnEdit.setIcon(iconedit);
		contentPane.add(btnEdit);

		btnCreate.setBackground(Color.WHITE);
		btnCreate.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnCreate.setFocusPainted(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 0;
				btnFind.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnCancel.setEnabled(true);
				btnSave.setEnabled(true);
				btnCreate.setEnabled(false);
				clear();
			}
		});
		btnCreate.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnCreate.setBounds(1021, 506, BUTTON_WIDTH, BUTTON_HEIGHT);
		// btnCreate.setIcon(new ImageIcon("resources/create.png"));
		ImageIcon iconcreate = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconadd.png"));
		Image iccreate = iconcreate.getImage();
		Image newiconcreate = iccreate.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconcreate = new ImageIcon(newiconcreate);
		btnCreate.setIcon(iconcreate);
		contentPane.add(btnCreate);

		btnFind.setFocusPainted(false);
		btnFind.setBackground(Color.WHITE);
		btnFind.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel.setEnabled(true);
				Find();
			}
		});

		btnFind.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnFind.setBounds(859, 506, BUTTON_WIDTH, BUTTON_HEIGHT);
		ImageIcon iconfind = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconsearch.png"));
		Image icfind = iconfind.getImage();
		Image newiconfind = icfind.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconfind = new ImageIcon(newiconfind);
		btnFind.setIcon(iconfind);
		contentPane.add(btnFind);

		JLabel lblQLTK = new JLabel("QUẢN LÝ TÀI KHOẢN");
		lblQLTK.setHorizontalAlignment(SwingConstants.CENTER);
		lblQLTK.setFont(new Font("Arial", Font.BOLD, 28));
		lblQLTK.setBounds(0, 0, 819, 47);
		contentPane.add(lblQLTK);

		JLabel lblTTTK = new JLabel("Thông Tin Tài Khoản");
		lblTTTK.setHorizontalAlignment(SwingConstants.CENTER);
		lblTTTK.setFont(new Font("Arial", Font.BOLD, 18));
		lblTTTK.setBounds(821, 0, 357, 47);
		contentPane.add(lblTTTK);

		load();
	}

	private static void setUndecorated(boolean b) {
		// TODO Auto-generated method stub
		FrmAccount.setUndecorated(true);
	}

	public static void load() {
		txtAID.setEnabled(true);
		txtUserName.setEnabled(true);
		ArrayList<Account> lisAccount = new ArrayList<Account>();
		try {
			lisAccount = Account.load(conn);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel) tabAccount.getModel();
		if (model.getRowCount() > 0) {
			model.setRowCount(0);
		}
		Object[] rows = new Object[9];
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		for (int i = 0; i < lisAccount.size(); i++) {
			rows[0] = (lisAccount.get(i).getAid());
			rows[1] = (lisAccount.get(i).getUserName());
			rows[2] = (lisAccount.get(i).getPassWord());
			rows[3] = (lisAccount.get(i).getPermission());
			rows[4] = (lisAccount.get(i).getDateOfCreate());

			model.addRow(rows);
		}
		return;
	}

	public static void Add() {
		Account sd = new Account();
		sd.setAid(txtAID.getText().toString());
		sd.setUserName(txtUserName.getText().toString());
		sd.setPassWord(String.valueOf(pwfPassword.getPassword()));
		sd.setPermission(((Permission) cbbPermission.getSelectedItem()).getType());
		sd.setDateOfCreate(dtDateofCreate.getDate());
		try {
			if (Account.Insert(sd, conn) == 1) {
				JOptionPane.showMessageDialog(tabAccount, "Thêm Thành Công", "Thông Báo",JOptionPane.INFORMATION_MESSAGE);
				lisAccount.add(sd);
				DefaultTableModel model = (DefaultTableModel) tabAccount.getModel();
				model.setRowCount(0); 
				load();
				
			} 
//			else
//				JOptionPane.showMessageDialog(tabAccount, "Thêm Lỗi", "Thông Báo",JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void Edit() {
		Account ac = new Account();
		ac.setAid(txtAID.getText().toString());
		ac.setUserName(txtUserName.getText().toString());
		ac.setPassWord(String.valueOf(pwfPassword.getPassword()));
		ac.setPermission(((Permission) cbbPermission.getSelectedItem()).getType());
		ac.setDateOfCreate(dtDateofCreate.getDate());
		try {
			if (Account.Edit(ac, conn) == 1) {
				JOptionPane.showMessageDialog(tabAccount, "Sửa Thành Công", "Thông Báo",
						JOptionPane.INFORMATION_MESSAGE);
				for (int i = 0; i < lisAccount.size(); i++) {
					if (lisAccount.get(i).getAid() == ac.getAid()) {
						lisAccount.get(i).setPassWord(ac.getPassWord());
						lisAccount.get(i).setPermission(ac.getPermission());
						lisAccount.get(i).setDateOfCreate(ac.getDateOfCreate());
						break;
					}
				}
				DefaultTableModel model = (DefaultTableModel) tabAccount.getModel();
				model.setRowCount(0);
				load();
				return;
			} 
//			else
//				JOptionPane.showMessageDialog(tabAccount, "Sửa Lỗi", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	public static void Del() {
		String index = txtAID.getText().toString();
		try {
			if (Account.Del(index, conn) == 1) {
				lisAccount.remove(index);
				JOptionPane.showMessageDialog(tabAccount, "Xóa thành công ", "Thông Báo",
						JOptionPane.INFORMATION_MESSAGE);
				DefaultTableModel model = (DefaultTableModel) tabAccount.getModel();
				model.setRowCount(0);
				load();
				return;
			} else
				JOptionPane.showMessageDialog(tabAccount, "Xóa lỗi", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void Find() {
		ArrayList<Account> lisAccount = new ArrayList<Account>();
		try {
			lisAccount = Account.load(conn);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel) tabAccount.getModel();
		Object[] rows = new Object[9];
		String index = txtAID.getText().toString();
		try {
			Account ac = Account.findAccount(index, conn);
			if (ac != null) {
				model.setRowCount(0);
				rows[0] = ac.getAid();
				rows[1] = ac.getUserName();
				rows[2] = ac.getPassWord();
				rows[3] = ac.getPermission();
				rows[4] = ac.getDateOfCreate();

				model.addRow(rows);
			} else {
				JOptionPane.showConfirmDialog(tabAccount, "Không Tìm Thấy", "Thông Báo", JOptionPane.OK_OPTION);

			}
		} catch (NumberFormatException | HeadlessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void clear() {
		txtAID.setText("");
		txtUserName.setText("");
		pwfPassword.setText("");
		cbbPermission.setSelectedIndex(-1);
	}

	class Permission {
		private int type;
		private String name;

		public Permission(int type, String name) {
			this.type = type;
			this.name = name;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	class PermissionRenderer extends BasicComboBoxRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Permission) {
				Permission val = (Permission) value;
				setText(val.getName());
			}
			return this;
		}
	}
}
