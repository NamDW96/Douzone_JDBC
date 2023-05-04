package DTO;

import lombok.Data;

@Data
public class Emp_Hol {
	private int holnum;
	private String empno;
	private String start_date;
	private String end_date;
	private int hol_term;
	private String category;
}
