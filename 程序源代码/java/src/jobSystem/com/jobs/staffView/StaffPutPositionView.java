package src.jobSystem.com.jobs.staffView;

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

import src.jobSystem.com.jobs.companyDao.CompanyTableModel;
import src.jobSystem.com.jobs.staffDao.StaffDao;

public class StaffPutPositionView extends JDialog {
	int staffNumber;
	
	//�������
	JPanel northPanel = null;
	JLabel title= null;
				
	//�в����
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
				
	//�ϲ����
	JPanel southPanel = null;
	JButton delPutBtn = null;
	
	public StaffPutPositionView() {}
	public StaffPutPositionView(StaffView staffView,int staffNumber) {
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
		title=new JLabel("ȫ��Ͷ�ݵ���Ƹ��Ϣ");
		title.setFont(new Font("����",Font.PLAIN,20));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);		

		//�в�����
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new CompanyTableModel(staffNumber,0));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
		
		//�ϲ�����
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		delPutBtn = new JButton("ɾ��Ͷ��");
		delPutBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(StaffPutPositionView.this, "����ѡ��Ҫɾ������Ϣ","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(StaffPutPositionView.this, "ȷ��Ҫɾ��ѡ�е���Ϣ��","��ʾ",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						StaffDao staffDao = new StaffDao();
						if(staffDao.delPutMessage(companyNumber, staffNumber)) {
							refreshTableModel();
							JOptionPane.showMessageDialog(StaffPutPositionView.this, "ɾ���ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(StaffPutPositionView.this, "ɾ��ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		southPanel.add(delPutBtn);
		this.add(southPanel,BorderLayout.SOUTH);	
		
	}
	
	//ˢ�±��
	public void refreshTableModel() {
		table.setModel(new CompanyTableModel(staffNumber,0));
	}	

}
