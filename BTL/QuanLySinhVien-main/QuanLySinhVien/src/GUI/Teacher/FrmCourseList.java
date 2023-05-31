package GUI.Teacher;

import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import GUI.InitGUI;
import GUI.Admin.FrmManHinhChinh;
import Model.Course;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class FrmCourseList extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection conn = null;

	private String FONT_TYPE;
	private int FONT;
	private int FONT_SIZE;
	private int BUTTON_HEIGHT;
	private int BUTTON_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCREEN_WIDTH;

	private static ArrayList<Course> lisCourse = new ArrayList<Course>();
	private static String[] columnName = {"CID", "Tên Học Phần", "Mô Tả", "Số Tín Chỉ"};
	static DefaultTableModel model = new DefaultTableModel(columnName,0);
	private static JTable tabCourse = new JTable(model) ;
	private static JButton btnReLoad = new JButton("Làm Mới ");
	private final JLabel lblDSHP = new JLabel("DANH SÁCH HỌC PHẦN");

	public void Init() {
		InitGUI init = new InitGUI();
		this.FONT_TYPE = init.getFONT_TYPE();
		this.FONT = init.getFONT();
		this.FONT_SIZE = init.getFONT_SIZE();
		this.BUTTON_HEIGHT = init.getBUTTON_HEIGHT();
		this.BUTTON_WIDTH = init.getBUTTON_WIDTH();
		this.SCREEN_WIDTH = init.getSCREEN_WIDTH();
		this.SCREEN_HEIGHT=init.getSCREEN_HEIGHT();
	}

	public FrmCourseList(Connection conn) {
		setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.conn = conn;
		Init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1178, 713);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		// contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabCourse.setFont(new Font("Arial", Font.PLAIN, 14));
		tabCourse.setBounds(10, 168, 870, 305);
		tabCourse.setRowHeight(30);
		tabCourse.setShowHorizontalLines(true);
		tabCourse.setShowVerticalLines(true);
		
		JScrollPane scrollPane = new JScrollPane(tabCourse);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(4, 50, 1171, 586);
		contentPane.add(scrollPane);
		btnReLoad.setFocusPainted(false);
		btnReLoad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnReLoad.setBackground(Color.WHITE);

		btnReLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(contentPane, "Làm Mới Thành Công", "Thông Báo",JOptionPane.INFORMATION_MESSAGE);
				load();
			}
		});
		btnReLoad.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnReLoad.setBounds(1056, 639, 120, 45);
		//btnSave.setIcon(new ImageIcon("resources/save.jpg"));
		ImageIcon iconrl = new ImageIcon(FrmManHinhChinh.class.getResource("/res/iconreload.png"));
		Image icrl = iconrl.getImage();
		Image newiconrl = icrl.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconrl = new ImageIcon(newiconrl);
		btnReLoad.setIcon(iconrl);
		contentPane.add(btnReLoad);
		lblDSHP.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSHP.setFont(new Font("Arial", Font.BOLD, 28));
		lblDSHP.setBounds(4, 0, 1169, 47);
		
		contentPane.add(lblDSHP);
		
		load();
	}
	public static void load() {
		ArrayList<Course> lisCourse = new ArrayList<Course>();
		try {
			lisCourse = Course.load(conn);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel)tabCourse.getModel();
		if(model.getRowCount() > 0) {
			model.setRowCount(0);
		}
		Object[] rows = new Object[4];
		for(int i=0; i <lisCourse.size();i++ )
		{    
			rows[0]=(lisCourse.get(i).getCid()); 
			rows[1]=(lisCourse.get(i).getName()); 
			rows[2]=(lisCourse.get(i).getDescription()); 
			rows[3]=(lisCourse.get(i).getNumOfCredits()); 

			model.addRow(rows); 
		}
	}
}
