package src.jobSystem.com.jobs.companyView;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.staffDao.StaffTableModel;
import src.jobSystem.com.jobs.util.Position;

public class StaffPutView extends JDialog {
	int companyNumber;
	String companyId;
	
	//�������
	JPanel northPanel = null;
	JLabel title= null;
			
	//�в����
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
	
	//�������
	JPanel westPanel = null;
	JLabel companyNameJLabel = new JLabel("��˾���ƣ�");
	JLabel jobNameJLabel = new JLabel("ְλ���ƣ� ");
	JLabel personJLabel = new JLabel(" ��  ϵ  �ˣ� ");
	JLabel phoneJLabel = new JLabel(" ��  ��  �� ��");
	JLabel countJLabel = new JLabel("��Ƹ������ ");
	JLabel learnJLabel = new JLabel(" ѧ        ���� ");
	JLabel requestJLabel = new JLabel("ְλҪ�� ");
	JLabel moneyJLabel = new JLabel(" н        �ʣ� ");
	JLabel AddressJLabel = new JLabel("��˾��ַ�� ");
	
	JTextField companyNameJTextField = new JTextField("",20);
	JTextField jobNameJTextField = new JTextField("",20);
	JTextField personJTextField = new JTextField("",20);
	JTextField phoneJTextField = new JTextField("",20);
	JTextField countJTextField = new JTextField("",20);
	JTextField learnJTextField = new JTextField("",20);
	JTextField requestJTextField = new JTextField("",20);
	JTextField moneyJTextField = new JTextField("",20);
	JTextField AddressJTextField = new JTextField("",20);	
			
	//�ϲ����
	JPanel southPanel = null;
	JButton invitionBtn = null;
	JButton returnBtn = null;
	
	public StaffPutView() {}
	public StaffPutView(PositionPutView positionPutView,String companyId,int companyNumber) {
		super(positionPutView,"�˲��г�����ϵͳ",true);
		this.companyId = companyId;
		this.companyNumber = companyNumber;
		this.createWindow();
	}
	
	public void createWindow() {
		CompanyDao companyDao = new CompanyDao();
		Position position = companyDao.findPutPositionMessageByNumber(companyId, companyNumber);
		this.setTitle("�˲��г�����ϵͳ");
		this.setSize(1100,500);
		this.setLocationRelativeTo(null);  //ʹ������ʾ����Ļ����
		this.setResizable(false);          //���ô��ڵĴ�СΪ���ɱ�
		
		//���߲���
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("��ְ��Ա��������Ϣ");
		title.setFont(new Font("����",Font.PLAIN,20));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);
		
		//�в�����
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new StaffTableModel(companyNumber));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
		
		//��������
		westPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		westPanel.setPreferredSize(new Dimension(300, 10));         //����JPanel�Ĵ�С	
		companyNameJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		companyNameJTextField.setText(position.getCompanyName());
		jobNameJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		jobNameJTextField.setText(position.getCompanyPosition());
		personJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		personJTextField.setText(position.getCompanyPerson());
		phoneJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		phoneJTextField.setText(position.getCompanyPhone());
		countJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		countJTextField.setText(position.getCompanyCount());
		learnJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		learnJTextField.setText(position.getCompanyLearn());
		requestJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		requestJTextField.setText(position.getCompanyRequest());
		moneyJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		moneyJTextField.setText(position.getCompanyMoney());
		AddressJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		AddressJTextField.setText(position.getCompanyAddress());
		
		westPanel.add(companyNameJLabel);
		westPanel.add(companyNameJTextField);
		westPanel.add(jobNameJLabel);
		westPanel.add(jobNameJTextField);
		westPanel.add(personJLabel);
		westPanel.add(personJTextField);
		westPanel.add(phoneJLabel);
		westPanel.add(phoneJTextField);
		westPanel.add(countJLabel);
		westPanel.add(countJTextField);
		westPanel.add(learnJLabel);
		westPanel.add(learnJTextField);
		westPanel.add(requestJLabel);
		westPanel.add(requestJTextField);
		westPanel.add(moneyJLabel);
		westPanel.add(moneyJTextField);	
		westPanel.add(AddressJLabel);
		westPanel.add(AddressJTextField);
		this.add(westPanel,BorderLayout.WEST);
		
		//�ϲ�����
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		invitionBtn = new JButton("��������");
		invitionBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				CompanyDao companyDao = new CompanyDao();
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(StaffPutView.this, "����ѡ��Ҫ�����ְԱ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else {
					int staffNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
					if(companyDao.selectInvitionMessage(companyNumber, staffNumber)) {
						JOptionPane.showMessageDialog(StaffPutView.this, "�����룬�����ٴ����룡","��ʾ",JOptionPane.WARNING_MESSAGE);
					}else {
						if(companyDao.invitionStaff(companyNumber, staffNumber)) {
							JOptionPane.showMessageDialog(StaffPutView.this, "����ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(StaffPutView.this, "����ʧ�ܣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});			
		southPanel.add(invitionBtn);	
		this.add(southPanel,BorderLayout.SOUTH);
	}

}
