import java.sql.Connection;
import java.sql.SQLException;

import kr.or.kosa.utils.SingletonHelper;

public class Ex07_SingletonHelper {
	public static void main(String[] args) throws SQLException {
		/*
		Connection conn = null;
		conn = SingletonHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		
		Connection conn2 = null;
		conn2 = SingletonHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		*/
		
		Connection conn = null;
		conn = SingletonHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		//conn.close(); //에러 발생 - Singleton은 close 를 하면 안된다.
		SingletonHelper.dbClose(); 
		
		
		Connection conn2 = null;
		conn2 = SingletonHelper.getConnection("mariadb");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		
	}
}
