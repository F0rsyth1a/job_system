package src.jobSystem.com.jobs.companyDao;

public class CompanyPageModel {
	CompanyDao companyDao = new CompanyDao();
	private String SearchTexts;      //�����������
	
	private int companyPageNo;      //�ڼ�ҳ
	private int companyPageSize;    //һҳ�ж�����
	
	private int companyTotalCount;  //������
	private int companyTotalPage;   //���ж���ҳ
	
	public int getCompanyPageNo() {
		return companyPageNo;
	}
	public void setCompanyPageNo(int companyPageNo) {
		this.companyPageNo = companyPageNo;
	}
	public int getCompanyPageSize() {
		return companyPageSize;
	}
	public void setCompanyPageSize(int companyPageSize) {
		this.companyPageSize = companyPageSize;
	}
	public int getCompanyTotalCount() {
		return companyTotalCount;
	}
	public void setCompanyTotalCount(int companyTotalCount) {
		this.companyTotalCount = companyTotalCount;
	}
	public int getCompanyTotalPage() {
		return companyTotalPage;
	}
	public void setCompanyTotalPage(int companyTotalPage) {
		this.companyTotalPage = companyTotalPage;
	}
	public String getSearchText() {
		return SearchTexts;
	}
	
	public void setSearchText(String SearchText) {
		this.SearchTexts=SearchText;
		this.companyPageNo = 1;
		this.companyTotalCount = (int)companyDao.getPositionTotal(SearchText);
		if(companyTotalCount%companyPageSize == 0) {
			companyTotalPage = companyTotalCount/companyPageSize;
		}else {
			companyTotalPage = companyTotalCount/companyPageSize+1;
		}
		if(companyTotalCount == 0) {
			companyTotalPage = 1;
		}
	}
	
	//���췽��
	public CompanyPageModel(int companyPageSize) {
		this.companyPageNo = 1;
		this.companyPageSize = companyPageSize;
		this.companyTotalCount = (int)companyDao.getPositionTotal("");
		if(companyTotalCount%companyPageSize == 0) {
			companyTotalPage = companyTotalCount/companyPageSize;
		}else {
			companyTotalPage = companyTotalCount/companyPageSize+1;
		}
		if(companyTotalCount == 0) {
			companyTotalPage = 1;
		}
	}
	
	//��һҳ
	public void next() {
		if(companyPageNo == companyTotalPage) {
			
		}else {
			companyPageNo++;
		}
	}
	
	//��һҳ
	public void preve() {
		if(companyPageNo == 1) {
			
		}else {
			companyPageNo--;
		}
	}
	
	//��ҳ
	public void first() {
		companyPageNo = 1;
	}
	
	//βҳ
	public void last() {
		companyPageNo = companyTotalPage;
	}
	
}

