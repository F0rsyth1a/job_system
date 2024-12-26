package src.jobSystem.com.jobs.manageDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import src.jobSystem.com.jobs.util.Company;
import src.jobSystem.com.jobs.util.JDBCUtil;
import src.jobSystem.com.jobs.util.Manage;
import src.jobSystem.com.jobs.util.Staff;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.ClientPreparedStatement;

public class ManageDao {

	//��½����
	public Manage manageLanding(String id,String pwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "select * from userTable where userRole=1 and userId=? and userPwd=?";
			//����ClientPreparedStatement������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//����ռλ����ֵ
			ps.setString(1, id);
			ps.setString(2, pwd);
			//ִ��sql ���ؽ����
			rs = ps.executeQuery();
			//�����˻��������Ȩ���Ƿ���ȷ
			if(rs.next()) {
				Manage manage = new Manage();         //�½���˾��λʵ�������ڴ�ŵ�½ʵ��
				manage.setManageName(rs.getString("userName"));
				manage.setManageId(rs.getString("userId"));
				manage.setManagePwd(rs.getString("userPwd"));
				manage.setManageRole(rs.getInt("userRole"));
				manage.setManageSign(rs.getInt("userSign"));
				return manage;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return null;
	}

	//�޸ĸ�����Ϣ
	public boolean changeManage(Manage manage) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userName=? where userId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, manage.getManageName());
			ps.setString(2, manage.getManageId());
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

	//�޸ĸ�������
	public boolean changeManagePwd(String id,String pwd,String newpwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userPwd = ? where userId = ? and  userPwd = ?";
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

	//�������еĹ�˾��Ϣ
	public ArrayList<Company> getCompanysList(int flag) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Company> companys = null;
		String sql = null;
		try {
			conn = JDBCUtil.getConnection();
			if(flag == 0) {
				sql = "select userNumber,userName,userId from companyView";
			}else if(flag == 1) {
				sql = "select userNumber,userName,userId from userTable where userRole = 2 and userSign = 1";
			}
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				companys =new ArrayList<Company>();
			}
			while(rs.next()) {
				Company company = new Company();
				company.setCompanyNumber(rs.getInt("userNumber"));
				company.setCompanyName(rs.getString("userName"));
				company.setCompanyId(rs.getString("userId"));
				companys.add(company);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return companys;
	}

	//�������е�ְԱ��Ϣ
	public ArrayList<Staff> getStaffsList(int flag) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Staff> staffs = null;
		String sql = null;
		try {
			conn = JDBCUtil.getConnection();
			if(flag == 0) {
				sql = "select staffNumber,staffId,staffName,staffCard,staffAddress,staffPhone,staffLearn,staffJob from staffView";
			}else if(flag == 1) {
				sql = "select staffNumber,staffId,staffName,staffCard,staffAddress,staffPhone,staffLearn,staffJob from staffTable where staffSign = 1";
			}
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				staffs =new ArrayList<Staff>();
			}
			while(rs.next()) {
				Staff staff = new Staff();
				staff.setStaffNumber(rs.getInt("staffNumber"));
				staff.setStaffId(rs.getString("staffId"));
				staff.setStaffName(rs.getString("staffName"));
				staff.setStaffCard(rs.getString("staffCard"));
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

	//�߼���ɾ��ְԱ��Ϣ
	public boolean deleStaff(int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update staffTable set staffSign = ? where staffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, staffNumber);
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

	//������ɾ��ְԱ��Ϣ
	public boolean deleteStaff(int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from staffTable where staffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, staffNumber);
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

	//�ָ�ְԱ�˺�
	public boolean regainStaff(int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update staffTable set staffSign = 0 where staffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, staffNumber);
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

	//�߼���ɾ����˾�˺�
	public boolean deleCompany(int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userSign = 1 where userNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
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

	//�߼���ɾ����˾����Ƹ��Ϣ
	public boolean deleCompanyPosition(String companyId) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update companyTable set companyFlag = 1 where companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, companyId);
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

	//������ɾ����˾�˺�
	public boolean deleteCompany(int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from userTable where userNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
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

	//������ɾ����˾����Ƹ��Ϣ
	public boolean deleteCompanyPosition(String companyId) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from companyTable where companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, companyId);
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

	//�ָ���˾�˺�
	public boolean regainCompany(int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userSign = 0 where userNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
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

	//�ָ���˾����Ƹ��Ϣ
	public boolean regainCompanyPosition(String companyId) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update companyTable set companyFlag = 0 where companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, companyId);
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

}
