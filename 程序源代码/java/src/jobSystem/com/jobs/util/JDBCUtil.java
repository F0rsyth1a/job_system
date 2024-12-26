package src.jobSystem.com.jobs.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;

public class JDBCUtil {

	//���ݿ�������
	public static final String DRIVER = "com.mysql.jdbc.Driver";

	/*URL
	 * jdbc:mysql:Э��
	 * localhost:�������ݿ�
	 * 3306�˿ں�
	 * job:���ݿ���
	 */
	public static final String URL = "jdbc:mysql://localhost:3306/jobsdb";
	//�û���
	public static final String USERNAME = "root";
	//����
	public static final String PASSWORD = "root123";

	//��̬����飬ÿ������ʱ�����Ȼ���ش˴���
	static {
		try {
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//�ṩһ����ȡ���ӷ���
	public static ConnectionImpl getConnection() {
		ConnectionImpl conn = null;
		try {
			conn = (ConnectionImpl) DriverManager.getConnection(URL,USERNAME,PASSWORD);

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//�ṩһ���ͷ����ӷ���
	public static void closeConn(ConnectionImpl conn, ClientPreparedStatement ps , ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
