package src.jobSystem.com.jobs.main;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.staffDao.StaffDao;

public class LoginView extends JDialog implements ActionListener {
	
	public LoginView() {}
	public LoginView(JFrame landingView) {
		super(landingView,"�˲��г�����ϵͳ",true);
		this.LoginWindow();
	}
	
	//-------------ע�����---------------------------------------------
	Container container=getContentPane();  //����һ������
	JLabel title=new JLabel("ע�����");    //��ǩ�ı�
	JLabel NameLabel=new JLabel("��    ����");
	JLabel IdJLabel=new JLabel("�ֻ��ţ�");
	JLabel pwdJLabel=new JLabel("��    �룺");
	JTextField NameJTextField=new JTextField("",30);             //������
	JTextField IdJTextField=new JTextField("",30);               //�ֻ��ſ�
	JPasswordField pwdJPasswordField=new JPasswordField("",30);  //�����
	ButtonGroup group = new ButtonGroup();
	JRadioButton company = new JRadioButton("��˾ע��");
	JRadioButton staff = new JRadioButton("����ע��",true);
	JButton LoginJButton=new JButton("ȷ��");
		
	public void LoginWindow() {
		
		this.setTitle("ע�����");
		this.setSize(430,340);
		this.setLocationRelativeTo(null);  //ʹ������ʾ����Ļ����
		this.setResizable(false);          //���ô��ڵĴ�СΪ���ɱ�
		this.getContentPane().setLayout(null);
		
		title.setFont(new Font("�����п�",Font.PLAIN,40));
		title.setBounds(120, 30, 300, 40);
		NameLabel.setBounds(85, 90, 60, 30);
		IdJLabel.setBounds(85, 130, 60, 30);
		pwdJLabel.setBounds(85, 170, 60, 30);
		NameJTextField.setBounds(140, 90, 150, 30);
		IdJTextField.setBounds(140, 130, 150, 30);
		pwdJPasswordField.setBounds(140, 170, 150, 30);
		company.setBounds(120, 200, 90, 30);
		staff.setBounds(220, 200, 90, 30);
		LoginJButton.setBounds(160, 240, 80, 30);
		group.add(company);
		group.add(staff);
		LoginJButton.addActionListener(this);
		container.add(title);
		container.add(NameLabel);
		container.add(IdJLabel);
		container.add(pwdJLabel);
		container.add(NameJTextField);
		container.add(IdJTextField);
		container.add(pwdJPasswordField);
		container.add(company);
		container.add(staff);
		container.add(LoginJButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CompanyDao companyDao = new CompanyDao();
		StaffDao staffDao = new StaffDao();
		String Name = NameJTextField.getText();
		String Id = IdJTextField.getText();
		String Pwd = new String(pwdJPasswordField.getPassword());	
		if(company.isSelected()) {
			if(Name != null && !Name.equals("") && Id != null && !Id.equals("") && Pwd != null && !Pwd.equals("")) {
				if(Id.length() == 11) {
					if(companyDao.selectID(Id) || staffDao.selectID(Id)) {
						JOptionPane.showMessageDialog(LoginView.this, "���˻��Ѵ���,�����ظ�ע�ᣡ","��ʾ",JOptionPane.WARNING_MESSAGE);
					}else {
						if(companyDao.companyLogin(Name,Id, Pwd,2)) {
							JOptionPane.showMessageDialog(LoginView.this, "ע��ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(LoginView.this, "ע��ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(LoginView.this, "�ֻ�����������","��ʾ",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(LoginView.this, "�������������˺ź����룡","��ʾ",JOptionPane.WARNING_MESSAGE);
			}
		}else if(staff.isSelected()) {
			if(Name != null && !Name.equals("") && Id != null && !Id.equals("") && Pwd != null && !Pwd.equals("")) {
				if(Id.length() == 11) {	
					if(companyDao.selectID(Id) || staffDao.selectID(Id)) {
						JOptionPane.showMessageDialog(LoginView.this, "���˻��Ѵ���,�����ظ�ע�ᣡ","��ʾ",JOptionPane.WARNING_MESSAGE);
					}else {
						if(staffDao.staffLogin(Name,Id, Pwd)) {
							JOptionPane.showMessageDialog(LoginView.this, "ע��ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(LoginView.this, "ע��ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(LoginView.this, "�ֻ�����������","��ʾ",JOptionPane.ERROR_MESSAGE);
				}					
			}else {
				JOptionPane.showMessageDialog(LoginView.this, "�������������˺ź����룡","��ʾ",JOptionPane.WARNING_MESSAGE);
			}
		}		
	}

}
