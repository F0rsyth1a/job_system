package src.jobSystem.com.jobs.companyView;

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

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.companyDao.CompanyTableModel;

public class PositionHistoryView extends JDialog {
	src.jobSystem.com.jobs.companyView.PositionView positionView = null;
	String companyId;
	
	//�������
	JPanel northPanel = null;
	JLabel title= null;	

	//�в����
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;	
	
	//�ϲ����
	JPanel southPanel = null;
	JButton regainBtn = null;
	JButton deleteBtn = null;
	
	public PositionHistoryView() {}
	public PositionHistoryView(src.jobSystem.com.jobs.companyView.PositionView positionView, String companyId) {
		super(positionView,"�˲��г�����ϵͳ",true);
		this.positionView = positionView;
		this.companyId = companyId;
		this.createWindow();
	}
	
	public void createWindow() {
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(1200,500);
		this.setLocationRelativeTo(null);  //ʹ������ʾ����Ļ����
		this.setResizable(false);          //���ô��ڵĴ�СΪ���ɱ�
		
		//���߲���
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("��˾��λ��������Ƹ��Ϣ");
		title.setFont(new Font("����",Font.PLAIN,25));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);		
		
		//�в�����
		centerPanel = new JPanel(new BorderLayout());		
		table = new JTable(new CompanyTableModel(companyId,1));	
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
			
		//�ϱ߲���
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		regainBtn = new JButton("�ָ�");
		regainBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionHistoryView.this, "����ѡ��Ҫ�ָ�����Ϣ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(PositionHistoryView.this, "ȷ��Ҫ�ָ�ѡ�е���Ϣ��","��ʾ",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						CompanyDao companyDao = new CompanyDao();
						if(companyDao.regainPositionMessage(companyId,companyNumber)) {
							refreshTableModel();
							positionView.refreshTableModel();
							JOptionPane.showMessageDialog(PositionHistoryView.this, "�ָ��ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(PositionHistoryView.this, "�ָ�ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});		
		
		deleteBtn = new JButton("������ɾ��");
		deleteBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionHistoryView.this, "����ѡ��Ҫɾ������Ϣ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(PositionHistoryView.this, "ȷ��Ҫɾ��ѡ�е���Ϣ��ɾ���󽫲��ɻָ���","��ʾ",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						CompanyDao companyDao = new CompanyDao();
						if(companyDao.deletePositionMessage(companyId,companyNumber)) {
							refreshTableModel();
							positionView.refreshTableModel();
							JOptionPane.showMessageDialog(PositionHistoryView.this, "ɾ���ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(PositionHistoryView.this, "ɾ��ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});		
		
		southPanel.add(regainBtn);
		southPanel.add(deleteBtn);		
		this.add(southPanel,BorderLayout.SOUTH);
		
	}
	
	//ˢ�±��
	public void refreshTableModel() {
		table.setModel(new CompanyTableModel(companyId,1));
	}		
	
}

