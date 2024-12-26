package src.jobSystem.com.jobs.staffView;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;

import src.jobSystem.com.jobs.companyDao.CompanyPageModel;
import src.jobSystem.com.jobs.companyDao.CompanyTableModel;
import src.jobSystem.com.jobs.staffDao.StaffDao;
import src.jobSystem.com.jobs.util.Position;
import src.jobSystem.com.jobs.util.Staff;

public class StaffView extends JFrame {
	Staff staff = new Staff();                //��¼��½��ְԱ����
	CompanyPageModel companyPageModel = null; //��Ƹ��Ϣ������
	
	//�������
	JPanel northPanel = null;
	JLabel title= null;               //ְԱ���������
	JTextField searchField = null;    //������
	JButton searchBtn = null;         //������ť
	JButton returnBtn = null;         //�������ذ�ť
	
	//�в����
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;   //������
	JTable table = null;              //��Ƹ��Ϣ���
	
	//�������
	JPanel westPanel = null;
	JButton messageBtn = null;        //�鿴������Ϣ��ť
	JButton changePwdBtn = null;      //�޸����밴ť
	JButton putBtn = null;            //Ͷ�ݸ��˼�����ť
	JButton showPutBtn = null;        //�鿴Ͷ�ݼ�����ť
	JButton showInvitionvBtn = null;  //�鿴�������밴ť
	JButton showEmployBtn = null;     //�鿴����¼�ð�ť
	
	//�ϲ����
	JPanel southPanel = null;
	JButton firstBtn = null;          //��ҳ��ť
	JButton preveBtn = null;          //��һҳ��ť
	JButton nextBtn = null;           //��һҳ��ť
	JButton lastBtn = null;           //βҳ��ť
	JLabel showPage = null;	          //ҳ����ʾ
	
