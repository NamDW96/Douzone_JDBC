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
  
 */
public class Ex03_Oracle_DML_insert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
