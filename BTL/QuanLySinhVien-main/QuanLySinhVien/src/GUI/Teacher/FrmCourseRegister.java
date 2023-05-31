package GUI.Teacher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;

import GUI.InitGUI;
import GUI.Teacher.FrmManHinhChinh;
import Model.Course_Class;
import Model.InfoCourse_Class;
import Model.Student;
import Model.Teacher;
import Model.Transcript;

public class FrmCourseRegister extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection conn = null;
	private static String tid;

	private String FONT_TYPE;
	private int FONT;
	private int FONT_SIZE;
	private int BUTTON_HEIGHT;
	private int BUTTON_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCREEN_WIDTH;

	private static JComboBox cbbSearchType;
	private static DefaultComboBoxModel cbbSearchTypeModel;
	private static JTextField txtSearch;
	private static ArrayList<InfoCourse_Class> lisInfoCourse_Class = new ArrayList<InfoCourse_Class>();
	private static String[] columnName = { "Mã Môn Học", "Mã Lớp Học Phần", "Tên Học Phần", "Phòng", "Giảng Viên",
			"Học Kỳ", "Mô Tả Lớp", "Số Tín Chỉ", "Mô Tả Môn Học" };
	private static DefaultTableModel model = new DefaultTableModel(columnName, 0);
	private static DefaultTableModel modelStudent = new DefaultTableModel(columnName, 0);
	private static JTable tabInfoCourse_Class = new JTable(model);
	private static JTable tabInfoCourse_ClassTeacher = new JTable(modelStudent);
	private static JButton btnReLoad = new JButton("Làm Mới");
	private static JButton btnSearch = new JButton("Tìm");
	private static JButton btnRegister = new JButton("Đăng Ký");
	private static JButton btnRegisterCancel = new JButton("Hủy");
	private final JLabel lblDKHP = new JLabel("ĐĂNG KÍ HỌC PHẦN GIẢNG DẠY");
	private final JLabel lblHPDDK = new JLabel("HỌC PHẦN ĐÃ ĐĂNG KÍ GIẢNG DẠY");

	public void Init() {
		InitGUI init = new InitGUI();
		this.FONT_TYPE = init.getFONT_TYPE();
		this.FONT = init.getFONT();
		this.FONT_SIZE = init.getFONT_SIZE();
		this.BUTTON_HEIGHT = init.getBUTTON_HEIGHT();
		this.BUTTON_WIDTH = init.getBUTTON_WIDTH();
		this.SCREEN_WIDTH = init.getSCREEN_WIDTH();
		this.SCREEN_HEIGHT = init.getSCREEN_HEIGHT();
	}

	public FrmCourseRegister(String userName, Connection conn) {
		setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		try {
			this.tid = Teacher.getTIDofUserName(userName, conn);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.conn = conn;
		Init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1178, 713);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		// contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabInfoCourse_Class.setFont(new Font("Arial", Font.PLAIN, 14));
		tabInfoCourse_Class.setBounds(10, 168, 870, 305);
		tabInfoCourse_Class.setRowHeight(30);
		tabInfoCourse_Class.setShowHorizontalLines(true);
		tabInfoCourse_Class.setShowVerticalLines(true);

		JScrollPane scrollPane = new JScrollPane(tabInfoCourse_Class);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(4, 54, 1171, 270);
		contentPane.add(scrollPane);

		btnReLoad.setBackground(Color.WHITE);
		btnReLoad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnReLoad.setFocusPainted(false);
		btnReLoad.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnReLoad.setBounds(1055, 330, 120, 45);
		btnReLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
//				JOptionPane.showMessageDialog(contentPane, "Làm Mới Thành Công", "Thông Báo",JOptionPane.INFORMATION_MESSAGE);
				load();
			}
		});
		ImageIcon iconrl = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconreload.png"));
		Image icrl = iconrl.getImage();
		Image newiconrl = icrl.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconrl = new ImageIcon(newiconrl);
		btnReLoad.setIcon(iconrl);
		contentPane.add(btnReLoad);
		btnSearch.setFocusPainted(false);
		btnSearch.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnSearch.setBackground(Color.WHITE);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		btnSearch.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnSearch.setBounds(1047, 6, 120, 40);
		ImageIcon iconfind = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconsearch.png"));
		Image icfind = iconfind.getImage();
		Image newiconfind = icfind.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconfind = new ImageIcon(newiconfind);
		btnSearch.setIcon(iconfind);
		contentPane.add(btnSearch);

		JLabel lblSearch = new JLabel("Tìm Theo :");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblSearch.setBounds(499, 3, 80, 40);
		contentPane.add(lblSearch);

		ArrayList<SearchType> searchTypes = new ArrayList<SearchType>();
		searchTypes.add(new SearchType(true, "Tìm Theo Mã Môn Học"));
		searchTypes.add(new SearchType(false, "Tìm Theo Tên Môn Học"));
		cbbSearchTypeModel = new DefaultComboBoxModel();
		for (SearchType sts : searchTypes) {
			cbbSearchTypeModel.addElement(sts);
		}

		cbbSearchType = new JComboBox();
		cbbSearchType.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cbbSearchType.setBackground(Color.WHITE);
		cbbSearchType.setModel(cbbSearchTypeModel);
		cbbSearchType.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		cbbSearchType.setBounds(589, 11, 225, 30);
		cbbSearchType.setRenderer(new SearchTypeRenderer());
		contentPane.add(cbbSearchType);

		txtSearch = new JTextField();
		txtSearch.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtSearch.setBackground(Color.WHITE);
		txtSearch.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		txtSearch.setBounds(822, 11, 217, 30);
		contentPane.add(txtSearch);

		tabInfoCourse_ClassTeacher.setFont(new Font("Arial", Font.PLAIN, 14));
		tabInfoCourse_ClassTeacher.setBounds(10, 168, 870, 305);
		tabInfoCourse_ClassTeacher.setRowHeight(30);
		tabInfoCourse_ClassTeacher.setShowHorizontalLines(true);
		tabInfoCourse_ClassTeacher.setShowVerticalLines(true);

		JLabel lblDanhSachHocPhan = new JLabel("Danh sách học phần đã đăng ký");
		lblDanhSachHocPhan.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblDanhSachHocPhan.setBounds(10, 283, 242, 20);
		contentPane.add(lblDanhSachHocPhan);

		JScrollPane scrollPaneTeacher = new JScrollPane(tabInfoCourse_ClassTeacher);
		scrollPaneTeacher.setBorder(new LineBorder(Color.BLACK));
		scrollPaneTeacher.setBackground(Color.WHITE);
		scrollPaneTeacher.setBounds(4, 404, 1171, 277);
		contentPane.add(scrollPaneTeacher);

		btnRegisterCancel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnRegisterCancel.setFocusPainted(false);
		btnRegisterCancel.setBackground(Color.WHITE);
		btnRegisterCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tabInfoCourse_ClassTeacher.getSelectedRow();
				if (row != -1) {
					String ccid = tabInfoCourse_ClassTeacher.getValueAt(row, 1).toString();
					try {
						Course_Class css = Course_Class.findCourse_Class(ccid, conn);
						css.setTid(null);
						if (Course_Class.Edit(css, conn) == 0) {
							JOptionPane.showMessageDialog(contentPane, "Hủy đăng ký thất bại!", "Thông Báo",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(contentPane, "Hủy đăng ký thành công!", "Thông Báo",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 
//					else {
//					JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn trong danh sách môn học đã đăng ký!",
//							"Thông Báo", JOptionPane.INFORMATION_MESSAGE);
//				}
				load();
			}
		});
		btnRegisterCancel.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnRegisterCancel.setBounds(927, 330, 120, 45);
		ImageIcon iconrgc = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconclose.png"));
		Image icrgc = iconrgc.getImage();
		Image newiconrgc = icrgc.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconrgc = new ImageIcon(newiconrgc);
		btnRegisterCancel.setIcon(iconrgc);
		contentPane.add(btnRegisterCancel);
		btnRegister.setFocusPainted(false);

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tabInfoCourse_Class.getSelectedRow();
				if(row != -1) {
					String ccid = tabInfoCourse_Class.getValueAt(row, 1).toString();
					try {
						if(Course_Class.existsCourseTeacher(ccid, conn)) {
							JOptionPane.showMessageDialog(contentPane, "Môn học này đã có giảng viên đăng ký!",  "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							Course_Class css = Course_Class.findCourse_Class(ccid, conn);
							css.setTid(tid);
							if(Course_Class.Edit(css, conn)==0) {
								JOptionPane.showMessageDialog(contentPane, "Đăng ký thất bại!",  "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "Đăng ký thành công!",  "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
//				else {
//					JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn môn học cần thêm trước!",  "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
//				}
				load();
			}
		});
		btnRegister.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnRegister.setBounds(799, 330, 120, 45);
		ImageIcon iconrg = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconrg.png"));
		Image icrg = iconrg.getImage();
		Image newiconrg = icrg.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconrg = new ImageIcon(newiconrg);
		btnRegister.setIcon(iconrg);
		contentPane.add(btnRegister);

		lblDKHP.setHorizontalAlignment(SwingConstants.LEFT);
		lblDKHP.setFont(new Font("Arial", Font.BOLD, 28));
		lblDKHP.setBounds(10, 0, 487, 53);
		contentPane.add(lblDKHP);

		lblHPDDK.setHorizontalAlignment(SwingConstants.LEFT);
		lblHPDDK.setFont(new Font("Arial", Font.BOLD, 28));
		lblHPDDK.setBounds(10, 357, 540, 46);
		contentPane.add(lblHPDDK);

		load();
	}

	public static void load() {
		String search = txtSearch.getText();
		boolean searchCID = ((SearchType) cbbSearchType.getSelectedItem()).isType();
		ArrayList<InfoCourse_Class> lisInfoCourse_Class = new ArrayList<InfoCourse_Class>();
		try {
			lisInfoCourse_Class = InfoCourse_Class.loadInfoOpen(search, searchCID, conn);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel) tabInfoCourse_Class.getModel();
		if (model.getRowCount() > 0) {
			model.setRowCount(0);
		}
		Object[] rows = new Object[9];
		for (int i = 0; i < lisInfoCourse_Class.size(); i++) {
			rows[0] = lisInfoCourse_Class.get(i).getCid();
			rows[1] = lisInfoCourse_Class.get(i).getCcid();
			rows[2] = lisInfoCourse_Class.get(i).getName();
			rows[3] = lisInfoCourse_Class.get(i).getRid();
			rows[4] = lisInfoCourse_Class.get(i).getTid();
			rows[5] = lisInfoCourse_Class.get(i).getSemester();
			rows[6] = lisInfoCourse_Class.get(i).getDescription();
			rows[7] = lisInfoCourse_Class.get(i).getNumOfCredits();
			rows[8] = lisInfoCourse_Class.get(i).getDescriptionCourse();

			model.addRow(rows);
		}
		ArrayList<InfoCourse_Class> lisInfoCourse_ClassTeacher = new ArrayList<InfoCourse_Class>();
		try {
			lisInfoCourse_ClassTeacher = InfoCourse_Class.loadInfoOfTeacher(tid, true, conn);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultTableModel modelStudent = (DefaultTableModel) tabInfoCourse_ClassTeacher.getModel();
		if (modelStudent.getRowCount() > 0) {
			modelStudent.setRowCount(0);
		}
		for (int i = 0; i < lisInfoCourse_ClassTeacher.size(); i++) {
			rows[0] = lisInfoCourse_ClassTeacher.get(i).getCid();
			rows[1] = lisInfoCourse_ClassTeacher.get(i).getCcid();
			rows[2] = lisInfoCourse_ClassTeacher.get(i).getName();
			rows[3] = lisInfoCourse_ClassTeacher.get(i).getRid();
			rows[4] = lisInfoCourse_ClassTeacher.get(i).getTid();
			rows[5] = lisInfoCourse_ClassTeacher.get(i).getSemester();
			rows[6] = lisInfoCourse_ClassTeacher.get(i).getDescription();
			rows[7] = lisInfoCourse_ClassTeacher.get(i).getNumOfCredits();
			rows[8] = lisInfoCourse_ClassTeacher.get(i).getDescriptionCourse();

			modelStudent.addRow(rows);
		}
	}

	class SearchType {
		private boolean type;
		private String name;

		public SearchType(boolean type, String name) {
			this.type = type;
			this.name = name;
		}

		public boolean isType() {
			return type;
		}

		public void setType(boolean type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	class SearchTypeRenderer extends BasicComboBoxRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof SearchType) {
				SearchType val = (SearchType) value;
				setText(val.getName());
			}
			return this;
		}
	}
}
