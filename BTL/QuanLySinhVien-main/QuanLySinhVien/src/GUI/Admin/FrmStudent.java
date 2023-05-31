 package GUI.Admin;

import java.awt.Component;
import java.awt.Font;
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
import Model.Student;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class FrmStudent extends JInternalFrame {

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

	private static JTextField txtID;
	private static JTextField txtName;
	private static JComboBox cbbGender;
	private static DefaultComboBoxModel cbbGenderModel;
	private static JDateChooser dtDateofBirth;
	private static JTextField txtEmail;
	private static JTextField txtPhone;
	private static JTextField txtAddress;
	private static JComboBox cbbAccount;
	private static DefaultComboBoxModel cbbAccountModel;
	private static JComboBox cbbFaculty;
	private static DefaultComboBoxModel cbbFacultyModel;
	private static ArrayList<Student> lisStudent = new ArrayList<Student>();
	private static String[] columnName = {"MSSV", "Tên Sinh Viên", "Giới Tính", "Ngày Sinh", "Email", "Số Điện Thoại", "Địa Chỉ","ID Tài Khoản", "Khoa"};
	private static DefaultTableModel model = new DefaultTableModel(columnName,0);
	private static JTable tabStudent = new JTable(model) ;
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
		this.SCREEN_HEIGHT=init.getSCREEN_HEIGHT();
	}

	public FrmStudent(Connection conn) {
		setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		FrmStudent.conn = conn;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		Init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1178, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		tabStudent.setFont(new Font("Arial", Font.PLAIN, 14));

		tabStudent.setBounds(10, 168, 870, 305);
		tabStudent.setRowHeight(30);
		tabStudent.setShowHorizontalLines(true);
		tabStudent.setShowVerticalLines(true);
		tabStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabStudent.getSelectedRow();
				if(row != -1) {
					txtID.setText(tabStudent.getValueAt(row, 0).toString());
					txtName.setText(tabStudent.getValueAt(row, 1).toString());
					boolean gender = Boolean.valueOf(tabStudent.getValueAt(row, 2).toString());
					if(gender) {
						cbbGender.setSelectedIndex(0);
					}
					else {
						cbbGender.setSelectedIndex(1);
					}
					try {
						Date dt =formatter.parse(tabStudent.getValueAt(row, 3).toString());
						dtDateofBirth.setDate(dt);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					txtEmail.setText(tabStudent.getValueAt(row, 4).toString());
					txtPhone.setText(tabStudent.getValueAt(row, 5).toString());
					txtAddress.setText(tabStudent.getValueAt(row, 6).toString());
					String AID = tabStudent.getValueAt(row, 7).toString();
					int aid=-1;
					for(int i = 0;i<cbbAccountModel.getSize();i++) {
						Account ac = (Account)cbbAccountModel.getElementAt(i);
						if(ac.getAid().equals(AID)) {
							aid=i;
							break;
						}
					}
					cbbAccount.setSelectedIndex(aid);
					String FID = tabStudent.getValueAt(row, 8).toString();
					int fid = -1;
					for(int i = 0;i<cbbFacultyModel.getSize();i++) {
						Faculty fl = (Faculty)cbbFacultyModel.getElementAt(i);
						if(fl.getFid().equals(FID)) {
							fid=i;
							break;
						}
					}
					cbbFaculty.setSelectedIndex(fid);;
				}
			}

		});
		JScrollPane scrollPane = new JScrollPane(tabStudent);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(2, 50, 819, 633);
		contentPane.add(scrollPane);

		JLabel lblSID = new JLabel("MSSV :");
		lblSID.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblSID.setBounds(829, 46, 107, 38);
		contentPane.add(lblSID);

		JLabel lblName = new JLabel("Tên Sinh Viên :");
		lblName.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblName.setBounds(827, 95, 109, 38);
		contentPane.add(lblName);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblEmail.setBounds(829, 392, 82, 38);
		contentPane.add(lblEmail);

		JLabel lblPhone = new JLabel("Số Điện Thoại :");
		lblPhone.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblPhone.setBounds(827, 339, 109, 38);
		contentPane.add(lblPhone);

		JLabel lblAddress = new JLabel("Địa Chỉ :");
		lblAddress.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblAddress.setBounds(829, 440, 73, 38);
		contentPane.add(lblAddress);

		txtID = new JTextField();
		txtID.setFont(new Font("Arial", Font.PLAIN, 14));
		txtID.setBounds(940, 55, 230, 27);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtName = new JTextField();
		txtName.setFont(new Font("Arial", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(940, 104, 230, 27);
		contentPane.add(txtName);

		ArrayList<Gender> genders = new ArrayList<Gender>();
		genders.add(new Gender(true, "Nam"));
		genders.add(new Gender(false, "Nữ"));
		cbbGenderModel = new DefaultComboBoxModel();
		for(Gender gd : genders) {
			cbbGenderModel.addElement(gd);
		}

		cbbGender = new JComboBox();
		cbbGender.setBackground(Color.WHITE);
		cbbGender.setBorder(null);
		cbbGender.setModel(cbbGenderModel);
		cbbGender.setFont(new Font("Arial", Font.PLAIN, 14));
		cbbGender.setBounds(940, 299, 230, 27);
		cbbGender.setRenderer(new GenderRenderer());
		contentPane.add(cbbGender);

		dtDateofBirth = new JDateChooser();
		dtDateofBirth.setBorder(null);
		dtDateofBirth.setBackground(Color.WHITE);
		dtDateofBirth.getCalendarButton().setBounds(209, 0, 21, 27);
		dtDateofBirth.setBounds(940, 250, 230, 27);
		contentPane.add(dtDateofBirth);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(940, 400, 230, 27);
		contentPane.add(txtEmail);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(940, 448, 230, 27);
		contentPane.add(txtAddress);

		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setBounds(940, 348, 230, 27);
		contentPane.add(txtPhone);

		ArrayList< Account> accounts = new ArrayList< Account>();
		try {
			accounts = Account.load(conn);

		}
		catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		cbbAccountModel = new DefaultComboBoxModel();
		for(Account ac : accounts) {
			cbbAccountModel.addElement(ac);
		}

		cbbAccount = new JComboBox(cbbAccountModel);
		cbbAccount.setBorder(null);
		cbbAccount.setBackground(Color.WHITE);
		cbbAccount.setFont(new Font("Arial", Font.PLAIN, 14));
		cbbAccount.setBounds(940, 199, 230, 27);
		cbbAccount.setRenderer(new AccountRenderer());
		contentPane.add(cbbAccount);

		ArrayList<Faculty> facultys = new ArrayList<Faculty>();
		try {
			facultys = Faculty.load(conn);
		}
		catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		cbbFacultyModel = new DefaultComboBoxModel();
		for(Faculty fl : facultys) {
			cbbFacultyModel.addElement(fl);
		}

		cbbFaculty = new JComboBox(cbbFacultyModel);
		cbbFaculty.setBackground(Color.WHITE);
		cbbFaculty.setBorder(null);
		cbbFaculty.setFont(new Font("Arial", Font.PLAIN, 14));
		cbbFaculty.setBounds(940, 151, 230, 27);
		cbbFaculty.setRenderer(new FacultyRenderer());
		contentPane.add(cbbFaculty);
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
				txtID.setEnabled(true);
				load();
			}
		});
		btnCancel.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnCancel.setBounds(1019, 625, BUTTON_WIDTH, BUTTON_HEIGHT);
		//btnCancel.setIcon(new ImageIcon("resources/cancel.png"));
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
				if(flag == 0)
					Add();
				else if(flag == 1)
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
		//btnSave.setIcon(new ImageIcon("resources/save.jpg"));
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
		btnDelete.setBounds(857, 625, BUTTON_WIDTH, BUTTON_HEIGHT);
		//btnDelete.setIcon(new ImageIcon("resources/delete.png"));
		ImageIcon icondelete = new ImageIcon(FrmManHinhChinh.class.getResource("/res/icondelete.png"));
		Image icdelete = icondelete.getImage();
		Image newicondelete = icdelete.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		icondelete = new ImageIcon(newicondelete);
		btnDelete.setIcon(icondelete);
		contentPane.add(btnDelete);
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnEdit.setFocusPainted(false);

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1; 
				txtID.setEnabled(false);
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
		//btnEdit.setIcon(new ImageIcon("resources/edit.png"));
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
		btnCreate.setBounds(1019, 498, BUTTON_WIDTH, BUTTON_HEIGHT);
		//btnCreate.setIcon(new ImageIcon("resources/create.png"));
		ImageIcon iconcreate = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconadd.png"));
		Image iccreate = iconcreate.getImage();
		Image newiconcreate = iccreate.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconcreate = new ImageIcon(newiconcreate);
		btnCreate.setIcon(iconcreate);
		contentPane.add(btnCreate);
		btnFind.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnFind.setBackground(Color.WHITE);
		btnFind.setFocusPainted(false);

		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel lblAccount = new JLabel("Tài Khoản :");
		lblAccount.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblAccount.setBounds(829, 191, 82, 38);
		contentPane.add(lblAccount);

		JLabel lblGender = new JLabel("Giới Tính :");
		lblGender.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblGender.setBounds(827, 291, 107, 38);
		contentPane.add(lblGender);

		JLabel lblDateofBirth = new JLabel("Ngày Sinh :");
		lblDateofBirth.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblDateofBirth.setBounds(829, 242, 82, 38);
		contentPane.add(lblDateofBirth);

		JLabel lblCourse = new JLabel("Khoa :");
		lblCourse.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCourse.setBounds(829, 143, 60, 38);
		contentPane.add(lblCourse);
		
		JLabel lblQLSV = new JLabel("QUẢN LÝ SINH VIÊN ");
		lblQLSV.setHorizontalAlignment(SwingConstants.CENTER);
		lblQLSV.setFont(new Font("Arial", Font.BOLD, 28));
		lblQLSV.setBounds(0, 0, 819, 47);
		contentPane.add(lblQLSV);
		
		JLabel lblTTSV = new JLabel("Thông Tin Sinh Viên");
		lblTTSV.setHorizontalAlignment(SwingConstants.CENTER);
		lblTTSV.setFont(new Font("Arial", Font.BOLD, 18));
		lblTTSV.setBounds(819, 0, 359, 47);
		contentPane.add(lblTTSV);
		
		load();
	}
	public static void load() {
		txtID.setEnabled(true);
		ArrayList<Student> lisStudent = new ArrayList<Student>();
		try {
			lisStudent = Student.load(conn);
		} catch(ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel)tabStudent.getModel();
		if(model.getRowCount() > 0) {
			model.setRowCount(0);
		}
		Object[] rows = new Object[9];
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		for(int i=0; i <lisStudent.size();i++ )
		{    
			rows[0]=(lisStudent.get(i).getId()); 
			rows[1]=(lisStudent.get(i).getName()); 
			rows[2]=(lisStudent.get(i).isGender());
			rows[3]=(lisStudent.get(i).getDateOfBirth());
			rows[4]=(lisStudent.get(i).getEmail()); 
			rows[5]=(lisStudent.get(i).getPhone());
			rows[6]=(lisStudent.get(i).getAddress()); 
			rows[7]=(lisStudent.get(i).getAid()); 
			rows[8]=(lisStudent.get(i).getFid());

			model.addRow(rows); 
		}
	}
	public static void Add() {
		Student sd = new Student();
		sd.setId(txtID.getText().toString());
		sd.setName(txtName.getText().toString());
		sd.setGender(((Gender)cbbGender.getSelectedItem()).isType());
		sd.setDateOfBirth(dtDateofBirth.getDate());
		sd.setEmail(txtEmail.getText().toString());
		sd.setPhone(txtPhone.getText().toString());
		sd.setAddress(txtAddress.getText().toString());
		sd.setAid(((Account)cbbAccount.getSelectedItem()).getAid());
		sd.setFid(((Faculty)cbbFaculty.getSelectedItem()).getFid());
		try {
			if(Student.Insert(sd, conn) == 1) {
				JOptionPane.showMessageDialog(tabStudent, "Thêm thành công",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				lisStudent.add(sd);
				DefaultTableModel model = (DefaultTableModel)tabStudent.getModel();
				model.setRowCount(0);
				load();
			}
//			else
//				JOptionPane.showMessageDialog(tabStudent, "Thêm thất bại",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void Edit() {
		Student sd = new Student();
		sd.setId(txtID.getText().toString());
		sd.setName(txtName.getText().toString());
		sd.setGender(((Gender)cbbGender.getSelectedItem()).isType());
		sd.setDateOfBirth(dtDateofBirth.getDate());
		sd.setEmail(txtEmail.getText().toString());
		sd.setPhone(txtPhone.getText().toString());
		sd.setAddress(txtAddress.getText().toString());
		sd.setAid(((Account)cbbAccount.getSelectedItem()).getAid());
		sd.setFid(((Faculty)cbbFaculty.getSelectedItem()).getFid());
		try {
			if(Student.Edit(sd, conn) == 1) {
				JOptionPane.showMessageDialog(tabStudent, "Sửa thành công",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				for(int i=0; i< lisStudent.size();i++){
					if(lisStudent.get(i).getId() == sd.getId()) {
						lisStudent.get(i).setName(sd.getName());
						lisStudent.get(i).setGender(sd.isGender());
						lisStudent.get(i).setDateOfBirth(sd.getDateOfBirth());
						lisStudent.get(i).setEmail(sd.getEmail());
						lisStudent.get(i).setPhone(sd.getPhone());
						lisStudent.get(i).setAddress(sd.getAddress());
						lisStudent.get(i).setAid(sd.getAid());
						lisStudent.get(i).setFid(sd.getFid());
						break;
					}

				}
				DefaultTableModel model = (DefaultTableModel)tabStudent.getModel();
				model.setRowCount(0);
				load();	
			}
//			else
//				JOptionPane.showMessageDialog(tabStudent, "Sửa thất bại",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);


		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	@SuppressWarnings("unlikely-arg-type")
	public static void Del() {
		String index=txtID.getText().toString();
		try {
			if(Student.Del(index, conn)==1)
			{
				lisStudent.remove(index);
				JOptionPane.showMessageDialog( tabStudent, "Xóa thành công",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				DefaultTableModel model = (DefaultTableModel)tabStudent.getModel();
				model.setRowCount(0);
				load();
			}
			else
				JOptionPane.showMessageDialog( tabStudent, "Xoá thất bại",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	public static void Find() {
		ArrayList<Student> lisStudent = new ArrayList<Student>();
		try {
			lisStudent = Student.load(conn);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel)tabStudent.getModel();
		Object[] rows = new Object[9]; 
		String index = txtID.getText().toString();
		Student sd;
		try {
			sd = Student.findStudent(index, conn);
			if(sd != null) {
				model.setRowCount(0);
				rows[0]=sd.getId(); 
				rows[1]=sd.getName(); 
				rows[2]=sd.isGender();
				rows[3]=sd.getDateOfBirth();
				rows[4]=sd.getEmail(); 
				rows[5]=sd.getPhone();
				rows[6]=sd.getAddress(); 
				rows[7]=sd.getAid(); 
				rows[8]=sd.getFid();

				model.addRow(rows); 
			}
			else {
				JOptionPane.showConfirmDialog(tabStudent, "Không tìm thấy","Thông báo",JOptionPane.OK_OPTION);

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void clear() {
		txtID.setText("");
		txtName.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
	}
	class Gender{
		private boolean type;
		private String name;
		public Gender(boolean type, String name) {
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
	class GenderRenderer extends BasicComboBoxRenderer
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(value instanceof Gender){
                Gender val = (Gender) value;
                setText(val.getName());
            }
            return this;
        }
	}
	class AccountRenderer extends BasicComboBoxRenderer
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(value instanceof Account){
                Account val1 = (Account) value;
                setText(val1.getUserName());
            }
            return this;
        }
	}
	class FacultyRenderer extends BasicComboBoxRenderer
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(value instanceof Faculty){
                Faculty val2 = (Faculty) value;
                setText(val2.getName());
            }
            return this;
        }
	}
}
