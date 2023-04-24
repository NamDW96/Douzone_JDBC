import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
JDBC 작업

 1.select
 1.1 결과 집합 생성
 
 
 2.insert, update, delete
 2.1 결과 집합 생성 없어요(X) >> resultSet (X)
 2.2 반영 결과를 리턴 (반영된 행의 수를 리턴) >> update 5건 수정 >> return 5;
 
 ex)
 update emp set sal = 0; >> 실행 >> update 14건 >> return 14
 update emp set sal = 100; where empno = 9999 >> update 0건  >> return 0
 
 결과를 받아서 자바코드 로직처리
 
 Today key Point
 1. Oracle DML (developer, Cmd (sqlplus), Tool) 작업 commit or rollback 강제
 2. JDBC API 사용해서 Oracle의 DML 작업을 수행하면 >> default >> auto commit >> 실반영
 3. JDBC API 사용해서 JAVA 코드에서 delete from emp 실행 >> JDBC 자동 commit >> 실반영
 4. JDBC API는 옵션을 통해서 commit, rollback 을 강제하는 방법을 제공
 
 begin 
 	A계좌 인출 (update ..
 	
 	B계좌 입금 (update ..
 end
 -> A 와 b의 전체성공 or 전체실패
 논리적인 단위로 transaction
 commit or rollback의 의미
 
 >>업무 처리 >> JDBC >> autocommit 옵션 >> false 전환 -> developer 의 환경과 같이 commit or rollback 해주어야함
 >> 반드시 java code 에서 commit, rollback 강제
  
  
  실습에 필요한 sql 예제
  	create table dmlemp
	as
    select * from emp;

	select * from emp;

	select * from dmlemp;

	select * from user_constraints where table_name='DMLEMP';

	alter table dmlemp
	add constraint pk_dmlemp_empno primary key(empno);

	select * from dmlemp;
  
  
 */
public class Ex04_Oracle_DML_update {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//드라이버 로딩 생략(생략가능)
			
			//연결객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KOSA","1004");
			stmt = conn.createStatement();
			
			//Update
			int deptno = 0;
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("부서입력");
			deptno = Integer.parseInt(sc.nextLine());
			
			// update dmlemp set sal = 0 where deptno = 부서번호
			//전통적이고 고전적인 방법
			String sql = "update dmlemp set sal = 0 where deptno=" + deptno;
			System.out.println(sql);
			
			//현재 values(?,?,?)  / ? 한개가 parameter 한개를 뜻함
			//executeUpdate() 함수 (insert, update, delete) 수행
			
			
			int resultrow = stmt.executeUpdate(sql);
			
			//resultrow > 0   << 반영되는 행이 존재한다
			if(resultrow > 0) {
				System.out.println("반영된 행의 수 : " + resultrow);
			} else {
				System.out.println("예외가 발생한 것이 아니고 :  반영된 행이 없다");
			}
			
			
		} catch (Exception e) {
			// 문제발생시 catch 문에서 처리한다
			System.out.println("SQL 예외발생 : " + e.getMessage());
		} finally {
			//강제 실행 블럭 사용
			// 자원해제
			try {
				stmt.close();

			} catch (SQLException e2) {
				
			}
			
			try {
				conn.close();

			} catch (SQLException e2) {
				
			}
			
		}
		
	}
	//autocommit 상태이기 때문에 따로 commit 할 필요없이 바로 반영된다.
	

}
