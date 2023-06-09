package kr.or.kosa.utils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SingletonHelper {
	
	private static Connection conn = null;
	private SingletonHelper() {}
	
	public static Connection getConnection(String dsn) {
		
		if(conn != null) {
			System.err.println("conn : " + conn);
			return conn;
		}
			try {
				if (dsn.equals("oracle")) {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KOSA", "1004");
				} else if (dsn.equals("mariadb")) {
					conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb", "kosa", "1004");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return conn;
	} 
		

   
   public static Connection getConnection(String dsn , String id , String pwd) {
	   Connection conn = null;
	   try {
		   		if(dsn.equals("oracle")) {
		   			conn =	DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",id,pwd);
		   		}else if(dsn.equals("mariadb")) {
		   			conn= DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb",id,pwd);
		   		}
	   		} catch (Exception e) {
	   			System.out.println(e.getMessage());
	   		}
	   return conn;
   }
   
   
   public static void close(Connection conn) {
	   if(conn != null) {
		   try {
			   conn.close(); //연결해제 (DB연결 끊기)
			   conn = null; // 참조해제
		} catch (Exception e) {
			  System.out.println(e.getMessage());
		}
	   }
   }
   
   //싱글톤 종료
   public static void dbClose() {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//ResultSet 종료
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//Statement 종료
	public static void close(Statement stsmt) {
		if (stsmt != null) {
			try {
				stsmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//PreparedStatement 종료
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	//JDBC 프로시져 사용을 위한 CallableStatement 종료
	
	public static void close(CallableStatement cstmt) {
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
  
}