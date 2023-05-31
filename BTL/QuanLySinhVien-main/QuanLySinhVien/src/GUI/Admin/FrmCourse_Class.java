package GUI.Admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;

import GUI.InitGUI;
import Model.Course;
import Model.Course_Class;
import Model.Room;
import Model.Teacher;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class FrmCourse_Class extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static Connection conn = null;

	private String FONT_TYPE;
	private int FONT;
	private int FONT_SIZE;
	private int COMPONENTS_HEIGHT;
	private int BUTTON_HEIGHT;
	private int BUTTON_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCREEN_WIDTH;
	private static JComboBox cbbCID;
	private static DefaultComboBoxModel cbbCIDModel;
	private static JTextField txtCCID;
	private static JComboBox cbbRID;
	private static DefaultComboBoxModel cbbRIDModel;
	private static JComboBox cbbTID;
	private static DefaultComboBoxModel cbbTIDModel;
	private static JComboBox cbbStatus;
	private static DefaultComboBoxModel cbbStatusModel;
	private static JComboBox cbbSemester;
	private static DefaultComboBoxModel cbbSemesterModel;
	private static JTextField txtDescription;
	private static ArrayList<Course_Class> lisCourse_Class = new ArrayList<Course_Class>();
	private static String[] columnName = { "Mã Lớp", "Mã Số Lớp", "Số Phòng", "MSGV", "Trạng thái", "Học Kỳ", "Mô Tả" };
	private static DefaultTableModel model = new DefaultTableModel(columnName, 0);
	private static JTable tabCourse_Class = new JTable(model);
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

	public FrmCourse_Class(Connection conn) {
		setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		FrmCourse_Class.conn = conn;
		Init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1178, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		
		tabCourse_Class.setFont(new Font("Arial", Font.PLAIN, 14));
		tabCourse_Class.setBounds(10, 168, 870, 305);
		tabCourse_Class.setRowHeight(30);
		tabCourse_Class.setShowHorizontalLines(true);
		tabCourse_Class.setShowVerticalLines(true);
		tabCourse_Class.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabCourse_Class.getSelectedRow();
				if (row != -1 && flag == 0) {
					int index;

					String cid = tabCourse_Class.getValueAt(row, 0).toString();
					index = -1;
					for (int i = 0; i < cbbCIDModel.getSize(); i++) {
						Course cs = (Course) cbbCIDModel.getElementAt(i);
						if (cid.equals(cs.getCid())) {
							index = i;
							break;
						}
					}
					cbbCID.setSelectedIndex(index);

					txtCCID.setText(tabCourse_Class.getValueAt(row, 1).toString());

					String rid = tabCourse_Class.getValueAt(row, 2).toString();
					index = -1;
					for (int i = 0; i < cbbRIDModel.getSize(); i++) {
						Room r = (Room) cbbRIDModel.getElementAt(i);
						if (rid.equals(r.getRid())) {
							index = i;
							break;
						}
					}
					cbbRID.setSelectedIndex(index);

					Object obj = tabCourse_Class.getValueAt(row, 3);
					if (obj == null) {
						cbbTID.setSelectedIndex(0);
					} else {
						String tid = tabCourse_Class.getValueAt(row, 3).toString();
						index = -1;
						for (int i = 0; i < cbbTIDModel.getSize(); i++) {
							Teacher tc = (Teacher) cbbTIDModel.getElementAt(i);
							if (tid.equals(tc.getId())) {
								index = i;
								break;
							}
						}
						cbbTID.setSelectedIndex(index);
					}

					Boolean status = Boolean.valueOf(tabCourse_Class.getValueAt(row, 4).toString());
					if (status) {
						cbbStatus.setSelectedIndex(0);
					} else {
						cbbStatus.setSelectedIndex(1);
					}

					int semester = Integer.valueOf(tabCourse_Class.getValueAt(row, 5).toString());
					index = -1;
					for (int i = 0; i < cbbSemesterModel.getSize(); i++) {
						Semester tc = (Semester) cbbSemesterModel.getElementAt(i);
						if (semester == tc.getType()) {
							index = i;
							break;
						}
					}
					cbbSemester.setSelectedIndex(index);

					txtDescription.setText(tabCourse_Class.getValueAt(row, 6).toString());
				}
			}

		});
		JScrollPane scrollPane = new JScrollPane(tabCourse_Class);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(2, 50, 819, 633);
		contentPane.add(scrollPane);

		JLabel lblID_Class = new JLabel("Tên Lớp :");
		lblID_Class.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblID_Class.setBounds(829, 46, 81, 38);
		contentPane.add(lblID_Class);

		JLabel lblCCID = new JLabel("Mã Số Lớp :");
		lblCCID.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblCCID.setBounds(829, 103, 98, 38);
		contentPane.add(lblCCID);

		JLabel lblRID = new JLabel("Số Phòng :");
		lblRID.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblRID.setBounds(831, 165, 98, 38);
		contentPane.add(lblRID);

		JLabel lblTID = new JLabel("Tên GV :");
		lblTID.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTID.setBounds(831, 219, 120, 38);
		contentPane.add(lblTID);

		JLabel lblStatus = new JLabel("Trạng Thái :");
		lblStatus.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblStatus.setBounds(831, 277, 98, 38);
		contentPane.add(lblStatus);

		JLabel lblSemester = new JLabel("Học Kỳ :");
		lblSemester.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblSemester.setBounds(831, 333, 98, 38);
		contentPane.add(lblSemester);

		JLabel lblDescription = new JLabel("Mô Tả :");
		lblDescription.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblDescription.setBounds(831, 390, 91, 38);
		contentPane.add(lblDescription);

		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			courses = Course.load(conn);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbCIDModel = new DefaultComboBoxModel();
		for (Course cs : courses) {
			cbbCIDModel.addElement(cs);
		}

		cbbCID = new JComboBox();
		cbbCID.setBackground(Color.WHITE);
		cbbCID.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cbbCID.setModel(cbbCIDModel);
		cbbCID.setFont(new Font("Arial", Font.PLAIN, 14));
		cbbCID.setFont(new Font("Dialog", Font.PLAIN, 16));
		cbbCID.setBounds(923, 52, 246, 27);
		cbbCID.setRenderer(new CIDRenderer());
		contentPane.add(cbbCID);

		txtCCID = new JTextField();
		txtCCID.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCCID.setBackground(Color.WHITE);
		txtCCID.setFont(new Font("Arial", Font.PLAIN, 14));
		txtCCID.setColumns(10);
		txtCCID.setBounds(923, 111, 246, 27);
		txtCCID.setEnabled(false);
		contentPane.add(txtCCID);

		ArrayList<Room> rooms = new ArrayList<Room>();
		try {
			rooms = Room.load(conn);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbRIDModel = new DefaultComboBoxModel();
		for (Room r : rooms) {
			cbbRIDModel.addElement(r);
		}

		cbbRID = new JComboBox();
		cbbRID.setBackground(Color.WHITE);
		cbbRID.setBorder(null);
		cbbRID.setModel(cbbRIDModel);
		cbbRID.setFont(new Font("Arial", Font.PLAIN, 14));
		cbbRID.setBounds(923, 173, 246, 27);
		cbbRID.setRenderer(new RIDRenderer());
		contentPane.add(cbbRID);

		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		try {
			teachers = Teacher.load(conn);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbTIDModel = new DefaultComboBoxModel();
		Teacher tct = new Teacher();
		tct.setId(null);
		tct.setName("");
		tct.setAddress(null);
		cbbTIDModel.addElement(tct);
		for (Teacher tc : teachers) {
			cbbTIDModel.addElement(tc);
		}

		cbbTID = new JComboBox();
		cbbTID.setBackground(Color.WHITE);
		cbbTID.setBorder(null);
		cbbTID.setModel(cbbTIDModel);
		cbbTID.setFont(new Font("Arial", Font.PLAIN, 14));
		cbbTID.setBounds(923, 227, 246, 27);
		cbbTID.setRenderer(new TIDRenderer());
		contentPane.add(cbbTID);

		ArrayList<Status> statuss = new ArrayList<Status>();
		statuss.add(new Status(true, "Mở"));
		statuss.add(new Status(false, "Đóng"));
		cbbStatusModel = new DefaultComboBoxModel();
		for (Status sts : statuss) {
			cbbStatusModel.addElement(sts);
		}

		cbbStatus = new JComboBox();
		cbbStatus.setBorder(null);
		cbbStatus.setBackground(Color.WHITE);
		cbbStatus.setModel(cbbStatusModel);
		cbbStatus.setFont(new Font("Arial", Font.PLAIN, 14));
		cbbStatus.setBounds(923, 285, 246, 27);
		cbbStatus.setRenderer(new StatusRenderer());
		contentPane.add(cbbStatus);

		ArrayList<Semester> semesters = new ArrayList<Semester>();
		semesters.add(new Semester(1, "Học Kỳ I"));
		semesters.add(new Semester(2, "Học Kỳ II"));
		semesters.add(new Semester(3, "Học Kỳ III"));
		semesters.add(new Semester(4, "Học Kỳ IV"));
		semesters.add(new Semester(5, "Học Kỳ V"));
		semesters.add(new Semester(6, "Học Kỳ VI"));
		semesters.add(new Semester(7, "Học Kỳ VII"));
		semesters.add(new Semester(8, "Học Kỳ VIII"));
		cbbSemesterModel = new DefaultComboBoxModel();
		for (Semester smt : semesters) {
			cbbSemesterModel.addElement(smt);
		}

		cbbSemester = new JComboBox();
		cbbSemester.setBackground(Color.WHITE);
		cbbSemester.setBorder(null);
		cbbSemester.setModel(cbbSemesterModel);
		cbbSemester.setFont(new Font("Arial", Font.PLAIN, 14));
		cbbSemester.setBounds(923, 341, 246, 27);
		cbbSemester.setRenderer(new SemesterRenderer());
		contentPane.add(cbbSemester);

		txtDescription = new JTextField();
		txtDescription.setBackground(Color.WHITE);
		txtDescription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDescription.setColumns(10);
		txtDescription.setBounds(923, 398, 246, 27);
		contentPane.add(txtDescription);
		btnCancel.setFocusPainted(false);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFind.setEnabled(true);
				btnCreate.setEnabled(true);
				btnEdit.setEnabled(true);
				btnCancel.setEnabled(false);
				btnSave.setEnabled(false);
				btnDelete.setEnabled(true);
				load();
			}
		});
		btnCancel.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnCancel.setBounds(1019, 625, BUTTON_WIDTH, BUTTON_HEIGHT);
		// btnCancel.setIcon(new ImageIcon("resources/cancel.png"));
		ImageIcon iconcancel = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconclose.png"));
		Image iccancel = iconcancel.getImage();
		Image newiconcancel = iccancel.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconcancel = new ImageIcon(newiconcancel);
		btnCancel.setIcon(iconcancel);
		contentPane.add(btnCancel);
		btnSave.setFocusPainted(false);
		btnSave.setBackground(Color.WHITE);
		btnSave.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag == -1)
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
		btnSave.setBounds(1019, 563, BUTTON_WIDTH, BUTTON_HEIGHT);
		// btnSave.setIcon(new ImageIcon("resources/save.jpg"));
		ImageIcon iconsave = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconsave.png"));
		Image icsave = iconsave.getImage();
		Image newiconsave = icsave.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconsave = new ImageIcon(newiconsave);
		btnSave.setIcon(iconsave);
		contentPane.add(btnSave);
		btnDelete.setFocusPainted(false);
		btnDelete.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnDelete.setBackground(Color.WHITE);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Del();
			}
		});
		btnDelete.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnDelete.setBounds(857, 625, BUTTON_WIDTH, BUTTON_HEIGHT);
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
				btnFind.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnCancel.setEnabled(true);
				btnSave.setEnabled(true);
				btnCreate.setEnabled(false);
			}
		});
		btnEdit.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnEdit.setBounds(857, 563, BUTTON_WIDTH, BUTTON_HEIGHT);
		// btnEdit.setIcon(new ImageIcon("resources/edit.png"));
		ImageIcon iconedit = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconfix.png"));
		Image icedit = iconedit.getImage();
		Image newiconedit = icedit.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconedit = new ImageIcon(newiconedit);
		btnEdit.setIcon(iconedit);
		contentPane.add(btnEdit);
		btnCreate.setFocusPainted(false);
		btnCreate.setBackground(Color.WHITE);
		btnCreate.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = -1;
				cbbCID.setEnabled(true);
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
		btnCreate.setBounds(1019, 498, BUTTON_WIDTH, BUTTON_HEIGHT);
		// btnCreate.setIcon(new ImageIcon("resources/create.png"));
		ImageIcon iconcreate = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconadd.png"));
		Image iccreate = iconcreate.getImage();
		Image newiconcreate = iccreate.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconcreate = new ImageIcon(newiconcreate);
		btnCreate.setIcon(iconcreate);
		contentPane.add(btnCreate);
		btnFind.setFocusPainted(false);
		btnFind.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnFind.setBackground(Color.WHITE);

		btnFind.setIcon(new ImageIcon("resources/find.png"));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbCID.setEnabled(true);
				btnCancel.setEnabled(true);
				Find();
			}
		});

		btnFind.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnFind.setBounds(857, 498, BUTTON_WIDTH, BUTTON_HEIGHT);
		ImageIcon iconfind = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconsearch.png"));
		Image icfind = iconfind.getImage();
		Image newiconfind = icfind.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconfind = new ImageIcon(newiconfind);
		btnFind.setIcon(iconfind);
		contentPane.add(btnFind);

		JLabel lblQLLHP = new JLabel("QUẢN LÝ LỚP HỌC PHẦN");
		lblQLLHP.setHorizontalAlignment(SwingConstants.CENTER);
		lblQLLHP.setFont(new Font("Arial", Font.BOLD, 28));
		lblQLLHP.setBounds(2, 0, 817, 47);
		contentPane.add(lblQLLHP);

		JLabel lblTTLHP = new JLabel("Thông Tin Lớp Học Phần");
		lblTTLHP.setHorizontalAlignment(SwingConstants.CENTER);
		lblTTLHP.setFont(new Font("Arial", Font.BOLD, 18));
		lblTTLHP.setBounds(819, 0, 359, 47);
		contentPane.add(lblTTLHP);

		load();
	}

	public static void load() {
		flag = 0;
		cbbCID.setEnabled(false);
		ArrayList<Course_Class> lisCourse_Class = new ArrayList<Course_Class>();
		try {
			lisCourse_Class = Course_Class.load(conn);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel) tabCourse_Class.getModel();
		if (model.getRowCount() > 0) {
			model.setRowCount(0);
		}
		Object[] rows = new Object[7];
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		for (int i = 0; i < lisCourse_Class.size(); i++) {
			rows[0] = lisCourse_Class.get(i).getCid();
			rows[1] = lisCourse_Class.get(i).getCcid();
			rows[2] = lisCourse_Class.get(i).getRid();
			rows[3] = lisCourse_Class.get(i).getTid();
			rows[4] = lisCourse_Class.get(i).isStatus();
			rows[5] = lisCourse_Class.get(i).getSemester();
			rows[6] = lisCourse_Class.get(i).getDescription();

			model.addRow(rows);
		}
	}

	public static void Add() {
		Course_Class cs = new Course_Class();
		cs.setCid(((Course) cbbCID.getSelectedItem()).getCid());
		cs.setCcid(txtCCID.getText());
		cs.setRid(((Room) cbbRID.getSelectedItem()).getRid());
		cs.setTid(((Teacher) cbbTID.getSelectedItem()).getId());
		cs.setStatus(((Status) cbbStatus.getSelectedItem()).isType());
		cs.setSemester(((Semester) cbbSemester.getSelectedItem()).getType());
		cs.setDescription(txtDescription.getText());
		try {
			if (Course_Class.Insert(cs, conn) == 1) {
				JOptionPane.showMessageDialog(tabCourse_Class, "Thêm Thành Công", "Thông Báo",
						JOptionPane.INFORMATION_MESSAGE);
				lisCourse_Class.add(cs);
				DefaultTableModel model = (DefaultTableModel) tabCourse_Class.getModel();
				model.setRowCount(0);
				load();
			} 
//			else
//				JOptionPane.showMessageDialog(tabCourse_Class, "Thêm thất bại", "Thông Báo",
//						JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void Edit() {
		Course_Class cs = new Course_Class();
		cs.setCid(((Course) cbbCID.getSelectedItem()).getCid());
		cs.setCcid(txtCCID.getText());
		cs.setRid(((Room) cbbRID.getSelectedItem()).getRid());
		cs.setTid(((Teacher) cbbTID.getSelectedItem()).getId());
		cs.setStatus(((Status) cbbStatus.getSelectedItem()).isType());
		cs.setSemester(((Semester) cbbSemester.getSelectedItem()).getType());
		cs.setDescription(txtDescription.getText());
		try {
			if (Course_Class.Edit(cs, conn) == 1) {
				JOptionPane.showMessageDialog(tabCourse_Class, "Sửa Thành Công", "Thông Báo",
						JOptionPane.INFORMATION_MESSAGE);
				for (int i = 0; i < lisCourse_Class.size(); i++) {
					if (lisCourse_Class.get(i).getCcid() == cs.getCcid()) {
						lisCourse_Class.get(i).setRid(cs.getRid());
						lisCourse_Class.get(i).setTid(cs.getTid());
						lisCourse_Class.get(i).setStatus(cs.isStatus());
						lisCourse_Class.get(i).setSemester(cs.getSemester());
						lisCourse_Class.get(i).setDescription(cs.getDescription());
						break;
					}

				}
				DefaultTableModel model = (DefaultTableModel) tabCourse_Class.getModel();
				model.setRowCount(0);
				load();
			} 
//			else
//				JOptionPane.showMessageDialog(tabCourse_Class, "Sửa thất bại", "Thông Báo",
//						JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	public static void Del() {
		String index = txtCCID.getText().toString();
		try {
			if (Course_Class.Del(index, conn) == 1) {
				lisCourse_Class.remove(index);
				JOptionPane.showMessageDialog(tabCourse_Class, "Xoa thanh cong!", "Thong Bao",
						JOptionPane.INFORMATION_MESSAGE);
				DefaultTableModel model = (DefaultTableModel) tabCourse_Class.getModel();
				model.setRowCount(0);
				load();
			} 
//			else
//				JOptionPane.showMessageDialog(tabCourse_Class, "Xay ra loi", "Thong Bao",
//						JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void Find() {
		ArrayList<Course_Class> lisCourse_Class = new ArrayList<Course_Class>();
		try {
			lisCourse_Class = Course_Class.load(conn);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel) tabCourse_Class.getModel();
		Object[] rows = new Object[7];
		String index = txtCCID.getText();
		try {
			Course_Class cs = Course_Class.findCourse_Class(index, conn);
			if (cs != null) {
				model.setRowCount(0);
				rows[0] = cs.getCid();
				rows[1] = cs.getCcid();
				rows[2] = cs.getRid();
				rows[3] = cs.getTid();
				rows[4] = cs.isStatus();
				rows[5] = cs.getSemester();
				rows[6] = cs.getDescription();

				model.addRow(rows);
			} else {
				JOptionPane.showConfirmDialog(tabCourse_Class, "Không Tìm Thấy!!", "Thông Báo", JOptionPane.OK_OPTION);

			}
		} catch (NumberFormatException | HeadlessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void clear() {
		cbbCID.setSelectedIndex(-1);
		txtCCID.setText("");
		cbbRID.setSelectedIndex(-1);
		cbbTID.setSelectedIndex(-1);
		cbbStatus.setSelectedIndex(-1);
		cbbSemester.setSelectedIndex(-1);
		txtDescription.setText("");
	}

	class Status {
		private boolean type;
		private String name;

		public Status(boolean type, String name) {
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

	class StatusRenderer extends BasicComboBoxRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Status) {
				Status val = (Status) value;
				setText(val.getName());
			}
			return this;
		}
	}

	class Semester {
		private int type;
		private String name;

		public Semester(int type, String name) {
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

	class SemesterRenderer extends BasicComboBoxRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Semester) {
				Semester val = (Semester) value;
				setText(val.getName());
			}
			return this;
		}
	}

	class CIDRenderer extends BasicComboBoxRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Course) {
				Course val = (Course) value;
				setText(val.getName());
				if (flag == -1) {
					try {
						txtCCID.setText(Course_Class.getCCIDCourse_Class(val.getCid(), conn));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					int row = tabCourse_Class.getSelectedRow();
					if (row != -1) {
						String ccid = tabCourse_Class.getValueAt(row, 1).toString();
						txtCCID.setText(ccid);
					}
				}
			}
			return this;
		}
	}

	class RIDRenderer extends BasicComboBoxRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Room) {
				Room val = (Room) value;
				setText(val.getArea() + " - " + val.getName());
			}
			return this;
		}
	}

	class TIDRenderer extends BasicComboBoxRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Teacher) {
				Teacher val = (Teacher) value;
				setText(val.getName());
			}
			return this;
		}
	}
}
