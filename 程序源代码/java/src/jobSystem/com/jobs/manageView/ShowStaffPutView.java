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

import src.jobSystem.com.jobs.manageDao.ManageDao;
import src.jobSystem.com.jobs.manageDao.StaffPutTableModel;

public class ShowStaffPutView extends JDialog {
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
	
	public ShowStaffPutView() {}
	public ShowStaffPutView(ManageView manageView) {
		super(manageView,"�˲��г�����ϵͳ",true);
		this.createWindow();
	}

	public void createWindow() {
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(1200,500);
		this.setLocationRelativeTo(null);  //ʹ������ʾ����Ļ����
		this.setResizable(false);          //���ô��ڵĴ�СΪ���ɱ�
		
		//���߲���
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("����ְԱ��������Ϣ");
		title.setFont(new Font("����",Font.PLAIN,20));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);
		
		//�в�����
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new StaffPutTableModel(0));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
		
		//�ϲ�����
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		deleteBtn = new JButton("ɾ��ְԱ");		
		deleteBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {		
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(ShowStaffPutView.this, "����ѡ��Ҫɾ����ְԱ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(ShowStaffPutView.this, "ȷ��Ҫɾ��ѡ�е���Ϣ��","��ʾ",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int staffNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						ManageDao manageDao = new ManageDao();
						if(manageDao.deleStaff(staffNumber)) {
							refreshTableModel();
							JOptionPane.showMessageDialog(ShowStaffPutView.this, "ɾ���ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(ShowStaffPutView.this, "ɾ��ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
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
		table.setModel(new StaffPutTableModel(0));
	}
	
}
