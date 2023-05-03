package DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class D_Emp {
	private int empno;
	private String ename;
	private Date birth;
	private Date hiredate;
	private String emp_rank;
	private int sal;
	private int deptno;
	private int mgr;
	
}
