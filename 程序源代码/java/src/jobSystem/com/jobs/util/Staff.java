package src.jobSystem.com.jobs.util;

public class Staff {
	//����
	private int staffNumber;      //���
	private String staffName;     //����
	private String staffId;       //�˺�
	private String staffPwd;      //����
	private String staffCard;     //���֤
	private String staffAddress;  //��ַ
	private String staffPhone;    //�绰
	private String staffLearn;    //ѧ��
	private String staffJob;      //ְ��
	private int staffSign;        //ɾ����־
	
	//���췽��
	public Staff() {}
	public Staff(String staffName, String staffId, String staffPwd) {
		this.staffName = staffName;
		this.staffId = staffId;
		this.staffPwd = staffPwd;
	}
	
	//get��set����
	public int getStaffNumber() {
		return staffNumber;
	}
	public void setStaffNumber(int staffNumber) {
		this.staffNumber = staffNumber;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffPwd() {
		return staffPwd;
	}
	public void setStaffPwd(String staffPwd) {
		this.staffPwd = staffPwd;
	}
	public String getStaffCard() {
		return staffCard;
	}
	public void setStaffCard(String staffCard) {
		this.staffCard = staffCard;
	}
	public String getStaffAddress() {
		return staffAddress;
	}
	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}
	public String getStaffPhone() {
		return staffPhone;
	}
	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}
	public String getStaffLearn() {
		return staffLearn;
	}
	public void setStaffLearn(String staffLearn) {
		this.staffLearn = staffLearn;
	}
	public String getStaffJob() {
		return staffJob;
	}
	public void setStaffJob(String staffJob) {
		this.staffJob = staffJob;
	}
	public int getStaffSign() {
		return staffSign;
	}
	public void setStaffSign(int staffSign) {
		this.staffSign = staffSign;
	}
	
}
