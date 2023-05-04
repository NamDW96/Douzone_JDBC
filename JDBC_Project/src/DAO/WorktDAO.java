  package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.Emp_Attend;
import UTILS.DBConnection;
import UTILS.DBdisConnection;

public class WorktDAO {
	
	public void getAttendList() {

		List<Emp_Attend> attlist = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		String sql = "select empno, attend_date, to_char(attend_stime,'HH24:MI:SS'), to_char(attend_etime,'HH24:MI:SS') from Emp_Attend";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Emp_Attend ea = new Emp_Attend();
				ea.setEmpno(rs.getString("empno"));
				ea.setAttend_date(rs.getString("attend_date"));
				ea.setAttend_stime(rs.getString("to_char(attend_stime,'HH24:MI:SS')"));
				ea.setAttend_etime(rs.getString("to_char(attend_etime,'HH24:MI:SS')"));
				attlist.add(ea);
			}
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBdisConnection.close(pstmt);
			DBdisConnection.close(rs);
		}

		if (attlist.size()==0) {
			System.out.println("데이터가 존재하지 않습니다!");
		} else {
			for (Emp_Attend data : attlist) {
				System.out.println(data.toString());
			}
		}
	}
	
	public void insertStime() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount = 0;

		try {
			conn = DBConnection.getConnection();
			String sql = "insert into emp_attend(empno, attend_date, attend_stime) values(?,to_char(sysdate,'YY-MM-DD'),sysdate)";
			pstmt = conn.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("사용자를 입력해주세요");
			String loginId = sc.nextLine();
			pstmt.setString(1, loginId);

			rowcount = pstmt.executeUpdate();
			System.out.println("출근 처리되었습니다. 입력된 값의 수 : " + rowcount);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(pstmt);
		}
	}
	
	
	public void insertEtime() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount = 0;

		try {
			conn = DBConnection.getConnection();
			String sql = "update emp_attend set attend_etime=sysdate where empno=? and attend_date=to_char(sysdate,'YY-MM-DD')";
			pstmt = conn.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("사용자를 입력해주세요");
			String loginId = sc.nextLine();
			pstmt.setString(1, loginId);
			
			rowcount = pstmt.executeUpdate();
			System.out.println("데이터가 입력되었습니다. 입력된 값의 수 : " + rowcount);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(pstmt);
		}
	}
	
}
