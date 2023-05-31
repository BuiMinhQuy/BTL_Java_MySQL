package GUI.Admin;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GUI.InitGUI;
import Model.Teacher;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;


public class FrmTeacher extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection conn = null;

	private String FONT_TYPE;
	private int FONT;
	private int FONT_SIZE;
	private int COMPONENTS_HEIGHT;
	private int BUTTON_HEIGHT;
	private int BUTTON_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCREEN_WIDTH;

	private static JTextField txtTID;
	private static JTextField txtName;
	private static JTextField txtEmail;
	private static JTextField txtPhone;
	private static JTextField txtAddress;
	private static JTextField txtAID;
	private static ArrayList<Teacher> lisTeacher = new ArrayList<Teacher>();
	private static String[] columnName = {"MSGV","ID Tài Khoản" , "Tên Giảng Viên", "Email", "Số Điện Thoại", "Địa Chỉ"};
	static DefaultTableModel model = new DefaultTableModel(columnName,0);
	private static JTable tabTeacher = new JTable(model) ;
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

	public FrmTeacher(Connection conn) {
		setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.conn = conn;
		Init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1178, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		tabTeacher.setFont(new Font("Arial", Font.PLAIN, 14));


		tabTeacher.setBounds(10, 168, 870, 305);
		tabTeacher.setRowHeight(30);
		tabTeacher.setShowHorizontalLines(true);
		tabTeacher.setShowVerticalLines(true);
		tabTeacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabTeacher.getSelectedRow();
				if(row != -1) {
					txtTID.setText(tabTeacher.getValueAt(row, 0).toString());
					txtAID.setText(tabTeacher.getValueAt(row, 1).toString());
					txtName.setText(tabTeacher.getValueAt(row, 2).toString());
					txtEmail.setText(tabTeacher.getValueAt(row, 3).toString());
					txtPhone.setText(tabTeacher.getValueAt(row, 4).toString());
					txtAddress.setText(tabTeacher.getValueAt(row, 5).toString());
				}
			}

		});
		JScrollPane scrollPane = new JScrollPane(tabTeacher);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(2, 50, 819, 633);
		contentPane.add(scrollPane);

		JLabel lblTID = new JLabel("MSGV :");
		lblTID.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblTID.setBounds(827, 49, 96, 38);
		contentPane.add(lblTID);

		JLabel lblName = new JLabel("Tên Giảng Viên :");
		lblName.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblName.setBounds(827, 157, 122, 38);
		contentPane.add(lblName);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblEmail.setBounds(827, 210, 122, 38);
		contentPane.add(lblEmail);

		JLabel lblPhone = new JLabel("Số Điện Thoại :");
		lblPhone.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblPhone.setBounds(827, 268, 122, 38);
		contentPane.add(lblPhone);

		JLabel lblAddress = new JLabel("Địa Chỉ :");
		lblAddress.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblAddress.setBounds(827, 323, 122, 38);
		contentPane.add(lblAddress);

		txtTID = new JTextField();
		txtTID.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTID.setBounds(953, 58, 217, 27);
		contentPane.add(txtTID);
		txtTID.setColumns(10);

		txtName = new JTextField();
		txtName.setFont(new Font("Arial", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(953, 166, 217, 27);
		contentPane.add(txtName);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(953, 219, 217, 27);
		contentPane.add(txtEmail);

		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setBounds(953, 277, 217, 27);
		contentPane.add(txtPhone);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(953, 332, 217, 27);
		contentPane.add(txtAddress);
		btnCancel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFocusPainted(false);

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFind.setEnabled(true);
				btnCreate.setEnabled(true);
				btnEdit.setEnabled(true);
				btnCancel.setEnabled(false);
				btnSave.setEnabled(false);
				btnDelete.setEnabled(true);
				txtTID.setEnabled(true);
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
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
				txtTID.setEnabled(false);
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
		btnCreate.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnCreate.setBackground(Color.WHITE);
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
		btnFind.setBackground(Color.WHITE);
		btnFind.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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

		JLabel lblAID = new JLabel("ID Tài Khoản :");
		lblAID.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblAID.setBounds(827, 102, 122, 38);
		contentPane.add(lblAID);

		txtAID = new JTextField();
		txtAID.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAID.setColumns(10);
		txtAID.setBounds(953, 111, 217, 27);
		contentPane.add(txtAID);
		
		JLabel lblQLLGV = new JLabel("QUẢN LÝ GIẢNG VIÊN");
		lblQLLGV.setHorizontalAlignment(SwingConstants.CENTER);
		lblQLLGV.setFont(new Font("Arial", Font.BOLD, 28));
		lblQLLGV.setBounds(0, 0, 819, 47);
		contentPane.add(lblQLLGV);
		
		JLabel lblTTGV = new JLabel("Thông Tin Giảng Viên");
		lblTTGV.setHorizontalAlignment(SwingConstants.CENTER);
		lblTTGV.setFont(new Font("Arial", Font.BOLD, 18));
		lblTTGV.setBounds(819, 0, 359, 47);
		contentPane.add(lblTTGV);
		load();
	}
	public static void load() {
		txtTID.setEnabled(true);
		ArrayList<Teacher> lisTeacher = new ArrayList<Teacher>();
		try {
			lisTeacher = Teacher.load(conn);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel)tabTeacher.getModel();
		if(model.getRowCount() > 0) {
			model.setRowCount(0);
		}
		Object[] rows = new Object[6];
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		for(int i=0; i <lisTeacher.size();i++ )
		{    
			rows[0]=(lisTeacher.get(i).getId()); 
			rows[1]=(lisTeacher.get(i).getAid()); 
			rows[2]=(lisTeacher.get(i).getName()); 
			rows[3]=(lisTeacher.get(i).getEmail()); 
			rows[4]=(lisTeacher.get(i).getPhone()); 
			rows[5]=(lisTeacher.get(i).getAddress()); 

			model.addRow(rows); 
		}
	}
	public static void Add() {
		Teacher tc = new Teacher();
		tc.setId(txtTID.getText().toString());
		tc.setAid(txtAID.getText().toString());
		tc.setName(txtName.getText().toString());
		tc.setEmail(txtEmail.getText().toString());
		tc.setPhone(txtPhone.getText().toString());
		tc.setAddress(txtAddress.getText().toString());
		try {
			if(Teacher.Insert(tc, conn) == 1) {
				JOptionPane.showMessageDialog(tabTeacher, "Thêm thành công",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				lisTeacher.add(tc);
				DefaultTableModel model = (DefaultTableModel)tabTeacher.getModel();
				model.setRowCount(0);
				load();
			}
//			else
//				JOptionPane.showMessageDialog(tabTeacher, "Thêm thất bại",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void Edit() {
		String[] teacher = new String[6];

		teacher[0] =txtTID.getText().toString();
		teacher[1] =txtAID.getText().toString();
		teacher[2] =txtName.getText().toString();
		teacher[3] =txtEmail.getText().toString();
		teacher[4] =txtPhone.getText().toString();
		teacher[5] =txtAddress.getText().toString();
		Teacher tc = new Teacher();
		tc.setId(teacher[0]);
		tc.setAid(teacher[1]);
		tc.setName(teacher[2]);
		tc.setEmail(teacher[3]);
		tc.setPhone(teacher[4]);
		tc.setAddress(teacher[5]);
		try {
			if(Teacher.Edit(tc, conn) == 1) {
				JOptionPane.showMessageDialog(tabTeacher, "Sửa Thành Công",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				for(int i=0; i< lisTeacher.size();i++){
					if(lisTeacher.get(i).getId() == teacher[0]) {
						lisTeacher.get(i).setAid(teacher[1]);
						lisTeacher.get(i).setName(teacher[2]);
						lisTeacher.get(i).setEmail(teacher[3]);
						lisTeacher.get(i).setPhone(teacher[4]);
						lisTeacher.get(i).setAddress(teacher[5]);
					}

				}
				DefaultTableModel model = (DefaultTableModel)tabTeacher.getModel();
				model.setRowCount(0);
				load();	
			}
//			else
//				JOptionPane.showMessageDialog(tabTeacher, "Sửa thất bại",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);


		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	public static void Del() {
		String index=txtTID.getText().toString();

		try {
			if(Teacher.Del(index, conn)==1)
			{
				lisTeacher.remove(index);
				JOptionPane.showMessageDialog( tabTeacher, "Xóa thành công",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				DefaultTableModel model = (DefaultTableModel)tabTeacher.getModel();
				model.setRowCount(0);
				load();
			}
//			else
//				JOptionPane.showMessageDialog( tabTeacher, "Xóa thất bại",  "Thông báo", JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	public static void Find() {
		ArrayList<Teacher> lisTeacher = new ArrayList<Teacher>();
		try {
			lisTeacher = Teacher.load(conn);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel)tabTeacher.getModel();
		Object[] rows = new Object[6]; 
		String index = txtTID.getText().toString();
		Teacher tc;
		try {
			tc = Teacher.findTeacher(index, conn);
			if(tc != null) {
				model.setRowCount(0);
				rows[0]=tc.getId(); 
				rows[1]=tc.getAid();
				rows[2]=tc.getName(); 
				rows[3]=tc.getEmail(); 
				rows[4]=tc.getPhone();
				rows[5]=tc.getAddress();  

				model.addRow(rows); 
			}
			else {
				JOptionPane.showConfirmDialog(tabTeacher, "Không tìm thấy","Thông báo",JOptionPane.DEFAULT_OPTION);

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void clear() {
		txtTID.setText("");
		txtName.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		txtAID.setText("");
	}
}
