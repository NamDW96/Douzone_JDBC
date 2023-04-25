package DTO;
import java.util.List;

import DAO.DeptDao;
/*
 App 서버 구성 (MVC 패턴)
 잘 하는 것만 해 
 
 pojo(plain old java object) - 순수한 자바 객체
 
 Model (java) >> DTO(데이터를 담을 수 있는 클래스), DAO(데이터를 처리할 수 있는 클래스(JDBC API)) , SERVICE
 
 View (UI) >> html, jsp, 등등 >> 현재 console 화면제어 (현재는 main에서)
 
 Controller >> 중앙제어 (통제) >> 웹의 접근 통제 >> 요청과 응답처리 >> JAVA >> Servlet(웹용 자바파일) (현재는 main에서)
 
 
 
 */


public class Program {
	public static void main(String[] args) {

		/*
		Dept dept = new Dept();
		dept.setDeptno(100);
		dept.setDname("IT");
		dept.setLoc("Seoul");
		
		System.out.println(dept.toString());
		
		Emp emp = new Emp(200, "김유신");
		System.out.println(emp.toString());
		*/
		
		DeptDao deptDao = new DeptDao();
		
//		List<Dept> depts = deptDao.getDeptAllList();
//		for(Dept dept : depts) {
//			System.out.println(dept.toString());
//		}
		
		
		
		//데이터 조회
		Dept dt = new Dept();
		
//		dt = deptDao.getDept();
//		System.out.println(dt.toString());
		
//		//데이터 삭제
//		deptDao.deleteDept();
//		
//		//데이터 삽입
//		deptDao.insertDept();
//		
//		//데이터 수정
		deptDao.updateDept();
	
	}
}
