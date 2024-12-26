package src.jobSystem.com.jobs.companyDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import src.jobSystem.com.jobs.util.Company;
import src.jobSystem.com.jobs.util.JDBCUtil;
import src.jobSystem.com.jobs.util.Position;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.ClientPreparedStatement;


public class CompanyDao {

	//��½����
	public Company companyLanding(String id,String pwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "select * from userTable where userRole=2 and userId=? and userPwd=?";
			//����ClientPreparedStatement������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//����ռλ����ֵ
			ps.setString(1, id);
			ps.setString(2, pwd);
			//ִ��sql ���ؽ����
			rs = ps.executeQuery();
			//�����˻��������Ȩ���Ƿ���ȷ
			if(rs.next()) {
				Company company = new Company();         //�½���˾��λʵ�������ڴ�ŵ�½ʵ��
				company.setCompanyName(rs.getString("userName"));
				company.setCompanyId(rs.getString("userId"));
				company.setCompanyPwd(rs.getString("userPwd"));
				company.setCompanyRole(rs.getInt("userRole"));
				company.setCompanySign(rs.getInt("userSign"));
				return company;
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
			String sql = "select userId from userTable where userId = ?";
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

	//��˾��λע��
	public boolean companyLogin(String Name,String Id,String Pwd,int Role) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into userTable(userName,userId,userPwd,userRole,userSign) values(?,?,?,?,?)";
			//Ԥ����������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setString(1, Name);
			ps.setString(2, Id);
			ps.setString(3, Pwd);
			ps.setInt(4, Role);
			ps.setInt(5, 0);
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

	//���ҵ�ȫ����Ƹ��Ϣ �� ������������
	public ArrayList<Position> getPositionList(src.jobSystem.com.jobs.companyDao.CompanyPageModel companyPageModel) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Position> positions =null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			if(companyPageModel.getSearchText()!=null && !companyPageModel.getSearchText().equals("")) {
				String sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyPosition like ? or companyRequest like ? limit ?,? ";
				//����ClientPreparedStatement������
				ps = (ClientPreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, "%"+companyPageModel.getSearchText()+"%");
				ps.setString(2, "%"+companyPageModel.getSearchText()+"%");
				ps.setInt(3, (companyPageModel.getCompanyPageNo()-1)*companyPageModel.getCompanyPageSize());
				ps.setInt(4, companyPageModel.getCompanyPageSize());
			}else {
				String sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView limit ?,? ";
				//����ClientPreparedStatement������
				ps = (ClientPreparedStatement) conn.prepareStatement(sql);
				ps.setInt(1, (companyPageModel.getCompanyPageNo()-1)*companyPageModel.getCompanyPageSize());
				ps.setInt(2, companyPageModel.getCompanyPageSize());
			}
			//ִ��sql ���ؽ����
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				positions =new ArrayList<Position>();
			}
			while(rs.next()) {
				Position position = new Position();
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
				positions.add(position);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return positions;
	}

	//����һ���ж�������Ƹ��Ϣ �� ������������һ���ж�������Ƹ��Ϣ
	public long getPositionTotal(String searchText) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		long totalCount = 0;
		try {
			conn = JDBCUtil.getConnection();
			//׼��sql��䣬�����ִ��sql���
			if(searchText != null && !searchText.equals("")) {
				String sql = "select count(companyNumber) from PositionView where companyPosition like ? or companyRequest like ?";
				ps = (ClientPreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, "%"+searchText+"%");
				ps.setString(2, "%"+searchText+"%");
			}else {
				String sql = "select count(companyNumber) from PositionView";
				ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			}
			rs = ps.executeQuery();
			if(rs.next()) {
				totalCount = rs.getLong(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return totalCount;
	}

	//����ĳ���ض��Ĺ�˾��������Ƹ��Ϣ �� ɾ������Ƹ��Ϣ������˾id����  flag=0 ��������Ƹ��Ϣ��flag=1 ɾ������Ƹ��Ϣ
	public ArrayList<Position> getPutPositionList(String companyId,int flag){
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Position> positions =null;
		String sql = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			if(flag==0) {
				sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyId = ?";
			}else if(flag==1) {
				sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from companyTable where companyId = ? and companySign = 1";
			}
			//����ClientPreparedStatement������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setString(1, companyId);
			//ִ��sql ���ؽ����
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				positions =new ArrayList<Position>();
			}
			while(rs.next()) {
				Position position = new Position();
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
				positions.add(position);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return positions;
	}

	//����Ų�ѯ��Ƹ��Ϣ
	public Position findPutPositionMessageByNumber(String companyId,int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		Position position = null;   //��ǰ���Ų�ѯ����Ƹ��Ϣ�Ƿ����
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyId = ? and companyNumber = ?";
			//����ClientPreparedStatement������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setString(1, companyId);
			ps.setInt(2, companyNumber);
			//ִ��sql ���ؽ����
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
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return position;
	}

	//����ĳ���ض��Ĺ�˾��������Ƹ��Ϣ ��Ͷ�� �� ����ְԱ ��ͳ�����  flag=0 ��Ͷ�ݵ���Ƹ��Ϣ��flag=1 ����ְԱ����Ƹ��Ϣ
	public ArrayList<Position> getPositionSumMessageList(String companyId,int flag) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Position> positions =null;
		String sql = null;
		try {
			conn = JDBCUtil.getConnection();
			if(flag==0) {
				sql = "select companyNumber ,companyName ,companyPosition ,companyPerson ,companyPhone ,companyCount ,companyLearn ,companyRequest ,companyMoney ,companyAddress ,staffSum from PositionView join ( select PcompanyNumber ,count(PcompanyNumber) as staffSum from putTable group by PcompanyNumber ) t on companyNumber = PcompanyNumber and companyId = ?";
			}else if(flag==1) {
				sql = "select companyNumber ,companyName ,companyPosition ,companyPerson ,companyPhone ,companyCount ,companyLearn ,companyRequest ,companyMoney ,companyAddress ,staffSum from PositionView join ( select IcompanyNumber ,count(IcompanyNumber ) as staffSum from invitionTable group by IcompanyNumber  ) t on companyNumber = IcompanyNumber  and companyId = ?";
			}else if(flag==2) {
				sql = "select companyNumber ,companyName ,companyPosition ,companyPerson ,companyPhone ,companyCount ,companyLearn ,companyRequest ,companyMoney ,companyAddress ,staffSum from PositionView join ( select EcompanyNumber ,count(EcompanyNumber ) as staffSum from employTable group by EcompanyNumber  ) t on companyNumber = EcompanyNumber  and companyId = ?";
			}
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, companyId);
			//ִ��sql ���ؽ����
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				positions =new ArrayList<Position>();
			}
			while(rs.next()) {
				Position position = new Position();
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
				position.setStaffSum(rs.getInt("staffSum"));
				positions.add(position);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return positions;
	}

	//����ĳ���ض�ְԱͶ�ݵ���Ƹ��Ϣ
	public ArrayList<Position> getPutStaffList(int staffNumber,int flag) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Position> positions =null;
		String sql = null;
		try {
			conn = JDBCUtil.getConnection();
			if(flag == 0) {
				sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyNumber in (select PcompanyNumber from putTable where PstaffNumber = ?)";
			}else if(flag == 1) {
				sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyNumber in (select IcompanyNumber from invitionTable where IstaffNumber = ?)";
			}
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, staffNumber);
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				positions =new ArrayList<Position>();
			}
			while(rs.next()) {
				Position position = new Position();
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
				positions.add(position);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return positions;
	}

	//�޸ĸ�����Ϣ
	public boolean changeCompanyMessage(Company company) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userName=? where userId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, company.getCompanyName());
			ps.setString(2, company.getCompanyId());
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
	public boolean changeCompanyPwd(String id,String pwd,String newpwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//1����ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//2��׼��sql���
			String sql = "update userTable set userPwd = ? where userId = ? and  userPwd = ?";
			//3������ClientPreparedStatement������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//4����sql������ֵ
			ps.setString(1, newpwd);
			ps.setString(2, id);
			ps.setString(3, pwd);
			//5��ִ��sql
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//6���ͷ���Դ
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//������Ƹ��Ϣ
	public boolean addPositionMessage(Position position) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "insert into companyTable (companyId,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress,companySign,companyFlag) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			//Ԥ����������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setString(1, position.getCompanyId());
			ps.setString(2, position.getCompanyName());
			ps.setString(3, position.getCompanyPosition());
			ps.setString(4, position.getCompanyPerson());
			ps.setString(5, position.getCompanyPhone());
			ps.setString(6, position.getCompanyCount());
			ps.setString(7, position.getCompanyLearn());
			ps.setString(8, position.getCompanyRequest());
			ps.setString(9, position.getCompanyMoney());
			ps.setString(10, position.getCompanyAddress());
			ps.setInt(11, position.getCompanySign());
			ps.setInt(12, position.getCompanyFlag());
			//ִ��sql
			int counts = ps.executeUpdate();   //count���ڼ�¼�Ƿ񷢲��ɹ�
			if(counts > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//�߼���ɾ����Ƹ��Ϣ
	public boolean delePositionMessage(String companyId,int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update companyTable set companySign = ? where companyNumber = ? and  companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, companyNumber);
			ps.setString(3, companyId);
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

	//������ɾ����Ƹ��Ϣ
	public boolean deletePositionMessage(String companyId,int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "delete from companyTable where companyId = ? and companyNumber = ?";
			//����ClientPreparedStatement������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//��sql������ֵ
			ps.setString(1, companyId);
			ps.setInt(2, companyNumber);
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

	//�޸���Ƹ��Ϣ
	public boolean changePositionMessage(Position position) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "update companyTable set companyName=?,companyPosition=?,companyPerson=?,companyPhone=?,companyCount=?,companyLearn=?,companyRequest=?,companyMoney=?,companyAddress=? where companyId = ? and companyNumber = ?";
			//Ԥ����������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setString(1, position.getCompanyName());
			ps.setString(2, position.getCompanyPosition());
			ps.setString(3, position.getCompanyPerson());
			ps.setString(4, position.getCompanyPhone());
			ps.setString(5, position.getCompanyCount());
			ps.setString(6, position.getCompanyLearn());
			ps.setString(7, position.getCompanyRequest());
			ps.setString(8, position.getCompanyMoney());
			ps.setString(9, position.getCompanyAddress());
			ps.setString(10, position.getCompanyId());
			ps.setInt(11, position.getCompanyNumber());
			//ִ��sql
			int counts = ps.executeUpdate();
			if(counts > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//�ָ���Ƹ��Ϣ
	public boolean regainPositionMessage(String companyId,int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update companyTable set companySign = ? where companyNumber = ? and  companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setInt(2, companyNumber);
			ps.setString(3, companyId);
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

	//�����Ƿ��ظ�����ְԱ
	public boolean selectInvitionMessage(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select IstaffNumber from invitionTable where IcompanyNumber = ? and IstaffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			rs = ps.executeQuery();
			//�ж�¼�õ���Ϣ�Ƿ����
			while(rs.next()) {
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

	//��ְԱ�ı������ְԱ����
	public boolean invitionStaff(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//��ȡ���ݿ�����
			conn = JDBCUtil.getConnection();
			//׼��sql���
			String sql = "insert into invitionTable(IcompanyNumber ,IstaffNumber ) values(?,?)";
			//Ԥ����������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			//ִ��sql
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

	//��ְԱ�ı��ɾ��������Ϣ
	public boolean deleteInvitionStaff(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from invitionTable where IcompanyNumber = ? and IstaffNumber = ?";
			//Ԥ����������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			//ִ��sql
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

	//�����Ƿ��ظ�¼��ְԱ
	public boolean selectEmployMessage(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select EstaffNumber from employTable where EcompanyNumber = ? and EstaffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			rs = ps.executeQuery();
			//�ж�¼�õ���Ϣ�Ƿ����
			while(rs.next()) {
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

	//���Ҹ�ְԱ�Ƿ�������˾¼��
	public boolean selectEmployStaff(int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select EstaffNumber from employTable where EstaffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, staffNumber);
			rs = ps.executeQuery();
			while(rs.next()) {
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

	//��ְԱ�ı����Ƹ¼��
	public boolean employStaff(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into employTable(EcompanyNumber ,EstaffNumber ) values(?,?)";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			//ִ��sql
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

	//��ְԱ�ı��ɾ��¼����Ϣ
	public boolean delStaff(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from employTable where EcompanyNumber = ? and EstaffNumber = ?";
			//Ԥ����������
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//�����Ÿ�ֵ
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			//ִ��sql
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

}

