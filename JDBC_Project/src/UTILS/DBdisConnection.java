package UTILS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBdisConnection {
		
		public static Connection conn = null;
		
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
		/*
		public static void close(CallableStatement cstmt) {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		*/
}
