package src.jobSystem.com.jobs.staffDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import src.jobSystem.com.jobs.util.JDBCUtil;
import src.jobSystem.com.jobs.util.Position;
import src.jobSystem.com.jobs.util.Staff;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.ClientPreparedStatement;


public class StaffDao {

	//��½����
	public Staff staffLanding(String id,String pwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "select * from staffTable where staffId=? and staffPwd=?";
			//����ClientPreparedStatement������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//����ռλ����ֵ
			ps.setString(1, id);
			ps.setString(2, pwd);
			//ִ��sql ���ؽ����
			rs = ps.executeQuery();
			//�����˻��������Ȩ���Ƿ���ȷ
			if(rs.next()) {
				Staff staff = new Staff();
				staff.setStaffName(rs.getString("staffName"));
				staff.setStaffId(rs.getString("staffId"));
				staff.setStaffPwd(rs.getString("staffPwd"));
				staff.setStaffSign(rs.getInt("staffSign"));

				staff.setStaffCard(rs.getString("staffCard"));
				staff.setStaffAddress(rs.getString("staffAddress"));
				staff.setStaffPhone(rs.getString("staffPhone"));
				staff.setStaffLearn(rs.getString("staffLearn"));
				staff.setStaffJob(rs.getString("staffJob"));
				staff.setStaffNumber(rs.getInt("staffNumber"));
				return staff;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return null;
	}

	//����ע��ID�Ƿ����
	public boolean selectID(String id) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "select staffId from staffTable where staffId = ?";
			//����ClientPreparedStatement������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//��sql������ֵ
			ps.setString(1, id);
			//ִ��sql ���ؽ����
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//�����˻�ע��
	public boolean staffLogin(String Name,String Id,String Pwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into staffTable(staffName,staffId,staffPwd,staffSign) values(?,?,?,?)";
			//Ԥ����������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setString(1, Name);
			ps.setString(2, Id);
			ps.setString(3, Pwd);
			ps.setInt(4, 0);
			//ִ��sql
			int count = ps.executeUpdate();   //count���ڼ�¼�Ƿ�ע��ɹ�
			if(count > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//����Ƹ��Ϣ�ı�Ų���Ͷ�ݼ�����ְԱ��Ϣ
	public ArrayList<Staff> getPutStaffsList(int companyNumber,int flag) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Staff> staffs =null;
		String sql = null;
		try {
			conn = JDBCUtil.getConnection();
			if(flag == 0) {
				sql = "select staffNumber,staffName,staffAddress,staffPhone,staffLearn,staffJob from staffView where staffNumber in (select PstaffNumber from putTable where PcompanyNumber = ?)";
			}else if(flag == 1) {
				sql = "select staffNumber,staffName,staffAddress,staffPhone,staffLearn,staffJob from staffView where staffNumber in (select IstaffNumber from invitionTable where IcompanyNumber = ?)";
			}else if(flag == 2) {
				sql = "select staffNumber,staffName,staffAddress,staffPhone,staffLearn,staffJob from staffView where staffNumber in (select EstaffNumber from employTable where EcompanyNumber = ?)";
			}
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				staffs =new ArrayList<Staff>();
			}
			while(rs.next()) {
				Staff staff = new Staff();
				staff.setStaffNumber(rs.getInt("staffNumber"));
				staff.setStaffName(rs.getString("staffName"));
				staff.setStaffAddress(rs.getString("staffAddress"));
				staff.setStaffPhone(rs.getString("staffPhone"));
				staff.setStaffLearn(rs.getString("staffLearn"));
				staff.setStaffJob(rs.getString("staffJob"));
				staffs.add(staff);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return staffs;
	}

	//�޸ĸ�����Ϣ
	public boolean changeStaffMessage(Staff staff) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "update staffTable set staffName=?,staffCard=?,staffAddress=?,staffPhone=?,staffLearn=?,staffJob=? where staffId = ?";
			//����ClientPreparedStatement������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//��sql������ֵ
			ps.setString(1, staff.getStaffName());
			ps.setString(2, staff.getStaffCard());
			ps.setString(3, staff.getStaffAddress());
			ps.setString(4, staff.getStaffPhone());
			ps.setString(5, staff.getStaffLearn());
			ps.setString(6, staff.getStaffJob());
			ps.setString(7, staff.getStaffId());
			//ִ��sql
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//�޸ĸ�������
	public boolean changeStaffPwd(String id,String pwd,String newpwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update staffTable set staffPwd = ? where staffId = ? and  staffPwd = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, newpwd);
			ps.setString(2, id);
			ps.setString(3, pwd);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//�鿴�Ƿ�Ͷ�ݹ�����
	public boolean selectPutMessage(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();                      //��ȡ���ݿ�����
			String sql = "select PcompanyNumber,PstaffNumber from putTable where PcompanyNumber = ? and PstaffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);  //����ClientPreparedStatement������
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			rs = ps.executeQuery();                               //ִ��sql ���ؽ����
			while(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);  //�ͷ���Դ
		}
		return false;
	}

	//Ͷ�ݼ���
	public boolean putMessage(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into putTable(PcompanyNumber ,PstaffNumber ) values(?,?)";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//ɾ��Ͷ�ݼ���
	public boolean delPutMessage(int companyNumber, int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from putTable where PcompanyNumber = ? and PstaffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//�鿴¼�����
	public Position employMessage(int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		Position position = null;   //��ǰ���Ų�ѯ����Ƹ��Ϣ�Ƿ����
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyNumber in (select EcompanyNumber from employTable where EstaffNumber = ?)";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, staffNumber);
			rs = ps.executeQuery();
			while(rs.next()) {
				position = new Position();
				position.setCompanyNumber(rs.getInt("companyNumber"));
				position.setCompanyName(rs.getString("companyName"));
				position.setCompanyPosition(rs.getString("companyPosition"));
				position.setCompanyPerson(rs.getString("companyPerson"));
				position.setCompanyPhone(rs.getString("companyPhone"));
				position.setCompanyCount(rs.getString("companyCount"));
				position.setCompanyLearn(rs.getString("companyLearn"));
				position.setCompanyRequest(rs.getString("companyRequest"));
				position.setCompanyMoney(rs.getString("companyMoney"));
				position.setCompanyAddress(rs.getString("companyAddress"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return position;

	}


}