	//���췽��
	public StaffView () {}
	public StaffView (Staff staff) {
		this.staff = staff;
		if(staff.getStaffCard() == null || staff.getStaffAddress() == null || staff.getStaffPhone() == null || staff.getStaffLearn() == null || staff.getStaffJob() == null) {
			JOptionPane.showMessageDialog(StaffView.this, "�뾡�����Ƹ������ϣ����Ƹ������Ϻ󷽿�Ͷ�ݼ�����","��ʾ",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void StaffWindow() {
		//���߲���
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("ְԱ������");
		title.setFont(new Font("����",Font.PLAIN,20));
		searchField = new JTextField(25);
		searchBtn = new JButton("����");	
		searchBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String searchText = searchField.getText();  //��ȡ�����������
				companyPageModel.setSearchText(searchText); //����������
				refreshTableModel();                        //ˢ�±��
			}
		});
		
		returnBtn = new JButton("����");	
		returnBtn.setBackground(Color.cyan);
		returnBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String searchText = "";                     //��ȡ�����������Ϊ��
				companyPageModel.setSearchText(searchText); //����������
				refreshTableModel();                        //ˢ�±��
			}
		});		
				
		northPanel.add(title);
		northPanel.add(searchField);
		northPanel.add(searchBtn);
		northPanel.add(returnBtn);
		this.add(northPanel,BorderLayout.NORTH);		
		
		//�в�����
		centerPanel = new JPanel(new BorderLayout());
		companyPageModel = new CompanyPageModel(20);                 //����һҳ����չʾ��������Ƹ��Ϣ
		table = new JTable(new CompanyTableModel(companyPageModel)); //������Ƹ��Ϣ���
		table.setRowHeight(30);                                      //���ñ��һ�еĸ߶�
		jScrollPane = new JScrollPane(table);                        //�������ӵ���������
		this.add(jScrollPane,BorderLayout.CENTER);		
			

		//��������
		westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,40));   //����һ���µ������ֹ�������������ָ���Ķ��뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
		westPanel.setPreferredSize(new Dimension(150, 400));               //����JPanel�Ĵ�С	
		messageBtn = new JButton("������Ϣ");
		messageBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				//ʹ�Ի�����ɼ���ʵ���˵��û������ð�ť�󽫵���������Ϣ�Ի���Ĺ���
				new src.jobSystem.com.jobs.staffView.StaffMessageView(StaffView.this,staff).setVisible(true);
			}
		});		

		changePwdBtn = new JButton("�޸�����");		
		changePwdBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new src.jobSystem.com.jobs.staffView.StaffChangePwdView(StaffView.this,staff).setVisible(true);
			}
		});		

		putBtn = new JButton("Ͷ�ݸ��˼���");
		putBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(staff.getStaffCard() == null || staff.getStaffAddress() == null || staff.getStaffPhone() == null || staff.getStaffLearn() == null || staff.getStaffJob() == null) {                   
					JOptionPane.showMessageDialog(StaffView.this, "�뾡�����Ƹ������ϣ����Ƹ������Ϻ󷽿�Ͷ�ݼ�����","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int rowIndex = table.getSelectedRow();
					if(rowIndex == -1) {
						JOptionPane.showMessageDialog(StaffView.this, "����ѡ��ҪͶ�ݵ���Ϣ��","��ʾ",JOptionPane.WARNING_MESSAGE);
					}else {
						int flag = JOptionPane.showConfirmDialog(StaffView.this, "ȷ��ҪͶ��ѡ�е���Ƹ��Ϣ��","��ʾ",JOptionPane.YES_NO_OPTION);
						if(flag == 0) {
							int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
							//Ͷ��
							StaffDao staffDao = new StaffDao();
							if(staffDao.selectPutMessage(companyNumber, staff.getStaffNumber())) {
								JOptionPane.showMessageDialog(StaffView.this, "��Ͷ�ݣ������ٴ�Ͷ�ݣ�","��ʾ",JOptionPane.WARNING_MESSAGE);
							}else {
								if(staffDao.putMessage(companyNumber, staff.getStaffNumber())) {
									JOptionPane.showMessageDialog(StaffView.this, "Ͷ�ݳɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
								}else {
									JOptionPane.showMessageDialog(StaffView.this, "Ͷ��ʧ�ܣ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				}
			}
		});					

		showPutBtn = new JButton("�鿴Ͷ�ݼ���");
		showPutBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				new src.jobSystem.com.jobs.staffView.StaffPutPositionView(StaffView.this,staff.getStaffNumber()).setVisible(true);
			}
		});
		
		showInvitionvBtn = new JButton("�鿴��������");
		showInvitionvBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				new src.jobSystem.com.jobs.staffView.StaffInvitionPositionView(StaffView.this,staff.getStaffNumber()).setVisible(true);
			}
		});

		showEmployBtn = new JButton("�鿴����¼��");
		showEmployBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				StaffDao staffDao = new StaffDao();
				Position position = staffDao.employMessage(staff.getStaffNumber());
				if(position != null) {
					
					new StaffEmployView(StaffView.this,position).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(StaffView.this, "��δ��¼�ã������ĵȴ���","��ʾ",JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		westPanel.add(messageBtn);
		westPanel.add(changePwdBtn);
		westPanel.add(putBtn);
		westPanel.add(showPutBtn);
		westPanel.add(showInvitionvBtn);
		westPanel.add(showEmployBtn);
		this.add(westPanel,BorderLayout.WEST);		
		
		//�ϱ߲���
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		firstBtn = new JButton("��ҳ");
		firstBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				companyPageModel.first();
				refreshTableModel();
			}
		});		
		preveBtn = new JButton("��һҳ");
		preveBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				companyPageModel.preve();
				refreshTableModel();
			}
		});
		nextBtn = new JButton("��һҳ");	
		nextBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				companyPageModel.next();
				refreshTableModel();
			}
		});		
		lastBtn = new JButton("βҳ");
		lastBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				companyPageModel.last();
				refreshTableModel();
			}
		});		
		showPage = new JLabel("��"+companyPageModel.getCompanyPageNo()+"ҳ/��"+companyPageModel.getCompanyTotalPage()+"ҳ");
		southPanel.add(firstBtn);		
		southPanel.add(preveBtn);
		southPanel.add(nextBtn);
		southPanel.add(lastBtn);
		southPanel.add(showPage);
		this.add(southPanel,BorderLayout.SOUTH);		

		//������
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(1500, 600);
		this.setLocationRelativeTo(null); //ʹ������ʾ����Ļ����
		this.setResizable(false);         //���ô��ڵĴ�СΪ���ɱ�
		this.setVisible(true);            //�����������ӻ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //���������Ĺرշ�ʽ
		
	}
	
	//ˢ�±���ҳ��
	public void refreshTableModel() {
		table.setModel(new CompanyTableModel(companyPageModel));
		showPage.setText("��"+companyPageModel.getCompanyPageNo()+"ҳ/��"+companyPageModel.getCompanyTotalPage()+"ҳ");
	}	

}
