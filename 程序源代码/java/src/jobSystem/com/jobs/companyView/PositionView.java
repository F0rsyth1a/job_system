package src.jobSystem.com.jobs.companyView;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.companyDao.CompanyTableModel;
import src.jobSystem.com.jobs.util.Company;

public class PositionView extends JFrame {
	Company company = new Company();
	CompanyView companyView;
	
	//�������
	JPanel northPanel = null;
	JLabel title= null;
		
	//�в����
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
		
	//�������
	JPanel westPanel = null;
	JButton addBtn = null;     //������Ƹ��Ϣ��ť
	JButton delBtn = null;     //ɾ����Ƹ��Ϣ��ť
	JButton changeBtn = null;  //�޸���Ƹ��Ϣ��ť
	JButton HistoryBtn = null; //��ʷ��Ƹ��Ϣ��ť
	JButton returnBtn = null;  //�˳���ť
	
	//���췽��
	public PositionView() {}
	public PositionView(Company company,CompanyView companyView) {
		this.company = company;
		this.companyView = companyView;   //�������洫�������� �򿪡��رպ�ˢ�½���
	}	
	
	public void createWindow() {
		//���߲���
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("��˾��λ��������Ƹ��Ϣ");
		title.setFont(new Font("����",Font.PLAIN,25));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);
		
		//�в�����
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new CompanyTableModel(company.getCompanyId(),0));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
		
		//��������
		westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,40));  //����һ���µ������ֹ�������������ָ���Ķ��뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
		westPanel.setPreferredSize(new Dimension(150, 400));              //����JPanel�Ĵ�С	
		addBtn = new JButton("������Ƹ");
		addBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				new CompanyAddMessageView(PositionView.this,company).setVisible(true);
			}
		});
		
		delBtn = new JButton("ɾ����Ƹ");
		delBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionView.this, "����ѡ��Ҫɾ������Ϣ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(PositionView.this, "ȷ��Ҫɾ��ѡ�е���Ϣ��","��ʾ",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						CompanyDao companyDao = new CompanyDao();
						if(companyDao.delePositionMessage(company.getCompanyId(),companyNumber)) {
							refreshTableModel();
							JOptionPane.showMessageDialog(PositionView.this, "ɾ���ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(PositionView.this, "ɾ��ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		changeBtn = new JButton("�޸���Ƹ");
		changeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionView.this, "����ѡ��Ҫ�޸ĵ���Ƹ��Ϣ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int id = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
					new CompanyChangeMessageView(PositionView.this,company.getCompanyId(),id).setVisible(true);
				}
			}
		});
		
		HistoryBtn = new JButton("��ʷ��Ƹ");
		HistoryBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				new PositionHistoryView(PositionView.this,company.getCompanyId()).setVisible(true);
			}
		});
		
		returnBtn = new JButton("��      ��");
		returnBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PositionView.this.dispose();
				companyView.refreshTableModel();
				companyView.setVisible(true);
			}
		});
		
		westPanel.add(addBtn);
		westPanel.add(delBtn);
		westPanel.add(changeBtn);
		westPanel.add(HistoryBtn);
		westPanel.add(returnBtn);
		this.add(westPanel,BorderLayout.WEST);
		
		//������
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(1500, 560);
		this.setLocationRelativeTo(null); //ʹ������ʾ����Ļ����
		this.setResizable(false);         //���ô��ڵĴ�СΪ���ɱ�
		this.setVisible(true);            //�����������ӻ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //���������Ĺرշ�ʽ
		
	}
	
	//ˢ�±��
	public void refreshTableModel() {
		table.setModel(new CompanyTableModel(company.getCompanyId(),0));
	}
	
}

