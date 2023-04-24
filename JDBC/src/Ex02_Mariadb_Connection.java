import java.sql.Connection;
import java.sql.DriverManager;

public class Ex02_Mariadb_Connection {
	public static void main(String[] args) throws Exception {
		
		//DB연결 -> 명령생성(CRUD) -> 실행 -> 처리 -> 자원해제
		Class.forName("oracle.jdbc.OracleDriver"); // new 와 같은 효과
		System.out.println("드라이버 로딩 .....");
				
		//loading 된 드라이버를 통해서 Oracle 서버 연결
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb","kosa","1004");
		//mysql , oracle ... 연결 객체생성 >> Connection 인터페이스 구현한 객체 >> 다형성
		System.out.println(conn.isClosed() + "아니 false 연결되어 있다  ^^");
				
				
				
	}
}
