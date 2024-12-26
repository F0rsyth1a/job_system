package src.jobSystem.com.jobs.staffView;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.util.Position;

public class StaffEmployView extends JDialog {
	Position position = null;
	
	
	public StaffEmployView() {}
	public StaffEmployView(StaffView staffView,Position position) {
		super(staffView,"�˲��г�����ϵͳ",true);
		this.position = position;
		this.createWindow();
	}
	
	Container container=getContentPane();        //����һ������
	JLabel title=new JLabel("¼ȡ����Ƹ��Ϣ");      //��ǩ�ı�
	JLabel companyNameJLabel = new JLabel("��˾���ƣ�");
	JLabel jobNameJLabel = new JLabel("ְλ���ƣ�");
	JLabel personJLabel = new JLabel("��ϵ�ˣ�");
	JLabel phoneJLabel = new JLabel("�ֻ��ţ�");
	JLabel countJLabel = new JLabel("��Ƹ������");
	JLabel learnJLabel = new JLabel("ѧ      ����");
	JLabel requestJLabel = new JLabel("ְλҪ��");
	JLabel moneyJLabel = new JLabel("н      �ʣ�");
	JLabel AddressJLabel = new JLabel("��˾��ַ��");
	
	JLabel companyName = null;
	JLabel jobName = null;
	JLabel person = null;
	JLabel phone = null;
	JLabel count = null;
	JLabel learn = null;
	JLabel request = null;
	JLabel money = null;
	JLabel address = null;
	
	
	public void createWindow() {
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(500,650);
		this.setLocationRelativeTo(null);  //ʹ������ʾ����Ļ����
		this.setResizable(false);          //���ô��ڵĴ�СΪ���ɱ�
		this.getContentPane().setLayout(null);				

		title.setFont(new Font("����",Font.PLAIN,25));
		title.setBounds(140, 20, 300, 25);	
		
		companyNameJLabel.setBounds(80,70,70,30);
		companyName = new JLabel(position.getCompanyName());
		companyName.setBounds(150,70,200,30);
				
		jobNameJLabel.setBounds(80,120,70,30);
		jobName = new JLabel(position.getCompanyPosition());
		jobName.setBounds(150,120,200,30);
		
		personJLabel.setBounds(80,170,60,30);
		person = new JLabel(position.getCompanyPerson());
		person.setBounds(150,170,200,30);
		
		phoneJLabel.setBounds(75,220,100,30);
		phone = new JLabel(position.getCompanyPhone());
		phone.setBounds(150,220,200,30);
		
		countJLabel.setBounds(80,270,70,30);
		count = new JLabel(position.getCompanyCount());
		count.setBounds(150,270,200,30);
		
		learnJLabel.setBounds(80,320,60,30);
		learn = new JLabel(position.getCompanyLearn());
		learn.setBounds(150,320,200,30);
		
		requestJLabel.setBounds(80,370,70,30);
		request = new JLabel(position.getCompanyRequest());
		request.setBounds(150,370,200,30);
		
		moneyJLabel.setBounds(80,420,60,30);
		money = new JLabel(position.getCompanyMoney());
		money.setBounds(150,420,200,30);
		
		AddressJLabel.setBounds(80,470,70,30);
		address = new JLabel(position.getCompanyAddress());
		address.setBounds(150,470,200,30);
		
		container.add(title);
		container.add(companyNameJLabel);
		container.add(companyName);
		container.add(jobNameJLabel);
		container.add(jobName);
		container.add(personJLabel);
		container.add(person);
		container.add(phoneJLabel);
		container.add(phone);
		container.add(countJLabel);
		container.add(count);
		container.add(learnJLabel);
		container.add(learn);
		container.add(requestJLabel);
		container.add(request);
		container.add(moneyJLabel);
		container.add(money);	
		container.add(AddressJLabel);
		container.add(address);
		
	}

}
