import kr.or.kosa.utils.SingletonHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
public class Ex14_Maria_Procedure_Select {

    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cstmt = null; // 명령 객체 (프로시저)
        ResultSet rs = null;

        try {
            conn = SingletonHelper.getConnection("mariadb");
            String sql = "{call usp_EmpList(?)}";
            cstmt = conn.prepareCall(sql);

            // usp_EmpList(?)  >> ? input
            cstmt.setInt(1, 2000);

            rs = cstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "/" + rs.getString(2) + "/" + rs.getInt(3));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            SingletonHelper.close(rs);
            SingletonHelper.close(cstmt);
        }
    }
}
