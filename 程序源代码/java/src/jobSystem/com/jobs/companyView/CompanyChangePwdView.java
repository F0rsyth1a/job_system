package src.jobSystem.com.jobs.companyView;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.util.Company;

public class CompanyChangePwdView extends JDialog {
	Company company = new Company();
	
	public CompanyChangePwdView() {}
	public CompanyChangePwdView(JFrame companyView,Company company) {
		super(companyView,"�˲��г�����ϵͳ",true);
		this.company = company;
		this.createWindow();
	}

	Container container=getContentPane();   //����һ������
	JLabel title=new JLabel("�޸�����");
	JLabel companyIdJLabel = new JLabel("��      �ţ�");
	JLabel companyNameJLabel = new JLabel("��      ����");
	JLabel companyPwdJLabel = new JLabel("�����룺");
	JLabel companyNewPwdJLabel = new JLabel("�����룺");
	JLabel companyRepwdJLabel = new JLabel("�ٴ����룺");
	
	JLabel companyIdJTextField = null;
	JLabel companyNameJTextField = null;
	JTextField companyPwdJTextField = new JTextField("",30);
	JTextField companyNewPwdJTextField = new JTextField("",30);
	JTextField companyRepwdJTextField = new JTextField("",30);
	
	JButton changeBtn = new JButton("�޸�");
	JButton cancelBtn = new JButton("����");	
	
	public void createWindow() {
		this.setTitle("�޸ĸ�������");
		this.setSize(400,500);
		this.setLocationRelativeTo(null);  //ʹ������ʾ����Ļ����
		this.setResizable(false);          //���ô��ڵĴ�СΪ���ɱ�
		this.getContentPane().setLayout(null);
		
		title.setFont(new Font("����",Font.PLAIN,25));
		title.setBounds(140, 10, 300, 25);
		companyIdJLabel.setBounds(80,50,60,30);
		companyIdJTextField = new JLabel(company.getCompanyId());
		companyIdJTextField.setBounds(150,50,150,30);
		companyNameJLabel.setBounds(80,100,60,30);
		companyNameJTextField = new JLabel(company.getCompanyName());
		companyNameJTextField.setBounds(150,100,150,30);
		companyPwdJLabel.setBounds(75,150,100,30);
		companyPwdJTextField.setBounds(150,150,150,30);	
		companyNewPwdJLabel.setBounds(80,200,60,30);
		companyNewPwdJTextField.setBounds(150,200,150,30);	
		companyRepwdJLabel.setBounds(75,250,80,30);
		companyRepwdJTextField.setBounds(150,250,150,30);		
		changeBtn.setBounds(70,330,80,30);
		cancelBtn.setBounds(230,330,80,30);
				
		container.add(title);
		container.add(companyIdJLabel);
		container.add(companyIdJTextField);
		container.add(companyNameJLabel);
		container.add(companyNameJTextField);
		container.add(companyPwdJLabel);
		container.add(companyPwdJTextField);
		container.add(companyNewPwdJLabel);
		container.add(companyNewPwdJTextField);
		container.add(companyRepwdJLabel);
		container.add(companyRepwdJTextField);

		//�޸�
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(companyPwdJTextField.getText().equals("")||companyNewPwdJTextField.getText().equals("")||companyRepwdJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(CompanyChangePwdView.this, "��Ϣ����Ϊ�գ�","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					if(companyPwdJTextField.getText().equals(company.getCompanyPwd()) && companyNewPwdJTextField.getText().equals(companyRepwdJTextField.getText())) {
						CompanyDao companyDao = new CompanyDao();
						if(companyDao.changeCompanyPwd(company.getCompanyId(), companyPwdJTextField.getText(), companyNewPwdJTextField.getText())) {
							CompanyChangePwdView.this.dispose();
							company.setCompanyPwd(companyNewPwdJTextField.getText());
							JOptionPane.showMessageDialog(CompanyChangePwdView.this, "�޸ĳɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);	
						}else {
							JOptionPane.showMessageDialog(CompanyChangePwdView.this, "�޸�ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(CompanyChangePwdView.this, "������������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		//����
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				companyPwdJTextField.setText("");
				companyNewPwdJTextField.setText("");
				companyRepwdJTextField.setText("");
			}
		});
	}

}
