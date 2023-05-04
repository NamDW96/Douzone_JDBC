package DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class Emp_Attend {
	private String empno;
	private String attend_date;
	private String attend_stime;
	private String attend_etime;
}
