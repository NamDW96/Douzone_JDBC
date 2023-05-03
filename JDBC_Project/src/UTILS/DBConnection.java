package UTILS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	// 싱글톤 패턴 사용을 위한객체생성 방지
	private DBConnection() {
	};

	// 싱글톤으로 사용하기위한 conn 변수 선언
	public static Connection conn;

	public static Connection getConnection() {
		// oracle에만 연결하여 사용할 예정이기때문에 매개변수는 받지 않는다
		try {
			if (conn == null) {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";

				String id = "KOSA";

				String pwd = "1004";

				conn = DriverManager.getConnection(url, id, pwd);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public static Connection getConnection(String id, String pwd) {

		try {
			if (conn == null) {

				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, id, pwd);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}
	
}
