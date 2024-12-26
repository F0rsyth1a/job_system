package src.jobSystem.com.jobs.companyView;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.util.Company;
import src.jobSystem.com.jobs.util.Position;

public class CompanyAddMessageView extends JDialog {
	Company company = new Company();
	src.jobSystem.com.jobs.companyView.CompanyView companyView = null;   //������
	src.jobSystem.com.jobs.companyView.PositionView positionView = null; //�鿴������Ƹ��Ϣ������
	
	public CompanyAddMessageView() {}
	//���췽����ͨ�������淢����Ƹ��Ϣ����������������ˢ�±��
	public CompanyAddMessageView(src.jobSystem.com.jobs.companyView.CompanyView companyView, Company company) {
		super(companyView,"�˲��г�����ϵͳ",true);
		this.company = company;
		this.companyView = companyView;
		this.createWindow();
	}
	//���췽����ͨ���鿴������Ƹ��Ϣ�����淢����Ƹ��Ϣ�����ݲ鿴������Ƹ��Ϣ����������ˢ�±��
	public CompanyAddMessageView(src.jobSystem.com.jobs.companyView.PositionView positionView, Company company) {
		super(positionView,"�˲��г�����ϵͳ",true);
		this.company = company;
		this.positionView = positionView;
		this.createWindow();
	}	

	Container container=getContentPane();   //����һ������
	JLabel title=new JLabel("������Ƹ��Ϣ");      //��ǩ�ı�
	JLabel companyNameJLabel = new JLabel("��˾���ƣ�");
	JLabel jobNameJLabel = new JLabel("ְλ���ƣ�");
	JLabel personJLabel = new JLabel("��ϵ�ˣ�");
	JLabel phoneJLabel = new JLabel("�ֻ��ţ�");
	JLabel countJLabel = new JLabel("��Ƹ������");
	JLabel learnJLabel = new JLabel("ѧ      ����");
	JLabel requestJLabel = new JLabel("ְλҪ��");
	JLabel moneyJLabel = new JLabel("н      �ʣ�");
	JLabel AddressJLabel = new JLabel("��˾��ַ��");
	
	JTextField companyNameJTextField = new JTextField("",30);
	JTextField jobNameJTextField = new JTextField("",30);
	JTextField personJTextField = new JTextField("",30);
	JTextField phoneJTextField = new JTextField("",30);
	JTextField countJTextField = new JTextField("",30);
	JTextField learnJTextField = new JTextField("",30);
	JTextField requestJTextField = new JTextField("",30);
	JTextField moneyJTextField = new JTextField("",30);
	JTextField AddressJTextField = new JTextField("",30);

	JButton addBtn = new JButton("���");
	JButton cancelBtn = new JButton("����");	
	
	public void createWindow() {
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(500,650);
		this.setLocationRelativeTo(null);  //ʹ������ʾ����Ļ����
		this.setResizable(false);          //���ô��ڵĴ�СΪ���ɱ�
		this.getContentPane().setLayout(null);		

		title.setFont(new Font("����",Font.PLAIN,25));
		title.setBounds(140, 20, 300, 25);
		
		companyNameJLabel.setBounds(80,70,70,30);
		companyNameJTextField.setBounds(150,70,200,30);
		jobNameJLabel.setBounds(80,120,70,30);
		jobNameJTextField.setBounds(150,120,200,30);
		personJLabel.setBounds(80,170,60,30);
		personJTextField.setBounds(150,170,200,30);
		phoneJLabel.setBounds(75,220,100,30);
		phoneJTextField.setBounds(150,220,200,30);
		countJLabel.setBounds(80,270,70,30);
		countJTextField.setBounds(150,270,200,30);
		learnJLabel.setBounds(80,320,60,30);
		learnJTextField.setBounds(150,320,200,30);
		requestJLabel.setBounds(80,370,70,30);
		requestJTextField.setBounds(150,370,200,30);
		moneyJLabel.setBounds(80,420,60,30);
		moneyJTextField.setBounds(150,420,200,30);
		AddressJLabel.setBounds(80,470,70,30);
		AddressJTextField.setBounds(150,470,200,30);
		addBtn.setBounds(90,540,80,30);
		cancelBtn.setBounds(250,540,80,30);
		
		container.add(title);
		container.add(companyNameJLabel);
		container.add(companyNameJTextField);
		container.add(jobNameJLabel);
		container.add(jobNameJTextField);
		container.add(personJLabel);
		container.add(personJTextField);
		container.add(phoneJLabel);
		container.add(phoneJTextField);
		container.add(countJLabel);
		container.add(countJTextField);
		container.add(learnJLabel);
		container.add(learnJTextField);
		container.add(requestJLabel);
		container.add(requestJTextField);
		container.add(moneyJLabel);
		container.add(moneyJTextField);	
		container.add(AddressJLabel);
		container.add(AddressJTextField);	

		container.add(addBtn);
		addBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(phoneJTextField.getText().length() == 11) {
					if(companyNameJTextField.getText().equals("")||jobNameJTextField.getText().equals("")||personJTextField.getText().equals("")||phoneJTextField.getText().equals("")||countJTextField.getText().equals("")
							||learnJTextField.getText().equals("")||requestJTextField.getText().equals("")||moneyJTextField.getText().equals("")||AddressJTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(CompanyAddMessageView.this, "��Ϣ����Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
					}else {
						Position position = new Position();
						position.setCompanyId(company.getCompanyId());
						position.setCompanyName(companyNameJTextField.getText());
						position.setCompanyPosition(jobNameJTextField.getText());
						position.setCompanyPerson(personJTextField.getText());
						position.setCompanyPhone(phoneJTextField.getText());
						position.setCompanyCount(countJTextField.getText());
						position.setCompanyLearn(learnJTextField.getText());
						position.setCompanyRequest(requestJTextField.getText());
						position.setCompanyMoney(moneyJTextField.getText());
						position.setCompanyAddress(AddressJTextField.getText());
						
						CompanyDao companyDao = new CompanyDao();
						if(companyDao.addPositionMessage(position)) {
							CompanyAddMessageView.this.dispose();
							if(companyView != null) {
								companyView.refreshTableModel();   //ˢ�±��
							}
							if(positionView != null) {
								positionView.refreshTableModel();   //ˢ�±��
							}
							JOptionPane.showMessageDialog(CompanyAddMessageView.this, "��ӳɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(CompanyAddMessageView.this, "���ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(CompanyAddMessageView.this, "�ֻ�����������","��ʾ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});			
		
		//����
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				companyNameJTextField.setText("");
				jobNameJTextField.setText("");
				personJTextField.setText("");
				phoneJTextField.setText("");
				countJTextField.setText("");
				learnJTextField.setText("");
				requestJTextField.setText("");
				moneyJTextField.setText("");
				AddressJTextField.setText("");
			}
		});		
		
	}

}
