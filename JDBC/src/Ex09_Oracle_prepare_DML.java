import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.or.kosa.utils.SingletonHelper;

public class Ex09_Oracle_prepare_DML {
	public static void main(String[] args) {
		//INSERT
		//insert into dmlemp(empno, ename, deptno) values(?, ?, ?)
		
		//UPDATE
		//update dmlemp set ename=? , sal=?, job=?, deptno =?
		//where empno =?
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into dmlemp(empno,ename,deptno) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql); //미리 컴파일
			//parameter만 설정해서 보내기
			pstmt.setInt(1, 9999);
			pstmt.setString(2, "홍길동");
			pstmt.setInt(3, 20);
			
			//실행
			int row = pstmt.executeUpdate();
			if(row > 0) {
				System.out.println("insert row count : " + row);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
	}
}
