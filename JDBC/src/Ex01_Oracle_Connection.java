import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/*

JDBC

1. Java 언어(APP)를 통해서 Oracle(소프트웨어) 연결해서 CRUD작업

2. Java App : Oracle , My-sql , MS-sql 등등 연결하고 작업(CRUD)

2.1 각각의 제품에 맞는 드라이버를 가지고 있어야 합니다

CASE 1: 삼성 노트북 >> HP 프린터 연결 >> HP프린터 사이트에서 드라이버 다운 >> 삼성 설치 

CASE 2: HP프린터 제조 회사는 ... 삼성, LG 회사마다 적용할 수 있는 드라이버를 별도 제작

각 언어에 맞는 드라이버를 다운로드 해서 제품에 맞게 설치 .... 접속 ...

Oracle (C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib)

Mysql (https://dev.mysql.com/downloads/connector/j/)

강사PC : C:\KOSA_IT\DataBase\JDBC\ConnectionUtils\Oracle >> ojdbc6.jar

3. 드라이버를 참조 (현재 프로젝트에서 사용) >> Java Project -> 속성 -> build path ->

jar 파일 추가 

3.1 드라이버 사용 준비 완료 >> 메모리에 load 사용 ....

3.2 Class.forName("oracle.jdbc.OracleDriver")..... new 객체 생성 ....

4. JAVA CODE (CRUD) >> JDBC API 제공 받는다

4.1 import java.sql.* >> interface , class 제공

4.2 개발자는 interface 를 통해서 표준화된 DB 작업 수행

POINT) why interface형태로 제공 >> JDBC API >> Oracle, Mysql , ....)

		//OracleConnection >> Connection 구현
		//MysqlConnection >> Connection 구현 
		//다형성 Connection 부모타입 : 유연한 프로그래밍 작성 
		>>다형성을 구현 (인터페이스 활용)

import java.sql.Connection

import java.sql.ResultSet

import java.sql.Statement 등등 

5. 작업순서

	5.1 DB연결 -> 명령생성 -> 명령실행 -> 처리 -> 자원해제

5.1 명령 : DDL (create , alter , drop)

		CRUD (insert , select , update , delete)

실행 : 쿼리문을 DB서버에게 전달 

처리 : 결과를 받아서 화면 출력 , 또는 다른 프로그램에 전달 등등

자원해제 : 연결해제 

*/
public class Ex01_Oracle_Connection {
	
	public static void main(String[] args) throws Exception {
		//DB연결 -> 명령생성(CRUD) -> 실행 -> 처리 -> 자원해제
		Class.forName("oracle.jdbc.OracleDriver"); // new 와 같은 효과   <- (생략 가능 자동로딩 ..)
		System.out.println("드라이버 로딩 .....");
		
		//loading 된 드라이버를 통해서 Oracle 서버 연결
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KOSA","1004");
		//mysql , oracle ... 연결 객체생성 >> Connection 인터페이스 구현한 객체 >> 다형성
		System.out.println(conn.isClosed() + "아니 false 연결되어 있다  ^^");
		
		//명령객체 생성
		Statement stmt =  conn.createStatement();
		
		//CRUD 구현
		String sql = "select empno, ename , sal, comm from emp";
		
		ResultSet rs = stmt.executeQuery(sql); //명령실행 >> 쿼리문장 >> DB서버에 보내서 실행
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		System.out.println("Total columns : " + rsmd.getColumnCount());
		System.out.println("column Name : " + rsmd.getColumnName(1));
		System.out.println("column type name of 1st column : " + rsmd.getColumnTypeName(1));
		
		//rs 객체를 통해서 DB 서버에 memory 에 있는 정보를 조회
		//화면처리 (목록출력)
		while(rs.next()) { //현재 접근한 row 있니
			System.out.println(
					rs.getInt("empno") + " / "
				+   rs.getString("ename") + " / "
				+   rs.getInt("sal") + " / "
				+   rs.getInt("comm")
					);						
		}
		
		//자원해제
		stmt.close();
		rs.close();
		conn.close();
		System.out.println("DB 연결 끊김 : " + conn.isClosed());
		
	}
}
