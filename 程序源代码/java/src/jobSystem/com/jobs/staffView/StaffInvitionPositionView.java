package src.jobSystem.com.jobs.staffView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import src.jobSystem.com.jobs.companyDao.CompanyTableModel;

public class StaffInvitionPositionView extends JDialog {
	int staffNumber;
	
	//�������
	JPanel northPanel = null;
	JLabel title= null;
				
	//�в����
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
	
	public StaffInvitionPositionView() {}
	public StaffInvitionPositionView(StaffView staffView,int staffNumber) {
		super(staffView,"�˲��г�����ϵͳ",true);
		this.staffNumber = staffNumber;
		this.createWindow();
	}		
	
	public void createWindow() {
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(1200, 560);
		this.setLocationRelativeTo(null);  //ʹ������ʾ����Ļ����
		this.setResizable(false);          //���ô��ڵĴ�СΪ���ɱ�		

		//���߲���
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("�յ��������Ƹ��Ϣ");
		title.setFont(new Font("����",Font.PLAIN,20));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);		

		//�в�����
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new CompanyTableModel(staffNumber,1));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);		
		
	}	
	
}
