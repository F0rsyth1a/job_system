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

public class CompanyMessageView extends JDialog {
	Company company = new Company();   //��¼���ݹ����ĵ�½�Ĺ�˾����
	
	public CompanyMessageView () {}
	public CompanyMessageView (JFrame companyView,Company company) {
		super(companyView,"�˲��г�����ϵͳ",true);
		this.company = company;
		this.createWindow();
	}
	
	Container container=getContentPane();   //����һ������
	JLabel title=new JLabel("������Ϣ");     //��ǩ�ı�
	JLabel companyIdJLabel = new JLabel("��      �ţ�");
	JLabel companyNameJLabel = new JLabel("��      ����");
	JLabel companyIdJTextField = null;
	JTextField companyNameJTextField = new JTextField("",30);	
	
	JButton changeBtn = new JButton("�޸�");
	JButton cancelBtn = new JButton("����");	
	
	public void createWindow() {
		this.setTitle("������Ϣ");
		this.setSize(400,450);
		this.setLocationRelativeTo(null);   //ʹ������ʾ����Ļ����
		this.setResizable(false);           //���ô��ڵĴ�СΪ���ɱ�
		this.getContentPane().setLayout(null);	
		
		title.setFont(new Font("����",Font.PLAIN,25));
		title.setBounds(140, 20, 300, 25);
		
		companyIdJLabel.setBounds(80,80,60,30);
		companyIdJTextField = new JLabel(company.getCompanyId());
		companyIdJTextField.setBounds(150,80,150,30);
		
		companyNameJLabel.setBounds(80,150,60,30);
		companyNameJTextField.setBounds(150,150,150,30);
		companyNameJTextField.setText(company.getCompanyName());
		changeBtn.setBounds(70,250,80,30);
		cancelBtn.setBounds(230,250,80,30);
		
		container.add(title);
		container.add(companyIdJLabel);
		container.add(companyIdJTextField);
		container.add(companyNameJLabel);
		container.add(companyNameJTextField);	
		
		//�޸�
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(companyNameJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(CompanyMessageView.this, "��Ϣ����Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					company.setCompanyName(companyNameJTextField.getText());
					CompanyDao companyDao = new CompanyDao();
					if(companyDao.changeCompanyMessage(company)) {
						CompanyMessageView.this.dispose();
						JOptionPane.showMessageDialog(CompanyMessageView.this, "�޸ĳɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(CompanyMessageView.this, "�޸�ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});		
		
		//����
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				companyNameJTextField.setText("");
			}
		});
		
	}

}
