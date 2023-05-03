package DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class Emp_Attend {
	private int attnum;
	private int empno;
	private Date attend_date;
	private Date attend_stime;
	private Date attend_etime;
}
