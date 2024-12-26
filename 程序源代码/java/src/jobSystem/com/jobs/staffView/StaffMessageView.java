package src.jobSystem.com.jobs.staffView;

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

import src.jobSystem.com.jobs.staffDao.StaffDao;
import src.jobSystem.com.jobs.util.Staff;

public class StaffMessageView extends JDialog {
	Staff staff = new Staff();  //��¼��½��ְԱ����	
	
	public StaffMessageView() {}
	public StaffMessageView(JFrame staffView,Staff staff) {
		super(staffView,"�˲��г�����ϵͳ",true);
		this.staff = staff;
		this.createWindow();
	}
	
	Container container=getContentPane();   //����һ������
	JLabel title=new JLabel("������Ϣ");     //��ǩ�ı�
	JLabel staffIdJLabel = new JLabel("��      �ţ�");
	JLabel staffNameJLabel = new JLabel("��      ����");
	JLabel staffCardJLabel = new JLabel("���֤�ţ�");
	JLabel staffAddressJLabel = new JLabel("��      ַ��");
	JLabel staffPhoneJLabel = new JLabel("�ֻ��ţ�");
	JLabel staffLearnJLabel = new JLabel("ѧ      ����");
	JLabel staffJobJLabel = new JLabel("ְ      �ƣ�");
	
	JLabel staffIdJTextField = null;
	JTextField staffNameJTextField = new JTextField("",30);
	JTextField staffCardJTextField = new JTextField("",30);
	JTextField staffAddressJTextField = new JTextField("",30);
	JTextField staffPhoneJTextField = new JTextField("",30);
	JTextField staffLearnJTextField = new JTextField("",30);
	JTextField staffJobJTextField = new JTextField("",30);

	JButton changeBtn = new JButton("�޸�");
	JButton cancelBtn = new JButton("����");	
	
	public void createWindow() {
		this.setTitle("������Ϣ");
		this.setSize(400,500);
		this.setLocationRelativeTo(null);   //ʹ������ʾ����Ļ����
		this.setResizable(false);           //���ô��ڵĴ�СΪ���ɱ�
		this.getContentPane().setLayout(null);
		
		title.setFont(new Font("����",Font.PLAIN,25));
		title.setBounds(140, 10, 300, 25);
		
		staffIdJLabel.setBounds(80,50,60,30);
		staffIdJTextField = new JLabel(staff.getStaffId());
		staffIdJTextField.setBounds(150,50,150,30);
		
		staffNameJLabel.setBounds(80,100,60,30);
		staffNameJTextField.setBounds(150,100,150,30);
		staffNameJTextField.setText(staff.getStaffName());
		
		staffCardJLabel.setBounds(75,150,100,30);
		staffCardJTextField.setBounds(150,150,150,30);
		staffCardJTextField.setText(staff.getStaffCard());
		
		staffAddressJLabel.setBounds(80,200,60,30);
		staffAddressJTextField.setBounds(150,200,150,30);
		staffAddressJTextField.setText(staff.getStaffAddress());
		
		staffPhoneJLabel.setBounds(80,250,60,30);
		staffPhoneJTextField.setBounds(150,250,150,30);
		staffPhoneJTextField.setText(staff.getStaffPhone());
		staffLearnJLabel.setBounds(80,300,60,30);
		staffLearnJTextField.setBounds(150,300,150,30);
		staffLearnJTextField.setText(staff.getStaffLearn());
		staffJobJLabel.setBounds(80,350,60,30);
		staffJobJTextField.setBounds(150,350,150,30);
		staffJobJTextField.setText(staff.getStaffJob());
		changeBtn.setBounds(70,400,80,30);
		cancelBtn.setBounds(230,400,80,30);
		
		container.add(title);
		container.add(staffIdJLabel);
		container.add(staffNameJLabel);
		container.add(staffCardJLabel);
		container.add(staffAddressJLabel);
		container.add(staffPhoneJLabel);
		container.add(staffLearnJLabel);
		container.add(staffJobJLabel);	
		container.add(staffIdJTextField);
		container.add(staffNameJTextField);
		container.add(staffCardJTextField);
		container.add(staffAddressJTextField);
		container.add(staffPhoneJTextField);
		container.add(staffLearnJTextField);
		container.add(staffJobJTextField);		
		
		//�޸�
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(staffPhoneJTextField.getText().length() == 11) {
					if(staffNameJTextField.getText().equals("")||staffCardJTextField.getText().equals("")||staffAddressJTextField.getText().equals("")
							||staffPhoneJTextField.getText().equals("")||staffLearnJTextField.getText().equals("")||staffJobJTextField.getText().equals("")) {
							JOptionPane.showMessageDialog(StaffMessageView.this, "��Ϣ����Ϊ�գ�","��ʾ",JOptionPane.WARNING_MESSAGE);
					}else {
						staff.setStaffName(staffNameJTextField.getText());
						staff.setStaffCard(staffCardJTextField.getText());
						staff.setStaffAddress(staffAddressJTextField.getText());
						staff.setStaffPhone(staffPhoneJTextField.getText());
						staff.setStaffLearn(staffLearnJTextField.getText());
						staff.setStaffJob(staffJobJTextField.getText());							
						StaffDao staffDao = new StaffDao();
						if(staffDao.changeStaffMessage(staff)) {
							StaffMessageView.this.dispose();
							JOptionPane.showMessageDialog(StaffMessageView.this, "�޸ĳɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(StaffMessageView.this, "�޸�ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(StaffMessageView.this, "�ֻ�����������","��ʾ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//����
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				staffNameJTextField.setText("");
				staffCardJTextField.setText("");
				staffAddressJTextField.setText("");
				staffPhoneJTextField.setText("");
				staffLearnJTextField.setText("");
				staffJobJTextField.setText("");
			}
		});
		
	}
	
}
