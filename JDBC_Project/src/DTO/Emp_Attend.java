package DTO;

import lombok.Data;
import oracle.sql.DATE;

@Data
public class Emp_Attend {
	private int attnum;
	private int empno;
	private DATE attend_date;
	private DATE attend_stime;
	private DATE attend_etime;
}
