package src.jobSystem.com.jobs.staffView;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.staffDao.StaffDao;
import src.jobSystem.com.jobs.util.Staff;

public class StaffChangePwdView extends JDialog {
	Staff staff = new Staff ();
	
	public StaffChangePwdView() {}
	public StaffChangePwdView(StaffView staffView,Staff staff) {
		super(staffView,"�˲��г�����ϵͳ",true);
		this.staff = staff;
		this.createWindow();
	}
	
	Container container=getContentPane();   //����һ������	
	JLabel title=new JLabel("�޸�����");	
	JLabel staffIdJLabel = new JLabel("��      �ţ�");
	JLabel staffNameJLabel = new JLabel("��      ����");
	JLabel staffPwdJLabel = new JLabel("�����룺");
	JLabel staffNewPwdJLabel = new JLabel("�����룺");
	JLabel staffRepwdJLabel = new JLabel("�ٴ����룺");
	
	JLabel staffIdJTextField = null;
	JLabel staffNameJTextField = null;
	JTextField staffPwdJTextField = new JTextField("",30);
	JTextField staffNewPwdJTextField = new JTextField("",30);
	JTextField staffRepwdJTextField = new JTextField("",30);
	
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
		
		staffIdJLabel.setBounds(80,50,60,30);
		staffIdJTextField = new JLabel(staff.getStaffId());
		staffIdJTextField.setBounds(150,50,150,30);
		
		staffNameJLabel.setBounds(80,100,60,30);
		staffNameJTextField = new JLabel(staff.getStaffName());
		staffNameJTextField.setBounds(150,100,150,30);
			
		staffPwdJLabel.setBounds(75,150,100,30);
		staffPwdJTextField.setBounds(150,150,150,30);	
		staffNewPwdJLabel.setBounds(80,200,60,30);
		staffNewPwdJTextField.setBounds(150,200,150,30);	
		staffRepwdJLabel.setBounds(75,250,80,30);
		staffRepwdJTextField.setBounds(150,250,150,30);		
		changeBtn.setBounds(70,330,80,30);
		cancelBtn.setBounds(230,330,80,30);
				
		container.add(title);
		container.add(staffIdJLabel);
		container.add(staffIdJTextField);
		container.add(staffNameJLabel);
		container.add(staffNameJTextField);
		container.add(staffPwdJLabel);
		container.add(staffPwdJTextField);
		container.add(staffNewPwdJLabel);
		container.add(staffNewPwdJTextField);
		container.add(staffRepwdJLabel);
		container.add(staffRepwdJTextField);		

		//�޸�
		container.add(changeBtn);		
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(staffPwdJTextField.getText().equals("")||staffNewPwdJTextField.getText().equals("")||staffRepwdJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(StaffChangePwdView.this, "��Ϣ����Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					if(staffPwdJTextField.getText().equals(staff.getStaffPwd()) && staffNewPwdJTextField.getText().equals(staffRepwdJTextField.getText())) {
						StaffDao staffDao = new StaffDao();
						if(staffDao.changeStaffPwd(staff.getStaffId(), staffPwdJTextField.getText(), staffNewPwdJTextField.getText())) {
							StaffChangePwdView.this.dispose();
							staff.setStaffPwd(staffNewPwdJTextField.getText());
							JOptionPane.showMessageDialog(StaffChangePwdView.this, "�޸ĳɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);	
						}else {
							JOptionPane.showMessageDialog(StaffChangePwdView.this, "�޸�ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(StaffChangePwdView.this, "������������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});	
		
		//����
		container.add(cancelBtn);		
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				staffPwdJTextField.setText("");
				staffNewPwdJTextField.setText("");
				staffRepwdJTextField.setText("");
			}
		});		
		
	}

}
