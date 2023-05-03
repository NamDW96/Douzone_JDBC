package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.D_Dept;
import DTO.D_Emp;
import UTILS.DBConnection;
import UTILS.DBdisConnection;

public class DeptDAO {

	public void getDeptAllList(){
		List<D_Dept> deptlist = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
				conn = DBConnection.getConnection();
				String sql = "select * from d_dept";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					D_Dept dept = new D_Dept(); 
					dept.setDeptno(rs.getInt("deptno"));
					dept.setDname(rs.getString("dname"));
					dept.setLoc(rs.getString("loc"));
					deptlist.add(dept); 
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}			

		if (deptlist == null) {
			System.out.println("데이터가 존재하지 않습니다!");
		} else {
			for (D_Dept data : deptlist) {
				System.out.println(data.toString());
			}
		}
	}
	
	
	
	public void getDept() {
		D_Dept dept = new D_Dept();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "select deptno, dname, loc from d_dept where deptno=?";
			System.out.println("조회할 테이블의 사번을 입력하세요");
			Scanner sc = new Scanner(System.in);

			
			int deptno = Integer.parseInt(sc.nextLine());
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
				
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}
		if(dept != null) {
			System.out.println(dept.toString());
		}else  {
			System.out.println("데이터를 조회하지 못했습니다. 다시 시도해라.");
		}

	}

	
	public void insertDept( ) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount = 0;

		try {
			conn = DBConnection.getConnection();
			String sql = "insert into d_dept(deptno, dname, loc) values(?,?,?)";
			Scanner sc = new Scanner(System.in);
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("사번을 입력하세요");
			int deptno = Integer.parseInt(sc.nextLine());
			System.out.println("이름을 입력하세요");
			String dname = sc.nextLine();
			System.out.println("지역을 입력하세요");
			String loc = sc.nextLine();
			
			pstmt.setInt(1, deptno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);
			rowcount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(pstmt);
		}

	}
	
	
	public void updateDept() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "update d_dept set dname=?, loc=? where deptno=?";
			Scanner sc = new Scanner(System.in);
			
			System.out.println("수정할 데이터의 사번을 입력하세요");
			int deptno = Integer.parseInt(sc.nextLine());
			System.out.println("수정할 데이터의 이름을 입력하세요");
			String dname = sc.nextLine();
			System.out.println("수정할 데이터의 locs을 입력하세요");
			String loc = sc.nextLine();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(3, deptno);
			pstmt.setString(1, dname);
			pstmt.setString(2, loc);

			rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}

	}
			
	
	
	public void deleteDept() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "delete from d_dept where deptno=?";
			Scanner sc = new Scanner(System.in);

			System.out.println("삭제할 데이터의 사번을 입력하세요");
			int deptno = Integer.parseInt(sc.nextLine());
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, deptno);

			rs = pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}

	}
}
