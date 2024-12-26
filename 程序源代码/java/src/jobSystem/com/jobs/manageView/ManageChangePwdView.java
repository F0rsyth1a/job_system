package src.jobSystem.com.jobs.manageView;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.manageDao.ManageDao;
import src.jobSystem.com.jobs.util.Manage;

public class ManageChangePwdView extends JDialog {
	Manage manage = new Manage ();
	
	public ManageChangePwdView() {}
	public ManageChangePwdView(ManageView manageView,Manage manage) {
		super(manageView,"�˲��г�����ϵͳ",true);
		this.manage = manage;
		this.createWindow();
	}
	
	Container container=getContentPane();   //����һ������
	JLabel title=new JLabel("�޸�����");
	JLabel manageIdJLabel = new JLabel("��      �ţ�");
	JLabel manageNameJLabel = new JLabel("��      ����");
	JLabel managePwdJLabel = new JLabel("�����룺");
	JLabel manageNewPwdJLabel = new JLabel("�����룺");
	JLabel manageRepwdJLabel = new JLabel("�ٴ����룺");
	
	JLabel manageIdJTextField = null;
	JLabel manageNameJTextField = null;
	JTextField managePwdJTextField = new JTextField("",30);
	JTextField manageNewPwdJTextField = new JTextField("",30);
	JTextField manageRepwdJTextField = new JTextField("",30);
	
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
		manageIdJLabel.setBounds(80,50,60,30);
		manageIdJTextField = new JLabel(manage.getManageId());
		manageIdJTextField.setBounds(150,50,150,30);
		manageNameJLabel.setBounds(80,100,60,30);
		manageNameJTextField = new JLabel(manage.getManageName());
		manageNameJTextField.setBounds(150,100,150,30);
		managePwdJLabel.setBounds(75,150,100,30);
		managePwdJTextField.setBounds(150,150,150,30);	
		manageNewPwdJLabel.setBounds(80,200,60,30);
		manageNewPwdJTextField.setBounds(150,200,150,30);	
		manageRepwdJLabel.setBounds(75,250,80,30);
		manageRepwdJTextField.setBounds(150,250,150,30);		
		changeBtn.setBounds(70,330,80,30);
		cancelBtn.setBounds(230,330,80,30);
				
		container.add(title);
		container.add(manageIdJLabel);
		container.add(manageIdJTextField);
		container.add(manageNameJLabel);
		container.add(manageNameJTextField);
		container.add(managePwdJLabel);
		container.add(managePwdJTextField);
		container.add(manageNewPwdJLabel);
		container.add(manageNewPwdJTextField);
		container.add(manageRepwdJLabel);
		container.add(manageRepwdJTextField);
		
		//�޸�
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(managePwdJTextField.getText().equals("")||manageNewPwdJTextField.getText().equals("")||manageRepwdJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(ManageChangePwdView.this, "��Ϣ����Ϊ�գ�","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					if(managePwdJTextField.getText().equals(manage.getManagePwd()) && manageNewPwdJTextField.getText().equals(manageRepwdJTextField.getText())) {
						ManageDao manageDao = new ManageDao();
						if(manageDao.changeManagePwd(manage.getManageId(), managePwdJTextField.getText(), manageNewPwdJTextField.getText())) {
							ManageChangePwdView.this.dispose();
							manage.setManagePwd(manageNewPwdJTextField.getText());
							JOptionPane.showMessageDialog(ManageChangePwdView.this, "�޸ĳɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);	
						}else {
							JOptionPane.showMessageDialog(ManageChangePwdView.this, "�޸�ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(ManageChangePwdView.this, "������������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});		
		
		//����
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				managePwdJTextField.setText("");
				manageNewPwdJTextField.setText("");
				manageRepwdJTextField.setText("");
			}
		});
		
	}

}
