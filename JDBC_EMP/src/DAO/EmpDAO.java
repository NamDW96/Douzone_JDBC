package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.Emp;
import UTILS.SingletonHelper;

public class EmpDAO {

	
	public void getEmpAllList(){
		
		List<Emp> emplist = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			
			String sql = "select * from emp";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getString("hiredate"));
				emp.setSal(rs.getInt("sal"));
				emp.setComm(rs.getInt("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				emplist.add(emp);
			} 							
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		for(Emp data : emplist) {
			System.out.println(data.toString());
		}
	}
	
	
	public void getEmp() {
		Emp emp = new Emp();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select * from emp where empno=?";
			Scanner sc = new Scanner(System.in);
			
			System.out.println("조회할 테이블의 사번을 입력하세요");
			int enpno = Integer.parseInt(sc.nextLine());
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, enpno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getString("hiredate"));
				emp.setSal(rs.getInt("sal"));
				emp.setComm(rs.getInt("comm"));
				emp.setDeptno(rs.getInt("deptno"));
			}
			System.out.println(emp.toString());
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

	}
	
	
	public void insertDataEmp() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount = 0;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) values(?,?,?,?,?,?,?,?)";
			Scanner sc = new Scanner(System.in);
			pstmt = conn.prepareStatement(sql);

			System.out.println("사번을 입력하세요");
			int empno = Integer.parseInt(sc.nextLine());
			System.out.println("이름을 입력하세요");
			String ename = sc.nextLine();
			System.out.println("직종을 입력하세요");
			String job = sc.nextLine();
			System.out.println("사수번호를 입력하세요");
			int mgr = Integer.parseInt(sc.nextLine());
			System.out.println("채용일자 입력하세요");
			String hiredate = sc.nextLine();
			System.out.println("연봉을 입력하세요");
			int sal = Integer.parseInt(sc.nextLine());
			System.out.println("보너스 입력하세요");
			int comm = Integer.parseInt(sc.nextLine());
			System.out.println("부서번호 입력하세요");
			int deptno = Integer.parseInt(sc.nextLine());

			pstmt.setInt(1, empno);
			pstmt.setString(2, ename);
			pstmt.setString(3, job);
			pstmt.setInt(4, mgr);
			pstmt.setString(5, hiredate);
			pstmt.setInt(6, sal);
			pstmt.setInt(7, comm);
			pstmt.setInt(8, deptno);

			rowcount = pstmt.executeUpdate();
			System.out.println("데이터가 입력되었습니다. 입력된 값의 수 : " + rowcount);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}

	}		
	
	public void deleteEmp() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete from emp where empno=?";
			Scanner sc = new Scanner(System.in);

			System.out.println("삭제할 데이터의 사번을 입력하세요");
			int empno = Integer.parseInt(sc.nextLine());
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

	}
	
	public void updateEmp() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update emp set ename=? , job=? , sal=? , hiredate=? where empno=?";
			Scanner sc = new Scanner(System.in);
			
			System.out.println("수정을 원하는 사번을 입력하세요");
			int empno = Integer.parseInt(sc.nextLine());
			System.out.println("수정할 데이터의 이름을 입력하세요");
			String ename = sc.nextLine();
			System.out.println("수정할 데이터의 직종을 입력하세요");
			String job = sc.nextLine();
			System.out.println("수정할 데이터의 급여을 입력하세요");
			int sal = Integer.parseInt(sc.nextLine());
			System.out.println("수정할 데이터의 입사일을 입력하세요");
			String hiredate = sc.nextLine();
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(5, empno);
			pstmt.setString(1, ename);
			pstmt.setString(2, job);
			pstmt.setInt(3, sal);
			pstmt.setString(4, hiredate);

			
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

	}
	
	public void searchEmpByName() {

		Emp emp = new Emp();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select * from emp where ename like  ?";
			Scanner sc = new Scanner(System.in);
			
			System.out.println("이름으로 검색합니다.");
			System.out.println("조회할 사원의 이름을 입력하세요");
			String ename = sc.nextLine();
			String searchname ="%"+ename+"%";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchname);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getString("hiredate"));
				emp.setSal(rs.getInt("sal"));
				emp.setComm(rs.getInt("comm"));
				emp.setDeptno(rs.getInt("deptno"));
			}
			System.out.println(emp.toString());
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
	}
}
	

