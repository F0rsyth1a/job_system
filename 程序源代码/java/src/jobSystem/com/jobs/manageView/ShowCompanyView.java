package src.jobSystem.com.jobs.manageView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import src.jobSystem.com.jobs.manageDao.CompanyIDTableModel;
import src.jobSystem.com.jobs.manageDao.ManageDao;
import src.jobSystem.com.jobs.manageDao.CompanyIDTableModel;

public class ShowCompanyView extends JDialog {
	ManageView manageView = null;
	//�������
	JPanel northPanel = null;
	JLabel title= null;
			
	//�в����
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
	
	//�ϲ����
	JPanel southPanel = null;
	JButton deleteBtn = null;
	
	public ShowCompanyView() {}
	public ShowCompanyView(ManageView manageView) {
		super(manageView,"�˲��г�����ϵͳ",true);
		this.manageView = manageView;
		this.createWindow();
	}
	
	public void createWindow() {
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(900,500);
		this.setLocationRelativeTo(null);  //ʹ������ʾ����Ļ����
		this.setResizable(false);          //���ô��ڵĴ�СΪ���ɱ�		
		
		//���߲���
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("���й�˾��������Ϣ");
		title.setFont(new Font("����",Font.PLAIN,20));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);
		
		//�в�����
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new CompanyIDTableModel(0));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);		
		
		//�ϲ�����
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		deleteBtn = new JButton("ɾ����˾");			
		deleteBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {		
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(ShowCompanyView.this, "����ѡ��Ҫɾ���Ĺ�˾��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(ShowCompanyView.this, "ȷ��Ҫɾ��ѡ�е���Ϣ��ɾ������ɾ���ù�˾������������Ƹ��Ϣ��","��ʾ",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						String companyId = ((String)table.getModel().getValueAt(rowIndex, 2));
						ManageDao manageDao = new ManageDao();
						if(manageDao.deleCompany(companyNumber)) {
							manageDao.deleCompanyPosition(companyId);
							refreshTableModel();
							JOptionPane.showMessageDialog(ShowCompanyView.this, "ɾ���ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(ShowCompanyView.this, "ɾ��ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		southPanel.add(deleteBtn);
		this.add(southPanel,BorderLayout.SOUTH);		
		
	}
	
	//ˢ�±��
	public void refreshTableModel() {
		table.setModel(new CompanyIDTableModel(0));
		manageView.companyPageModel.setSearchText("");
		manageView.refreshTableModel();
	}
			
}
