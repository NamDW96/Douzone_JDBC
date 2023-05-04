package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.DualNumber;
import DTO.Emp_Hol;
import UTILS.DBConnection;
import UTILS.DBdisConnection;

public class HoltDAO {

	public void getAllHOlList() {
		List<Emp_Hol> hollist;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "select holnum, empno, to_char(start_date,'YYYY-MM-dd'), to_char(end_date,'YYYY-MM-dd'), category from emp_hol";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			hollist = new ArrayList<>();

			while (rs.next()) {
				Emp_Hol eh = new Emp_Hol();
				eh.setHolnum(rs.getInt("holnum"));
				eh.setEmpno(rs.getString("empno"));
				eh.setStart_date(rs.getString("to_char(start_date,'YYYY-MM-dd')"));
				eh.setEnd_date(rs.getString("to_char(end_date,'YYYY-MM-dd')"));
				eh.setCategory(rs.getString("category"));
				hollist.add(eh);
			}
			if (hollist.size()==0) {
				System.out.println("데이터가 존재하지 않습니다!");
			} else {
				for (Emp_Hol data : hollist) {
					System.out.println(data.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}

	}

	// 내 휴가내역 가져요기 - 다중row값을 가져와서 일반 select로는 안됐던거간거같다
	public void getMysList(String ads) {
		List<Emp_Hol> hollist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "select * from hol_emp where empno=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ads/* 내아이디 */);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Emp_Hol eh = new Emp_Hol();
				eh.setHolnum(rs.getInt("holnum"));
				eh.setEmpno(rs.getString("empno"));
				eh.setStart_date(rs.getString("start_date"));
				eh.setEnd_date(rs.getString("end_date"));
				eh.setHol_term(rs.getInt("hol_term"));
				eh.setCategory(rs.getString("category"));
				hollist.add(eh);
			}
			if (hollist.size()==0) {
				System.out.println("데이터가 존재하지 않습니다!");
			} else {
				for (Emp_Hol data : hollist) {
					System.out.println(data.toString());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}

		
	}

	public void insertDataHOl() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount = 0;

		try {
			conn = DBConnection.getConnection();
			String sql = "insert into emp_hol(holnum,empno,start_date, end_date, category) values(emphol_num.nextval,?,?,?,?)";
			Scanner sc = new Scanner(System.in);
			pstmt = conn.prepareStatement(sql);

			System.out.println("휴가를 신청합니다.");
			System.out.println("휴가 시작날짜를 입력하세요");
			String start_date = sc.nextLine();
			System.out.println("휴가 마지막날짜를 입력하세요");
			String end_date = sc.nextLine();
			System.out.println("휴가의 유형을 입력하세요.");
			String category = sc.nextLine();

			String loginId = "dd";
			pstmt.setString(1, loginId);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
			pstmt.setString(4, category);

			rowcount = pstmt.executeUpdate();
			System.out.println("데이터가 입력되었습니다. : " + rowcount);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(pstmt);
		}
	}

	public void deleteHolCheck() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DualNumber dn = new DualNumber();
		try {
			conn = DBConnection.getConnection();

			String sql2 = "select (select start_date from emp_hol where holnum=?) - sysdate as HOLIDAY from dual";
			Scanner sc = new Scanner(System.in);
			System.out.println("취소할 휴가의 휴가번호를 입력하세요");
			int holnum = Integer.parseInt(sc.nextLine());
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, holnum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dn.setDualnum(rs.getInt("HOLIDAY"));
			}
			if (dn.getDualnum() > 0) {
				deleteEmp(holnum);
			} else {
				System.out.println("삭제할 수 없는 휴가입니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}
	}

	public void deleteEmp(int holnum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "delete from emp_hol where holnum=?";
			Scanner sc = new Scanner(System.in);

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, holnum);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBdisConnection.close(rs);
			DBdisConnection.close(pstmt);
		}
		System.out.println("삭제되었습니다.");
	}

}
