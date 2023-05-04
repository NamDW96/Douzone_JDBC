package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.D_Emp;
import UTILS.DBConnection;
import UTILS.DBdisConnection;


public class EmpDAO {

	
	public void getAllEmpList() {

		List<D_Emp> emplist = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select empno, ename, to_char(birth,'YYYY-MM-DD'), to_char(hiredate,'YYYY-MM-DD'), emp_rank,sal,  password, deptno, mgr from d_emp";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				D_Emp demp = new D_Emp();
				demp.setEmpno(rs.getString(1));
				demp.setEname(rs.getString(2));
				demp.setBirth(rs.getString(3));
				demp.setHiredate(rs.getString(4));
				demp.setEmp_rank(rs.getString(5));
				demp.setSal(rs.getInt(6));
				demp.setPassword(rs.getString(7));
				demp.setDeptno(rs.getInt(8));
				demp.setMgr(rs.getString(9));
				emplist.add(demp);
			}
			if (emplist.size()==0) {
				System.out.println("데이터가 존재하지 않습니다!");
			} else {
				for (D_Emp data : emplist) {
					System.out.println(data.toString());
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBdisConnection.close(pstmt);
			DBdisConnection.close(rs);
		}
	}
	
	public void getEmp() {
		D_Emp demp = new D_Emp();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "select empno, ename, to_char(birth,'YYYY-MM-DD'), to_char(hiredate,'YYYY-MM-DD'), emp_rank,sal,  password, deptno, mgr from d_emp where empno=?";
			System.out.println("조회할 테이블의 사번을 입력하세요");
			Scanner sc = new Scanner(System.in);
					
			String empno = sc.nextLine();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				demp.setEmpno(rs.getString("empno"));
				demp.setEname(rs.getString("ename"));
				demp.setBirth(rs.getString("to_char(birth,'YYYY-MM-DD')"));
				demp.setHiredate(rs.getString("to_char(hiredate,'YYYY-MM-DD')"));
				demp.setEmp_rank(rs.getString("emp_rank"));
				demp.setSal(rs.getInt("sal"));
				demp.setDeptno(rs.getInt("deptno"));
				demp.setMgr(rs.getString("mgr"));
			}
				System.out.println(demp.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(pstmt);
			DBdisConnection.close(rs);
		}
	
	}
	
	
	public void insertDataEmp() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount = 0;

		try {
			conn = DBConnection.getConnection();
			String sql = "insert into d_emp(empno, ename, birth, hiredate, emp_rank, sal, password, deptno, mgr) values(?,?,?,?,?,?,?,?,?)";
			Scanner sc = new Scanner(System.in);
			pstmt = conn.prepareStatement(sql);

			System.out.println("사번을 입력하세요");
			String empno = sc.nextLine();
			System.out.println("이름을 입력하세요");
			String ename = sc.nextLine();
			System.out.println("생년월일을 입력하세요");
			String birth = sc.nextLine();			
			System.out.println("입사일 입력하세요");
			String hiredate = sc.nextLine();
			System.out.println("직급을 입력하세요");
			String emp_rank = sc.nextLine();
			System.out.println("급여를 입력하세요");
			int sal = Integer.parseInt(sc.nextLine());
			System.out.println("비밀번호를 입력하세요");
			String password = sc.nextLine();
			System.out.println("부서번호 입력하세요");
			int deptno = Integer.parseInt(sc.nextLine());
			System.out.println("사수번호를 입력하세요");
			String mgr = sc.nextLine();
			
			
			pstmt.setString(1, empno);
			pstmt.setString(2, ename);
			pstmt.setString(3, birth);
			pstmt.setString(4, hiredate);
			pstmt.setString(5, emp_rank);
			pstmt.setInt(6, sal);
			pstmt.setString(7, password);
			pstmt.setInt(8, deptno);
			pstmt.setString(9, mgr);

			rowcount = pstmt.executeUpdate();
			System.out.println("데이터가 입력되었습니다. 입력된 값의 수 : " + rowcount);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			DBdisConnection.close(pstmt);
		}
	}		
	
	public void deleteEmp() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "delete from d_emp where empno=?";
			Scanner sc = new Scanner(System.in);

			System.out.println("삭제할 데이터의 사번을 입력하세요우");
			String empno = sc.nextLine();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, empno);

			rs = pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}

	}
	
	public void updateEmp() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "update emp set ename=? , mgr=? , sal=? , emp_rank=? where empno=?";
			Scanner sc = new Scanner(System.in);
			StringBuilder sb = new StringBuilder();
			
			System.out.println("수정을 원하는 사번을 입력하세요");
			int empno = Integer.parseInt(sc.nextLine());
				
			System.out.println("수정할 데이터의 이름을 입력하세요");
			System.out.println("이름의 수정을 원하지 않으실경우 0 을 입력해주세요");
			String ename = sc.nextLine();
			
			System.out.println("수정할 데이터의 직속상사번호 입력하세요");
			int mgr = Integer.parseInt(sc.nextLine());

			System.out.println("수정할 데이터의 급여을 입력하세요");
			int sal = Integer.parseInt(sc.nextLine());
			
			System.out.println("수정할 데이터의 직급을 입력하세요");
			String emp_rank = sc.nextLine();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(5, empno);
			pstmt.setString(1, ename);
			pstmt.setInt(2, mgr);
			pstmt.setInt(3, sal);
			pstmt.setString(4, emp_rank);

			
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}

	}
	
	public void searchEmpByName() {

		D_Emp demp = new D_Emp();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
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
				demp.setEmpno(rs.getString("empno"));
				demp.setEname(rs.getString("ename"));
				demp.setBirth(rs.getString("to_char(birth,'YYYY-MM-DD')"));
				demp.setHiredate(rs.getString("to_char(hiredate,'YYYY-MM-DD')"));
				demp.setEmp_rank(rs.getString("emp_rank"));
				demp.setSal(rs.getInt("sal"));
				demp.setDeptno(rs.getInt("deptno"));
				demp.setMgr(rs.getString("mgr"));
			}
			System.out.println(demp.toString());
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}
	}
		
}


/*
 *try {
			conn = DBConnection.getConnection();
			String sql = "update emp set ename=? , mgr=? , sal=? , emp_rank=? where empno=?";
			Scanner sc = new Scanner(System.in);
			StringBuilder sb = new StringBuilder();
			
			System.out.println("수정을 원하는 사번을 입력하세요");
			int empno = Integer.parseInt(sc.nextLine());
				
			System.out.println("수정할 데이터의 이름을 입력하세요");
			System.out.println("이름의 수정을 원하지 않으실경우 0 을 입력해주세요");
			String ename = sc.nextLine();
				if(!ename.equals("0")) {
					sb.append("ename=?");
				}
			System.out.println("수정할 데이터의 직속상사번호 입력하세요");
			int mgr = Integer.parseInt(sc.nextLine());
				if(mgr!=0) {
					sb.append("mgr=?");
				}
			
			System.out.println("수정할 데이터의 급여을 입력하세요");
			int sal = Integer.parseInt(sc.nextLine());
				if(sal!=0) {
					sb.append("sal=?");
				}
			
			System.out.println("수정할 데이터의 직급을 입력하세요");
			String emp_rank = sc.nextLine();
				if(!ename.equals("0")) {
					sb.append("ename=?");
				}
				*/
