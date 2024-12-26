package src.jobSystem.com.jobs.companyView;

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
import src.jobSystem.com.jobs.util.Company;

public class CompanyView extends JFrame {
	Company company = null;                   //��¼��½�Ĺ�˾����
	CompanyPageModel companyPageModel = null; //��Ƹ��Ϣ������
	
	//�������
	JPanel northPanel = null;
	JLabel title= null;               //��˾��λ���������
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
	JButton putBtn = null;            //������Ƹ��Ϣ��ť
	JButton showSendBtn = null;       //�鿴������Ƹ��ť
	JButton showPutBtn = null;        //�鿴��ƸͶ�ݰ�ť
	JButton showInvitionBtn = null;   //�鿴����ְԱ��ť
	JButton showEmployBtn = null;     //�鿴¼��ְԱ��ť
		
	//�ϲ����
	JPanel southPanel = null;
	JButton firstBtn = null;          //��ҳ��ť
	JButton preveBtn = null;          //��һҳ��ť
	JButton nextBtn = null;           //��һҳ��ť
	JButton lastBtn = null;           //βҳ��ť
	JLabel showPage = null;	          //ҳ����ʾ
	
	//���췽��
	public CompanyView () {}
	public CompanyView (Company company) {
		this.company = company;
	}
	
	public void CompanyWindow() {
		//���߲���
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("��˾��λ������");
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
		westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,40));  //����һ���µ������ֹ�������������ָ���Ķ��뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
		westPanel.setPreferredSize(new Dimension(200, 400));              //����JPanel�Ĵ�С	
		messageBtn = new JButton("������Ϣ");
		messageBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				//ʹ�Ի�����ɼ���ʵ���˵��û������ð�ť�󽫵���������Ϣ�Ի���Ĺ���
				new CompanyMessageView(CompanyView.this,company).setVisible(true);
			}
		});

		changePwdBtn = new JButton("�޸�����");	
		changePwdBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new CompanyChangePwdView(CompanyView.this,company).setVisible(true);
			}
		});		
		
		putBtn = new JButton("������Ƹ��Ϣ");		
		putBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new CompanyAddMessageView(CompanyView.this,company).setVisible(true);
			}
		});	
		
		showSendBtn = new JButton("�鿴������Ƹ");
		showSendBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			src.jobSystem.com.jobs.companyView.PositionView positionView = new src.jobSystem.com.jobs.companyView.PositionView(company,CompanyView.this);
				CompanyView.this.setVisible(false);
				positionView.createWindow();
			}
		});
		
		showPutBtn = new JButton("�鿴��ƸͶ��");	
		showPutBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				src.jobSystem.com.jobs.companyView.PositionPutView positionPutView = new src.jobSystem.com.jobs.companyView.PositionPutView(company,CompanyView.this);
				CompanyView.this.setVisible(false);
				positionPutView.createWindow();
			}
		});
		
		showInvitionBtn = new JButton("�鿴����ְԱ");	
		showInvitionBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				src.jobSystem.com.jobs.companyView.PositionInvitionView positionInvitionView = new src.jobSystem.com.jobs.companyView.PositionInvitionView(company,CompanyView.this);
				CompanyView.this.setVisible(false);
				positionInvitionView.createWindow();
			}
		});

		showEmployBtn = new JButton("�鿴¼��ְԱ");
		showEmployBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				src.jobSystem.com.jobs.companyView.PositionEmployView positionEmployView = new src.jobSystem.com.jobs.companyView.PositionEmployView(company,CompanyView.this);
				CompanyView.this.setVisible(false);
				positionEmployView.createWindow();
			}
		});
		
		westPanel.add(messageBtn);
		westPanel.add(changePwdBtn);
		westPanel.add(putBtn);
		westPanel.add(showSendBtn);
		westPanel.add(showPutBtn);
		westPanel.add(showInvitionBtn);
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

