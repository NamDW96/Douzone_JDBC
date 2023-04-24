import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import kr.or.kosa.utils.SingletonHelper;

/*
 (1) 설명 

미리 SQL문이 셋팅된 Statement 가 DB가 전송되어져서 컴파일되어지고, SQL문의 ?만 나중에 추가 셋팅해서 실행이 되어지는 준비된 Statement 

(2) 장점

<1> Statement 에 비해서 반복적인 SQL문을 사용할 경우에 더 빠르다. ( 특히, 검색문 )

<2> DB컬럼타입과 상관없이 ?하나로 표시하면 되므로 개발자가 햇갈리지 않고 쉽다. ( 특히, INSERT문 )

(이유: ?를 제외한 SQL문이 DB에서 미리 컴파일되어져서 대기)

(3) 단점

SQL문마다 PreparedStatement 객체를 각각 생성해야 하므로 재사용불가

(but, Statement 객체는 SQL문이 달라지더라도 한 개만 생성해서 재사용이 가능하다. )

(4) 특징

<1> Statement stmt = con.createStatement(); //생성 stmt.execute(sql);//실행

<2> PreparedStatement pstmt = con.prepareStatement(sql); //생성 pstmt.execute(); //실행

(5) 주의

DB 객체들(table, ..)의 뼈대( 테이블명 or 컬럼명 or 시퀀스명 등의 객체나 속성명)은

?로 표시할 수 없다.

즉, data 자리에만 ?로 표시할 수 있다.

cf) 그래서, DDL문에서는 PreparedStatement를 사용하지 않는다. 
 
 */
/*
public class Ex08_Oracle_preparestatement {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ename from emp where empno=?"; //where empno = 7788
			//wehre id=? and name =? and job = ? >> where id = 100 and name ='김유신' and job ='IT'
			//? -> parameter
			
			
			// Statement 방식 -> Statement를 생성하고 -> 명령을 실행 할 때 쿼리문을 보냄
			 
			 
			
			
			pstmt = conn.prepareStatement(sql); // preparestatement 가 생성될 때 미리 컴파일 쿼리를....
			//실행하려는 쿼리는 DB 서버가 (shared pool)에 있다.
			Scanner sc = new Scanner(System.in);
			int deptno = Integer.parseInt(sc.nextLine());
			
			//where empno=? pstmt.setInt(n번째 물음표, n번째 값);
			pstmt.setInt(1, deptno); //where deptno=30 - 실제로 서버에는 30값만 보낸다
			
			rs = pstmt.executeQuery(); //parameter 만 서버에 전달
			
			//공식같은 로직
			// 데이터 1건 or 1건이상 or 없는경우
			if(rs.next()) {
				//1건 또는 그 이상 
				do {
					System.out.println(rs.getInt(1) + " / " + rs.getString(2));
				}while(rs.next()); //1건 or 여러건
			}else {
				System.out.println("조회된 데이터가 없습니다.");
			}
		
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
			
			// 싱글톤은 close() 하지 않는다 (APP 살아 있는 동안 같은 운명)
		}
	}

}
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.kosa.utils.SingletonHelper;

/*

PreparedStatement (준비된 Statement ) 

(1) 설명 
미리 SQL문이 셋팅된 Statement 가 DB가 전송되어져서 컴파일되어지고, SQL문의 ?만 나중에 추가 셋팅해서 실행 
이 되어지는 준비된 Statement 

(2) 장점
<1> Statement 에 비해서 반복적인 SQL문을 사용할 경우에 더 빠르다. ( 특히, 검색문 )
<2> DB컬럼타입과 상관없이 ?하나로 표시하면 되므로 개발자가 헷깔리지 않고 쉽다. ( 특히, INSERT문 )
(이유: ?를 제외한 SQL문이 DB에서 미리 컴파일되어져서 대기)

(3) 단점
SQL문마다 PreparedStatement 객체를 각각 생성해야 하므로 재사용불가
(but, Statement 객체는 SQL문이 달라지더라도 한 개만 생성해서 재사용이 가능하다. )

(4) 특징
<1> Statement stmt = con.createStatement(); //생성 stmt.execute(sql);//실행
<2> PreparedStatement pstmt = con.prepareStatement(sql); //생성 pstmt.execute(); //실행

(5) 주의
DB 객체들(table, ..)의 뼈대( 테이블명 or 컬럼명 or 시퀀스명 등의 객체나 속성명)은
?로 표시할 수 없다.
즉, data 자리에만 ?로 표시할 수 있다.
cf) 그래서, DDL문에서는 PreparedStatement를 사용하지 않는다.

장점 : 보안 (전체문장을 DB에 보내지 않아요) 나누어서 ... SQL문 보내서 컴파일 ... 그 다음에는 parameter 만
      미리 쿼리문 컴파일 (쿼리문은 DB서버에서 보관) >> 그 이후 전송 >>parameter 값만 >> 네트워크 트래픽 감소

단점 : 쿼리문이 바뀌면 다시 컴파일 ...   

*/
public class Ex08_Oracle_preparestatement {

	public static void main(String[] args) {
		Connection conn =null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql="select empno , ename  from emp where deptno=?"; //where deptno=20
			//where id=? and name=? and job=?  >> where id=100 and name='김유신' and job='IT'
			// ? parameter
			
			/*
			Statement stmt = conn.createStatement(); 
			String sql="select empno , ename , sal , comm from emp";
			ResultSet rs = stmt.executeQuery(sql); //명령실행 >> 쿼리문장 >> DB서버에 보내서 실행
			*/
			
			pstmt = conn.prepareStatement(sql); //미리 컴파일 쿼리를 ....
			//실행 하려는 쿼리는  DB서버가 (공유풀)
			
			
			Scanner sc = new Scanner(System.in);
			int deptno = Integer.parseInt(sc.nextLine());
			
			
			//where empno=?
			pstmt.setInt(1, deptno); // where deptno=30
			
			rs = pstmt.executeQuery(); // parameter 값만 서버 전달
			
			//공식같은 로직
			//데이터 1건 or 1건 이상 or 없는 경우
			if(rs.next()) {
				//1건 또는 그 이상
				do {
					System.out.println(rs.getInt(1) + " / " + rs.getString(2));
				}while(rs.next());
			}else {
				System.out.println("조회가 데이터가 없습니다");
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
			//싱글톤은  close() 하지 않아요 (APP 살아 있는 동안 같은 운명)
		}

	}

}



