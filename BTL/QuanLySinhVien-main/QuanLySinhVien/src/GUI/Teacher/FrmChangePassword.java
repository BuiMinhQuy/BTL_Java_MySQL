package GUI.Teacher;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import GUI.FrmLogin;
import GUI.InitGUI;
import GUI.Teacher.FrmChangePassword;
import Model.Account;
import javax.swing.SwingConstants;

public class FrmChangePassword extends JDialog{
	private static Connection conn;
	private static String userName;

	private String FONT_TYPE;
	private int FONT;
	private int FONT_SIZE;
	private int COMPONENTS_HEIGHT;
	private int BUTTON_HEIGHT;
	private int BUTTON_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCREEN_WIDTH;

	private static JPanel contentPane;
	private static JLabel lblOldPassword;
	private static JLabel lblNewPassword;
	private static JLabel lblReEnterPassword;
	private static JLabel lblShowPassword;
	private static JPasswordField pwfOldPassword;
	private static JPasswordField pwfNewPassword;
	private static JPasswordField pwfReEnterPassword;
	private static JCheckBox cbShowPassword;
	private static JButton btnChangePassword;
	private static JButton btnClose;

	private static FrmLogin frmLG;
	private static FrmManHinhChinh frmMHC;

	public void Init() {
		InitGUI init = new InitGUI();
		this.FONT_TYPE = init.getFONT_TYPE();
		this.FONT = init.getFONT();
		this.FONT_SIZE = init.getFONT_SIZE();
		this.COMPONENTS_HEIGHT = init.getCOMPONENTS_HEIGHT();
		this.BUTTON_HEIGHT = init.getBUTTON_HEIGHT();
		this.BUTTON_WIDTH = init.getBUTTON_WIDTH();
		this.SCREEN_WIDTH = 400;
		this.SCREEN_HEIGHT = 400;
	}

	public FrmChangePassword(FrmLogin frmLG, FrmManHinhChinh frmMHC, Connection conn, String userName) {
		setUndecorated(true);
		this.frmLG = frmLG;
		this.frmMHC = frmMHC;
		this.conn = conn;
		this.userName = userName;
		Init();
		setTitle("Thay đổi mật khẩu");

		int x = Toolkit.getDefaultToolkit().getScreenSize().width;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height;
		x = (x - this.SCREEN_WIDTH)/2;
		y = (y - this.SCREEN_HEIGHT)/2;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 444, 352);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblOldPassword = new JLabel("Mật khẩu cũ :");
		lblOldPassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblOldPassword.setBounds(28, 60, 100, 20);
		contentPane.add(lblOldPassword);

		lblNewPassword = new JLabel("Mật khẩu mới :");
		lblNewPassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblNewPassword.setBounds(28, 126, 134, 20);
		contentPane.add(lblNewPassword);

		lblReEnterPassword = new JLabel("Nhập lại mật khẩu mới :");
		lblReEnterPassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblReEnterPassword.setBounds(25, 194, 193, 20);
		contentPane.add(lblReEnterPassword);

		pwfOldPassword = new JPasswordField(10);
		pwfOldPassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		pwfOldPassword.setBounds(204, 60, 232, 20);
		contentPane.add(pwfOldPassword);
		pwfOldPassword.setColumns(10);

		pwfNewPassword = new JPasswordField(10);
		pwfNewPassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		pwfNewPassword.setColumns(10);
		pwfNewPassword.setBounds(204, 126, 232, 20);
		contentPane.add(pwfNewPassword);

		pwfReEnterPassword = new JPasswordField(10);
		pwfReEnterPassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		pwfReEnterPassword.setColumns(10);
		pwfReEnterPassword.setBounds(204, 194, 232, 20);
		contentPane.add(pwfReEnterPassword);

