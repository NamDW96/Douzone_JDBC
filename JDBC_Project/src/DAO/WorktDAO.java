  package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.D_Emp;
import DTO.Emp_Attend;
import UTILS.DBConnection;
import UTILS.DBdisConnection;

public class WorktDAO {
	
	public void getAllEmpList() {

		List<Emp_Attend> attlist = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select attnum, empno,to_char(attend_date,'YY-MM-DD'), to_char(attend_stime,'HH24:MI:SS'), to_char(attend_etime,'HH24:MI:SS') from Emp_Attend";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Emp_Attend ea = new Emp_Attend();
				ea.setAttnum(rs.getInt("attnum"));
				ea.setEmpno(rs.getInt("empno"));
				ea.setAttend_date(rs.getDate("));
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBdisConnection.close(pstmt);
			DBdisConnection.close(rs);
		}

		if (emplist == null) {
			System.out.println("데이터가 존재하지 않습니다!");
		} else {
			for (D_Emp data : emplist) {
				System.out.println(data.toString());
			}
		}
	}
	
}
