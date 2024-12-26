package src.jobSystem.com.jobs.staffDao;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import src.jobSystem.com.jobs.util.Staff;

public class StaffEmployTableModel extends AbstractTableModel {
	StaffDao staffDao = new StaffDao();
	String[] columnNames = {"���","����","��ַ","�绰","ѧ��","ְ��"};
	ArrayList<Staff> staffs =null;
	Object[][] rowData = null;
	
	//���췽������ȡĳ���ض���˾������Ƹ��Ϣ��Ͷ�ݵ�ְԱ��Ϣ��������
	public StaffEmployTableModel(int companyNumber) {
		staffs = staffDao.getPutStaffsList(companyNumber,2);
		rowData = new Object[staffs.size()][columnNames.length];
		for(int i=0;i<staffs.size();i++) {
			rowData[i][0] = staffs.get(i).getStaffNumber();
			rowData[i][1] = staffs.get(i).getStaffName();
			rowData[i][2] = staffs.get(i).getStaffAddress();
			rowData[i][3] = staffs.get(i).getStaffPhone();
			rowData[i][4] = staffs.get(i).getStaffLearn();
			rowData[i][5] = staffs.get(i).getStaffJob();
		}
	}
	
	//��ȡ����
	public String getColumnName(int column) {
		return columnNames[column];
	}	

	//��ȡ����
	public int getRowCount() {
		return staffs.size();
	}

	//��ȡ����
	public int getColumnCount() {
		return columnNames.length;
	}

	//��ȡ��Ԫֵ
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rowData[rowIndex][columnIndex];
	}
	
}

