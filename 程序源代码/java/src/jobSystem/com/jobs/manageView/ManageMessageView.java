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

public class ManageMessageView extends JDialog {
	Manage manage = new Manage ();           //��¼��½�Ĺ���Ա����
	
	public ManageMessageView() {}
	public ManageMessageView(ManageView manageView,Manage manage) {
		super(manageView,"�˲��г�����ϵͳ",true);
		this.manage = manage;
		this.createWindow();
	}
	
	Container container=getContentPane();   //����һ������
	JLabel title=new JLabel("������Ϣ");     //��ǩ�ı�
	JLabel manageIdJLabel = new JLabel("��      �ţ�");
	JLabel manageNameJLabel = new JLabel("��      ����");
	JLabel manageIdJTextField = null;
	JTextField manageNameJTextField = new JTextField("",30);	
	
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
		
		manageIdJLabel.setBounds(80,80,60,30);
		manageIdJTextField = new JLabel(manage.getManageId());
		manageIdJTextField.setBounds(150,80,150,30);
		
		manageNameJLabel.setBounds(80,150,60,30);
		manageNameJTextField.setBounds(150,150,150,30);
		manageNameJTextField.setText(manage.getManageName());
		changeBtn.setBounds(70,250,80,30);
		cancelBtn.setBounds(230,250,80,30);
		
		container.add(title);
		container.add(manageIdJLabel);
		container.add(manageIdJTextField);
		container.add(manageNameJLabel);
		container.add(manageNameJTextField);			
	
		//�޸�
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(manageNameJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(ManageMessageView.this, "��Ϣ����Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					manage.setManageName(manageNameJTextField.getText());
					ManageDao manageDao = new ManageDao();
					if(manageDao.changeManage(manage)) {
						ManageMessageView.this.dispose();
						JOptionPane.showMessageDialog(ManageMessageView.this, "�޸ĳɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(ManageMessageView.this, "�޸�ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		//����
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				manageNameJTextField.setText("");
			}
		});		
	}
	
}
