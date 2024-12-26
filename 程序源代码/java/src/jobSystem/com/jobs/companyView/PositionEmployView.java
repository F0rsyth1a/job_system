package src.jobSystem.com.jobs.companyView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import src.jobSystem.com.jobs.companyDao.PositionEmployTableModel;
import src.jobSystem.com.jobs.util.Company;

public class PositionEmployView extends JFrame {
	Company company = new Company();
	CompanyView companyView;
	
	//�������
	JPanel northPanel = null;
	JLabel title= null;
	
	//�в����
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
		
	//�ϲ����
	JPanel southPanel = null;
	JButton showBtn = null;
	JButton returnBtn = null;
	
	public PositionEmployView() {}
	public PositionEmployView(Company company,CompanyView companyView) {
		this.company = company;
		this.companyView = companyView;
	}

	public void createWindow() {
		//���߲���
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("ȫ����Ƹ��Ϣ��¼�����");
		title.setFont(new Font("����",Font.PLAIN,25));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);
		
		//�в�����
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new PositionEmployTableModel(company.getCompanyId()));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);	
		
		//�ϲ�����
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		showBtn = new JButton("�鿴����");
		showBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionEmployView.this, "����ѡ��Ҫ�鿴����Ƹ��Ϣ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int id = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
					new StaffEmployView(PositionEmployView.this,company.getCompanyId(),id).setVisible(true);
				}
			}
		});	
		
		returnBtn = new JButton("��      ��");
		returnBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PositionEmployView.this.dispose();
				companyView.refreshTableModel();
				companyView.setVisible(true);
			}
		});	
					
		southPanel.add(showBtn);
		southPanel.add(returnBtn);
		this.add(southPanel,BorderLayout.SOUTH);
		
		//������
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(1200, 560);
		this.setLocationRelativeTo(null); //ʹ������ʾ����Ļ����
		this.setResizable(false);         //���ô��ڵĴ�СΪ���ɱ�
		this.setVisible(true);            //�����������ӻ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	//ˢ�±��
	public void refreshTableModel() {
		table.setModel(new PositionEmployTableModel(company.getCompanyId()));
	}		

}

