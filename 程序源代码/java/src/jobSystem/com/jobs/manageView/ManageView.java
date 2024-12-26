package src.jobSystem.com.jobs.manageView;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.companyDao.CompanyPageModel;
import src.jobSystem.com.jobs.companyDao.CompanyTableModel;
import src.jobSystem.com.jobs.util.Manage;

public class ManageView extends JFrame {
	Manage manage = new Manage ();            //��¼��½�Ĺ���Ա����
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
	JButton showStaffBtn = null;      //�鿴����ְԱ��Ϣ��ť
	JButton showCompanyBtn = null;    //�鿴���й�˾��Ϣ��ť
	JButton HistoryStaffBtn = null;   //�鿴ְԱ��ʷ�˺Ű�ť
	JButton HistoryCompanyBtn = null; //�鿴��˾��ʷ�˺Ű�ť
	
	//�ϲ����
	JPanel southPanel = null;
	JButton firstBtn = null;          //��ҳ��ť
	JButton preveBtn = null;          //��һҳ��ť
	JButton nextBtn = null;           //��һҳ��ť
	JButton lastBtn = null;           //βҳ��ť
	JLabel showPage = null;	          //ҳ����ʾ	
	
	//���췽��
	public ManageView() {}
	public ManageView (Manage manage) {
		this.manage = manage;
	}
	
	public void ManageWindow() {
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
				String searchText = "";  // �������������
				companyPageModel.setSearchText(searchText); // ����������
				refreshTableModel(); // ˢ�±��

				// ���ص�ǰ���ڲ����ܷ�����һ������
				ManageView.this.dispose();  // ���ٵ�ǰ����
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
				new ManageMessageView(ManageView.this,manage).setVisible(true);
			}
		});
		
		changePwdBtn = new JButton("�޸�����");
		changePwdBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new ManageChangePwdView(ManageView.this,manage).setVisible(true);
			}
		});
		
		showStaffBtn = new JButton("�鿴����ְԱ");
		showStaffBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new src.jobSystem.com.jobs.manageView.ShowStaffPutView(ManageView.this).setVisible(true);
			}
		});	
		
		showCompanyBtn = new JButton("�鿴���й�˾");
		showCompanyBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new src.jobSystem.com.jobs.manageView.ShowCompanyView(ManageView.this).setVisible(true);
			}
		});			
		
		HistoryStaffBtn = new JButton("ְԱ��ʷ�˺�");
		HistoryStaffBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new HistoryStaffView(ManageView.this).setVisible(true);
			}
		});
		
		HistoryCompanyBtn = new JButton("��˾��ʷ�˺�");
		HistoryCompanyBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new HistoryCompanyView(ManageView.this).setVisible(true);
			}
		});		
		
		westPanel.add(messageBtn);
		westPanel.add(changePwdBtn);
		westPanel.add(showStaffBtn);
		westPanel.add(showCompanyBtn);
		westPanel.add(HistoryStaffBtn);
		westPanel.add(HistoryCompanyBtn);
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
