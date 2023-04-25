import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.or.kosa.utils.SingletonHelper;

/*
create table trans_A(
    num number,
    name varchar2(20)
);

create table trans_B(
    num number constraint pk_trans_B_num primary key,
    name varchar2(20)
);

JDBC >> default(DML) >> autocommit >> 실반영된다

JDBC >>  autocommit >> 변경(옵션) >> False >> 개발자가 직접(반드시) >> commit , rollback 제어 허락

은행업무 (여러개의 DML 처리되어야 하는 경우)
ex) 송금에서 출금만되고 입금은 에러가 발생하면 안되기 때문에 두 작업의 작업성공여부가 같아야 하는경우 


*/
public class Ex10_Oracle_Transaction {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		conn = SingletonHelper.getConnection("oracle");
		
		String sql="insert into trans_A(num,name) values(100,'A')";
		String sql2="insert into trans_B(num,name) values(100,'B')";
		
		try {
			//업무상(둘다성공 or 둘다실패) >>하나의 논리적 단위
			conn.setAutoCommit(false); //JDBC 개발자가 commit,rollback을 직접하겠다
			
			//begin tran
				pstmt = conn.prepareStatement(sql);
				pstmt2 = conn.prepareStatement(sql2);
			
				pstmt.executeUpdate(); //insert
				pstmt2.executeUpdate();//insert
		
			//예외가 발생하지 않으면
			conn.commit(); // 둘다 정상적으로 실행 예외가 발생하지 않으면 : commit
			//end tran
			
		}catch (Exception e) {
			//예외가 발생하면 : rollback
			System.out.println("문제발생 : " + e.getMessage());
			try {
				conn.rollback();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
			
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(pstmt2);
		}
	}
}

// 1번실행 : A와 B에 각각 100과 A,B의 값이 들어간다
// 2번 실행 : B table의 기본키값으로 데이터가 입력되지 않게되고,Autocommict을 설정했기 때문에 table A 에도 값이 들어가지 않는다.