		cbShowPassword = new JCheckBox();
		cbShowPassword.setBackground(new Color(255, 255, 204));
		cbShowPassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		cbShowPassword.setBounds(200, 240, 19, 20);
		cbShowPassword.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbShowPassword.isSelected()) {
					pwfOldPassword.setEchoChar((char) 0);
					pwfNewPassword.setEchoChar((char) 0);
					pwfReEnterPassword.setEchoChar((char) 0);
				} else {
					pwfOldPassword.setEchoChar('*');
					pwfNewPassword.setEchoChar('*');
					pwfReEnterPassword.setEchoChar('*');
				}
			}
		});
		contentPane.add(cbShowPassword);

		lblShowPassword = new JLabel("Hiển thị mật khẩu");
		lblShowPassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		lblShowPassword.setBounds(233, 240, 146, 20);
		contentPane.add(lblShowPassword);

		btnChangePassword = new JButton("Lưu");
		btnChangePassword.setForeground(Color.WHITE);
		btnChangePassword.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnChangePassword.setFocusPainted(false);
		btnChangePassword.setBackground(new Color(0, 0, 83));
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!focus()) {
					int choose = JOptionPane.showConfirmDialog(contentPane,"Bạn có muốn cập nhật khẩu không" , "Thông báo", JOptionPane.OK_CANCEL_OPTION);
					if(choose == JOptionPane.OK_OPTION) {
						try {
							String oldPassword = String.valueOf(pwfOldPassword.getPassword());
							String newPassword = String.valueOf(pwfNewPassword.getPassword());
							try {
								if(Account.checkLogin(userName, oldPassword, 2, conn)) {
									try {
										int success = Account.UpdatePassWord(userName, newPassword, conn);
										if(success == 0) {
											JOptionPane.showMessageDialog(contentPane,"Cập nhật không thành công! Vui lòng thử lại" , "Thông báo", JOptionPane.OK_CANCEL_OPTION);
										}
										else {
											JOptionPane.showMessageDialog(contentPane,"Cập nhật mật khẩu thành công", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
											frmLG.setVisible(true);
											frmMHC.setVisible(false);
											frmMHC.dispose();
											setVisible(false);
											dispose();
										}
									} catch (ClassNotFoundException | SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							} catch (HeadlessException | ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnChangePassword.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnChangePassword.setBounds(43, 281, BUTTON_WIDTH, BUTTON_HEIGHT);
		contentPane.add(btnChangePassword);

		btnClose = new JButton("Đóng");
		btnClose.setForeground(Color.WHITE);
		btnClose.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnClose.setBackground(new Color(0, 0, 83));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FrmChangePassword.frmLG.setVisible(true);
				dispose();
			}
		});
		btnClose.setFont(new Font(FONT_TYPE, FONT, FONT_SIZE));
		btnClose.setBounds(282, 281, BUTTON_WIDTH, BUTTON_HEIGHT);
		contentPane.add(btnClose);
		
		JPanel panelTDMK = new JPanel();
		panelTDMK.setLayout(null);
		panelTDMK.setBackground(new Color(0, 0, 83));
		panelTDMK.setBounds(0, 0, 444, 40);
		contentPane.add(panelTDMK);
		
		JLabel lblTDMK = new JLabel("THAY ĐỔI MẬT KHẨU");
		lblTDMK.setHorizontalAlignment(SwingConstants.CENTER);
		lblTDMK.setForeground(Color.WHITE);
		lblTDMK.setFont(new Font("Arial", Font.BOLD, 19));
		lblTDMK.setBackground(new Color(0, 0, 83));
		lblTDMK.setBounds(0, 0, 444, 40);
		panelTDMK.add(lblTDMK);
	}
	public boolean focus() {
		if(pwfOldPassword.getPassword().equals("")) {
			JOptionPane.showMessageDialog(null,"Vui lòng nhập mật khẩu cũ");
			pwfOldPassword.requestFocus();
			return true;
		}
		String newPassword = String.valueOf(pwfNewPassword.getPassword());
		String reEnterPassword = String.valueOf(pwfReEnterPassword.getPassword());
		if(newPassword.equals("")) {
			JOptionPane.showMessageDialog(null,"Vui lòng nhập mật khẩu mới");
			pwfNewPassword.requestFocus();
			return true;
		}
		if(reEnterPassword.equals("")) {
			JOptionPane.showMessageDialog(null,"Vui lòng nhập nhập lại mật khẩu mới");
			pwfReEnterPassword.requestFocus();
			return true;
		}
		if(!newPassword.equals(reEnterPassword)) {
			JOptionPane.showMessageDialog(null,"Mật khẩu cũ và mật khẩu mới không giống! Vui lòng nhập lại");
			clear();
			pwfOldPassword.requestFocus();
			return true;
		}
		return false;
	}
	public void clear() {
		pwfOldPassword.setText("");
		pwfNewPassword.setText("");;
		pwfReEnterPassword.setText("");
	}
}
