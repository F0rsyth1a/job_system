package src.jobSystem.com.jobs.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.companyView.CompanyView;
import src.jobSystem.com.jobs.manageDao.ManageDao;
import src.jobSystem.com.jobs.manageView.ManageView;
import src.jobSystem.com.jobs.staffDao.StaffDao;
import src.jobSystem.com.jobs.staffView.StaffView;
import src.jobSystem.com.jobs.util.Company;
import src.jobSystem.com.jobs.util.Manage;
import src.jobSystem.com.jobs.util.Staff;

public class LandingView implements ActionListener {
	
	//-------------��½����---------------------------------------------
	JFrame landingView=new JFrame();              //��½����
	JLabel title=new JLabel("�˲��г�����ϵͳ");   //��ǩ�ı�
	JLabel photoJLabel = new JLabel(new ImageIcon("D:\\ѧУ\\�γ�\\java�γ����\\java���ݿ����\\����Դ����\\java\\src\\img\\photo.png"));
	JLabel IdJLabel=new JLabel("�ʺţ�");
	JLabel pwdJLabel=new JLabel("���룺");
	JTextField IdJTextField=new JTextField("",30);               //�ֻ��ſ�
	JPasswordField pwdJPasswordField=new JPasswordField("",30);  //�����
	ButtonGroup group = new ButtonGroup();
	JRadioButton manage = new JRadioButton("����Ա��½");          //��˾��½��ѡ��ť
	JRadioButton company = new JRadioButton("��˾��½");          //��˾��½��ѡ��ť
	JRadioButton staff = new JRadioButton("���˵�½",true);       //���˵�½��ѡ��ť��Ĭ��ѡ��
	JButton LoginJButton=new JButton("ע��");
	JButton LangJButton=new JButton("��½");
		
	public void LandingWindow() {
		landingView.setTitle("�˲��г�����ϵͳ");
		landingView.setSize(500,600);
		landingView.setLocationRelativeTo(null);      //ʹ������ʾ����Ļ����
		landingView.setResizable(false);              //���ô��ڵĴ�СΪ���ɱ�
		landingView.getContentPane().setLayout(null); //�ղ���
		landingView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //���ô��ڵĹرշ�ʽ
				
		title.setFont(new Font("����",Font.PLAIN,35));
		title.setBounds(100, 40, 300, 35);
		photoJLabel.setBounds(60, 290, 385, 255);
		IdJLabel.setBounds(130, 100, 50, 30);
		IdJTextField.setBounds(180, 100, 150, 30);
		pwdJLabel.setBounds(130, 150, 50, 30);
		pwdJPasswordField.setBounds(180, 150, 150, 30);
		manage.setBounds(90, 190, 90, 30);
		company.setBounds(200, 190, 90, 30);
		staff.setBounds(300, 190, 90, 30);
		LoginJButton.setBounds(150, 230, 80, 30);
		LangJButton.setBounds(250, 230, 80, 30);
		group.add(manage);
		group.add(company);
		group.add(staff);			
		landingView.add(title);
		landingView.add(photoJLabel);
		landingView.add(IdJLabel);
		landingView.add(pwdJLabel);
		landingView.add(IdJTextField);
		landingView.add(pwdJPasswordField);
		landingView.add(manage);
		landingView.add(company);
		landingView.add(staff);
		landingView.add(LoginJButton);
		landingView.add(LangJButton);
		
		LoginJButton.addActionListener(this);
		LangJButton.addActionListener(this);
		landingView.setVisible(true);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		if(buttonName.equals("��½")) {
			String id = IdJTextField.getText();
			String pwd = new String(pwdJPasswordField.getPassword());
			if(manage.isSelected()) {
				if(id != null && !id.equals("") && pwd != null && !pwd.equals("")) {
					ManageDao manageDao = new ManageDao();
					Manage manage = manageDao.manageLanding(id, pwd);
					if(manage != null) {
						if(manage.getManageSign() != 1) {
							//��½�ɹ�
							landingView.dispose();
							JOptionPane.showMessageDialog(landingView, "��½�ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
							ManageView manageView = new ManageView(manage);   //����Ա��½
							manageView.ManageWindow();
							
						}else {
							JOptionPane.showMessageDialog(landingView, "���ʺ�����Υ�棬�ѱ�ע����","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(landingView, "�ʺŻ��������","��ʾ",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(landingView, "�������˺ź����룡","��ʾ",JOptionPane.WARNING_MESSAGE);
				}	
			}else if(company.isSelected()) {
				if(id != null && !id.equals("") && pwd != null && !pwd.equals("")) {
					CompanyDao manageDao = new CompanyDao();
					Company company = manageDao.companyLanding(id, pwd);
					if(company != null) {
						if(company.getCompanySign() != 1) {
							//��½�ɹ�
							landingView.dispose();
							JOptionPane.showMessageDialog(landingView, "��½�ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
							CompanyView companyView = new CompanyView(company);   //��˾��½
							companyView.CompanyWindow();
							
						}else {
							JOptionPane.showMessageDialog(landingView, "���ʺ�����Υ�棬�ѱ�ע����","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(landingView, "�ʺŻ��������","��ʾ",JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(landingView, "�������˺ź����룡","��ʾ",JOptionPane.WARNING_MESSAGE);
				}
			}else if(staff.isSelected()) {
				if(id != null && !id.equals("") && pwd != null && !pwd.equals("")) {
					StaffDao staffDao = new StaffDao();
					Staff staff = staffDao.staffLanding(id, pwd);
					if(staff != null) {
						if(staff.getStaffSign() != 1) {
							//��½�ɹ�
							landingView.dispose();
							JOptionPane.showMessageDialog(landingView, "��½�ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
							StaffView staffView = new StaffView(staff);   //ְԱ��½
							staffView.StaffWindow();

						}else {
							JOptionPane.showMessageDialog(landingView, "���ʺ�����Υ�棬�ѱ�ע����","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(landingView, "�ʺŻ��������","��ʾ",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(landingView, "�������˺ź����룡","��ʾ",JOptionPane.WARNING_MESSAGE);
				}
			}
			
		}else if(buttonName.equals("ע��")){
			//ʹ�Ի�����ɼ���ʵ���˵��û������ð�ť�󽫵���ע��Ի���Ĺ���
			new src.jobSystem.com.jobs.main.LoginView(landingView).setVisible(true);

		}
	}

}

