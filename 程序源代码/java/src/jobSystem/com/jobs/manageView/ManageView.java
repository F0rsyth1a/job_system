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
	Manage manage = new Manage ();            //记录登陆的管理员对象
	CompanyPageModel companyPageModel = null; //招聘信息表格对象

	//北部组件
	JPanel northPanel = null;
	JLabel title= null;               //职员主界面标题
	JTextField searchField = null;    //搜索框
	JButton searchBtn = null;         //搜索按钮
	JButton returnBtn = null;         //搜索返回按钮
	
	//中部组件
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;   //滑动框
	JTable table = null;              //招聘信息表格	
	
	//西部组件
	JPanel westPanel = null;
	JButton messageBtn = null;        //查看个人信息按钮
	JButton changePwdBtn = null;      //修改密码按钮
	JButton showStaffBtn = null;      //查看所有职员信息按钮
	JButton showCompanyBtn = null;    //查看所有公司信息按钮
	JButton HistoryStaffBtn = null;   //查看职员历史账号按钮
	JButton HistoryCompanyBtn = null; //查看公司历史账号按钮
	
	//南部组件
	JPanel southPanel = null;
	JButton firstBtn = null;          //首页按钮
	JButton preveBtn = null;          //上一页按钮
	JButton nextBtn = null;           //下一页按钮
	JButton lastBtn = null;           //尾页按钮
	JLabel showPage = null;	          //页数显示	
	
	//构造方法
	public ManageView() {}
	public ManageView (Manage manage) {
		this.manage = manage;
	}
	
	public void ManageWindow() {
		//北边布局
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("职员主界面");
		title.setFont(new Font("宋体",Font.PLAIN,20));
		searchField = new JTextField(25);
		searchBtn = new JButton("搜索");	
		searchBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String searchText = searchField.getText();  //获取搜索框的内容
				companyPageModel.setSearchText(searchText); //设置搜索框
				refreshTableModel();                        //刷新表格
			}
		});
		
		returnBtn = new JButton("返回");	
		returnBtn.setBackground(Color.cyan);
		returnBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String searchText = "";  // 清空搜索框内容
				companyPageModel.setSearchText(searchText); // 设置搜索框
				refreshTableModel(); // 刷新表格

				// 隐藏当前窗口并可能返回上一个界面
				ManageView.this.dispose();  // 销毁当前窗口
			}
		});


		northPanel.add(title);
		northPanel.add(searchField);
		northPanel.add(searchBtn);
		northPanel.add(returnBtn);
		this.add(northPanel,BorderLayout.NORTH);
		
		//中部布局
		centerPanel = new JPanel(new BorderLayout());
		companyPageModel = new CompanyPageModel(20);                 //设置一页可以展示多少条招聘信息
		table = new JTable(new CompanyTableModel(companyPageModel)); //创建招聘信息表格
		table.setRowHeight(30);                                      //设置表格一行的高度
		jScrollPane = new JScrollPane(table);                        //将表格添加到滑动框内
		this.add(jScrollPane,BorderLayout.CENTER);				
		
		//西部布局
		westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,40));   //创建一个新的流布局管理器，它具有指定的对齐方式以及指定的水平和垂直间隙。
		westPanel.setPreferredSize(new Dimension(150, 400));               //设置JPanel的大小	
		messageBtn = new JButton("个人信息");
		messageBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				//使对话框窗体可见，实现了当用户单击该按钮后将弹出个人信息对话框的功能
				new ManageMessageView(ManageView.this,manage).setVisible(true);
			}
		});
		
		changePwdBtn = new JButton("修改密码");
		changePwdBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new ManageChangePwdView(ManageView.this,manage).setVisible(true);
			}
		});
		
		showStaffBtn = new JButton("查看所有职员");
		showStaffBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new src.jobSystem.com.jobs.manageView.ShowStaffPutView(ManageView.this).setVisible(true);
			}
		});	
		
		showCompanyBtn = new JButton("查看所有公司");
		showCompanyBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new src.jobSystem.com.jobs.manageView.ShowCompanyView(ManageView.this).setVisible(true);
			}
		});			
		
		HistoryStaffBtn = new JButton("职员历史账号");
		HistoryStaffBtn.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
				
				new HistoryStaffView(ManageView.this).setVisible(true);
			}
		});
		
		HistoryCompanyBtn = new JButton("公司历史账号");
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
		
		//南边布局
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		firstBtn = new JButton("首页");
		firstBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				companyPageModel.first();
				refreshTableModel();
			}
		});		
		preveBtn = new JButton("上一页");
		preveBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				companyPageModel.preve();
				refreshTableModel();
			}
		});
		nextBtn = new JButton("下一页");	
		nextBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				companyPageModel.next();
				refreshTableModel();
			}
		});		
		lastBtn = new JButton("尾页");
		lastBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				companyPageModel.last();
				refreshTableModel();
			}
		});		
		showPage = new JLabel("第"+companyPageModel.getCompanyPageNo()+"页/共"+companyPageModel.getCompanyTotalPage()+"页");
		southPanel.add(firstBtn);		
		southPanel.add(preveBtn);
		southPanel.add(nextBtn);
		southPanel.add(lastBtn);
		southPanel.add(showPage);
		this.add(southPanel,BorderLayout.SOUTH);		

		//主界面
		this.setTitle("人才市场管理系统");
		this.setSize(1500, 600);
		this.setLocationRelativeTo(null); //使窗口显示在屏幕中央
		this.setResizable(false);         //设置窗口的大小为不可变
		this.setVisible(true);            //设置容器可视化
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置容器的关闭方式	
	}
	
	//刷新表格和页数
	public void refreshTableModel() {
		table.setModel(new CompanyTableModel(companyPageModel));
		showPage.setText("第"+companyPageModel.getCompanyPageNo()+"页/共"+companyPageModel.getCompanyTotalPage()+"页");
	}
	
}
