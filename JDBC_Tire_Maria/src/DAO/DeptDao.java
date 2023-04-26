package DAO;
/*
 1. DB연결
 2. CRUD 함수
 3. 기본 5가지 + 알파 (조건검색, 문자열 검색)
 3.1 전체조회, 조건조회, insert, update, delete 함수
 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.Dept;
import UTILS.SingletonHelper;

public class DeptDao {
		

		
		//1. 전체조회 >> select 결과 >> return multi row (Dept 객체 여러개)
		//select deptno, dname, loc from dept 
		
		public List<Dept> getDeptAllList(){
			//여러건의 데이터 (Dept 객체 여러개)
			List<Dept> deptlist = new ArrayList();
			//deptlist.add(new Dept())
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
					conn = SingletonHelper.getConnection("mariadb");
					String sql = "select deptno, dname from dept";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						Dept dept = new Dept(); //하나의 row값을 담을 수 있는 DTO 객체를 생성
						dept.setDeptno(rs.getInt("deptno"));
						dept.setDname(rs.getString("dname"));
						deptlist.add(dept); // ArrayList에 객체 담기... row 5개 >> Dept 객체 5개 add
					}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				SingletonHelper.close(rs);
				SingletonHelper.close(pstmt);
			}			
			return deptlist;
		}
		
		
		
		//2. 조건조회 >> select 결과(where deptno = ?) >> return single row (Dept 객체 한개)
		//select deptno, dname, loc from dept where deptno=?
		
		public Dept getDept() {
			Dept dept = new Dept();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = SingletonHelper.getConnection("mariadb");
				String sql = "select deptno, dname from dept where deptno=?";
				Scanner sc = new Scanner(System.in);
				

				pstmt = conn.prepareStatement(sql);
				System.out.println("조회할 테이블의 부서번호을 입력하세요");
				int deptno = Integer.parseInt(sc.nextLine());
				pstmt.setInt(1, deptno);

				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					dept.setDeptno(rs.getInt("deptno"));
					dept.setDname(rs.getString("dname"));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				SingletonHelper.close(rs);
				SingletonHelper.close(pstmt);
			}
			return dept;

		}

		//3. 데이터 삽입 >> parameter (Dept 객체) >> return 정수
		//insert into dept(deptno, dname, loc) values(?,?,?)
		
		public void insertDept( ) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			//ResultSet rs = null; -insert에서는 필요없다.
			int rowcount = 0;

			try {
				conn = SingletonHelper.getConnection("mariadb");
				String sql = "insert into dept(deptno, dname) values(?,?)";
				Scanner sc = new Scanner(System.in);
				pstmt = conn.prepareStatement(sql);
				
				System.out.println("사번을 입력하세요");
				int deptno = Integer.parseInt(sc.nextLine());
				System.out.println("이름을 입력하세요");
				String dname = sc.nextLine();
				
				pstmt.setInt(1, deptno);
				pstmt.setString(2, dname);

				rowcount = pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				SingletonHelper.close(pstmt);
			}

		}
		
		
		//4. 데이터 수정 >> parameter (Dept 객체) >> return 정수
		//update dept set dname=?, loc=? where dpetno=?
		
		public void updateDept() {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = SingletonHelper.getConnection("mariadb");
				String sql = "update dept set dname=? where deptno=?";
				Scanner sc = new Scanner(System.in);
				
				System.out.println("수정할 데이터의 사번을 입력하세요");
				int deptno = Integer.parseInt(sc.nextLine());
				System.out.println("수정할 데이터의 이름을 입력하세요");
				String dname = sc.nextLine();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(2, deptno);
				pstmt.setString(1, dname);

				
				rs = pstmt.executeQuery();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				SingletonHelper.close(rs);
				SingletonHelper.close(pstmt);
			}

		}
				
		
		//5. 데이터 삭제 >> parameter (deptno) >> return 함수
		//delete from dept where deptno=?
		
		public void deleteDept() {

			Dept dept = new Dept();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = SingletonHelper.getConnection("mariadb");
				String sql = "delete from dept where deptno=?";
				Scanner sc = new Scanner(System.in);

				System.out.println("삭제할 데이터의 사번을 입력하세요");
				int deptno = Integer.parseInt(sc.nextLine());
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, deptno);

				rs = pstmt.executeQuery();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				SingletonHelper.close(rs);
				SingletonHelper.close(pstmt);
			}

		}
		
		
	
}
